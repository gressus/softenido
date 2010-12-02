/*
 *  OSName.java
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
package com.softenido.core.util;

/**
 *
 * @author franci
 */
public class OSName
{
    // string for os.name property only few tested

    private static final String DARWIN        = "darwin";
    private static final String MAC           = "mac";
    private static final String MAC_OS_X      = "mac os x";
    private static final String OPENBSD       = "openbsd";
    private static final String LINUX         = "linux";        // tested
    private static final String SOLARIS       = "solaris";
    private static final String SUNOS         = "sunos";        // tested
    private static final String FREEBSD       = "freebsd";
    private static final String WINDOWS       = "windows";      // tested
    private static final String WINDOWS_98    = "windows 98";
    private static final String WINDOWS_98SE  = "windows 98se";
    private static final String WINDOWS_ME    = "windows me";
    private static final String WINDOWS_2000  = "windows 2000";
    private static final String WINDOWS_XP    = "windows xp";   // tested
    private static final String WINDOWS_VISTA = "windows vista";   // tested
    private static final String WINDOWS_CE    = "windows ce";
    private static final String OS_NAME       = "os.name";
    private final boolean linux;
    private final boolean solaris;
    private final boolean freebsd;
    private final boolean openbsd;
    private final boolean macos;
    private final boolean macosx;
    private final boolean posix;
    private final boolean windows;
    private final boolean windows98;
    private final boolean windows98SE;
    private final boolean windowsME;
    private final boolean windows2000;
    private final boolean windowsXP;
    private final boolean windowsVista;
    private final boolean windowsCE;
    private final boolean unknown;
    public static final OSName os = getInstance(System.getProperty(OS_NAME));
    private final String name;

    public OSName(String osName)
    {
        name   = osName;
        osName = osName.toLowerCase();

        boolean _linux = false;
        boolean _solaris = false;
        boolean _freebsd = false;
        boolean _openbsd = false;
        boolean _macos = false;
        boolean _macosx = false;
        boolean _posix = false;
        boolean _windows = false;

        boolean _windows98 = false;
        boolean _windows98SE = false;
        boolean _windowsME = false;
        boolean _windows2000 = false;
        boolean _windowsXP = false;
        boolean _windowsVista = false;
        boolean _windowsCE = false;

        boolean _unknown = false;

        if (osName.startsWith(LINUX))
        {
            _linux = true;
            _posix = true;
        }
        else if (osName.equals(SOLARIS) || osName.equals(SUNOS))
        {
            _solaris = true;
            _posix = true;
        }
        else if (osName.startsWith(FREEBSD))
        {
            _freebsd = true;
            _posix = true;
        }
        else if (osName.startsWith(OPENBSD))
        {
            _openbsd = true;
            _posix = true;
        }
        else if (osName.startsWith(MAC) || osName.startsWith(DARWIN))
        {
            _macos = true;
            _posix = true;
            _macosx = osName.startsWith(MAC_OS_X);
        }
        else if (osName.startsWith(WINDOWS))
        {
            _windows = true;
            if (osName.equals(WINDOWS_XP))
            {
                _windowsXP = true;
            }
            else if (osName.equals(WINDOWS_VISTA))
            {
                _windowsVista = true;
            }
            else if (osName.startsWith(WINDOWS_2000))
            {
                _windows2000 = true;
            }
            else if (osName.startsWith(WINDOWS_CE))
            {
                _windowsCE = true;
            }
            else if (osName.startsWith(WINDOWS_ME))
            {
                _windowsME = true;
            }
            else if (osName.startsWith(WINDOWS_98SE))
            {
                _windows98SE = true;
            }
            else if (osName.startsWith(WINDOWS_98))
            {
                _windows98 = true;
            }
        }
        else
        {
            _unknown = true;
        }

        this.linux   = _linux;
        this.solaris = _solaris;
        this.freebsd = _freebsd;
        this.openbsd = _openbsd;
        this.macos   = _macos;
        this.macosx  = _macosx;
        this.posix   = _posix;
        
        this.windows       = _windows;
        this.windows98     = _windows98;
        this.windows98SE   = _windows98SE;
        this.windowsME     = _windowsME;
        this.windows2000   = _windows2000;
        this.windowsXP     = _windowsXP;
        this.windowsVista  = _windowsVista;
        this.windowsCE     = _windowsCE;

        this.unknown = _unknown;
    }

    public static OSName getInstance()
    {
        return os;
    }

    public static OSName getInstance(String osName)
    {
        return new OSName(osName);
    }

    public boolean isFreebsd()
    {
        return freebsd;
    }

    public boolean isLinux()
    {
        return linux;
    }

    public boolean isMacos()
    {
        return macos;
    }

    public boolean isMacosx()
    {
        return macosx;
    }

    public boolean isOpenbsd()
    {
        return openbsd;
    }

    public boolean isPosix()
    {
        return posix;
    }

    public boolean isSolaris()
    {
        return solaris;
    }

    public boolean isUnknown()
    {
        return unknown;
    }

    public boolean isWindows()
    {
        return windows;
    }

    public boolean isWindowsCE()
    {
        return windowsCE;
    }

    public boolean isWindowsXP()
    {
        return windowsXP;
    }

    public boolean isWindows2000()
    {
        return windows2000;
    }

    public boolean isWindows98()
    {
        return windows98;
    }

    public boolean isWindows98SE()
    {
        return windows98SE;
    }

    public boolean isWindowsME()
    {
        return windowsME;
    }

    public boolean isWindowsVista()
    {
        return windowsVista;
    }

    public String getName()
    {
        return name;
    }

}
