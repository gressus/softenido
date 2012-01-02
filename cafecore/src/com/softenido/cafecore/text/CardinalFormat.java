/*
 *  CardinalFormat.java
 *
 *  Copyright (C) 2011  Francisco Gómez Carrasco
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
package com.softenido.cafecore.text;

import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

class NumberRule
{
    private static final int EXACT=0;
    private static final int ADD=1;
    private static final int MUL=2;
    static final NumberRule identityRule = new NumberRule(0, null, null, null, null);

    final long value;
    
    final String[] exact;
    final String[] add;
    final String[] mul;
    final String[] mulAdd;

    NumberRule(long value, String[] exact, String[] add, String[] mul, String[] mulAdd)
    {
        this.value = value;
        this.exact = exact;
        this.add = add;
        this.mul = mul;
        this.mulAdd = mulAdd;
    }

    static public NumberRule build(String id, ResourceBundle rb, CardinalFormat.Gender gender, int style)
    {
        long value = Double.valueOf(id).longValue();
        String rule = rb.getString(id);
        if(rule.equals("{=}"))
        {
            return identityRule;
        }
        String[] subrules = rule.split(";");

        String[][] items = new String[subrules.length][];
        for(int i=0;i<subrules.length;i++)
        {
            items[i]= subrules[i].split(",");
        }
        
        String[] exact  = null;
        String[] add    = null;
        String[] mul    = null;
        String[] mulAdd = null;
        
        for(int i=0;i<subrules.length;i++)
        {
            String n = (items[i].length>0)?items[i][0]:"";
            String m = (items[i].length>1)?items[i][1]:n;
            String f = (items[i].length>2)?items[i][2]:n;
            int mode = (n.contains("{*}")?MUL:0)|(n.contains("{+}")?ADD:0);
            
            String[] tmp = new String[3];
            tmp[0] = n.replace("{*}", "{0}").replace("{+}", "{1}");
            tmp[1] = m.replace("{*}", "{0}").replace("{+}", "{1}");
            tmp[2] = f.replace("{*}", "{0}").replace("{+}", "{1}");
            
            if(style==CardinalFormat.LONG)
            {
                for(int j=0;j<tmp.length;j++)
                {
                    tmp[j] = tmp[j].replace("[", "").replace("]", "");
                }
            }
            else
            {
                for(int j=0;j<tmp.length;j++)
                {
                    tmp[j] = tmp[j].replaceAll("\\[[^\\[\\]]*\\]", "");
                }
            }
            
            switch(mode)
            {
                case EXACT: exact=tmp;  break;
                case ADD:   add=tmp;    break;
                case MUL:   mul=tmp;    break;
                default:    mulAdd=tmp; break;
            }
        }
        return new NumberRule(value,exact,add,mul,mulAdd);
    }
}
/**
 *
 * @author franci
 */
public class CardinalFormat
{
    private final NumberRule[] rules;
    private final Gender gender;
    private boolean identitiy=false;
    
    static public enum Gender
    {
        NEUTRAL(0),MALE(1),FEMALE(2);
        final int value;
        private Gender(int value)
        {
            this.value = value;
        }
    }
    //this shoul be the default value, adding a default entry in property file "default=SHORT"
    //so with the same rules US and GB files will give the apropiated default Cardinals
    //static public int AUTO  = 1; 

    static public final int SHORT = 1;
    static public final int LONG  = 2;
    static private final Comparator<NumberRule> cmp = new Comparator<NumberRule>()
    {
        public int compare(NumberRule o1, NumberRule o2)
        {
            if(o1.value<o2.value)
                return -1;
            if(o1.value>o2.value)
                return +1;
            return 0;
        }
    };
    
    public CardinalFormat(Locale locale, Gender gender, int style)
    {
        if(style!=CardinalFormat.SHORT && style!=CardinalFormat.LONG)
        {
            throw new InvalidParameterException();
        }
        this.gender = gender;
        ResourceBundle rb = getResourceBundle( locale);
        ArrayList<NumberRule> tokens = new ArrayList<NumberRule>();

        for(Enumeration<String> e = rb.getKeys(); e.hasMoreElements();)
        {
            String id = e.nextElement();
            NumberRule rule = NumberRule.build(id, rb, gender, style);
            if(rule.equals(NumberRule.identityRule))
            {
                identitiy = true;
            }
            tokens.add(rule);
        }
        this.rules = tokens.toArray(new NumberRule[tokens.size()]);
        Arrays.sort(rules,cmp);
    }

    ResourceBundle getResourceBundle(Locale locale)
    {
        return ResourceBundle.getBundle(CardinalFormat.class.getName(), locale);
    }
    public String format(long num)
    {
        if(identitiy)
        {
            return Long.toString(num);
        }
        NumberRule rule=null;
        long   div = 0;
        long   mod = 0;
        for(int i=0;i<rules.length&& rules[i].value<=num;i++)
        {
            if(rules[i].value==num)
            {
                return rules[i].exact[gender.value];
            }
            
            long d= (rules[i].value==0)?0:num/rules[i].value;
            long m= (rules[i].value==0)?0:num%rules[i].value;
            if( d>1 && m>0 && rules[i].mulAdd!=null)
            {
                rule = rules[i];
                div = d;
                mod = m;
            }
            else if( d>1 && m==0  && rules[i].mul!=null)
            {
                rule = rules[i];
                div = d;
                mod = m;
            }
            else if( d==1 && m>0  && rules[i].add!=null)
            {
                rule = rules[i];
                div = d;
                mod = m;
            }
        }
        if(rule==null)
        {
            throw new UnsupportedValueException(num);
        }
        
        String formula;
        String text0="";
        String text1="";

        if( rule.mul!=null && mod==0 )
        {
            formula = rule.mul[gender.value];
            text0=format(div);
        }
        else if( rule.add!=null && div==1 && mod!=0 )
        {
            formula = rule.add[gender.value];
            text1=format(mod);
        }
        else if( rule.mulAdd!=null )
        {
            formula = rule.mulAdd[gender.value];
            text0=format(div);
            text1=format(mod);
        }
        else
        {
            throw new UnsupportedValueException(num);
        }
        formula = MessageFormat.format(formula, text0, text1);
        return formula;
    }
    static public CardinalFormat getInstance(Locale locale, Gender gender, int style) 
    {
        return new CardinalFormat(locale,gender,style);
    }
    static public CardinalFormat getInstance(Locale locale, Gender gender) 
    {
        return new CardinalFormat(locale,gender,CardinalFormat.SHORT);
    }
    static public CardinalFormat getInstance(Locale locale) 
    {
        return new CardinalFormat(locale,Gender.NEUTRAL,CardinalFormat.SHORT);
    }
    static public CardinalFormat getInstance(Gender gender) 
    {
        return new CardinalFormat(Locale.getDefault(),gender,CardinalFormat.SHORT);
    }
    static public CardinalFormat getInstance() 
    {
        return new CardinalFormat(Locale.getDefault(),Gender.NEUTRAL,CardinalFormat.SHORT);
    }
    static public CardinalFormat getInstance(Locale locale, int style) 
    {
        return new CardinalFormat(locale,Gender.NEUTRAL,style);
    }
    
}
