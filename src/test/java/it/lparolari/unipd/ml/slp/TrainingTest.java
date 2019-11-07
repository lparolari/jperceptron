package it.lparolari.unipd.ml.slp;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class TrainingTest {

    private ClassLoader classLoader;

    @Before
    public void setUp() {
        classLoader = Thread.currentThread().getContextClassLoader();
    }

    @Test
    public void andTrainingTest() {
        InputStream trainingSetInputStream = classLoader.getResourceAsStream("and/train.txt");
        InputStream dataSetInputStream = classLoader.getResourceAsStream("and/test.txt");

        ReadInput dataTraining = new ReadInput(), dataTest = new ReadInput();
        dataTraining.readFrom(new Scanner(trainingSetInputStream));
        dataTest.readFrom(new Scanner(dataSetInputStream));

        TrainingSet training = new TrainingSet(dataTraining.getData());

        Slp slp = new Slp(training, 42);
        slp.setAlpha(dataTraining.getAlpha());

        slp.learn();

        List<Sample> tests = dataTest.getData();

        tests.stream()
                .filter(s -> s.getY() == 1)
                .forEach(s -> assertEquals(s.getY(), slp.classify(s)));
        tests.stream()
                .filter(s -> s.getY() == -1)
                .forEach(s -> assertEquals(s.getY(), slp.classify(s)));
    }

    @Test
    public void orTrainingTest() {
        InputStream trainingSetInputStream = classLoader.getResourceAsStream("or/train.txt");
        InputStream dataSetInputStream = classLoader.getResourceAsStream("or/test.txt");

        ReadInput dataTraining = new ReadInput(), dataTest = new ReadInput();
        dataTraining.readFrom(new Scanner(trainingSetInputStream));
        dataTest.readFrom(new Scanner(dataSetInputStream));

        TrainingSet training = new TrainingSet(dataTraining.getData());

        Slp slp = new Slp(training, 42);
        slp.setAlpha(dataTraining.getAlpha());

        slp.learn();

        List<Sample> tests = dataTest.getData();

        tests.stream()
                .filter(s -> s.getY() == 1)
                .forEach(s -> assertEquals(s.getY(), slp.classify(s)));
        tests.stream()
                .filter(s -> s.getY() == -1)
                .forEach(s -> assertEquals(s.getY(), slp.classify(s)));
    }
}
