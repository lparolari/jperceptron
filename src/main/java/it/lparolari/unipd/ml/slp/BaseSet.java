package it.lparolari.unipd.ml.slp;

import java.util.ArrayList;
import java.util.List;

public class BaseSet {
    public List<Sample> data = new ArrayList<>();

    public BaseSet() {}
    public BaseSet(List<Sample> data) {
        this.data = data;
    }

    public void add(Sample sample) {
        data.add(sample);
    }

    public List<Sample> getData() {
        return data;
    }
}
