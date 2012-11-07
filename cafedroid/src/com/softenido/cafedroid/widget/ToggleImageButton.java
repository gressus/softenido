/*
 * ToogleImageButton.java
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

package com.softenido.cafedroid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created with IntelliJ IDEA.
 * User: franci
 * Date: 19/10/12
 * Time: 11:29
 * To change this template use File | Settings | File Templates.
 */
public class ToggleImageButton extends ImageButton
{
    private final Object lock = new Object();
    int state = 0;
    int[] stateResources = null;

    public ToggleImageButton(Context context)
    {
        super(context);
    }

    public ToggleImageButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ToggleImageButton(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }
    public void setStateResources(int[] stateResources)
    {
        synchronized(lock)
        {
            this.stateResources = stateResources;
            state = (stateResources!=null && stateResources.length>0)? state % stateResources.length : 0;
        }
    }

    @Override
    public boolean performClick()
    {
        synchronized(lock)
        {
            if(stateResources!=null && stateResources.length>0)
            {
                state = ++state % stateResources.length;
                this.setImageResource(stateResources[state]);
            }
        }
        super.performClick();
        return true;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }
}
