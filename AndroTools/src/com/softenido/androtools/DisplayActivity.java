/*
 * DisplayActivity.java
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
package com.softenido.androtools;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.softenido.cafecore.gauge.LongGaugeProgress;
import com.softenido.cafedroid.admob.AdMob;
import com.softenido.cafedroid.gauge.DroidGaugeBuilder;
import com.softenido.cafedroid.gauge.DroidGaugeProgress;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.WeakHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: franci
 * Date: 1/11/12
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 */
public class DisplayActivity extends Activity
{
    private AdMob admob=null;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_data);
        admob = AdMob.addBanner(this, R.id.performance_main, true);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        TextView pixels = (TextView)findViewById(R.id.pixels);
        TextView density = (TextView)findViewById(R.id.density);
        TextView scale = (TextView)findViewById(R.id.scale);
        TextView xdpi = (TextView)findViewById(R.id.xdpi);
        TextView ydpi = (TextView)findViewById(R.id.ydpi);

        pixels.setText("pixels="+metrics.heightPixels+"x"+metrics.widthPixels);
        density.setText("density="+metrics.densityDpi+"dpi");
        scale.setText("scale="+metrics.scaledDensity);
        xdpi.setText("xdpi="+metrics.xdpi);
        ydpi.setText("ydpi="+metrics.ydpi);

    }
}
