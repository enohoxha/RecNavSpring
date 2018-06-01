package com.recnav.app.models.Services;

import com.recnav.app.models.RecNavContentBased;

import java.util.HashMap;
import java.util.List;

public interface RecNavContentBasedService {

    public void save(RecNavContentBased recNavContentBasedDao);

    public List get(HashMap values, String type);
}
