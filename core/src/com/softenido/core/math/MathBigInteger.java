/*
 *  MathBigInteger.java
 *
 *  Copyright (C) 2010  Francisco Gómez Carrasco
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Report bugs or new features to: flikxxi@gmail.com
 *
 */
package com.softenido.core.math;

import java.math.BigInteger;

/**
 *
 * @author franci
 */
public class MathBigInteger
{
    public static BigInteger gcd(BigInteger a, BigInteger b)
    {
        return a.gcd(b);
    }
    public static BigInteger gcd(BigInteger a, BigInteger ... b)
    {
        return gcd(a,gcd(b,0,b.length));
    }
    private static BigInteger gcd(BigInteger[] n,int from, int to)
    {
        if (from == to)
        {
            return BigInteger.ZERO;
        }
        if(from+1 == to)
        {
            return n[from];
        }
        if(from+2 == to)
        {
            return gcd(n[from],n[from+1]);
        }
        int mid = (from+to)/2;
        return gcd(gcd(n,from,mid),gcd(n,mid,to));
    }

    public static BigInteger lcm(BigInteger a, BigInteger b)
    {
        return a.multiply(b).divide(gcd(a,b));
}
    public static BigInteger lcm(BigInteger a, BigInteger ... b)
    {
        return lcm(a,lcm(b,0,b.length));
    }

    private static BigInteger lcm(BigInteger[] n, int from, int to)
    {
        if (from == to)
        {
            return BigInteger.ZERO;
        }
        if(from+1 == to)
        {
            return n[from];
        }
        if(from+2 == to)
        {
            return lcm(n[from],n[from+1]);
        }
        int mid = (from+to)/2;
        return lcm(lcm(n,from,mid),lcm(n,mid,to));
    }
}
