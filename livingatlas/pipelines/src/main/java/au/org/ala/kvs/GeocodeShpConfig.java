package au.org.ala.kvs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/** Store configuration of SHP files including fields for ALA Country/State interpretation */
@Data
public class GeocodeShpConfig implements Serializable {

  private ShapeFile country;
  private ShapeFile eez;
  private ShapeFile stateProvince;
  private ShapeFile biome;

  @JsonCreator
  public GeocodeShpConfig(
      @JsonProperty("country") ShapeFile country,
      @JsonProperty("eez") ShapeFile eez,
      @JsonProperty("stateProvince") ShapeFile stateProvince,
      @JsonProperty("biome") ShapeFile biome) {
    this.country = country;
    this.eez = eez;
    this.stateProvince = stateProvince;
    this.biome = biome;
  }
}
