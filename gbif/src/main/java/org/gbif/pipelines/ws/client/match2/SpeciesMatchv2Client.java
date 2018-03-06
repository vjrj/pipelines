package org.gbif.pipelines.ws.client.match2;

import org.gbif.api.model.checklistbank.NameUsageMatch.MatchType;
import org.gbif.api.v2.NameUsageMatch2;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.pipelines.interpretation.parsers.taxonomy.TaxonomyValidator;
import org.gbif.pipelines.io.avro.ExtendedRecord;
import org.gbif.pipelines.ws.HttpResponse;
import org.gbif.pipelines.ws.HttpResponse.ErrorCode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Handles the calls to the species match WS.
 */
public class SpeciesMatchv2Client {

  private static final Logger LOG = LoggerFactory.getLogger(SpeciesMatchv2Client.class);

  private SpeciesMatchv2Client() {}

  /**
   * Matches a {@link ExtendedRecord} to an existing specie using the species match 2 WS.
   *
   * @param extendedRecord avro file with the taxonomic data
   *
   * @return {@link NameUsageMatch2} with the match received from the WS.
   */
  public static HttpResponse<NameUsageMatch2> getMatch(ExtendedRecord extendedRecord) {
    HttpResponse<NameUsageMatch2> response = tryNameMatch(extendedRecord.getCoreTerms());

    if (isSuccessfulMatch(response) || !hasIdentifications(extendedRecord)) {
      return response;
    }

    LOG.info("Retrying match with identification extension");
    // get identifications
    List<Map<String, String>> identifications =
      extendedRecord.getExtensions().get(DwcTerm.Identification.qualifiedName());

    // FIXME: use new generic functions to parse the date??
    // sort them by date identified
    // Ask Markus D if this can be moved to the API?
    identifications.sort(Comparator.comparing((Map<String, String> map) -> LocalDateTime.parse(map.get(DwcTerm.dateIdentified.qualifiedName())))
                           .reversed());
    for (Map<String, String> record : identifications) {
      response = tryNameMatch(record);
      if (isSuccessfulMatch(response)) {
        LOG.info("match with identificationId {} succeed", record.get(DwcTerm.identificationID.name()));
        return response;
      }
    }

    return response;
  }

  private static HttpResponse<NameUsageMatch2> tryNameMatch(Map<String, String> terms) {
    Map<String, String> params = NameUsageMatchQueryConverter.convert(terms);

    Call<NameUsageMatch2> call = SpeciesMatchv2ServiceRest.getInstance().getService().match(params);

    try {
      Response<NameUsageMatch2> response = call.execute();

      if (!response.isSuccessful()) {
        String errorMessage = "Call to species match name WS failed: " + response.message();
        return HttpResponse.fail(response.body(), response.code(), errorMessage, ErrorCode.CALL_FAILED);
      }

      return HttpResponse.success(response.body());
    } catch (IOException e) {
      LOG.error("Error calling the match species name WS", e);
      String errorMessage = "Error calling the match species name WS";
      return HttpResponse.fail(null, errorMessage, ErrorCode.UNEXPECTED_ERROR);
    }

  }

  private static boolean isSuccessfulMatch(HttpResponse<NameUsageMatch2> response) {
    return !TaxonomyValidator.isEmpty(response.getBody()) && MatchType.NONE != response.getBody()
      .getDiagnostics()
      .getMatchType();
  }

  private static boolean hasIdentifications(ExtendedRecord extendedRecord) {
    return extendedRecord.getExtensions().containsKey(DwcTerm.Identification.qualifiedName());
  }

}