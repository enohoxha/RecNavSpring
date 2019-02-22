package com.recnav.app.models.Services;

import com.recnav.app.models.CountryDistribution;
import com.recnav.app.models.UserClicks;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface CountryDistributionService {

    public void addCountryDistribution(CountryDistribution p);

    public void updateCountryDistribution(CountryDistribution p);

    public List<CountryDistribution> listCountryDistribution();

    public CountryDistribution getCountryDistributionById(int id);

    public void removeCountryDistribution(int id);

    public CountryDistribution getCountryDistributionByKey(String key);

    public void calculateClicksPerLocation(List<UserClicks> userClicks);

    public void calculateDistributions(List<UserClicks> userClicks);

    String getLastInsertedItemDate();

    void cleanValues();

    void saveDistributions(Date start, Date end, String type);

    public List get(HashMap values, String type);

    void deleteAllShort();
}
