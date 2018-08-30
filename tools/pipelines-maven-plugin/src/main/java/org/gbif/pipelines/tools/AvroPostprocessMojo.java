package org.gbif.pipelines.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Mojo class adds new annotations and Issue interface to avro generated classes
 *
 * <p>Adds Beam Avro annotation:
 *
 * <pre>{@code
 * import org.apache.beam.sdk.coders.AvroCoder;
 * import org.apache.beam.sdk.coders.DefaultCoder;
 *
 * @DefaultCoder(AvroCoder.class)
 * }</pre>
 *
 * <p>Creates Issue.java interface:
 *
 * <pre>{@code
 * package org.gbif.pipelines.io.avro;
 * public interface Issues {
 *    org.gbif.pipelines.io.avro.IssueRecord getIssues();
 * }
 * }</pre>
 *
 * <p>Adds Override annotation:
 *
 * <pre>{@code
 *  @Override
 *  public org.gbif.pipelines.io.avro.IssueRecord getIssues() {
 *    return issues;
 *  }
 * }</pre>
 */
@Mojo(name = "postprocess", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class AvroPostprocessMojo extends AbstractMojo {

  private static final String DEFAULT = "-gbif";
  private static final String ISSUE = "getIssues() {";
  private static final String BEFORE = "@SuppressWarnings(\"all\")";
  private static final String INTER_BASE = "org.apache.avro.specific.SpecificRecord";
  private static final String INTER = INTER_BASE + " {";

  @Parameter(property = "postprocess.directory", defaultValue = DEFAULT)
  private String directory;

  @Parameter(property = "postprocess.defaultPackage", defaultValue = DEFAULT)
  private String defaultPackage;

  @Override
  public void execute() throws MojoExecutionException {

    if (!DEFAULT.equals(directory) && !DEFAULT.equals(defaultPackage)) {
      boolean interfaceExist = createIssuesInterface();

      if (!interfaceExist) {
        searchClasses().forEach(this::modifyFile);
      }
    }
  }

  /** Modifies java class, adds Issues interface, Beam Avro and override annotations */
  private void modifyFile(Path path) {
    List<String> lines = getLines(path);
    List<Integer> idxs = getIdx(lines);

    addOverrideMethod(lines, idxs);
    changeInterface(lines, idxs);
    addAvroCodecAnnotation(lines, idxs);

    writeFile(path, lines, idxs);
  }

  /** Writes changes to a java class */
  private void writeFile(Path path, List<String> lines, List<Integer> idxs) {
    if (idxs.get(0) != -1 || idxs.get(1) != -1) {
      try {
        Files.write(path, lines);
      } catch (IOException ex) {
        throw new IORuntimeException(ex.getMessage(), ex);
      }
      getLog().info("Modified - " + path.toString());
    }
  }

  /** Adds override annotation to a "getIssues()" method */
  private void addOverrideMethod(List<String> lines, List<Integer> idxs) {
    int ovrdIdx = idxs.get(2);
    if (ovrdIdx != -1) {
      lines.add(ovrdIdx, "  @Override");
    }
  }

  /** Adds @DefaultCoder(AvroCoder.class) annotation to class */
  private void addAvroCodecAnnotation(List<String> lines, List<Integer> idxs) {
    int beforeIdx = idxs.get(0);
    if (beforeIdx != -1) {
      String imports =
          "import org.apache.beam.sdk.coders.AvroCoder;\nimport org.apache.beam.sdk.coders.DefaultCoder;";
      lines.add(beforeIdx, imports);
      lines.add(beforeIdx + 1, "@DefaultCoder(AvroCoder.class)");
    }
  }

  /** Adds Issues interface extension to a class */
  private void changeInterface(List<String> lines, List<Integer> idxs) {
    int interIdx = idxs.get(1);
    int ovrdIdx = idxs.get(2);
    if (interIdx != -1 && ovrdIdx != -1) {
      String replace = INTER_BASE + ", " + defaultPackage + ".Issues {";
      replace = lines.get(interIdx).replace(INTER, replace);
      lines.set(interIdx, replace);
    }
  }

  /** Finds line indexes for, Beam Avro annotation, Issues interface and override annotation */
  private List<Integer> getIdx(List<String> lines) {
    int beforeIdx = -1;
    int interIdx = -1;
    int ovrdIdx = -1;
    for (int x = 0; x < lines.size(); x++) {
      String line = lines.get(x);
      if (beforeIdx != -1 && interIdx != -1 && ovrdIdx != -1) {
        break;
      }
      if (line.equals(BEFORE)) {
        beforeIdx = x;
      } else if (line.endsWith(INTER)) {
        interIdx = x;
      } else if (line.endsWith(ISSUE)) {
        ovrdIdx = x;
      }
    }

    return Arrays.asList(beforeIdx, interIdx, ovrdIdx);
  }

  /** Reads lines in a java class */
  private List<String> getLines(Path path) {
    try {
      return Files.readAllLines(path);
    } catch (IOException ex) {
      throw new IORuntimeException(ex.getMessage(), ex);
    }
  }

  /** Searches for all java classes in a directory */
  private List<Path> searchClasses() {
    try (Stream<Path> paths = Files.walk(Paths.get(directory))) {
      return paths.filter(path -> path.toFile().isFile()).collect(Collectors.toList());
    } catch (IOException ex) {
      throw new IORuntimeException(ex.getMessage(), ex);
    }
  }

  /** Creates Issues.java interface in a defaultPackage directory */
  private boolean createIssuesInterface() {
    String path = directory + defaultPackage.replaceAll("\\.", "/") + "/Issues.java";
    String clazz =
        "package "
            + defaultPackage
            + ";\npublic interface Issues {\n  org.gbif.pipelines.io.avro.IssueRecord getIssues();\n}";
    try {
      Path path1 = Paths.get(path);
      boolean exists = path1.toFile().exists();
      if (!exists) {
        Files.write(path1, clazz.getBytes(UTF_8));
      }
      return exists;
    } catch (IOException ex) {
      throw new IORuntimeException(ex.getMessage(), ex);
    }
  }
}