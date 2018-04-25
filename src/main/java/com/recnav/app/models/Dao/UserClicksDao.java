package com.recnav.app.models.Dao;

import com.recnav.app.models.UserClicks;

import java.util.List;

public interface UserClicksDao {

    public void addUserClick(UserClicks userClicks);

    public List<UserClicks> getUserClicks();

}
