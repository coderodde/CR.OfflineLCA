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
 * This class implements the 
 * <a href="https://en.wikipedia.org/wiki/Tarjan%27s_off-line_lowest_common_ancestors_algorithm">Robert Tarjan's offline lowest common ancestors algorithm</a>.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 12, 2023)
 * @since 1.6 (Feb 12, 2023)
 */
public final class TarjansOfflineLCAAlgorithm<E> 
        implements OfflineLCAAlgorithm<E> {

    private final DisjointSetRootFinder
            <GeneralTreeNode<E>> rootFinder = 
            new DisjointSetRootFinder<>();
    
    private final DisjointSetUnionComputer<GeneralTreeNode<E>> 
            unionComputer = new DisjointSetUnionComputer<>();
    
    public List<LowestCommonAncestorResult<E>> 
        processQueries(GeneralTree<E> root,
                       List<LowestCommonAncestorQuery<E>> queries) {
            
        List<LowestCommonAncestorResult<E>> results = 
                new ArrayList<>(queries.size());
        
        DisjointSet<GeneralTreeNode<E>> disjointSet = 
                new DisjointSet<>(
                        rootFinder,
                        unionComputer);
        
        Map<GeneralTreeNode<E>, GeneralTreeNode<E>> ancestorMap =
                new HashMap<>(queries.size());
        
        Set<GeneralTreeNode<E>> blackSet = 
                new HashSet<>(queries.size());
        
        processQueriesImpl(
                root.getRoot(), 
                results,
                queries,
                disjointSet,
                ancestorMap,
                blackSet);
        
        return results;
    }
        
    private void processQueriesImpl(
            GeneralTreeNode<E> node,
            List<LowestCommonAncestorResult<E>> results,
            List<LowestCommonAncestorQuery<E>> queries,
            DisjointSet<GeneralTreeNode<E>> disjointSet,
            Map<GeneralTreeNode<E>, GeneralTreeNode<E>> ancestorMap,
            Set<GeneralTreeNode<E>> blackSet) {
        
        disjointSet.find(node);
        ancestorMap.put(node, node);
        
        for (GeneralTreeNode<E> child : node.getChildren()) {
            processQueriesImpl(
                    child, 
                    results,
                    queries,
                    disjointSet,
                    ancestorMap,
                    blackSet);
            
            unionComputer.union(node, child);
            ancestorMap.put(rootFinder.find(node), node);
        }
        
        blackSet.add(node);
        
        for (LowestCommonAncestorQuery<E> pair : queries) {
            if (pair.queryContainsNode(node)) {
                GeneralTreeNode<E> v = pair.getOppositeNode(node);
                
                if (blackSet.contains(v)) {
                    LowestCommonAncestorResult<E> result =
                            new LowestCommonAncestorResult<>(
                                    pair.getFirstGeneralTreeNode(),
                                    pair.getSecondGeneralTreeNode(),
                                    ancestorMap.get(
                                            rootFinder.find(v)));
                    
                    results.add(result);
                }
            }
        }
    }
}
