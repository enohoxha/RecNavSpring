package com.recnav.app.models.ServicesImp;

import com.recnav.app.models.Dao.CategoriesDao;
import com.recnav.app.models.DaoImp.RecNavContentBasedDaoImp;
import com.recnav.app.models.RecNavContentBased;
import com.recnav.app.models.Services.RecNavContentBasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RecNavContentBasedServiceImp implements RecNavContentBasedService{

    @Autowired
    private RecNavContentBasedDaoImp recNavContentBasedDaoImp;

    @Override
    public void save(RecNavContentBased recNavContentBasedDao) {
        this.recNavContentBasedDaoImp.save(recNavContentBasedDao);
    }

    @Override
    public List get(HashMap values, String type) {
        return this.recNavContentBasedDaoImp.get(values, type);
    }
}
