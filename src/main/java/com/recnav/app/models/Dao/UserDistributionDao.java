package com.recnav.app.models.Dao;

import com.recnav.app.models.UserDistribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface UserDistributionDao {

    public List<UserDistribution> find(String key, String value);

    public UserDistribution save(UserDistribution ud);

    UserDistribution getLastInsertedItem();

    List<UserDistribution> get(HashMap values, String type);
}
