package com.github.coderodde.algo.lca;

import java.util.Objects;

/**
 * This class implements the LCA query.
 * 
 * @param <E> the satellite datum type.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 12, 2023)
 * @since 1.6 (Feb 12, 2023)
 */
public final class LowestCommonAncestorQuery<E> {
    
    private final GeneralTreeNode<E> firstNode;
    private final GeneralTreeNode<E> secondNode;
    
    public LowestCommonAncestorQuery(GeneralTreeNode<E> firstNode,
                                     GeneralTreeNode<E> secondNode) {
        this.firstNode = Objects.requireNonNull(
                firstNode, 
                "The first node is null.");
        
        this.secondNode = Objects.requireNonNull(
                secondNode, 
                "The second node is null.");
    }
    
    public GeneralTreeNode<E> getFirstGeneralTreeNode() {
        return firstNode;
    }
    
    public GeneralTreeNode<E> getSecondGeneralTreeNode() {
        return secondNode;
    }
    
    /**
     * Checks that the input node is contained in this query.
     * 
     * @param node the node to check for inclusion.
     * @return {@code true} if and only if the input node is in this query.
     */
    public boolean queryContainsNode(GeneralTreeNode<E> node) {
        return Objects.equals(node, firstNode) ||
               Objects.equals(node, secondNode);
    }
    
    /**
     * If the input node belongs to this query, returns the other node. 
     * Otherwise, throws an exception.
     * 
     * @throws IllegalStateException if this query does not mention 
     *                               {@code node}.
     * 
     * @param node the node whose opposite node to return.
     * @return the opposite node.
     */
    public GeneralTreeNode<E> getOppositeNode(GeneralTreeNode<E> node) {
        if (node.equals(firstNode)) {
            return secondNode;
        }
        
        if (node.equals(secondNode)) {
            return firstNode;
        }
        
        throw new IllegalStateException("This should not happen.");
    }
    
    @Override
    public String toString() {
        return new StringBuilder().append("[")
                                  .append(firstNode.getDatum().toString())
                                  .append(", ")
                                  .append(secondNode.getDatum().toString())
                                  .append("]")
                                  .toString();
    }
}
