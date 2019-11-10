# SLP algorithm example

The aim of this project is implementing the perceptron algorithm. The perceptron
 algorithm uses only a neuron to compute the result. The perceptron algorithm works
 in few steps:
 
 - randomly initialize weights;
 - repeat
   - take a sample `(X, y)`
   - if `o = sign(W * X) != y` then `W <- W + alpha * (y-o) * X`

This algorithm is implemented in `Slp::learn` method. The `classify` method is 
used to classify test set samples.

You can find an usage example [here](src/main/java/it/lparolari/unipd/ml/slp/Main.java). However the `Slp` class can be used as follows:
```java
// dataTraining, dataTest are ReadInput objects (data readed from file).

// get read data
List<Sample> data = dataTraining.getData();
Double alpha = dataTraining.getAlpha();

// training set
TrainingSet training = new TrainingSet(data);

// perceptron
Slp slp = new Slp(training, 42);
slp.setAlpha(alpha);

// weights before training
System.out.println(slp.info());
// learning
slp.learn();
// weights after training
System.out.println(slp.info());

// test set
List<Sample> tests = dataTest.getData();
// classification
tests.stream()
        .map(s -> String.format("Classification of sample %s is %s", s, slp.classify(s)))
        .forEach(System.out::println);
```

The `Slp` class treats the training set as a list of samples, that can be hardcoded
in your program or read by a file. A traning set file is composed by
```
TRAINING            // keyword identifying the file type
0.05                // learning rate
3                   // number of features N
---                 // delimiter (required for reading samples)
1 0 0 -1            // x0 x1 x2 ... xN y
1 0 1 -1
1 1 0 -1
1 1 1 1
```
Note that comments are not allowed. Keep the training data delimited by one spaces 
and always put the bias `x0` equal to 1.

The test set file is similar, but without the learning rate and number of features.

## Author

Luca Parolari <<luca.parolari23@gmail.com>>

## License

MIT
