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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for {@link EvaluatorImpl}.
 *
 * @author janvojt
 */
public class EvaluatorImplTest {
    
    /** Tested instance. */
    private Evaluator evaluator;
    
    @Before
    public void setUp() {
        // make sure tests are independent
        evaluator = new EvaluatorImpl();
    }

    /**
     * Test addition.
     */
    @Test
    public void testEvaluateAdd() {
        
        // define the computation
        Computation computation = new Computation();
        computation.pushCommand(new Command(Operation.ADD, BigDecimal.TEN));
        computation.setInitializer(BigDecimal.ONE);
        
        // test the evaluator
        assertEquals(new BigDecimal(11), evaluator.evaluate(computation));
    }
    
    /**
     * Test subtraction.
     */
    @Test
    public void testEvaluateSubtract() {
        
        // define the computation
        Computation computation = new Computation();
        computation.pushCommand(new Command(Operation.SUBTRACT, BigDecimal.TEN));
        computation.setInitializer(BigDecimal.ONE);
        
        // test the evaluator
        assertEquals(new BigDecimal(-9), evaluator.evaluate(computation));
    }
    
    /**
     * Test multiplication.
     */
    @Test
    public void testEvaluateMultiply() {
        
        // define the computation
        Computation computation = new Computation();
        computation.pushCommand(new Command(Operation.MULTIPLY, BigDecimal.TEN));
        computation.setInitializer(new BigDecimal(2));
        
        // test the evaluator
        assertEquals(new BigDecimal(20), evaluator.evaluate(computation));
    }
    
    /**
     * Test division.
     */
    @Test
    public void testEvaluateDivide() {
        
        // define the computation
        Computation computation = new Computation();
        computation.pushCommand(new Command(Operation.DIVIDE, BigDecimal.TEN));
        computation.setInitializer(BigDecimal.ONE);
        
        // test the evaluator
        assertEquals(new BigDecimal(".1"), evaluator.evaluate(computation));
    }
    
    /**
     * Test computation sequence with multiple operations.
     */
    @Test
    public void testEvaluateComplex() {
        
        // define the computation
        Computation computation = new Computation();
        computation.pushCommand(new Command(Operation.DIVIDE, BigDecimal.TEN));
        computation.pushCommand(new Command(Operation.ADD, BigDecimal.ONE));
        computation.pushCommand(new Command(Operation.ADD, BigDecimal.ONE));
        computation.pushCommand(new Command(Operation.DIVIDE, BigDecimal.ONE));
        computation.pushCommand(new Command(Operation.MULTIPLY, BigDecimal.TEN));
        computation.pushCommand(new Command(Operation.SUBTRACT, BigDecimal.ONE));
        computation.setInitializer(BigDecimal.TEN);
        
        // test the evaluator
        assertEquals(new BigDecimal(29), evaluator.evaluate(computation));
    }
    
    /**
     * Test uninitialized computation sequence.
     */
    @Test(expected = RuntimeException.class)
    public void testEvaluateEmpty() {
        
        // define the computation
        Computation computation = new Computation();
        
        // test the evaluator
        evaluator.evaluate(computation);
    }
    
    /**
     * Test missing initializer in computation sequence.
     */
    @Test(expected = RuntimeException.class)
    public void testEvaluateEmptyInitializer() {
        
        // define the computation
        Computation computation = new Computation();
        computation.pushCommand(new Command(Operation.ADD, BigDecimal.TEN));
        
        // test the evaluator
        evaluator.evaluate(computation);
    }
    
}
