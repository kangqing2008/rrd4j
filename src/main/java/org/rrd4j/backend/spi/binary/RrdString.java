package org.rrd4j.backend.spi.binary;

import java.io.IOException;


class RrdString extends RrdPrimitive {
    private String cache;

    RrdString(Allocated updater, boolean isConstant) throws IOException {
        super(updater, RrdType.STRING, isConstant);
    }

    RrdString(Allocated updater) throws IOException {
        this(updater, false);
    }

    void set(String value) throws IOException {
        if (!isCachingAllowed()) {
            writeString(value);
        }
        // caching allowed
        else if (cache == null || !cache.equals(value)) {
            // update cache
            writeString(cache = value);
        }
    }

    String get() throws IOException {
        if (!isCachingAllowed()) {
            return readString();
        }
        else {
            if (cache == null) {
                cache = readString();
            }
            return cache;
        }
    }
}
