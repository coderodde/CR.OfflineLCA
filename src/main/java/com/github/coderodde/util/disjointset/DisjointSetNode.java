package com.github.coderodde.util.disjointset;

/**
 * This class implements the nodes of the disjoint-set data structures.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Sep 4, 2021)
 * @since 1.6 (Sep 4, 2021)
 */
public final class DisjointSetNode<E> {

    /**
     * The actual item being held.
     */
    private final E item;

    /**
     * The parent node of this node.
     */
    private DisjointSetNode<E> parent;

    /**
     * The rank of this node. The rank of a node is its maximum height from any
     * leaf node.
     */
    private int rank;

    /**
     * The size of this node. The size of a node is the number of nodes under
     * it.
     */
    private int size;

    public DisjointSetNode(E item) {
        this.item = item;;
        this.parent = this; 
    }

    public E getItem() {
        return item;
    }

    public DisjointSetNode<E> getParent() {
        return parent;
    }

    public int getRank() {
        return rank;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "[" + item + "]";
    }

    void setParent(DisjointSetNode<E> parent) {
        this.parent = parent;
    }

    void setRank(int rank) {
        this.rank = rank;
    }

    void setSize(int size) {
        this.size = size;
    }
}