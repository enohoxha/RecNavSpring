package com.recnav.parser.Adapters;

import com.recnav.parser.contract.AlgorithmContract;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class AlgorithmAdapter {

    private AlgorithmContract algorithm;

    public AlgorithmAdapter(AlgorithmContract contract) {
        this.algorithm = contract;
    }


    public void startProcess() throws ParseException {
        this.algorithm.startProcess();
    }
}
