package com.recnav.app.models.ServicesImp;

import com.recnav.app.models.Dao.UserClicksDao;
import com.recnav.app.models.Services.UserClicksService;
import com.recnav.app.models.UserClicks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class UserClicksServiceImp implements UserClicksService {
    @Autowired
    UserClicksDao userClicksDao;

    @Transactional
    @Override
    public void addUserClick(UserClicks userClicks) {
        userClicksDao.addUserClick(userClicks);
    }

    @Transactional
    @Override
    public List<UserClicks> getUserClicks() {
        return userClicksDao.getUserClicks();
    }
}
