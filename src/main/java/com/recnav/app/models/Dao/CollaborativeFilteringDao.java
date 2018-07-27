package com.recnav.app.models.Dao;

import com.recnav.app.models.CollaborativeFiltering;
import com.recnav.app.models.RecNavContentBased;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface CollaborativeFilteringDao {

    public void save(CollaborativeFiltering collaborativeFiltering);

    public List get(HashMap values, String type);

    void saveAll(ArrayList<CollaborativeFiltering> collaborativeFiltering);

}
