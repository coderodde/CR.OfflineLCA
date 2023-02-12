package com.github.coderodde.algo.lca.impl;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.github.coderodde.algo.lca.*; 

public abstract class AbstractOfflineLCAAlgorithmTest {

    protected final OfflineLCAAlgorithm<Integer> algorithm;
    
    public AbstractOfflineLCAAlgorithmTest(
            OfflineLCAAlgorithm<Integer> algorithm) {
        this.algorithm = algorithm;
    }
    
    @Test
    public void test1() {
        GeneralTreeNode<Integer> node1 = new GeneralTreeNode<>(1);
        GeneralTreeNode<Integer> node2 = new GeneralTreeNode<>(2);
        GeneralTreeNode<Integer> node3 = new GeneralTreeNode<>(3);
        GeneralTreeNode<Integer> node4 = new GeneralTreeNode<>(4);
        GeneralTreeNode<Integer> node5 = new GeneralTreeNode<>(5);
        GeneralTreeNode<Integer> node6 = new GeneralTreeNode<>(6);
        GeneralTreeNode<Integer> node7 = new GeneralTreeNode<>(7);
        GeneralTreeNode<Integer> node8 = new GeneralTreeNode<>(8);
        
        node1.addChild(node2);
        node1.addChild(node3);
        
        node2.addChild(node4);
        node2.addChild(node5);
        
        node3.addChild(node6);
        node3.addChild(node7);
        node3.addChild(node8);
        
        GeneralTree<Integer> tree = new GeneralTree<>(node1);
        
        LowestCommonAncestorQuery<Integer> query1 = 
                new LowestCommonAncestorQuery<>(node4, node5);
        
        LowestCommonAncestorQuery<Integer> query2 = 
                new LowestCommonAncestorQuery<>(node7, node6);
        
        LowestCommonAncestorQuery<Integer> query3 = 
                new LowestCommonAncestorQuery<>(node5, node3);
        
        List<LowestCommonAncestorQuery<Integer>> queries =
                Arrays.asList(query2, query3, query1);
        
        List<LowestCommonAncestorResult<Integer>> results = 
                algorithm.processQueries(tree, queries);
        
        assertEquals(3, results.size());
        
        LowestCommonAncestorResult<Integer> result1 = 
                new LowestCommonAncestorResult<>(
                        node4,
                        node5, 
                        node2);
        
        LowestCommonAncestorResult<Integer> result2 = 
                new LowestCommonAncestorResult<>(
                        node6,
                        node7, 
                        node3);
        
        LowestCommonAncestorResult<Integer> result3 = 
                new LowestCommonAncestorResult<>(
                        node5,
                        node3, 
                        node1);
        
        assertTrue(results.contains(result1));
        assertTrue(results.contains(result2));
        assertTrue(results.contains(result3));
    }
    
    @Test
    public void test2() {
        GeneralTreeNode<Integer> node1 = new GeneralTreeNode<>(1);
        GeneralTreeNode<Integer> node2 = new GeneralTreeNode<>(2);
        GeneralTreeNode<Integer> node3 = new GeneralTreeNode<>(3);
        
        node1.addChild(node2);
        node1.addChild(node3);
        
        GeneralTree<Integer> tree = new GeneralTree<>(node1);
        
        LowestCommonAncestorQuery<Integer> query1 = 
                new LowestCommonAncestorQuery<>(node1, node2);
        
        LowestCommonAncestorQuery<Integer> query2 = 
                new LowestCommonAncestorQuery<>(node2, node3);
        
        List<LowestCommonAncestorQuery<Integer>> queries =
                Arrays.asList(query2,query1);
        
        List<LowestCommonAncestorResult<Integer>> results = 
                algorithm.processQueries(tree, queries);
                
        assertEquals(2, results.size());
        
        LowestCommonAncestorResult<Integer> result1 = 
                new LowestCommonAncestorResult<>(
                        node1,
                        node2, 
                        node1);
        
        LowestCommonAncestorResult<Integer> result2 = 
                new LowestCommonAncestorResult<>(
                        node3,
                        node2, 
                        node1);
        
        assertTrue(results.contains(result1));
        assertTrue(results.contains(result2));
    }
    
    @Test
    public void sameQueryNodes() {
        GeneralTreeNode<Integer> node1 = new GeneralTreeNode<>(1);
        GeneralTreeNode<Integer> node2 = new GeneralTreeNode<>(2);
        GeneralTreeNode<Integer> node3 = new GeneralTreeNode<>(3);
        
        node1.addChild(node2);
        node2.addChild(node3);
        
        GeneralTree<Integer> tree = new GeneralTree<>(node1);
        
        List<LowestCommonAncestorResult<Integer>> results = 
                algorithm.processQueries(
                        tree,
                        Arrays.asList(
                                new LowestCommonAncestorQuery<Integer>(
                                        node2,
                                        node2)));
        
        assertEquals(1, results.size());
        
        LowestCommonAncestorResult<Integer> result = 
                new LowestCommonAncestorResult<>(node2, node2, node2);
        
        assertEquals(result, results.get(0));
    }
}
