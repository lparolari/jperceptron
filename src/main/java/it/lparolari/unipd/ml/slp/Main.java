package it.lparolari.unipd.ml.slp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("SLP algorithm example - Luca Parolari");

        Scanner in = new Scanner(System.in);
        System.out.print("Training set file name: ");
        String trainingSetFileName = in.nextLine();
        System.out.print("Test set file name: ");
        String testSetFileName = in.nextLine();
        System.out.println();

        ReadInput dataTraining = new ReadInput(), dataTest = new ReadInput();
        try {
            dataTraining.readFrom(new Scanner(new File(trainingSetFileName)));
            dataTest.readFrom(new Scanner(new File(testSetFileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        System.out.println(dataTraining);

        TrainingSet training = new TrainingSet(dataTraining.getData());

        Slp slp = new Slp(training, 42);
        slp.setAlpha(dataTraining.getAlpha());

        System.out.println("BEFORE TRAINING");
        System.out.println(slp.info());
        System.out.println();

        slp.learn();

        System.out.println("AFTER TRAINING");
        System.out.println(slp.info());
        System.out.println();

        List<Sample> tests = dataTest.getData();

        tests.stream()
                .map(s -> String.format("Classification of sample %s is %s", s, slp.classify(s)))
                .forEach(System.out::println);
    }
}
