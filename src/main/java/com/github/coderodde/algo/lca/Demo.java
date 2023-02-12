package com.github.coderodde.algo.lca;

import com.github.coderodde.algo.lca.impl.FasterTarjansOfflineLCAAlgorithm;
import com.github.coderodde.algo.lca.impl.TarjansOfflineLCAAlgorithm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * This class implements the demo program/benchmark for comparing performance of
 * the two offline LCA algorithms in this project.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 12, 2023)
 * @since 1.6 (Feb 12, 2023)
 * @author Potilaskone
 */
public final class Demo {
    
    private static final int TREE_SIZE = 1_000_000;
    private static final int TREE_DEPTH = 10000;
    private static final int NUMBER_OF_QUERIES = 2000;
    
    public static void main(String[] args) {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        System.out.println("Seed: " + seed);
        long startTime = System.currentTimeMillis();
        
        GeneralTreeBuilder builder = 
                new GeneralTreeBuilder(
                        TREE_SIZE,
                        TREE_DEPTH,
                        random);
        
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
        
        List<LowestCommonAncestorResult<Integer>> results1 =
                new TarjansOfflineLCAAlgorithm<Integer>()
                        .processQueries(tree, queries);
        
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println(
                TarjansOfflineLCAAlgorithm.class.getSimpleName()
                        + " in "
                        + duration 
                        + " milliseconds.");
        
        startTime = System.currentTimeMillis();
        
        List<LowestCommonAncestorResult<Integer>> results2 =
                new FasterTarjansOfflineLCAAlgorithm<Integer>()
                        .processQueries(tree, queries);
        
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println(
                FasterTarjansOfflineLCAAlgorithm.class.getSimpleName()
                        + " in "
                        + duration 
                        + " milliseconds.");
        
        Set<LowestCommonAncestorResult<Integer>> resultSet1 = 
                new HashSet<>(results1);
        
        Set<LowestCommonAncestorResult<Integer>> resultSet2 = 
                new HashSet<>(results2);
        
        System.out.println("Algorithms agree: " + 
                resultSet1.equals(resultSet2));
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
