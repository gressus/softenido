/*
 *  EqualsHashMap.java
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
package com.softenido.core.equals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author franci
 */
public class EqualsHashMap<K,V> implements Map<K,V>
{
    private final Map<Equals<K>,V> map = new HashMap<Equals<K>,V>();
    final EqualsBuilder<K> wrapper;

    public EqualsHashMap(EqualsBuilder<K> wrapper)
    {
        this.wrapper = wrapper;
    }
    @SuppressWarnings("unchecked")
    private Equals<K> wrap(Object key)
    {
        return wrapper.build((K)key);
    }
    public int size()
    {
        return map.size();
    }

    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    public boolean containsKey(Object key)
    {
        return map.containsKey(wrap(key));
    }

    @SuppressWarnings("element-type-mismatch")
    public boolean containsValue(Object value)
    {
        return map.containsValue(value);
    }

    public V get(Object key)
    {
        return map.get(wrap(key));
    }

    public V put(K key, V value)
    {
        return map.put(wrap((K) key), value);
    }

    public V remove(Object key)
    {
        return map.remove(wrap(key));
    }

    public void putAll(Map<? extends K, ? extends V> m)
    {
         throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear()
    {
        map.clear();
    }

    public Set<K> keySet()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<V> values()
    {
        return map.values();
    }

    public Set<Entry<K, V>> entrySet()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
