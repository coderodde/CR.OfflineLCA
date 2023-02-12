package com.github.coderodde.algo.lca;

import java.util.List;

/**
 *
 * @author Potilaskone
 */
public interface OfflineLCAAlgorithm<E> {
    
    public List<LowestCommonAncestorResult<E>> 
        processQueries(GeneralTree<E> root,
                       List<LowestCommonAncestorQuery<E>> queries);
}
