package com.recnav.app.models.Services;

import com.recnav.app.models.UserClicks;
import com.recnav.app.models.UserDistribution;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface UserDistributionService {

    public List<UserDistribution> find(String key, String value);

    public UserDistribution save(UserDistribution ud);

    void calculateDistributions(List<UserClicks> userClicks);

    void saveDistributions(Date startDate, Date endDate, String type);

    public String getLastInsertedItemDate();

    public List<UserDistribution> get(HashMap values, String type);
}
