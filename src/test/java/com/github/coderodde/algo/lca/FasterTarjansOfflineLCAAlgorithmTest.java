package com.github.coderodde.algo.lca;

import com.github.coderodde.algo.lca.impl.FasterTarjansOfflineLCAAlgorithm;

public class FasterTarjansOfflineLCAAlgorithmTest 
extends AbstractOfflineLowestCommonAncestorComputerTest {
    
    public FasterTarjansOfflineLCAAlgorithmTest() {
        super(new FasterTarjansOfflineLCAAlgorithm<>());
    }
}