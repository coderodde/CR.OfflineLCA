package com.github.coderodde.algo.lca.impl;

import com.github.coderodde.algo.lca.GeneralTree;
import com.github.coderodde.algo.lca.GeneralTreeNode;
import com.github.coderodde.algo.lca.LowestCommonAncestorQuery;
import com.github.coderodde.algo.lca.LowestCommonAncestorResult;
import com.github.coderodde.algo.lca.OfflineLCAAlgorithm;
import java.util.ArrayList;
import java.util.List;
import com.github.coderodde.util.disjointset.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class improves on the 
 * {@link com.github.coderodde.algo.lca.impl.TarjansOfflineLCAAlgorithm} via 
 * caching queries.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 12, 2023)
 * @since 1.6 (Feb 12, 2023)
 */
public final class FasterTarjansOfflineLCAAlgorithm<E> 
        implements OfflineLCAAlgorithm<E> {

    private final AbstractDisjointSetRootFinder<GeneralTreeNode<E>> rootFinder = 
            new DisjointSetRecursivePathCompressionRootFinder<>();
    
    private final AbstractDisjointSetUnionComputer<GeneralTreeNode<E>> 
            unionComputer = new DisjointSetUnionByRankComputer<>();
    
    public List<LowestCommonAncestorResult<E>> 
        processQueries(GeneralTree<E> tree,
                       List<LowestCommonAncestorQuery<E>> queries) {
            
        List<LowestCommonAncestorResult<E>> lowestCommonAncestorsResults = 
                new ArrayList<>(queries.size());
        
        DisjointSet<GeneralTreeNode<E>> disjointSet = 
                new DisjointSet<>(
                        rootFinder,
                        unionComputer);
        
        Map<GeneralTreeNode<E>, GeneralTreeNode<E>> ancestorMap =
                new HashMap<>(queries.size());
        
        Set<GeneralTreeNode<E>> blackSet = 
                new HashSet<>(queries.size());
        
        Map<GeneralTreeNode<E>, Set<LowestCommonAncestorQuery<E>>> nodePairMap = 
                new HashMap<>();
        
        loadPairMap(nodePairMap, queries);
        
        tarjanOffLineLowestCommonAncestors(
                tree.getRoot(), 
                lowestCommonAncestorsResults,
                queries,
                disjointSet,
                ancestorMap,
                nodePairMap,
                blackSet);
        
        return lowestCommonAncestorsResults;
    }
        
    private void tarjanOffLineLowestCommonAncestors(
            GeneralTreeNode<E> node,
            List<LowestCommonAncestorResult<E>> lowestCommonAncestorsList,
            List<LowestCommonAncestorQuery<E>> queries,
            DisjointSet<GeneralTreeNode<E>> disjointSet,
            Map<GeneralTreeNode<E>, GeneralTreeNode<E>> ancestorMap,
            Map<GeneralTreeNode<E>, 
                Set<LowestCommonAncestorQuery<E>>> nodePairMap,
            Set<GeneralTreeNode<E>> blackSet) {
        
        disjointSet.find(node);
        ancestorMap.put(node, node);
        
        for (GeneralTreeNode<E> child : node.getChildren()) {
            tarjanOffLineLowestCommonAncestors(
                    child, 
                    lowestCommonAncestorsList,
                    queries,
                    disjointSet,
                    ancestorMap,
                    nodePairMap,
                    blackSet);
            
            unionComputer.union(node, child);
            ancestorMap.put(rootFinder.find(node), node);
        }
        
        blackSet.add(node);
        
        Set<LowestCommonAncestorQuery<E>> pairSet = nodePairMap.get(node);
        
        if (pairSet == null) {
            // Guard against the null values in the below for each loop.
            return;
        }
        
        for (LowestCommonAncestorQuery<E> pair : nodePairMap.get(node)) {
            if (pair.queryContainsNode(node)) {
                GeneralTreeNode<E> v = pair.getOppositeNode(node);
                
                if (blackSet.contains(v)) {
                    LowestCommonAncestorResult<E> result =
                            new LowestCommonAncestorResult<>(
                                    pair.getFirstGeneralTreeNode(),
                                    pair.getSecondGeneralTreeNode(),
                                    ancestorMap.get(
                                            rootFinder.find(v)));
                    
                    lowestCommonAncestorsList.add(result);
                }
            }
        }
    }
    
    private static <E> void loadPairMap(
            Map<GeneralTreeNode<E>, Set<LowestCommonAncestorQuery<E>>> pairMap, 
            List<LowestCommonAncestorQuery<E>> queries) {
        
        for (LowestCommonAncestorQuery<E> query : queries) {
            GeneralTreeNode<E> firstNode = query.getFirstGeneralTreeNode();
            GeneralTreeNode<E> secondNode = query.getSecondGeneralTreeNode();
            
            if (!pairMap.containsKey(firstNode)) {
                pairMap.put(firstNode, new HashSet<>());
            }
            
            pairMap.get(firstNode).add(query);
            
            if (!pairMap.containsKey(secondNode)) {
                pairMap.put(secondNode, new HashSet<>());
            }
            
            pairMap.get(secondNode).add(query);
        }
    }
}