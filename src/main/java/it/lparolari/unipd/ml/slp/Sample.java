package it.lparolari.unipd.ml.slp;

import java.util.List;

public class Sample {
    private List<Double> X;
    private Integer y;

    public Sample(List<Double> inputs, Integer expected) {
        X = inputs;
        y = expected;
    }

    public List<Double> getXs() {
        return X;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(X=%s, y=%s)", X, y);
    }
}
