package org.gbif.pipelines.core.tools.parsers;

import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EstablishmentMeans;
import org.gbif.api.vocabulary.LifeStage;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.Sex;
import org.gbif.api.vocabulary.TypeStatus;
import org.gbif.common.parsers.BasisOfRecordParser;
import org.gbif.common.parsers.ContinentParser;
import org.gbif.common.parsers.CountryParser;
import org.gbif.common.parsers.EstablishmentMeansParser;
import org.gbif.common.parsers.LifeStageParser;
import org.gbif.common.parsers.RankParser;
import org.gbif.common.parsers.SexParser;
import org.gbif.common.parsers.TypeStatusParser;
import org.gbif.common.parsers.core.EnumParser;
import org.gbif.common.parsers.core.ParseResult;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.pipelines.io.avro.ExtendedRecord;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Utility class that parses Enum based terms.
 */
public class VocabularyParsers<T extends Enum<T>> {

  private static final TypeStatusParser TYPE_PARSER = TypeStatusParser.getInstance();
  private static final BasisOfRecordParser BOR_PARSER = BasisOfRecordParser.getInstance();
  private static final SexParser SEX_PARSER = SexParser.getInstance();
  private static final EstablishmentMeansParser EST_PARSER = EstablishmentMeansParser.getInstance();
  private static final LifeStageParser LST_PARSER = LifeStageParser.getInstance();
  private static final CountryParser COUNTRY_PARSER = CountryParser.getInstance();
  private static final ContinentParser CONTINENT_PARSER = ContinentParser.getInstance();
  private static final RankParser RANK_PARSER = RankParser.getInstance();

  //Parser to be used
  private final EnumParser<T> parser;

  //Term ot be parsed
  private final DwcTerm term;

  /**
   * Private constructor that keeps the basic info to run a parser.
   */
  private VocabularyParsers(EnumParser<T> parser, DwcTerm term) {
    this.parser = parser;
    this.term = term;
  }

  /**
   * @return a basis of record parser.
   */
  public static VocabularyParsers<BasisOfRecord> basisOfRecordParser() {
    return new VocabularyParsers<>(BOR_PARSER, DwcTerm.basisOfRecord);
  }

  /**
   * @return a sex parser.
   */
  public static VocabularyParsers<Sex> sexParser() {
    return new VocabularyParsers<>(SEX_PARSER, DwcTerm.sex);
  }

  /**
   * @return a life stage parser.
   */
  public static VocabularyParsers<LifeStage> lifeStageParser() {
    return new VocabularyParsers<>(LST_PARSER, DwcTerm.lifeStage);
  }

  /**
   * @return a establishmentMeans parser.
   */
  public static VocabularyParsers<EstablishmentMeans> establishmentMeansParser() {
    return new VocabularyParsers<>(EST_PARSER, DwcTerm.establishmentMeans);
  }

  /**
   * @return a type status parser.
   */
  public static VocabularyParsers<TypeStatus> typeStatusParser() {
    return new VocabularyParsers<>(TYPE_PARSER, DwcTerm.typeStatus);
  }

  /**
   * @return a country parser.
   */
  public static VocabularyParsers<Country> countryParser() {
    return new VocabularyParsers<>(COUNTRY_PARSER, DwcTerm.country);
  }

  /**
   * @return a continent parser.
   */
  public static VocabularyParsers<Continent> continentParser() {
    return new VocabularyParsers<>(CONTINENT_PARSER, DwcTerm.continent);
  }

  /**
   * @return a waterBody parser.
   */
  public static VocabularyParsers<Country> waterBodyParser() {
    return new VocabularyParsers<>(COUNTRY_PARSER, DwcTerm.waterBody);
  }

  /**
   * @return a minimumElevationInMeters parser.
   */
  public static VocabularyParsers<Country> minimumElevationInMetersParser() {
    return new VocabularyParsers<>(COUNTRY_PARSER, DwcTerm.minimumElevationInMeters);
  }

  /**
   * @return a maximumElevationInMeters parser.
   */
  public static VocabularyParsers<Country> maximumElevationInMetersParser() {
    return new VocabularyParsers<>(COUNTRY_PARSER, DwcTerm.maximumElevationInMeters);
  }

  /**
   * @return a minimumDepthInMeters parser.
   */
  public static VocabularyParsers<Country> minimumDepthInMetersParser() {
    return new VocabularyParsers<>(COUNTRY_PARSER, DwcTerm.minimumDepthInMeters);
  }

  /**
   * @return a maximumDepthInMeters parser.
   */
  public static VocabularyParsers<Country> maximumDepthInMetersParser() {
    return new VocabularyParsers<>(COUNTRY_PARSER, DwcTerm.maximumDepthInMeters);
  }

  /**
   * Runs a parsing method on a extended record.
   *
   * @param extendedRecord to be used as input
   * @param onParse        consumer called during parsing
   */
  public void parse(ExtendedRecord extendedRecord, Consumer<ParseResult<T>> onParse) {
    Optional.ofNullable(extendedRecord.getCoreTerms().get(term.qualifiedName()))
      .ifPresent(value -> onParse.accept(parser.parse(value)));
  }

  /**
   * @return a type status parser.
   */
  public static VocabularyParsers<Rank> rankParser() {
    return new VocabularyParsers<>(RANK_PARSER, DwcTerm.taxonRank);
  }

  /**
   * @return a type status parser.
   */
  public static VocabularyParsers<Rank> verbatimTaxonRankParser() {
    return new VocabularyParsers<>(RANK_PARSER, DwcTerm.verbatimTaxonRank);
  }

  /**
   * Runs a parsing method on a extended record and applies a mapping function to the result.
   *
   * @param extendedRecord to be used as input
   * @param mapper         function mapper
   */
  public <U> Optional<U> map(ExtendedRecord extendedRecord, Function<ParseResult<T>, U> mapper) {
    return map(extendedRecord.getCoreTerms(), mapper);
  }

  /**
   * Runs a parsing method on a map of terms and applies a mapping function to the result.
   *
   * @param terms  to be used as input
   * @param mapper function mapper
   */
  public <U> Optional<U> map(Map<String, String> terms, Function<ParseResult<T>, U> mapper) {
    return Optional.ofNullable(terms.get(term.qualifiedName())).map(value -> mapper.apply(parser.parse(value)));
  }

}