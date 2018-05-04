package com.recnav.app.models.Services;

import com.recnav.app.models.UserClicks;

import java.util.List;

public interface UserClicksService {

    public void addUserClick(UserClicks userClicks);

    public List<UserClicks> getUserClicks();

    List<UserClicks> getUserClicksDateRange(String start, String end);

}
