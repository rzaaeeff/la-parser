package com.rzaaeeff.core.model;

/**
 * Created by Rzaaeeff on 3/1/2017.
 */
public class SubstringModel {
    private int start;
    private int end;

    public SubstringModel(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int start() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int end() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
