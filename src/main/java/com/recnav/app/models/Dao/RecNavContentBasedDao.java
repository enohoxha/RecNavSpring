package com.recnav.app.models.Dao;

import com.recnav.app.models.RecNavContentBased;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface RecNavContentBasedDao {

    public void save(RecNavContentBased recNavContentBasedDao);

    public List get(HashMap values, String type);

    void saveAll(ArrayList<RecNavContentBased> recNavContentBaseds);
}
