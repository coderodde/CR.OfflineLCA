package com.github.coderodde.util.disjointset;

/**
 * This abstract class defines the API for for root finding algorithms in a 
 * disjoint-set data structure.
 * 
 * @param <E> the item data type.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Sep 5, 2021)
 * @since 1.6 (Sep 5, 2021)
 */
public abstract class AbstractDisjointSetRootFinder<E> {

    protected DisjointSet<E> ownerDisjointSet;

    /**
     * If {@code item} belongs to a tree, returns the root of that three. 
     * Otherwise, a trivial, empty tree {@code T} is created, and {@code item} 
     * is added to {@code T}.
     * 
     * @param item the query item.
     * @return the root of the tree to which {@code item} belongs to.
     */
    public abstract E find(E item);
}