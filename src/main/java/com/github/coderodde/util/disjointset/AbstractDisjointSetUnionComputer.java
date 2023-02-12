package com.github.coderodde.util.disjointset;

/**
 * This abstract class defines the API for the union computer algorithms in a 
 * disjoint-set data structure.
 * 
 * @param <E> the satellite date type.
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Sep 5, 2021)
 * @since 1.6 (Sep 5, 2021)
 */
public abstract class AbstractDisjointSetUnionComputer<E> {

    protected DisjointSet<E> ownerDisjointSet;

    /**
     * If both {@code item1} and {@code item2} belong to the same tree, does 
     * nothing. Otherwise, unites the two into a single tree.
     * 
     * @param item1 the first item.
     * @param item2 the second item;
     */
    public abstract void union(E item1, E item2);
}