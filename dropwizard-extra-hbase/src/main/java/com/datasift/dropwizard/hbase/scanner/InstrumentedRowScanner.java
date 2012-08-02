package com.datasift.dropwizard.hbase.scanner;

import com.datasift.dropwizard.hbase.InstrumentedHBaseClient;
import com.datasift.dropwizard.hbase.metrics.HBaseInstrumentation;
import com.datasift.dropwizard.hbase.util.TimerStoppingCallback;
import com.stumbleupon.async.Deferred;
import com.yammer.metrics.core.Metric;
import com.yammer.metrics.core.TimerContext;
import org.hbase.async.KeyValue;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * A {@link RowScanner} that is instrumented with {@link Metric}s.
 *
 * To obtain an instance of a {@link RowScanner}, call
 * {@link InstrumentedHBaseClient#newScanner(byte[])}.
 */
public class InstrumentedRowScanner implements RowScanner {

    private final RowScanner scanner;
    private final HBaseInstrumentation metrics;

    /**
     * Creates a new {@link InstrumentedRowScanner} for the given underlying
     * {@link RowScanner}, instrumented using the given
     * {@link HBaseInstrumentation}.
     *
     * @param scanner the underlying {@link RowScanner} implementation
     * @param metrics the {@link Metric}s to instrument this
     *                {@link InstrumentedRowScanner} with
     */
    public InstrumentedRowScanner(final RowScanner scanner,
                                  final HBaseInstrumentation metrics) {
        this.scanner = scanner;
        this.metrics = metrics;
    }

    /**
     * Get the key of the current row being scanned.
     *
     * @see RowScanner#getCurrentKey()
     * @return the key of the current row
     */
    public byte[] getCurrentKey() {
        return scanner.getCurrentKey();
    }

    /**
     * Set the first key in the range to scan.
     *
     * @see RowScanner#setStartKey(byte[])
     * @param start_key the first key to scan from (inclusive)
     */
    public void setStartKey(final byte[] start_key) {
        scanner.setStartKey(start_key);
    }

    /**
     * Set the first key in the range to scan.
     *
     * @see RowScanner#setStartKey(String)
     * @param start_key the first key to scan from (inclusive)
     */
    public void setStartKey(final String start_key) {
        scanner.setStartKey(start_key);
    }

    /**
     * Set the end key in the range to scan.
     *
     * @see RowScanner#setStopKey(byte[])
     * @param stop_key the end key to scan until (exclusive)
     */
    public void setStopKey(final byte[] stop_key) {
        scanner.setStopKey(stop_key);
    }

    /**
     * Set the end key in the range to scan.
     *
     * @see RowScanner#setStopKey(byte[])
     * @param stop_key the end key to scan until (exclusive)
     */
    public void setStopKey(final String stop_key) {
        scanner.setStopKey(stop_key);
    }

    /**
     * Set the family to scan.
     *
     * @see RowScanner#setFamily(byte[])
     * @param family the family to scan
     */
    public void setFamily(final byte[] family) {
        scanner.setFamily(family);
    }

    /**
     * Set the family to scan.
     *
     * @see RowScanner#setFamily(String)
     * @param family the family to scan
     */
    public void setFamily(final String family) {
        scanner.setFamily(family);
    }

    /**
     * Set the qualifier to select from cells
     *
     * @see RowScanner#setQualifier(byte[])
     * @param qualifier the family to select from cells
     */
    public void setQualifier(final byte[] qualifier) {
        scanner.setQualifier(qualifier);
    }

    /**
     * Set the qualifier to select from cells
     *
     * @see RowScanner#setQualifier(String)
     * @param qualifier the family to select from cells
     */
    public void setQualifier(final String qualifier) {
        scanner.setQualifier(qualifier);
    }

    /**
     * Set a regular expression to filter keys being scanned.
     *
     * @see RowScanner#setKeyRegexp(String)
     * @param regexp a regular expression to filter keys with
     */
    public void setKeyRegexp(final String regexp) {
        scanner.setKeyRegexp(regexp);
    }

    /**
     * Set a regular expression to filter keys being scanned.
     *
     * @see RowScanner#setKeyRegexp(String)
     * @param regexp a regular expression to filter keys with
     * @param charset the charset to decode the keys as
     */
    public void setKeyRegexp(final String regexp, final Charset charset) {
        scanner.setKeyRegexp(regexp, charset);
    }

    /**
     * Set whether to use the server-side block cache during the scan.
     *
     * @see RowScanner#setServerBlockCache(boolean)
     * @param populate_blockcache whether to use the server-side block cache
     */
    public void setServerBlockCache(final boolean populate_blockcache) {
        scanner.setServerBlockCache(populate_blockcache);
    }

    /**
     * Set the maximum number of rows to fetch in each batch.
     *
     * @see RowScanner#setMaxNumRows(int)
     * @param max_num_rows the maximum number of rows to fetch in each batch
     */
    public void setMaxNumRows(final int max_num_rows) {
        scanner.setMaxNumRows(max_num_rows);
    }

    /**
     * Set the maximum number of {@link KeyValue}s to fetch in each batch.
     *
     * @see RowScanner#setMaxNumKeyValues(int)
     * @param max_num_kvs the maximum number of {@link KeyValue}s to fetch in
     *                    each batch
     */
    public void setMaxNumKeyValues(final int max_num_kvs) {
        scanner.setMaxNumKeyValues(max_num_kvs);
    }

    /**
     * Sets the minimum timestamp of the cells to yield.
     *
     * @see RowScanner#setMinTimestamp(long)
     * @param timestamp the minimum timestamp of the cells to yield
     */
    public void setMinTimestamp(final long timestamp) {
        scanner.setMinTimestamp(timestamp);
    }

    /**
     * Gets the minimum timestamp of the cells to yield.
     *
     * @see RowScanner#getMinTimestamp()
     * @return the minimum timestamp of the cells to yield
     */
    public long getMinTimestamp() {
        return scanner.getMinTimestamp();
    }

    /**
     * Sets the maximum timestamp of the cells to yield.
     *
     * @see RowScanner#setMaxTimestamp(long)
     * @param timestamp the maximum timestamp of the cells to yield
     */
    public void setMaxTimestamp(final long timestamp) {
        scanner.setMaxTimestamp(timestamp);
    }

    /**
     * Gets the maximum timestamp of the cells to yield.
     *
     * @see RowScanner#getMaxTimestamp()
     * @return the maximum timestamp of the cells to yield
     */
    public long getMaxTimestamp() {
        return scanner.getMaxTimestamp();
    }

    /**
     * Sets the timerange of the cells to yield.
     *
     * @see RowScanner#setMinTimestamp(long)
     * @param min_timestamp the minimum timestamp of the cells to yield
     * @param max_timestamp the maximum timestamp of the cells to yield
     */
    public void setTimeRange(final long min_timestamp,
                             final long max_timestamp) {
        scanner.setTimeRange(min_timestamp, max_timestamp);
    }

    /**
     * Closes this Scanner
     *
     * @see RowScanner#close()
     * @return a Deferred indicating when the close operation has completed
     */
    public Deferred<Object> close() {
        final TimerContext ctx = metrics.getCloses().time();
        return scanner.close().addBoth(new TimerStoppingCallback<Object>(ctx));
    }

    /**
     * Scans the next batch of rows
     *
     * @see RowScanner#nextRows()
     * @return next batch of rows that were scanned
     */
    public Deferred<ArrayList<ArrayList<KeyValue>>> nextRows() {
        final TimerContext ctx = metrics.getScans().time();
        return scanner.nextRows()
                .addBoth(new TimerStoppingCallback<ArrayList<ArrayList<KeyValue>>>(ctx));
    }

    /**
     * Scans the next batch of rows
     *
     * @see RowScanner#nextRows(int)
     * @param rows maximum number of rows to retrieve in the batch
     * @return next batch of rows that were scanned
     */
    public Deferred<ArrayList<ArrayList<KeyValue>>> nextRows(final int rows) {
        final TimerContext ctx = metrics.getScans().time();
        return scanner.nextRows(rows)
                .addBoth(new TimerStoppingCallback<ArrayList<ArrayList<KeyValue>>>(ctx));
    }
}
