package it.lparolari.unipd.ml.slp;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Slp {

    private Integer maxEpoch = 100;
    private Double alpha = 0.05;

    private TrainingSet training;
    private List<Double> weigths;

    public Slp(TrainingSet training, Integer randomSeed) {
        Random randomGen = new Random(randomSeed);

        this.training = training;
        this.training.getData()
                .stream()
                .findFirst()
                .ifPresent(sample -> {
                    this. weigths = sample.getXs()
                            .stream()
                            .map(x -> randomGen.nextDouble())
                            .collect(Collectors.toList());
        });
    }

    public void learn() {
        Stream.iterate(0, i -> i + 1)
                .limit(maxEpoch)  // TODO: this cycle is executed the maximum number of times, should be executed the minimum required to work.
                .forEach(j -> {
                        training.getData().stream()
                                .map(sample -> Pair.of(
                                        sample,
                                        activation(sigma(sample.getXs()))))
                                .forEach(p -> {
                                    Sample sample = p.fst();
                                    Integer expected = sample.getY();
                                    Integer actual = p.snd();

                                    if (!expected.equals(actual)) {
                                        for (int i = 0; i < weigths.size(); i++) {
                                            //System.out.println(weigths.get(i));
                                            //System.out.println(expected);
                                            //System.out.println(actual);
                                            //System.out.println(sample.getXs().get(i));
                                            //System.out.println(weigths.get(i) + alpha * (expected - actual) * sample.getXs().get(i));
                                            //System.out.println("---");

                                            weigths.set(i, weigths.get(i) + alpha * (expected - actual) * sample.getXs().get(i));
                                        }
                                    }
                                });
                        //System.out.println(j);
                    }
                );

    }

    public Integer classify(Sample sample) {
        return activation(sigma(sample.getXs()));
    }

    private Double sigma(List<?> X) {
        double sum = 0;//BIAS;

        int i = 0;
        for (Double w: weigths) {
            sum += w * (Double) X.get(i);
            i++;
        }

        return sum;
    }

    private Integer activation(Double x) {
         return x > 0 ? 1 : -1;  // hard threshold
    }

    public String info() {
        return
                String.format("Training set: %s\n", training.getData()) +
                String.format("Weights: %s", weigths.toString());
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public void setMaxEpoch(Integer maxEpoch) {
        this.maxEpoch = maxEpoch;
    }
}
