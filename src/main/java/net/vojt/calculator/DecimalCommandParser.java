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
import java.util.StringTokenizer;

/**
 * Parser of commands with decimal values, respecting system locale
 * setting by using the correct decimal separator.
 *
 * @author janvojt
 */
public class DecimalCommandParser implements CommandParser {
    
    /**
     * Default decimal separator used by {@link BigDecimal(java.lang.String)}.
     */
    private static final char DEFAULT_DECIMAL_SEP = '.';
    
    /** The decimal separator. */
    private char DECIMAL_SEP;
    
    /**
     * Constructor determines the decimal separator from default locale.
     */
    public DecimalCommandParser() {
        setLocale(Locale.getDefault());
    }
    
    @Override
    public Command parse(String command) throws InvalidCommandSyntaxException {
        StringTokenizer st = new StringTokenizer(command);
        
        if (st.countTokens() != 2) {
            throw new InvalidCommandSyntaxException("Invalid number of tokens in command '" + command + "'.");
        }

        // parse command operation
        Operation operation;
        String strOp = st.nextToken();
        try {
            operation = Operation.valueOf(strOp.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new InvalidCommandSyntaxException("Unrecognized operation '" + strOp + "' in command '" + command + "'.");
        }
        
        // implement support for local decimal separator
        String strValue = st.nextToken().replace(DECIMAL_SEP, DEFAULT_DECIMAL_SEP);
        
        // parse command value
        BigDecimal value;
        try {
            value = new BigDecimal(strValue);
        } catch (NumberFormatException ex) {
            throw new InvalidCommandSyntaxException("Command value '" + strValue + "' cannot be parsed as a numeric expression.");
        }
        
        return new Command(operation, value);
    }

    @Override
    public final void setLocale(Locale locale) {
        DecimalFormat format = (DecimalFormat) DecimalFormat.getNumberInstance(locale);
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        DECIMAL_SEP = symbols.getDecimalSeparator();
    }
    
}
