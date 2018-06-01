package com.recnav.parser.content_based;


import com.recnav.parser.content_based.Helpers.Distributions;
import com.recnav.parser.contract.AlgorithmContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ContentBasedAlgorithm implements AlgorithmContract {


    @Autowired
    private Distributions contentBasedAlgorithmImplementation;

    public ContentBasedAlgorithm() {
    }


    @Override
    public void startProcess() throws ParseException {
        this.contentBasedAlgorithmImplementation.calculateShortTimeDistribution();
        contentBasedAlgorithmImplementation.calculateLongTimeDistribution();
    }



}
