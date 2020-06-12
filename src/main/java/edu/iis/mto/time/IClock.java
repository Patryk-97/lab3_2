package edu.iis.mto.time;

import org.joda.time.Instant;

public interface IClock {

    public Instant getActualTime();
}
