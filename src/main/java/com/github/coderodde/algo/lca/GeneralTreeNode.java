package com.github.coderodde.algo.lca;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Potilaskone
 */
public final class GeneralTreeNode<E> {
    
    private final Set<GeneralTreeNode<E>> children = new HashSet<>();
    private final E datum;
    
    public GeneralTreeNode(E datum) {
        this.datum = Objects.requireNonNull(
                datum, 
                "The input datum is null.");
    }
    
    public void addChild(GeneralTreeNode<E> child) {
        children.add(child);
    }
    
    public E getDatum() {
        return datum;
    }
    
    public Collection<GeneralTreeNode<E>> getChildren() {
        return Collections.<GeneralTreeNode<E>>unmodifiableSet(children);
    }
    
    @Override
    public boolean equals(Object o) {
        GeneralTreeNode<E> other = (GeneralTreeNode<E>) o;
        return datum.equals(other.datum);
    }
    
    @Override
    public int hashCode() {
        return datum.hashCode();
    }
    
    @Override
    public String toString() {
        return new StringBuilder().append("[")
                                  .append(datum.toString())
                                  .append("]")
                                  .toString();
    }
}
