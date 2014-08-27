/*
 * Copyright (C) 2014 janvojt.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

package net.vojt.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;

/**
 * Calculator working with {@link Reader input reader}
 * and {@link Writer output writer}.
 *
 * @author janvojt
 */
public class InteractiveCalculator {
    
    /** End of line character to use. */
    private static final String EOL = "\n";
    
    /** Input with commands. */
    private Reader reader;
    
    /** Output of the calculator. */
    private Writer writer;
    
    /** Computation evaluator. */
    private Evaluator eval = new EvaluatorImpl();
    
    /** Parser for single-lined commands. */
    private CommandParser parser = new DecimalCommandParser();
    
    /**
     * Starts the reading from input and the computing process itself.
     * 
     * @throws IOException in case of I/O error
     */
    public void run() throws IOException {
        
        // make sure reader/writer were correctly set
        validate();
        
        // start reading input
        try (BufferedReader bReader = new BufferedReader(reader)) {
            
            // computation may be reused repeatedly
            Computation comp = new Computation();
            
            // read input line by line
            for (String line = bReader.readLine();
                    line != null;
                    line = bReader.readLine()) {

                processLine(line, comp);
            }
        }
    }

    /**
     * Sets the input reader.
     * 
     * @param reader input reader
     */
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    /**
     * Sets the output writer.
     * 
     * @param writer output writer
     */
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    /**
     * Setter may be used to change the default implementation
     * of {@link Evaluator}.
     * 
     * @param eval evaluator
     */
    public void setEval(Evaluator eval) {
        this.eval = eval;
    }

    /**
     * Setter may be used to change the default implementation
     * of {@link CommandParser}.
     * 
     * @param parser command parser
     */
    public void setParser(CommandParser parser) {
        this.parser = parser;
    }
    
    /**
     * Parses a single line into {@link Command} and pushes it onto FIFO queue.
     * 
     * @param line single-lined command
     * @param comp computation queue
     * @throws IOException in case of I/O error
     */
    private void processLine(String line, Computation comp) throws IOException {

        try {
            Command command = parser.parse(line);
            comp.pushCommand(command);
            if (Operation.APPLY.equals(command.getOperation())) {
                processComputation(comp);
            }

        } catch (InvalidCommandSyntaxException ex) {
            writeLn("ERROR: Invalid command syntax.");
        }

    }
    
    /**
     * Processes the {@link Computation} after the
     * {@link Operation#APPLY apply command} has been received.
     * 
     * @param comp computation FIFO queue
     * @throws IOException in case of I/O error
     */
    private void processComputation(Computation comp) throws IOException {
        try {
            BigDecimal result = eval.evaluate(comp);
            writeLn("Result is: " + result.toString());
        } catch (ArithmeticException ex) {
            comp.clear();
            writeLn("ERROR: " + ex.getMessage());
        }
    }
    
    /**
     * Validates input/output.
     * 
     * @throws IllegalStateException in case of invalid input/output
     */
    private void validate() throws IllegalStateException {
        if (reader == null || writer == null) {
            throw new IllegalStateException("Trying to run calculator without input/output.");
        }
    }
    
    /**
     * Writes a single line to output writer and terminates it by EOL.
     * 
     * @param line line to write
     * @throws IOException in case of I/O error
     */
    private void writeLn(String line) throws IOException {
        writer.write(line + EOL);
        writer.flush();
    }
    
}
