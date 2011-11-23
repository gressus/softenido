/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softenido.trading.pricing;

import com.softenido.trading.OrderType;
import com.softenido.trading.TransactionType;

/**
 *
 * @author franci
 */
public class NullPricing implements Pricing
{
    public double getCost(TransactionType tt, OrderType ot, double price, int shares)
    {
        return 0;
    }
}
