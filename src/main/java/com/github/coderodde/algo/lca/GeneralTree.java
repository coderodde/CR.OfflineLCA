package com.github.coderodde.algo.lca;

import java.util.Objects;

/**
 * This class implements a general rooted tree.
 * 
 * @param <E> the satellite datum type. Relies on its {@code equals} and 
 *            {@code hashCode}.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 12, 2023)
 * @since 1.6 (Feb 12, 2023)
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
