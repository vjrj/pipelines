/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.gbif.pipelines.io.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Diagnostics extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Diagnostics\",\"namespace\":\"org.gbif.pipelines.io.avro\",\"fields\":[{\"name\":\"matchType\",\"type\":[\"null\",{\"type\":\"enum\",\"name\":\"MatchType\",\"symbols\":[\"EXACT\",\"FUZZY\",\"HIGHERRANK\",\"NONE\"]}],\"default\":null},{\"name\":\"confidence\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"status\",\"type\":[\"null\",{\"type\":\"enum\",\"name\":\"Status\",\"symbols\":[\"ACCEPTED\",\"DOUBTFUL\",\"SYNONYM\",\"HETEROTYPIC_SYNONYM\",\"HOMOTYPIC_SYNONYM\",\"PROPARTE_SYNONYM\",\"MISAPPLIED\",\"INTERMEDIATE_RANK_SYNONYM\",\"DETERMINATION_SYNONYM\"]}],\"default\":null},{\"name\":\"lineage\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"string\"}],\"default\":null},{\"name\":\"alternatives\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"TaxonRecord\",\"doc\":\"A taxonomic record\",\"fields\":[{\"name\":\"id\",\"type\":\"string\",\"doc\":\"The record id\"},{\"name\":\"synonym\",\"type\":[\"null\",\"boolean\"],\"default\":null},{\"name\":\"usage\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"RankedName\",\"fields\":[{\"name\":\"key\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"rank\",\"type\":[\"null\",{\"type\":\"enum\",\"name\":\"Rank\",\"symbols\":[\"DOMAIN\",\"SUPERKINGDOM\",\"KINGDOM\",\"SUBKINGDOM\",\"INFRAKINGDOM\",\"SUPERPHYLUM\",\"PHYLUM\",\"SUBPHYLUM\",\"INFRAPHYLUM\",\"SUPERCLASS\",\"CLASS\",\"SUBCLASS\",\"INFRACLASS\",\"PARVCLASS\",\"SUPERLEGION\",\"LEGION\",\"SUBLEGION\",\"INFRALEGION\",\"SUPERCOHORT\",\"COHORT\",\"SUBCOHORT\",\"INFRACOHORT\",\"MAGNORDER\",\"SUPERORDER\",\"GRANDORDER\",\"ORDER\",\"SUBORDER\",\"INFRAORDER\",\"PARVORDER\",\"SUPERFAMILY\",\"FAMILY\",\"SUBFAMILY\",\"INFRAFAMILY\",\"SUPERTRIBE\",\"TRIBE\",\"SUBTRIBE\",\"INFRATRIBE\",\"SUPRAGENERIC_NAME\",\"GENUS\",\"SUBGENUS\",\"INFRAGENUS\",\"SECTION\",\"SUBSECTION\",\"SERIES\",\"SUBSERIES\",\"INFRAGENERIC_NAME\",\"SPECIES_AGGREGATE\",\"SPECIES\",\"INFRASPECIFIC_NAME\",\"GREX\",\"SUBSPECIES\",\"CULTIVAR_GROUP\",\"CONVARIETY\",\"INFRASUBSPECIFIC_NAME\",\"PROLES\",\"RACE\",\"NATIO\",\"ABERRATION\",\"MORPH\",\"VARIETY\",\"SUBVARIETY\",\"FORM\",\"SUBFORM\",\"PATHOVAR\",\"BIOVAR\",\"CHEMOVAR\",\"MORPHOVAR\",\"PHAGOVAR\",\"SEROVAR\",\"CHEMOFORM\",\"FORMA_SPECIALIS\",\"CULTIVAR\",\"STRAIN\",\"OTHER\",\"UNRANKED\"]}],\"default\":null}]}],\"default\":null},{\"name\":\"acceptedUsage\",\"type\":[\"null\",\"RankedName\"],\"default\":null},{\"name\":\"nomenclature\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"Nomenclature\",\"fields\":[{\"name\":\"source\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"id\",\"type\":[\"null\",\"string\"],\"default\":null}]}],\"default\":null},{\"name\":\"classification\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"RankedName\"}],\"default\":null},{\"name\":\"diagnostics\",\"type\":[\"null\",\"Diagnostics\"],\"default\":null}]}}],\"default\":null},{\"name\":\"note\",\"type\":[\"null\",\"string\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public org.gbif.pipelines.io.avro.MatchType matchType;
  @Deprecated public java.lang.Integer confidence;
  @Deprecated public org.gbif.pipelines.io.avro.Status status;
  @Deprecated public java.util.List<java.lang.CharSequence> lineage;
  @Deprecated public java.util.List<org.gbif.pipelines.io.avro.TaxonRecord> alternatives;
  @Deprecated public java.lang.CharSequence note;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public Diagnostics() {}

  /**
   * All-args constructor.
   */
  public Diagnostics(org.gbif.pipelines.io.avro.MatchType matchType, java.lang.Integer confidence, org.gbif.pipelines.io.avro.Status status, java.util.List<java.lang.CharSequence> lineage, java.util.List<org.gbif.pipelines.io.avro.TaxonRecord> alternatives, java.lang.CharSequence note) {
    this.matchType = matchType;
    this.confidence = confidence;
    this.status = status;
    this.lineage = lineage;
    this.alternatives = alternatives;
    this.note = note;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return matchType;
    case 1: return confidence;
    case 2: return status;
    case 3: return lineage;
    case 4: return alternatives;
    case 5: return note;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: matchType = (org.gbif.pipelines.io.avro.MatchType)value$; break;
    case 1: confidence = (java.lang.Integer)value$; break;
    case 2: status = (org.gbif.pipelines.io.avro.Status)value$; break;
    case 3: lineage = (java.util.List<java.lang.CharSequence>)value$; break;
    case 4: alternatives = (java.util.List<org.gbif.pipelines.io.avro.TaxonRecord>)value$; break;
    case 5: note = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'matchType' field.
   */
  public org.gbif.pipelines.io.avro.MatchType getMatchType() {
    return matchType;
  }

  /**
   * Sets the value of the 'matchType' field.
   * @param value the value to set.
   */
  public void setMatchType(org.gbif.pipelines.io.avro.MatchType value) {
    this.matchType = value;
  }

  /**
   * Gets the value of the 'confidence' field.
   */
  public java.lang.Integer getConfidence() {
    return confidence;
  }

  /**
   * Sets the value of the 'confidence' field.
   * @param value the value to set.
   */
  public void setConfidence(java.lang.Integer value) {
    this.confidence = value;
  }

  /**
   * Gets the value of the 'status' field.
   */
  public org.gbif.pipelines.io.avro.Status getStatus() {
    return status;
  }

  /**
   * Sets the value of the 'status' field.
   * @param value the value to set.
   */
  public void setStatus(org.gbif.pipelines.io.avro.Status value) {
    this.status = value;
  }

  /**
   * Gets the value of the 'lineage' field.
   */
  public java.util.List<java.lang.CharSequence> getLineage() {
    return lineage;
  }

  /**
   * Sets the value of the 'lineage' field.
   * @param value the value to set.
   */
  public void setLineage(java.util.List<java.lang.CharSequence> value) {
    this.lineage = value;
  }

  /**
   * Gets the value of the 'alternatives' field.
   */
  public java.util.List<org.gbif.pipelines.io.avro.TaxonRecord> getAlternatives() {
    return alternatives;
  }

  /**
   * Sets the value of the 'alternatives' field.
   * @param value the value to set.
   */
  public void setAlternatives(java.util.List<org.gbif.pipelines.io.avro.TaxonRecord> value) {
    this.alternatives = value;
  }

  /**
   * Gets the value of the 'note' field.
   */
  public java.lang.CharSequence getNote() {
    return note;
  }

  /**
   * Sets the value of the 'note' field.
   * @param value the value to set.
   */
  public void setNote(java.lang.CharSequence value) {
    this.note = value;
  }

  /** Creates a new Diagnostics RecordBuilder */
  public static org.gbif.pipelines.io.avro.Diagnostics.Builder newBuilder() {
    return new org.gbif.pipelines.io.avro.Diagnostics.Builder();
  }
  
  /** Creates a new Diagnostics RecordBuilder by copying an existing Builder */
  public static org.gbif.pipelines.io.avro.Diagnostics.Builder newBuilder(org.gbif.pipelines.io.avro.Diagnostics.Builder other) {
    return new org.gbif.pipelines.io.avro.Diagnostics.Builder(other);
  }
  
  /** Creates a new Diagnostics RecordBuilder by copying an existing Diagnostics instance */
  public static org.gbif.pipelines.io.avro.Diagnostics.Builder newBuilder(org.gbif.pipelines.io.avro.Diagnostics other) {
    return new org.gbif.pipelines.io.avro.Diagnostics.Builder(other);
  }
  
  /**
   * RecordBuilder for Diagnostics instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Diagnostics>
    implements org.apache.avro.data.RecordBuilder<Diagnostics> {

    private org.gbif.pipelines.io.avro.MatchType matchType;
    private java.lang.Integer confidence;
    private org.gbif.pipelines.io.avro.Status status;
    private java.util.List<java.lang.CharSequence> lineage;
    private java.util.List<org.gbif.pipelines.io.avro.TaxonRecord> alternatives;
    private java.lang.CharSequence note;

    /** Creates a new Builder */
    private Builder() {
      super(org.gbif.pipelines.io.avro.Diagnostics.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(org.gbif.pipelines.io.avro.Diagnostics.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.matchType)) {
        this.matchType = data().deepCopy(fields()[0].schema(), other.matchType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.confidence)) {
        this.confidence = data().deepCopy(fields()[1].schema(), other.confidence);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.status)) {
        this.status = data().deepCopy(fields()[2].schema(), other.status);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.lineage)) {
        this.lineage = data().deepCopy(fields()[3].schema(), other.lineage);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.alternatives)) {
        this.alternatives = data().deepCopy(fields()[4].schema(), other.alternatives);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.note)) {
        this.note = data().deepCopy(fields()[5].schema(), other.note);
        fieldSetFlags()[5] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Diagnostics instance */
    private Builder(org.gbif.pipelines.io.avro.Diagnostics other) {
            super(org.gbif.pipelines.io.avro.Diagnostics.SCHEMA$);
      if (isValidValue(fields()[0], other.matchType)) {
        this.matchType = data().deepCopy(fields()[0].schema(), other.matchType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.confidence)) {
        this.confidence = data().deepCopy(fields()[1].schema(), other.confidence);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.status)) {
        this.status = data().deepCopy(fields()[2].schema(), other.status);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.lineage)) {
        this.lineage = data().deepCopy(fields()[3].schema(), other.lineage);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.alternatives)) {
        this.alternatives = data().deepCopy(fields()[4].schema(), other.alternatives);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.note)) {
        this.note = data().deepCopy(fields()[5].schema(), other.note);
        fieldSetFlags()[5] = true;
      }
    }

    /** Gets the value of the 'matchType' field */
    public org.gbif.pipelines.io.avro.MatchType getMatchType() {
      return matchType;
    }
    
    /** Sets the value of the 'matchType' field */
    public org.gbif.pipelines.io.avro.Diagnostics.Builder setMatchType(org.gbif.pipelines.io.avro.MatchType value) {
      validate(fields()[0], value);
      this.matchType = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'matchType' field has been set */
    public boolean hasMatchType() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'matchType' field */
    public org.gbif.pipelines.io.avro.Diagnostics.Builder clearMatchType() {
      matchType = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'confidence' field */
    public java.lang.Integer getConfidence() {
      return confidence;
    }
    
    /** Sets the value of the 'confidence' field */
    public org.gbif.pipelines.io.avro.Diagnostics.Builder setConfidence(java.lang.Integer value) {
      validate(fields()[1], value);
      this.confidence = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'confidence' field has been set */
    public boolean hasConfidence() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'confidence' field */
    public org.gbif.pipelines.io.avro.Diagnostics.Builder clearConfidence() {
      confidence = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'status' field */
    public org.gbif.pipelines.io.avro.Status getStatus() {
      return status;
    }
    
    /** Sets the value of the 'status' field */
    public org.gbif.pipelines.io.avro.Diagnostics.Builder setStatus(org.gbif.pipelines.io.avro.Status value) {
      validate(fields()[2], value);
      this.status = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'status' field has been set */
    public boolean hasStatus() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'status' field */
    public org.gbif.pipelines.io.avro.Diagnostics.Builder clearStatus() {
      status = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'lineage' field */
    public java.util.List<java.lang.CharSequence> getLineage() {
      return lineage;
    }
    
    /** Sets the value of the 'lineage' field */
    public org.gbif.pipelines.io.avro.Diagnostics.Builder setLineage(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[3], value);
      this.lineage = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'lineage' field has been set */
    public boolean hasLineage() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'lineage' field */
    public org.gbif.pipelines.io.avro.Diagnostics.Builder clearLineage() {
      lineage = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'alternatives' field */
    public java.util.List<org.gbif.pipelines.io.avro.TaxonRecord> getAlternatives() {
      return alternatives;
    }
    
    /** Sets the value of the 'alternatives' field */
    public org.gbif.pipelines.io.avro.Diagnostics.Builder setAlternatives(java.util.List<org.gbif.pipelines.io.avro.TaxonRecord> value) {
      validate(fields()[4], value);
      this.alternatives = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'alternatives' field has been set */
    public boolean hasAlternatives() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'alternatives' field */
    public org.gbif.pipelines.io.avro.Diagnostics.Builder clearAlternatives() {
      alternatives = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'note' field */
    public java.lang.CharSequence getNote() {
      return note;
    }
    
    /** Sets the value of the 'note' field */
    public org.gbif.pipelines.io.avro.Diagnostics.Builder setNote(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.note = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'note' field has been set */
    public boolean hasNote() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'note' field */
    public org.gbif.pipelines.io.avro.Diagnostics.Builder clearNote() {
      note = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    public Diagnostics build() {
      try {
        Diagnostics record = new Diagnostics();
        record.matchType = fieldSetFlags()[0] ? this.matchType : (org.gbif.pipelines.io.avro.MatchType) defaultValue(fields()[0]);
        record.confidence = fieldSetFlags()[1] ? this.confidence : (java.lang.Integer) defaultValue(fields()[1]);
        record.status = fieldSetFlags()[2] ? this.status : (org.gbif.pipelines.io.avro.Status) defaultValue(fields()[2]);
        record.lineage = fieldSetFlags()[3] ? this.lineage : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[3]);
        record.alternatives = fieldSetFlags()[4] ? this.alternatives : (java.util.List<org.gbif.pipelines.io.avro.TaxonRecord>) defaultValue(fields()[4]);
        record.note = fieldSetFlags()[5] ? this.note : (java.lang.CharSequence) defaultValue(fields()[5]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
