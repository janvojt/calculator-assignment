Calculator
==========

Write some code to calculate a result from a set of instructions.
Instructions comprise of a keyword and a number that are separated by a space
per line. Instructions are loaded from file and results are output to the
screen. Any number of Instructions can be specified. Instructions can be any
binary operators of your choice (e.g., add, divide, subtract, multiply etc).
The instructions will ignore mathematical precedence. The last instruction
should be “apply” and a number (e.g., “apply 3”). The calculator is then
initialised with that number and the previous instructions are applied to that
number.

Build
-----

You can build the application by running the following in the project root
directory:

```
mvn clean install
```

This will also run tests. If you want to build quickly without testing issue the
following command instead:

```
mvn clean install -DskipTests
```

Run tests
---------

You can run application tests by running the following in the project root
directory:

```
mvn test
```

Launch the application
----------------------

You can launch the Calculator by issuing the following command:

```
java -jar target/Calculator-1.0.jar
```

In such case application will be waiting for your input in interactive mode.
You can start issuing commands as is specified in the problem assignment.
Calculator will print the result after the "apply" command, and continue
listening to your input. If you wish to terminate the application, just type
the end-of-file character (Ctrl+D on Unix, Ctrl+Z on Windows).

If you wish to read input from a file instead, you can put the file path as
the first argument like so:

```
java -jar target/Calculator-1.0.jar src/test/resources/in/example-1.txt
```

Application will print the results and terminate immediately.

