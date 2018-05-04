package com.recnav.app.models.Dao;

import com.recnav.app.models.CountryDistribution;
import com.recnav.app.models.Users;

import java.util.List;

public interface CountryDistributionDao {

    public void addCountryDistribution(CountryDistribution p);

    public void updateCountryDistribution(CountryDistribution p);

    public List<CountryDistribution> listCountryDistribution();

    public CountryDistribution getCountryDistributionById(int id);

    public void removeCountryDistribution(int id);

    public CountryDistribution getCountryDistributionByKey(String key);

    CountryDistribution getLastItem();
}
