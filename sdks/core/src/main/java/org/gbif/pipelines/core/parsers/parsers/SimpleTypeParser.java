package org.gbif.pipelines.core.parsers.parsers;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import org.gbif.common.parsers.NumberParser;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.pipelines.io.avro.ExtendedRecord;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.gbif.pipelines.core.parsers.utils.ModelUtils.extractNullAwareValue;

/** Utility class that parses basic data types. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleTypeParser {

  private static final Pattern INT_PATTERN = Pattern.compile("(^-?\\d{1,10}$)");
  private static final Pattern INT_POSITIVE_PATTERN = Pattern.compile("(^\\d{1,10}$)");

  /** Parses an integer value and consumes its response (if any). */
  public static void parseInt(ExtendedRecord er, DwcTerm term, Consumer<Optional<Integer>> consumer) {
    Optional.ofNullable(extractNullAwareValue(er, term))
        .ifPresent(termValue -> {
          boolean matches = INT_PATTERN.matcher(termValue).matches();
          Optional<Integer> v = matches ? Optional.ofNullable(NumberParser.parseInteger(termValue)) : Optional.empty();
          consumer.accept(v);
        });
  }

  /** Parses a positive integer value and consumes its response (if any). */
  public static void parsePositiveInt(ExtendedRecord er, DwcTerm term, Consumer<Optional<Integer>> consumer) {
    Optional.ofNullable(extractNullAwareValue(er, term))
        .ifPresent(termValue -> {
          boolean matches = INT_POSITIVE_PATTERN.matcher(termValue).matches();
          Optional<Integer> v = matches ? Optional.ofNullable(NumberParser.parseInteger(termValue)) : Optional.empty();
          consumer.accept(v);
        });
  }

  /** Parses a double value and consumes its response (if any). */
  public static void parseDouble(ExtendedRecord er, DwcTerm term, Consumer<Optional<Double>> consumer) {
    parseDouble(extractNullAwareValue(er, term), consumer);
  }

  /** Parses a double value and consumes its response (if any). */
  public static void parseDouble(String value, Consumer<Optional<Double>> consumer) {
    Optional.ofNullable(value)
        .ifPresent(termValue -> consumer.accept(Optional.ofNullable(NumberParser.parseDouble(termValue))));
  }
}
