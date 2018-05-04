package com.recnav.parser.Adapters;

import com.recnav.parser.contract.AlgorithmContract;
import org.springframework.stereotype.Component;

@Component
public class AlgorithmAdapter {

    private AlgorithmContract algorithm;

    public AlgorithmAdapter(AlgorithmContract contract) {
        this.algorithm = contract;
    }


    public void startProcess() {
        this.algorithm.startProcess();
    }
}
