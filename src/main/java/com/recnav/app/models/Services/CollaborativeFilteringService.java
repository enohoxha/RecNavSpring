package com.recnav.app.models.Services;

import com.recnav.app.models.CollaborativeFiltering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface CollaborativeFilteringService {

    public void save(CollaborativeFiltering collaborativeFiltering);

    public List<CollaborativeFiltering> get(HashMap values, String type);

    void saveAll(ArrayList<CollaborativeFiltering> collaborativeFilterings);
}
