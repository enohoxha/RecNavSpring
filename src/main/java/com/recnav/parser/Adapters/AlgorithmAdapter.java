package com.recnav.parser.Adapters;

import com.recnav.parser.contract.AlgorithmContract;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class AlgorithmAdapter {


    private AlgorithmContract algorithm;

    public AlgorithmAdapter(@Qualifier("contentBasedAlgorithm") AlgorithmContract contract) {
        this.algorithm = contract;
    }




    public void startProcess() throws Exception {
        this.algorithm.startProcess();
    }
}
