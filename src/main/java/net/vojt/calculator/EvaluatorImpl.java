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
 * Evaluates the computation result.
 *
 * @author janvojt
 */
public class EvaluatorImpl implements Evaluator {

    @Override
    public BigDecimal evaluate(Computation computation) {
        
        // initialize the result
        BigDecimal result = computation.getInitializer();
        
        // check for missing initializer
        if (result == null) {
            throw new RuntimeException("Computation sequence has an empty initializer!");
        }
        
        // iteratively apply all the operations
        while (!computation.isEmpty()) {
            Command command = computation.popCommand();
            switch (command.getOperation()) {
                case ADD :
                    result = result.add(command.getValue());
                    break;
                case SUBTRACT :
                    result = result.subtract(command.getValue());
                    break;
                case MULTIPLY :
                    result = result.multiply(command.getValue());
                    break;
                case DIVIDE :
                    result = result.divide(command.getValue());
                    break;
                default :
                    throw new RuntimeException("Unsupported operation : " + command.getOperation().toString());
            }
        }
        
        return result;
    }
}
