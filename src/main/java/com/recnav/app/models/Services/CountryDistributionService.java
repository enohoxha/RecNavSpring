package com.recnav.app.models.Services;

import com.recnav.app.models.CountryDistribution;
import com.recnav.app.models.UserClicks;

import java.util.List;

public interface CountryDistributionService {

    public void addCountryDistribution(CountryDistribution p);

    public void updateCountryDistribution(CountryDistribution p);

    public List<CountryDistribution> listCountryDistribution();

    public CountryDistribution getCountryDistributionById(int id);

    public void removeCountryDistribution(int id);

    public CountryDistribution getCountryDistributionByKey(String key);

    public void calculateCountryDistribution(List<UserClicks> userClicks);

    String getLastInsertedItemDate();
}
