package com.recnav.app.models.ServicesImp;

import com.recnav.app.models.Dao.UserDistributionDao;
import com.recnav.app.models.Services.UserDistributionService;
import com.recnav.app.models.UserDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDistributionServiceImp implements UserDistributionService{

    @Autowired
    UserDistributionDao userDistributionDao;

    @Override
    public List<UserDistribution> find(String key, String value) {
        return null;
    }

    @Override
    public UserDistribution save(UserDistribution ud) {
        return null;
    }
}
