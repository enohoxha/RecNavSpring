package com.recnav.parser.content_based;


import com.recnav.app.models.Services.CountryDistributionService;
import com.recnav.app.models.Services.RecNavContentBasedService;
import com.recnav.app.models.Services.UserDistributionService;
import com.recnav.parser.content_based.Helpers.Distributions;
import com.recnav.parser.contract.AlgorithmContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ContentBasedAlgorithm implements AlgorithmContract {

    private double similarityCoefficient; //p0(category =  ci | click)

    private final static int VIRTUAL_CLICKS = 10;

    @Autowired
    private Distributions contentBasedAlgorithmImplementation;

    @Autowired
    private CountryDistributionService countryDistributionService;

    @Autowired
    private UserDistributionService userDistributionService;

    @Autowired
    private RecNavContentBasedService recNavContentBasedService;


    public ContentBasedAlgorithm() {
    }


    @Override
    public void startProcess() throws ParseException {
        contentBasedAlgorithmImplementation.calculateLongTimeDistribution();
        this.contentBasedAlgorithmImplementation.calculateShortTimeDistribution();
        this.calculateCoefficients();
    }

    /**
     * Similarity
     *
     * short time country distribution * ({ ϵ[t](total click month <= t) * (user distribution[t] / long time country distribution[t]) } + G )
     *__________________________________________________________________________________________________________________________________________
     *
     *                                                         (ϵ[t](total click month <= t)) + G
     */

    private void calculateCoefficients() {
        this.similarityCoefficient =
                (this.getShortTimeCountryDistribution() * this.getUserPreference())
                        /
                        this.getTotalClick() * VIRTUAL_CLICKS;
    }


    private double getShortTimeCountryDistribution(){
        return 0;
    }

    private double getLongTimeCountryDistribution(){
        return 0;
    }

    private int getTotalClick(){
        return 0;
    }


    private double getUserDistribution(){
        return 0;
    }

    private double getUserPreference(){
        return 0;
    }



}
