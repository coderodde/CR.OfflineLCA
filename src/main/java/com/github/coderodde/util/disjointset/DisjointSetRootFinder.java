package com.github.coderodde.util.disjointset;

/**
 * This class implements the root finding algorithm for the disjoint-set data
 * structure using recursive path compression.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Sep 5, 2021)
 * @since 1.6 (Sep 5, 2021)
 */
public final class DisjointSetRootFinder<E> {

    protected DisjointSet<E> ownerDisjointSet;
    
    public E find(E item) {
        DisjointSetNode<E> node = 
                ownerDisjointSet.find(ownerDisjointSet.getNode(item));

        if (node == node.getParent()) {
            return node.getItem();
        }

        node.setParent(ownerDisjointSet.find(node.getParent()));
        return node.getParent().getItem();
    }
}
