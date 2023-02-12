package com.github.coderodde.algo.lca;

import java.util.Objects;

/**
 *
 * @author Potilaskone
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
    
    public boolean queryContainsNode(GeneralTreeNode<E> node) {
        return Objects.equals(node, firstNode) ||
               Objects.equals(node, secondNode);
    }
    
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
