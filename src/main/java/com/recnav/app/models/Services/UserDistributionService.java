package com.recnav.app.models.Services;

import com.recnav.app.models.UserDistribution;

import java.util.List;

public interface UserDistributionService {

    public List<UserDistribution> find(String key, String value);

    public UserDistribution save(UserDistribution ud);
}
