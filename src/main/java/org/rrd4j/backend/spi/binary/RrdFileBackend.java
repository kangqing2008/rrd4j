package org.rrd4j.backend.spi.binary;

import java.io.File;
import java.io.IOException;

/**
 * An abstract backend which is used to store RRD data to ordinary files on the disk.
 * <p>
 * Every backend storing RRD data as ordinary files should inherit from it, some check are done
 * in the code for instanceof.
 *
 */
public abstract class RrdFileBackend extends RrdBinaryBackend {
    /**
     * Read/write file status.
     */
    protected final boolean readOnly;

    protected final File file;

    /**
     * <p>Constructor for RrdFileBackend.</p>
     *
     * @param path a {@link java.lang.String} object.
     * @param readOnly a boolean.
     */
    protected RrdFileBackend(String path, boolean readOnly) {
        super(path);
        this.readOnly = readOnly;
        this.file = new File(path);
    }

    /**
     * Returns canonical path to the file on the disk.
     *
     * @return Canonical file path
     * @throws java.io.IOException Thrown in case of I/O error
     */
    public String getUniqId() throws IOException {
        return file.getCanonicalPath();
    }

    /**
     * Closes the underlying RRD file.
     *
     * @throws java.io.IOException Thrown in case of I/O error
     */
    abstract public void close() throws IOException;

    /**
     * {@inheritDoc}
     *
     * Returns RRD file length.
     */
    @Override
    public long getLength() throws IOException {
        return file.length();
    }

}
