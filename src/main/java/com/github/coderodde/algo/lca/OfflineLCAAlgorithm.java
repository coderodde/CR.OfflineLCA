package com.github.coderodde.algo.lca;

import java.util.List;

/**
 * This interface defines the API for the offline lowest common ancestors
 * algorithms.
 * 
 * @param <E> the general tree node satellite datum type.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 12, 2023)
 * @since 1.6 (Feb 12, 2023)
 */
public interface OfflineLCAAlgorithm<E> {
    
    /**
     * Process all the LCA queries and returns their respective results.
     * 
     * @param tree    tree to search in.
     * @param queries the list of queries to perform.
     * @return        the list of lowest common ancestors entries.
     */
    public List<LowestCommonAncestorResult<E>> 
        processQueries(GeneralTree<E> tree,
                       List<LowestCommonAncestorQuery<E>> queries);
}
