package com.airbnb.lottie.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyPath {
    public static final KeyPath COMPOSITION = new KeyPath("COMPOSITION");
    private final List<String> keys;
    private KeyPathElement resolvedElement;

    public KeyPath(String... keys2) {
        this.keys = Arrays.asList(keys2);
    }

    private KeyPath(KeyPath keyPath) {
        this.keys = new ArrayList(keyPath.keys);
        this.resolvedElement = keyPath.resolvedElement;
    }

    public KeyPath addKey(String key) {
        KeyPath newKeyPath = new KeyPath(this);
        newKeyPath.keys.add(key);
        return newKeyPath;
    }

    public KeyPath resolve(KeyPathElement element) {
        KeyPath keyPath = new KeyPath(this);
        keyPath.resolvedElement = element;
        return keyPath;
    }

    public KeyPathElement getResolvedElement() {
        return this.resolvedElement;
    }

    public boolean matches(String key, int depth) {
        if (isContainer(key)) {
            return true;
        }
        if (depth >= this.keys.size()) {
            return false;
        }
        if (this.keys.get(depth).equals(key) || this.keys.get(depth).equals("**") || this.keys.get(depth).equals("*")) {
            return true;
        }
        return false;
    }

    public int incrementDepthBy(String key, int depth) {
        if (isContainer(key)) {
            return 0;
        }
        if (!this.keys.get(depth).equals("**")) {
            return 1;
        }
        if (depth != this.keys.size() - 1 && this.keys.get(depth + 1).equals(key)) {
            return 2;
        }
        return 0;
    }

    public boolean fullyResolvesTo(String key, int depth) {
        if (depth >= this.keys.size()) {
            return false;
        }
        boolean isLastDepth = depth == this.keys.size() - 1;
        String keyAtDepth = this.keys.get(depth);
        if (!keyAtDepth.equals("**")) {
            boolean matches = keyAtDepth.equals(key) || keyAtDepth.equals("*");
            if ((isLastDepth || (depth == this.keys.size() - 2 && endsWithGlobstar())) && matches) {
                return true;
            }
            return false;
        }
        if (!isLastDepth && this.keys.get(depth + 1).equals(key)) {
            if (depth == this.keys.size() - 2 || (depth == this.keys.size() - 3 && endsWithGlobstar())) {
                return true;
            }
            return false;
        } else if (isLastDepth) {
            return true;
        } else {
            if (depth + 1 < this.keys.size() - 1) {
                return false;
            }
            return this.keys.get(depth + 1).equals(key);
        }
    }

    public boolean propagateToChildren(String key, int depth) {
        if (!"__container".equals(key) && depth >= this.keys.size() - 1 && !this.keys.get(depth).equals("**")) {
            return false;
        }
        return true;
    }

    private boolean isContainer(String key) {
        return "__container".equals(key);
    }

    private boolean endsWithGlobstar() {
        List<String> list = this.keys;
        return list.get(list.size() - 1).equals("**");
    }

    public String keysToString() {
        return this.keys.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KeyPath keyPath = (KeyPath) o;
        if (!this.keys.equals(keyPath.keys)) {
            return false;
        }
        KeyPathElement keyPathElement = this.resolvedElement;
        if (keyPathElement != null) {
            return keyPathElement.equals(keyPath.resolvedElement);
        }
        if (keyPath.resolvedElement == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.keys.hashCode() * 31;
        KeyPathElement keyPathElement = this.resolvedElement;
        return hashCode + (keyPathElement != null ? keyPathElement.hashCode() : 0);
    }

    public String toString() {
        return "KeyPath{keys=" + this.keys + ",resolved=" + (this.resolvedElement != null) + '}';
    }
}
