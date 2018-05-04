package com.recnav.app.models.ServicesImp;

import com.recnav.app.models.CountryDistribution;
import com.recnav.app.models.Dao.CountryDistributionDao;
import com.recnav.app.models.Services.CountryDistributionService;
import com.recnav.app.models.UserClicks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryDistributionServiceImp implements CountryDistributionService {

    @Autowired
    CountryDistributionDao countryDistributionDao;

    @Override
    public void addCountryDistribution(CountryDistribution p) {

    }

    @Override
    public void updateCountryDistribution(CountryDistribution p) {

    }

    @Override
    public List<CountryDistribution> listCountryDistribution() {
        return null;
    }

    @Override
    public CountryDistribution getCountryDistributionById(int id) {
        return null;
    }

    @Override
    public void removeCountryDistribution(int id) {

    }

    @Override
    public CountryDistribution getCountryDistributionByKey(String key) {
        return null;
    }

    @Override
    public void calculateCountryDistribution(List<UserClicks> userClicks) {

    }
    @Transactional
    @Override
    public String getLastInsertedItemDate() {

        CountryDistribution cd = countryDistributionDao.getLastItem();
        if(cd == null){
            return cd.getCreated().toString();
        }
        return null;
    }
}
