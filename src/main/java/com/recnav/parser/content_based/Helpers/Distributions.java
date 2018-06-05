package com.recnav.parser.content_based.Helpers;

import com.recnav.app.models.Services.CountryDistributionService;
import com.recnav.app.models.Services.UserClicksService;
import com.recnav.app.models.Services.UserDistributionService;
import com.recnav.app.models.UserClicks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class Distributions {

    public  static final int timePeriod  = 1;
    public  static final int shortTimePeriod  = 1;
    public static final String LONG_TIME_DISTRIBUTION = "long_time";
    public static final String SHORT_TIME_DISTRIBUTION = "short_time";
    public static final String LONG_TIME_USER_DISTRIBUTION = "long_time_user";
    public static final String SHORT_TIME_USER_DISTRIBUTION = "short_time_user";

    @Autowired
    private CountryDistributionService countryDistributionService;

    @Autowired
    private UserDistributionService userDistributionService;


    @Autowired
    private UserClicksService userClicksService;


    public void calculateLongTimeDistribution() throws ParseException {

        System.out.println("\n Calculateing long time distribution");
        this.calculateDistributions(LONG_TIME_DISTRIBUTION);
        this.calculateDistributions(LONG_TIME_USER_DISTRIBUTION);
        System.out.println("\n Done Calculateing long time distribution");

    }


    public void calculateShortTimeDistribution(){
        System.out.println("\n Calculateing short time distribution");
        Calendar cal = Calendar.getInstance();
        Date too = cal.getTime();
        cal.add(Calendar.HOUR, -shortTimePeriod);
        Date from = cal.getTime();

        List<UserClicks> userClicks = userClicksService.getUserClicksDateRange(from, too);
        countryDistributionService.calculateDistributions(userClicks);
        countryDistributionService.saveDistributions(from, too, SHORT_TIME_DISTRIBUTION);
        System.out.println("\n done Calculateing short time distribution");
    }

    private void calculateDistributions(String type) throws ParseException {

        String startDateFromDb = null;
        if (type == LONG_TIME_USER_DISTRIBUTION){
            startDateFromDb = userDistributionService.getLastInsertedItemDate();
        } else {
            startDateFromDb = countryDistributionService.getLastInsertedItemDate();
        }
        String startString = "";

        if(startDateFromDb != null){
            startString = startDateFromDb;
        } else{
            startString = "2018-02-24 12:27:59";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(startString);
        Date endDate = formatter.parse(formatter.format(new Date()));

        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        //c.add(Calendar.DATE, 1);
        endDate = c.getTime();

        Calendar start = Calendar.getInstance();
        Calendar frame = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        int clickDebug = 0;
        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, timePeriod), date = start.getTime()) {

            // Do your job here with `date`.
            frame.setTime(date);
            frame.add(Calendar.DATE, timePeriod);
            List<UserClicks> userClicks = userClicksService.getUserClicksDateRange(date, frame.getTime());
            clickDebug += userClicks.size();
            System.out.println("Calculate " + type + " between days: " + date + " - " + frame.getTime());
            if (type == LONG_TIME_USER_DISTRIBUTION){
                if(userClicks.size() > 0) {
                    userDistributionService.calculateDistributions(userClicks);
                    userDistributionService.saveDistributions(date, frame.getTime(), type);
                }
            } else{

                if(userClicks.size() > 0){
                    countryDistributionService.calculateDistributions(userClicks);
                    countryDistributionService.saveDistributions(date, frame.getTime(), type);
                }
            }

        }

        System.out.println("Click debug for " + type + ": " + clickDebug);

    }
}
