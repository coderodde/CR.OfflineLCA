package com.github.coderodde.algo.lca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class Demo {
    
    private static final int TREE_SIZE = 100_000;
    private static final int TREE_DEPTH = 1000;
    private static final int NUMBER_OF_QUERIES = 1000;
    
    public static void main(String[] args) {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        System.out.println("Seed: " + seed);
        GeneralTreeBuilder builder = 
                new GeneralTreeBuilder(
                        TREE_SIZE,
                        TREE_DEPTH,
                        random);
        
        long startTime = System.currentTimeMillis();
        GeneralTree<Integer> tree = builder.getTree();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println(
                "Tree constructed in " + duration + " milliseconds.");
        
        System.out.println("Number of nodes: " + builder.getActualSize());
        
        Map<Integer, GeneralTreeNode<Integer>> nodeMap = computeNodeMap(tree);
        List<LowestCommonAncestorQuery<Integer>> queries = 
                createRandomQueries(
                        builder, 
                        nodeMap,
                        NUMBER_OF_QUERIES, 
                        random);
        
        startTime = System.currentTimeMillis();
        
        List<LowestCommonAncestorResult<Integer>> results =
                new OfflineLowestCommonAncestorComputer<Integer>()
                        .tarjanOffLineLowestCommonAncestors(tree, queries);
        
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println(
                OfflineLowestCommonAncestorComputer.class.getSimpleName()
                        + " in "
                        + duration 
                        + " milliseconds.");
    }
    
    private static GeneralTreeBuilder getGeneralTreeBuilder() {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        
        System.out.println("Seed: " + seed);
        
        GeneralTreeBuilder builder = 
                new GeneralTreeBuilder(
                        TREE_SIZE, 
                        TREE_DEPTH,
                        random);
        
        GeneralTree<Integer> tree = builder.getTree();
        
        System.out.println("Tree size: " + builder.getActualSize());
        return builder;
    }
    
    private static Map<Integer, GeneralTreeNode<Integer>> 
        computeNodeMap(GeneralTree<Integer> tree) {
        Map<Integer, GeneralTreeNode<Integer>> nodeMap = 
                new HashMap<>(2 * TREE_SIZE);
        
        GeneralTreeNode<Integer> rootNode = tree.getRoot();
        nodeMap.put(rootNode.getDatum(), rootNode);
        
        for (GeneralTreeNode<Integer> child : rootNode.getChildren()) {
            computeNodeMapImpl(nodeMap, child);
        }
        
        return nodeMap;
    }
        
    private static void computeNodeMapImpl(
            Map<Integer, GeneralTreeNode<Integer>> nodeMap, 
            GeneralTreeNode<Integer> node) {
        
        nodeMap.put(node.getDatum(), node);
        
        for (GeneralTreeNode<Integer> child : node.getChildren()) {
            computeNodeMapImpl(nodeMap, child);
        }
    }

    private static List<LowestCommonAncestorQuery<Integer>> 
        createRandomQueries(GeneralTreeBuilder builder,
                            Map<Integer, GeneralTreeNode<Integer>> nodeMap,
                            int numberOfQueries,
                            Random random) {
            
        int sz = builder.getActualSize();
        List<LowestCommonAncestorQuery<Integer>> queries = new ArrayList<>();
        
        for (int i = 0; i < numberOfQueries; i++) {
            GeneralTreeNode<Integer> treeNode1 =
                    nodeMap.get(random.nextInt(sz));
            
            GeneralTreeNode<Integer> treeNode2 = 
                    nodeMap.get(random.nextInt(sz));
            
            LowestCommonAncestorQuery<Integer> query = 
                    new LowestCommonAncestorQuery<>(
                            treeNode1, 
                            treeNode2);
            
            queries.add(query);
        }
        
        return queries;
    }
}
