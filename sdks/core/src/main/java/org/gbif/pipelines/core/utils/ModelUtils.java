package org.gbif.pipelines.core.utils;

import org.gbif.api.vocabulary.OccurrenceIssue;
import org.gbif.dwc.terms.DcTerm;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.pipelines.io.avro.ExtendedRecord;
import org.gbif.pipelines.io.avro.Issue;

import java.util.List;
import java.util.Objects;

/** */
public class ModelUtils {

  private ModelUtils() {}

  public static String extractValue(ExtendedRecord er, DwcTerm term) {
    return er.getCoreTerms().get(term.qualifiedName());
  }

  public static String extractValue(ExtendedRecord er, DcTerm term) {
    return er.getCoreTerms().get(term.qualifiedName());
  }

  /** Checks if a {@link ExtendedRecord} is null or empty. */
  public static void checkNullOrEmpty(ExtendedRecord extendedRecord) {
    Objects.requireNonNull(extendedRecord, "ExtendedRecord cannot be null");

    if (extendedRecord.getId() == null || extendedRecord.getId().isEmpty()) {
      throw new IllegalArgumentException("ExtendedRecord with id is required");
    }

    if (extendedRecord.getCoreTerms() == null || extendedRecord.getCoreTerms().isEmpty()) {
      throw new IllegalArgumentException("ExtendedRecord with core terms is required");
    }
  }

  public static void addIssue(Issue model, String issue) {
    model.getIssues().getIssueList().add(issue);
  }

  public static void addIssue(Issue model, OccurrenceIssue issue) {
    model.getIssues().getIssueList().add(issue.name());
  }

  public static void addIssue(Issue model, List<String> issues) {
    model.getIssues().getIssueList().addAll(issues);
  }
}
