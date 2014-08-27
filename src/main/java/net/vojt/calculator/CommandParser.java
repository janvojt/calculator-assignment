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

import java.util.Locale;

/**
 * Parses a single command given as a {@link String}.
 *
 * @author janvojt
 */
public interface CommandParser {
    
    /**
     * Parses a single command.
     * 
     * @param command command line
     * @return command
     * @throws net.vojt.calculator.InvalidCommandSyntaxException
     */
    public Command parse(String command) throws InvalidCommandSyntaxException;
    
    /**
     * Sets the locale to be used by the parser.
     * By default the system locale is used.
     * 
     * @param locale 
     */
    public void setLocale(Locale locale);
    
}
