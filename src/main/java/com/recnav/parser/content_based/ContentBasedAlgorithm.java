package com.recnav.parser.content_based;

import com.recnav.app.models.Services.CountryDistributionService;
import com.recnav.app.models.Services.UserClicksService;
import com.recnav.app.models.UserClicks;
import com.recnav.parser.contract.AlgorithmContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class ContentBasedAlgorithm implements AlgorithmContract {

    public  static final int timePeriod  = 1;
    public  static final int shortTimePeriod  = 1;

    @Autowired
    private CountryDistributionService countryDistributionService;

    @Autowired
    private UserClicksService userClicksService;

    public ContentBasedAlgorithm() {

        /*String startDateFromDb = countryDistributionService.getLastInsertedItemDate();

        String startString = "";
        if(startDateFromDb != null){
            startString = startDateFromDb;
        } else{
            startString = "2017-12-20";
        }


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(startString);
        Date endDate = formatter.parse(formatter.format(new Date()));

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, timePeriod), date = start.getTime()) {
            // Do your job here with `date`.
            System.out.println(date);
        }

        countryDistributionService.calculateCountryDistribution(userClicksService.getUserClicks());*/
    }


    @Override
    public void startProcess() {
        String startDateFromDb = countryDistributionService.getLastInsertedItemDate();
        String startString = "";
    }
}
