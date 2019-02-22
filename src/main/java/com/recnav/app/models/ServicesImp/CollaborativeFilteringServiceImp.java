package com.recnav.app.models.ServicesImp;

import com.recnav.app.models.CollaborativeFiltering;
import com.recnav.app.models.DaoImp.CollaborativeFilteringDaoImp;
import com.recnav.app.models.Services.CollaborativeFilteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CollaborativeFilteringServiceImp implements CollaborativeFilteringService {

    @Autowired
    private CollaborativeFilteringDaoImp collaborativeFilteringDaoImp;

    @Override
    @Transactional
    public void save(CollaborativeFiltering collaborativeFiltering) {
        this.collaborativeFilteringDaoImp.save(collaborativeFiltering);
    }

    @Override
    @Transactional
    public List get(HashMap values, String type) {
        return this.collaborativeFilteringDaoImp.get(values, type);
    }

    @Override
    @Transactional
    public void saveAll(ArrayList<CollaborativeFiltering> collaborativeFilterings) {
        this.collaborativeFilteringDaoImp.saveAll(collaborativeFilterings);
    }
}
