/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softenido.dao;

import java.util.ArrayList;

/**
 *
 * @author franci
 */
public interface MarketReader
{
    Market getMarket(String ticker);
    ArrayList<Market> getMarkets();
}
