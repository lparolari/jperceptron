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

Examples are given in `Main`, some simple test can be found in the test directory.

## Author

Luca Parolari <<luca.parolari23@gmail.com>>

## License

MIT