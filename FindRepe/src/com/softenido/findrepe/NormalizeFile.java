/*
 *  NormalizeFile.java
 *
 *  Copyright (C) 2009-2010 Francisco Gómez Carrasco
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
package com.softenido.findrepe;

import com.softenido.cafe.io.Files;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author franci
 */
public class NormalizeFile
{
    private final Object lock = new Object();
    private Process child;
    private OutputStream out;
    
    public String normalize(File fd) throws IOException
    {
        synchronized (lock)
        {
            if (child == null)
            {
                child = Runtime.getRuntime().exec("sh");
                out = child.getOutputStream();
            }
            String name = fd.getCanonicalPath();
            String escape = Files.escape(name);
            String normal = Files.normalize(escape);
            escape = Files.wildcard(escape);
            final String cmd = "mv " + escape + " " + normal;
            out.write(cmd.getBytes());
            return normal;
        }
        
    }
    public void close() throws IOException, InterruptedException
    {
        if(out!=null)
        {
            out.close();
            out = null;
        }
        if(child!=null)
        {
            child.waitFor();
            child=null;
        }
    }

    @Override
    protected void finalize() throws Throwable
    {
        close();
        super.finalize();
    }

}
