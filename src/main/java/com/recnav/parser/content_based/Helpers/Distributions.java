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


        this.calculateDistributions(LONG_TIME_DISTRIBUTION);
        this.calculateDistributions(LONG_TIME_USER_DISTRIBUTION);

    }


    public void calculateShortTimeDistribution(){

        Calendar cal = Calendar.getInstance();
        Date too = cal.getTime();
        cal.add(Calendar.HOUR, -shortTimePeriod);
        Date from = cal.getTime();

        List<UserClicks> userClicks = userClicksService.getUserClicksDateRange(from, too);
        countryDistributionService.calculateDistributions(userClicks);
        countryDistributionService.saveDistributions(from, too, SHORT_TIME_DISTRIBUTION);
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
        System.out.println(startString+" "+type);
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

        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, timePeriod), date = start.getTime()) {

            // Do your job here with `date`.
            frame.setTime(date);
            frame.add(Calendar.DATE, timePeriod);
            List<UserClicks> userClicks = userClicksService.getUserClicksDateRange(date, frame.getTime());

            if (type == LONG_TIME_USER_DISTRIBUTION){
                userDistributionService.calculateDistributions(userClicks);
                userDistributionService.saveDistributions(startDate, endDate, type);
            } else{

                countryDistributionService.calculateDistributions(userClicks);
                countryDistributionService.saveDistributions(startDate, endDate, type);
            }

        }
        System.out.println("done");
    }
}
