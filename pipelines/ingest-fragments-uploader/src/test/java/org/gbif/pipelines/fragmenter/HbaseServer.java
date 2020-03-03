package org.gbif.pipelines.fragmenter;

import java.io.IOException;

import org.gbif.pipelines.keygen.config.KeygenConfig;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.rules.ExternalResource;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HbaseServer extends ExternalResource {

  static final KeygenConfig CFG =
      KeygenConfig.create("test_occurrence", "test_occurrence_counter", "test_occurrence_lookup", null);

  static final String FRAGMENT_TABLE_NAME = "fragment_table";
  private static final byte[] FRAGMENT_TABLE = Bytes.toBytes(FRAGMENT_TABLE_NAME);
  private static final byte[] FF = Bytes.toBytes("o");

  private static final byte[] LOOKUP_TABLE = Bytes.toBytes(CFG.getLookupTable());
  private static final String CF_NAME = "o";
  private static final byte[] CF = Bytes.toBytes(CF_NAME);
  private static final byte[] COUNTER_TABLE = Bytes.toBytes(CFG.getCounterTable());
  private static final String COUNTER_CF_NAME = "o";
  private static final byte[] COUNTER_CF = Bytes.toBytes(COUNTER_CF_NAME);
  private static final byte[] OCCURRENCE_TABLE = Bytes.toBytes(CFG.getOccTable());

  private static final HBaseTestingUtility TEST_UTIL = new HBaseTestingUtility();

  private Connection connection = null;

  @Before
  public void beforeClass() throws IOException {
    log.info("Trancate the table");
    TEST_UTIL.truncateTable(FRAGMENT_TABLE);
    TEST_UTIL.truncateTable(LOOKUP_TABLE);
    TEST_UTIL.truncateTable(COUNTER_TABLE);
    TEST_UTIL.truncateTable(OCCURRENCE_TABLE);
  }

  @Override
  protected void before() throws Exception {
    log.info("Create hbase mini-cluster");
    TEST_UTIL.startMiniCluster(1);

    TEST_UTIL.createTable(FRAGMENT_TABLE, FF);
    TEST_UTIL.createTable(LOOKUP_TABLE, CF);
    TEST_UTIL.createTable(COUNTER_TABLE, COUNTER_CF);
    TEST_UTIL.createTable(OCCURRENCE_TABLE, CF);

    connection = ConnectionFactory.createConnection(TEST_UTIL.getConfiguration());
  }

  @SneakyThrows
  @Override
  protected void after() {
    log.info("Shut down hbase mini-cluster");
    TEST_UTIL.shutdownMiniCluster();
    if (connection != null) {
      connection.close();
    }
  }

}