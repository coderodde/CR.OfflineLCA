package com.github.coderodde.util.disjointset;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class implements the 
 * <a href="https://en.wikipedia.org/wiki/Disjoint-set_data_structure">
 * disjoint-set data structure</a>.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Sep 5, 2021)
 * @since 1.6 (Sep 5, 2021).
 */
public final class DisjointSet<E> {

    private final Map<E, DisjointSetNode<E>> disjointSetMap = new HashMap<>();

    /**
     * The disjoint set root finder.
     */
    private final DisjointSetRootFinder<E> 
            disjointSetRootFinder;

    /**
     * The disjoint set union operation provider.
     */
    private final DisjointSetUnionComputer<E> disjointSetUnionComputer;

    /**
     * Constructs a disjoint-set data structure with specific operation 
     * implementations.
     * 
     * @param disjointSetRootFinder the root finder operation implementation 
     * object.
     * 
     * @param disjointSetUnionComputer the union operation implementation 
     * object.
     */
    public DisjointSet(DisjointSetRootFinder<E> disjointSetRootFinder,
                       DisjointSetUnionComputer<E> disjointSetUnionComputer) {

        this.disjointSetRootFinder = 
                Objects.requireNonNull(
                        disjointSetRootFinder, 
                        "The input DisjointSetRootFinder is null.");

        this.disjointSetUnionComputer = 
                Objects.requireNonNull(
                        disjointSetUnionComputer, 
                        "The input DisjointSetUnionComputer is null.");

        this.disjointSetRootFinder.ownerDisjointSet = this;
        this.disjointSetUnionComputer.ownerDisjointSet = this;
    }

    /**
     * Finds the root of the tree to which {@code item} belongs to.
     * 
     * @param item the target item.
     * @return the root of the tree to which {@code item} belongs to.
     */
    public E find(E item) {
        return disjointSetRootFinder.find(item);
    }

    /**
     * Unites the two trees into one, unless the two items already belong to the
     * same tree.
     * 
     * @param item1 the first item.
     * @param item2 the second item.
     */
    public void union(E item1, E item2) {
        disjointSetUnionComputer.union(item1, item2);
    }

    DisjointSetNode<E> find(DisjointSetNode<E> node) {
        if (node == node.getParent()) {
            return node;
        }

        return find(node.getParent());
    }

    DisjointSetNode<E> getNode(E item) {
        DisjointSetNode<E> node = disjointSetMap.get(item);

        if (node == null) {
            node = new DisjointSetNode<>(item);
            disjointSetMap.put(item, node);
        }

        return node;
    }
}