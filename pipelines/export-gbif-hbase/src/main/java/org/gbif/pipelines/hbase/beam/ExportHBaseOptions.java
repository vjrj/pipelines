package org.gbif.pipelines.hbase.beam;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

/**
 * Pipeline settings and arguments for Hbase to Avro export.
 */
public interface ExportHBaseOptions extends PipelineOptions {

  @Description("HBase Zookeeper ensemble")
  String getHbaseZk();
  void setHbaseZk(String hbaseZk);

  @Description("Export path of avro files")
  String getExportPath();
  void setExportPath(String exportPath);

  @Description("Batch size of documents to be read from HBase")
  @Default.Integer(10000)
  int getBatchSize();
  void setBatchSize(int batchSize);

  @Description("Occurrence table")
  String getTable();
  void setTable(String table);
}