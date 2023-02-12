package com.github.coderodde.algo.lca;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class implements a multibranched node in a general tree.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 12, 2023)
 * @since 1.6 (Feb 12, 2023)
 */
public final class GeneralTreeNode<E> {
    
    /**
     * The set of child nodes.
     */
    private final Set<GeneralTreeNode<E>> children = new HashSet<>();
    
    /**
     * The satellite datum this node holds.
     */
    private final E datum;
    
    public GeneralTreeNode(E datum) {
        this.datum = Objects.requireNonNull(
                datum, 
                "The input datum is null.");
    }
    
    /**
     * Adds {@code child} to the child list of this node. This operation cannot
     * prevent directed cycles in the tree.
     * 
     * @param child the child node to add.
     */
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
