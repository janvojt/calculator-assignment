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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for command parser.
 * 
 * @author janvojt
 */
public class DecimalCommandParserTest {
    
    /** Tested instance. */
    private CommandParser parser;
    
    @Before
    public void setUp() {
        // create separate parser for each test for independent testing
        parser = new DecimalCommandParser();
    }

    /**
     * Test parsing an integer.
     * 
     * @throws net.vojt.calculator.InvalidCommandSyntaxException
     */
    @Test
    public void testParseInteger() throws InvalidCommandSyntaxException {
        Command command = parser.parse("subtract 54");
        assertEquals(Operation.SUBTRACT, command.getOperation());
        assertEquals(new BigDecimal(54), command.getValue());
    }
    
    /**
     * Test parsing a decimal number.
     * 
     * @throws InvalidCommandSyntaxException 
     */
    @Test
    public void testParseDecimal() throws InvalidCommandSyntaxException {
        Command command = parser.parse("add 54.05");
        assertEquals(Operation.ADD, command.getOperation());
        assertEquals(new BigDecimal("54.05"), command.getValue());
    }
    
    /**
     * Test parsing a localized number.
     * 
     * @throws InvalidCommandSyntaxException 
     */
    @Test
    public void testParseLocal() throws InvalidCommandSyntaxException {
        Command command = parser.parse("add 54" + getDecimalSeparator() + "05");
        assertEquals(Operation.ADD, command.getOperation());
        assertEquals(new BigDecimal("54.05"), command.getValue());
    }
    
    /**
     * Test parsing a command with comma as a decimal separator.
     * 
     * @throws InvalidCommandSyntaxException 
     */
    @Test
    public void testParseCommaSeparator() throws InvalidCommandSyntaxException {
        
        // set czech locale so we get a parser with different decimal separator
        parser.setLocale(Locale.forLanguageTag("cs-CZ"));
        
        // test the new parser with comma as the decimal separator
        Command command = parser.parse("add 54,05");
        assertEquals(Operation.ADD, command.getOperation());
        assertEquals(new BigDecimal("54.05"), command.getValue());
        
        // test that the point still works
        Command command2 = parser.parse("add 54.05");
        assertEquals(Operation.ADD, command2.getOperation());
        assertEquals(new BigDecimal("54.05"), command2.getValue());
    }
    
    /**
     * Fetch decimal separator from locale.
     * 
     * @return decimal separator
     */
    private char getDecimalSeparator() {
        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        return symbols.getDecimalSeparator();
    }
    
}
