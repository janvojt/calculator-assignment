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

/**
 * Represents immutable, single-lined command given to the calculator.
 * A command specifies an operation and a value to operate with.
 *
 * @author janvojt
 */
public class Command {
    
    /** Operation to compute for this command. */
    private final Operation operation;
    
    /** Operation argument. */
    private final BigDecimal value;

    /**
     * Constructs the Command.
     * 
     * @param operation operation to compute
     * @param value operation argument
     */
    public Command(final Operation operation, final BigDecimal value) {
        this.operation = operation;
        this.value = value;
    }

    public Operation getOperation() {
        return operation;
    }

    public BigDecimal getValue() {
        return value;
    }
    
}
