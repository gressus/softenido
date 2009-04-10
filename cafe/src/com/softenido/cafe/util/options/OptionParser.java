/*
 *  OptionParser.java
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
package com.softenido.cafe.util.options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author franci
 */
public class OptionParser
{

    private static final String ONE_HYPHEN = "-";
    private static final String TWO_HYPHEN = "--";
    private boolean ignoreShort = false;
    private boolean oneHyphen = true;
    private boolean twoHyphen = true;
    private boolean posixly = false;
    List<Option> optionList = new ArrayList<Option>();
    private final Option oneHyphenOption = new BooleanOption(ONE_HYPHEN);
    private final Option twoHyphenOption = new BooleanOption(TWO_HYPHEN);

    public OptionParser()
    {
    }
    public <T extends Option> T add(T item)
    {
        optionList.add(item);
        return item;
    }

    public String[] parse(String[] args) throws InvalidOptionException
    {
        Option[] rules = optionList.toArray(new Option[0]);
        String remainder[] = new String[args.length];
        int remainderSize = 0;
        boolean noMoreOptions = false;

        for (int i = 0; i < args.length; i++)
        {
            if (!noMoreOptions)
            {
                if (oneHyphen && args[i].equals(ONE_HYPHEN))
                {
                    // opcion - input by stdin
                    oneHyphenOption.addCount();
                    continue;
                }
                else if (twoHyphen && args[i].equals(TWO_HYPHEN))
                {
                    // option -- no more options
                    twoHyphenOption.addCount();
                    noMoreOptions = true;
                    continue;
                }
                else
                {
                    // long options
                    int size = parseLong(i, args, rules);
                    if (size == 0 && !ignoreShort)
                    {
                        size = parseShort(i, args, rules);
                    }
                    if (size > 0)
                    {
                        i += (size - 1);
                        continue;
                    }

                    if (posixly)
                    {
                        noMoreOptions = true;
                    }
                }
            }
            remainder[remainderSize++] = args[i];
        }
        return Arrays.copyOf(remainder, remainderSize);
    }

    public boolean isPosixly()
    {
        return posixly;
    }

    public void setPosixly(boolean posixly)
    {
        this.posixly = posixly;
    }

    private int parseLong(int index, String[] args, Option[] rules)
    {
        int size = 0;
        for (int i = 0; i < rules.length && size == 0; i++)
        {
            size = rules[i].parseLong(index, args);
        }
        return size;
    }

    private int parseShort(int index, String[] args, Option[] rules) throws InvalidOptionException
    {
        if (!args[index].startsWith(ONE_HYPHEN))
        {
            return 0;
        }

        int num = args[index].length();
        for (int i = 1; i < num; i++)
        {
            int size = 0;
            for (int j = 0; j < rules.length && size == 0; j++)
            {
                size = rules[j].parseShort(index, i, args);
            }
            if (size == 1)
            {
                continue;
            }
            if (size > 1)
            {
                return (size - 1);
            }
            throw new InvalidOptionException("Invalid option -- " + args[index].charAt(i));
        }
        return 1;
    }

    public boolean isIgnoreShort()
    {
        return ignoreShort;
    }

    public void setIgnoreShort(boolean ignoreShort)
    {
        this.ignoreShort = ignoreShort;
    }

}
