package com.recnav.app.models.Dao;

import com.recnav.app.models.UserClicks;

import java.util.Date;
import java.util.List;

public interface UserClicksDao {

    public void addUserClick(UserClicks userClicks);

    public List<UserClicks> getUserClicks();

    public List<UserClicks> getUserClicksDateRange(Date start, Date end);


}
