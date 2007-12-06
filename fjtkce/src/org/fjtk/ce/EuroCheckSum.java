/*
 *  ExtracIcons.java
 *
 *  Copyright (C) 2007  Francisco G�mez Carrasco
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
package org.fjtk.ce;

/**
 *
 * @author franci
 */
public class EuroCheckSum
{
    private static final String letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String number = "23456789123456789123456789";
    
    private static String countries[][] =
    {
        {"Z","B�lgica"},
        {"Y","Grecia"},
        {"X","Alem�nia"},
        {"W","Dinamarca"},//
        {"V","Espa�a"},
        {"U","Francia"},
        {"T","Irlanda"},
        {"S","Italia"},
        {"R","Luxemburgo"},
        {"Q","No se usa"},
        {"P","Holanda"},
        {"O","No se usa"},
        {"N","Austria"},
        {"M","Portugal"},
        {"L","Finlandia"},
        {"K","Suiza"},//
        {"J","Reino Unido"}    //
    };
    /** Creates a new instance of EuroCheckSum */
    private EuroCheckSum()
    {
    }
    public static boolean verifySerial(String serial)
    {
        int i;
        int sum;
        
        if (serial.length() != 12)
            return false;
        
        for(sum=i=0;i<serial.length();i++)
        {
            char c = serial.charAt(i);
            
            if( c < '0' || c >'9' )
                c = number.charAt(letter.indexOf(Character.toUpperCase(c)));
            
            sum += ( c - '0' );
            
        }
        if( (sum % 9) == 0 )
            return true;
        
        return false;
        
    }
}