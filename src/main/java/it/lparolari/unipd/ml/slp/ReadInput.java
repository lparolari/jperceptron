package it.lparolari.unipd.ml.slp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReadInput {

    private Double alpha;
    private Integer nInput;
    private List<Sample> data = new ArrayList<>();

    public void readFrom(Scanner scanner) {
        // file type
        // alpha
        // number of features N
        // x1 x2 ... xN y
        // ...

        String fileType = "";
        boolean delimiter = false;

        for (int i = 0; scanner.hasNext(); i++) {
            String line = scanner.nextLine();

            if (i == 0) {
                fileType = line;
            }
            if (i > 0) {
                if (fileType.equals("TRAINING")) {
                    if (i == 1) {
                        alpha = Double.parseDouble(line);
                    }
                    if (i == 2) {
                        nInput = Integer.parseInt(line);
                    }
                }
                if (line.equals("---")) {
                    delimiter = true;
                    continue;
                }
                if (delimiter) {
                    List<String> in = Arrays.asList(line.split(" "));
                    data.add(new Sample(
                            in.stream().limit(in.size() - 1).map(Double::parseDouble).collect(Collectors.toList()),
                            Integer.parseInt(in.stream().reduce((first, second) -> second).get())
                    ));
                }
            }

        }
    }

    public Double getAlpha() {
        return alpha;
    }

    public Integer getnInput() {
        return nInput;
    }

    public List<Sample> getData() {
        return data;
    }

    @Override
    public String toString() {
        return String.format("Alpha=%s, Number of features=%s,\nData=%s", alpha, nInput, data);
    }
}
