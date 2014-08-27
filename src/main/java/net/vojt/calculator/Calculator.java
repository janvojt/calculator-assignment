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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

/**
 * The main class that launches the application.
 *
 * @author janvojt
 */
public class Calculator {

    /**
     * Entry point of the application.
     * 
     * @param args the command line arguments
     * @throws java.io.IOException in case of I/O error
     */
    public static void main(String[] args) throws IOException {
        
        InteractiveCalculator calc = new InteractiveCalculator();
        
        // we are always writing to stdout
        calc.setWriter(new OutputStreamWriter(System.out));
        
        if (args.length > 0) {
            // we are to read from file
            String filePath = args[0];
            try (Reader reader = new FileReader(filePath)) {
                calc.setReader(reader);
                calc.run();
            } catch (FileNotFoundException ex) {
                System.err.println("Could not read file: '" + filePath + "'");
            }
        } else {
            // we are to read from stdin
            System.out.println("You may start typing commands. Terminate with EOF on a new line.");
            calc.setReader(new InputStreamReader(System.in));
            calc.run();
        }
    }
}
