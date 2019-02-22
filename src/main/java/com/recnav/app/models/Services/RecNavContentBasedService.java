package com.recnav.app.models.Services;

import com.recnav.app.models.RecNavContentBased;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface RecNavContentBasedService {

    public void save(RecNavContentBased recNavContentBasedDao);

    public List<RecNavContentBased> get(HashMap values, String type);

    void saveAll(ArrayList<RecNavContentBased> recNavContentBaseds);
}
