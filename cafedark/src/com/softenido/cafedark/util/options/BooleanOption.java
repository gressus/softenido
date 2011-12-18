/*
 *  BooleanOption.java
 *
 *  Copyright (C) 2009  Francisco Gómez Carrasco
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
package com.softenido.cafedark.util.options;

import java.text.MessageFormat;

/**
 *
 * @author franci
 */
public class BooleanOption implements Option
{

    private final char shortName;
    protected final String longName;
    protected final String _longName;
    protected final String __longName;
    protected String usedName;
    protected int count = 0;
    protected int lastUsed = 0;           //index of the last item using this option
    protected boolean oneHyphen = true;
    protected boolean twoHyphen = true;
    protected boolean nonHyphen = false;

    BooleanOption(char shortName, String longName, boolean command)
    {
        this.shortName = shortName;
        this.longName = longName;
        this._longName = "-" + longName;
        this.__longName = "--" + longName;
        if(command)
        {
            oneHyphen = false;
            twoHyphen = false;
            nonHyphen = true;
        }
    }
    public BooleanOption(char shortName, String longName)
    {
        this(shortName,longName,false);
    }
    public BooleanOption(String longName, boolean command)
    {
        this((char) 0, longName, command);
    }
    public BooleanOption(String longName)
    {
        this((char) 0, longName, false);
    }

    public String getLongName()
    {
        return longName;
    }

    public char getShortName()
    {
        return shortName;
    }

    public int getCount()
    {
        return count;
    }

    public void addCount()
    {
        count++;
    }

    public boolean isUsed()
    {
        return (count > 0);
    }

    public int getLastUsed()
    {
        return lastUsed;
    }

    public int parseLong(int index, String[] args)
    {
        String option = args[index];
        if (twoHyphen && option.equals(__longName))
        {
            count++;
            lastUsed = index;
            usedName = option;
            return 1;
        }
        if (oneHyphen && option.equals(_longName))
        {
            count++;
            lastUsed = index;
            usedName = option;
            return 1;
        }
        if (nonHyphen && option.equals(longName))
        {
            count++;
            lastUsed = index;
            usedName = option;
            return 1;
        }

        return 0;
    }

    public int parseShort(int argIndex, int charIndex, String[] args)
    {
        String option = args[argIndex];
        if (option.charAt(charIndex) == shortName)
        {
            count++;
            lastUsed = argIndex;
            usedName = Character.toString(shortName);
            return 1;
        }
        return 0;
    }

    public String getUsedName()
    {
        return usedName;
    }

    @Override
    public String toString()
    {
        return MessageFormat.format("--{0}={1} (count={2})",new Object[]{longName,(count>0),count});
    }
}
