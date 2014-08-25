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
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A FIFO queue of commands given in a single computation request.
 * 
 * @author janvojt
 */
public class Computation {
    
    /**
     * Queue for caching computational commands.
     * A queue is needed, because we cannot start the computation until we hit
     * the apply command.
     */
    private final Deque<Command> commands = new ArrayDeque<>();
    
    /**
     * Initializing value given after apply command.
     */
    private BigDecimal initializer;
    
    /**
     * Pushes a single {@link Command} onto the command queue.
     * 
     * @param command command to process in the computation sequence
     */
    public void pushCommand(Command command) {
        commands.push(command);
    }
    
    /**
     * Dequeues a single command obeying the FIFO principle.
     * 
     * @return next command to process
     */
    public Command popCommand() {
        return commands.pop();
    }

    /** @return value initializing the computation sequence */
    public BigDecimal getInitializer() {
        return initializer;
    }

    /** @param initializer value initializing the computation sequence */
    public void setInitializer(BigDecimal initializer) {
        this.initializer = initializer;
    }
    
}
