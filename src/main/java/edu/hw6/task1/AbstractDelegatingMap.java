package edu.hw6.task1;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public abstract class AbstractDelegatingMap implements Map<String, String> {

    private Map<String, String> delegate;


    protected AbstractDelegatingMap(Map<String, String> delegate) {
        this.delegate = delegate;
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public String get(Object key) {
        return delegate.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return delegate.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return delegate.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        delegate.putAll(m);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return delegate.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return delegate.containsValue(value);
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return delegate.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return delegate.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return delegate.entrySet();
    }

    public Map<String, String> getDelegatingMap() {
        return delegate;
    }
}
