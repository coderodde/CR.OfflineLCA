package com.github.coderodde.algo.lca;

import java.util.Random;

/**
 * This class attempts to build a general tree with {@code requestedSize} nodes.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 12, 2023)
 * @since 1.6 (Feb 12, 2023)
 */
public final class GeneralTreeBuilder {
    
    private int id;
    private int actualSize = 1; // 1 for the root.
    private final int requestedSize;
    private final Random random;
    private final GeneralTree<Integer> tree = 
            new GeneralTree<>(new GeneralTreeNode<>(0));
    
    public GeneralTreeBuilder(int size, int depth, Random random) {
        this.requestedSize = size;
        this.random = random;
        buildImpl(tree.getRoot(), depth);
    }
    
    public GeneralTree<Integer> getTree() {
        return tree;
    }
    
    public int getActualSize() {
        return actualSize;
    }
    
    private void buildImpl(GeneralTreeNode<Integer> parent, int depth) {
        
        if (depth == -1 || actualSize >= requestedSize) {
            return;
        }
        
        int branches = random.nextInt(4, 10);
        
        for (int i = 0; i < branches; i++) {
            if (actualSize >= requestedSize) {
                return;
            }
            
            GeneralTreeNode<Integer> child = new GeneralTreeNode<>(++id);
            parent.addChild(child);
            actualSize++;
            
            buildImpl(child, depth - 1);
        }
    }
}
