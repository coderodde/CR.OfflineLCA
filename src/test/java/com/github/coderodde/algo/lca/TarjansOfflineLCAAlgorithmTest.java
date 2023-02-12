package com.github.coderodde.algo.lca;

import com.github.coderodde.algo.lca.impl.TarjansOfflineLCAAlgorithm;

public class TarjansOfflineLCAAlgorithmTest
extends AbstractOfflineLowestCommonAncestorComputerTest {
    
    public TarjansOfflineLCAAlgorithmTest() {
        super(new TarjansOfflineLCAAlgorithm<>());
    }
}
