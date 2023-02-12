package com.github.coderodde.util.disjointset;

/**
 * This class implements the union operation by rank.
 * 
 * @param <E> the satellite data type.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Sep 5, 2021)
 * @since 1.6 (Sep 5, 2021)
 */
public final class DisjointSetUnionByRankComputer<E> 
extends AbstractDisjointSetUnionComputer<E> {

    /**
     * {@inheritDoc }
     */
    @Override
    public void union(E item1, E item2) {
        DisjointSetNode<E> node1 = 
                    ownerDisjointSet.find(ownerDisjointSet.getNode(item1));

        DisjointSetNode<E> node2 = 
                ownerDisjointSet.find(ownerDisjointSet.getNode(item2));

        if (node1 == node2) {
            return;
        }

        if (node1.getRank() < node2.getRank()) {
            DisjointSetNode<E> tempNode = node1;
            node1 = node2;
            node2 = tempNode;
        }

        node2.setParent(node1);

        if (node1.getRank() == node2.getRank()) {
            node1.setRank(node1.getRank() + 1);
        }
    }
}
