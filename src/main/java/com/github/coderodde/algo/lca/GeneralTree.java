package com.github.coderodde.algo.lca;

import java.util.Objects;

/**
 *
 * @author Potilaskone
 */
public final class GeneralTree<E> {
    
    private final GeneralTreeNode<E> root;
    
    public GeneralTree(GeneralTreeNode<E> root) {
        this.root = Objects.requireNonNull(
                root, 
                "The root node is null.");
    }
    
    public GeneralTreeNode<E> getRoot() {
        return root;
    }
}
