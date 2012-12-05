/*
 * TextClassifier.java
 *
 * Copyright (c) 2012 Francisco Gómez Carrasco
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
package com.softenido.cafecore.statistics.classifier;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author franci
 */
public interface TextClassifier
{
    Score classify(String text);
    Score classify(InputStream text);
    void coach(String category, String text);
    void coach(String category, InputStream text);
    boolean containsCategory(String category);
    String getUnmatched();
    boolean setPreferred(String... categories);
    void setUnmatched(String unmatched);
    void load(InputStream in, boolean strict, String... allowedCategories) throws ClassifierFormatException;
    void loadGZ(InputStream in, boolean strict, String... allowedCategories) throws ClassifierFormatException, IOException, NoSuchAlgorithmException;
    void save(OutputStream out, int min, int max, String... allowedCategories) throws UnsupportedEncodingException;
    void saveGZ(OutputStream out, int min, int max, String... allowedCategories) throws UnsupportedEncodingException, IOException, NoSuchAlgorithmException;

}
