package com.github.coderodde.algo.lca;

import java.util.Objects;

/**
 * This class specifies the solution to the LCA (lowest common ancestor) 
 * queries.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 12, 2023)
 * @since 1.6 (Feb 12, 2023)
 */
public final class LowestCommonAncestorResult<E> {
    
    private final GeneralTreeNode<E> queryNode1;
    private final GeneralTreeNode<E> queryNode2;
    private final GeneralTreeNode<E> queryResultNode;
    
    public LowestCommonAncestorResult(GeneralTreeNode<E> queryNode1,
                                      GeneralTreeNode<E> queryNode2,   
                                      GeneralTreeNode<E> queryResultNode) {
        this.queryNode1 = 
                Objects.requireNonNull(
                        queryNode1, 
                        "The first query node is null.");
        
        this.queryNode2 =
                Objects.requireNonNull(
                        queryNode2, 
                        "The second query node is null.");
        
        this.queryResultNode = 
                Objects.requireNonNull(
                        queryResultNode,
                        "The result query node is null.");
    }   

    public GeneralTreeNode<E> getQueryNode1() {
        return queryNode1;
    }

    public GeneralTreeNode<E> getQueryNode2() {
        return queryNode2;
    }

    public GeneralTreeNode<E> getQueryResultNode() {
        return queryResultNode;
    }          
    
    @Override
    public boolean equals(Object o) {
        LowestCommonAncestorResult<E> other = (LowestCommonAncestorResult<E>) o;
        boolean queryNodesMatch =
                getQueryNode1().equals(other.getQueryNode1()) &&
                getQueryNode2().equals(other.getQueryNode2())
                ||
                getQueryNode1().equals(other.getQueryNode2()) &&
                getQueryNode2().equals(other.getQueryNode1());
        
        if (!queryNodesMatch) {
            return false;
        }
        
        return getQueryResultNode().equals(other.getQueryResultNode());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.queryNode1);
        hash = 59 * hash + Objects.hashCode(this.queryNode2);
        hash = 59 * hash + Objects.hashCode(this.queryResultNode);
        return hash;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().append("[(")
                                  .append(queryNode1.getDatum())
                                  .append(", ")
                                  .append(queryNode2.getDatum())
                                  .append(") -> ")
                                  .append(queryResultNode.getDatum())
                                  .append("]")
                                  .toString();
    }
}
