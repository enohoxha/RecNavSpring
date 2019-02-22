package com.recnav.app.models.ServicesImp;

import com.recnav.app.models.Dao.CategoriesDao;
import com.recnav.app.models.DaoImp.RecNavContentBasedDaoImp;
import com.recnav.app.models.RecNavContentBased;
import com.recnav.app.models.Services.RecNavContentBasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RecNavContentBasedServiceImp implements RecNavContentBasedService{

    @Autowired
    private RecNavContentBasedDaoImp recNavContentBasedDaoImp;

    @Override
    @Transactional
    public void save(RecNavContentBased recNavContentBasedDao) {
        this.recNavContentBasedDaoImp.save(recNavContentBasedDao);
    }

    @Override
    @Transactional
    public List get(HashMap values, String type) {
        return this.recNavContentBasedDaoImp.get(values, type);
    }

    @Override
    @Transactional
    public void saveAll(ArrayList<RecNavContentBased> recNavContentBaseds) {
        this.recNavContentBasedDaoImp.saveAll(recNavContentBaseds);
    }
}
