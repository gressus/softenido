/*
 * NullPricingTest.java
 *
 * Copyright (c) 2012  Francisco Gómez Carrasco
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Report bugs or new features to: flikxxi@gmail.com
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softenido.trading.pricing;

import com.softenido.trading.OrderType;
import com.softenido.trading.TransactionType;
import java.math.BigDecimal;
import org.junit.*;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author franci
 */
public class NullPricingTest
{
    
    public NullPricingTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getCost method, of class NullPricing.
     */
    @Test
    public void testGetCost()
    {
        Pricing zero = new NullPricing();
        
        assertEquals(BigDecimal.ZERO, zero.getCost(TransactionType.BUY, OrderType.LIMIT, 10.0, 1));
        assertEquals(BigDecimal.ZERO, zero.getCost(TransactionType.SELL, OrderType.MARKET, 10.0, 1));
        assertEquals(BigDecimal.ZERO, zero.getCost(TransactionType.SHORT, OrderType.STOP, 10.0, 1));
        assertEquals(BigDecimal.ZERO, zero.getCost(TransactionType.COVER, OrderType.STOP_LIMIT, 10.0, 1));
    }
}
