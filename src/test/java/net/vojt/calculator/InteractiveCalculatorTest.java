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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URI;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Tests suite for {@link InteractiveCalculator} reading test resources.
 *
 * @author janvojt
 */
@RunWith(Parameterized.class)
public class InteractiveCalculatorTest {
    
    /** Path to test resource directory with input files. */
    private static final URL INPUT_DIR = InteractiveCalculatorTest.class.getResource("/in");
    
    /** Path to test resource directory with output files. */
    private static final URL OUTPUT_DIR = InteractiveCalculatorTest.class.getResource("/out");
    
    /** Tested instance. */
    private InteractiveCalculator calculator;
    
    /**
     * Parameter for the {@link #testRun()} initialized before each unit test.
     */
    private Path filePath;

    /**
     * Setup parameters for each test.
     * 
     * @param filePath path to input file to be used as a test parameter
     */
    public InteractiveCalculatorTest(Path filePath) {
        this.filePath = filePath;
    }
    
    /**
     * Factory for test parameter {@link #filePath}.
     * 
     * @return array of file paths for each test run
     * @throws IOException
     * @throws URISyntaxException 
     */
    @Parameterized.Parameters
    public static Collection testFileset() throws IOException, URISyntaxException {
        List<Path> filePaths = new ArrayList<>();
        
        // read the resource directory with input files
        URI pathUri = INPUT_DIR.toURI();
        Path dirPath = Paths.get(pathUri);
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dirPath)) {
            for (Path path : directoryStream) {
                filePaths.add(path);
            }
        }
        
        // build array of array forming test parameters
        Path[][] fps = new Path[filePaths.size()][1];
        int i = 0;
        for (Path path : filePaths) {
            fps[i++][0] = path;
        }
        
        // return the parameter definition
        return Arrays.asList(fps);
    }
    
    /**
     * Sets up new independent tested instance before each test.
     */
    @Before
    public void setUp() {
        calculator = new InteractiveCalculator();
    }

    /**
     * Test of run method for the testing file set.
     * 
     * @throws java.io.IOException
     */
    @Test
    public void testRun() throws IOException {
            
        Reader reader = new FileReader(filePath.toFile());
        Writer writer = new StringWriter();

        calculator.setReader(reader);
        calculator.setWriter(writer);
        calculator.run();

        String expected = readFileToString(new File(
                OUTPUT_DIR.getPath(),
                filePath.getFileName().toString()
        ));

        assertEquals(expected, writer.toString().trim());
    }
    
    /**
     * Converts given file into a string with the file contents.
     * 
     * @param file file to read into string
     * @return file contents
     */
    private String readFileToString(File file) {
        String content = "";
        try (Scanner scanner = new Scanner(file)) {
            content = scanner.useDelimiter("\\Z").next();
        } catch (FileNotFoundException ex) {
            // should not happen
            throw new RuntimeException(ex);
        }
        return content;
    }
    
}
