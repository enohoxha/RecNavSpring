package com.recnav.parser;

import com.recnav.parser.Adapters.AlgorithmAdapter;
import com.recnav.parser.CollaborativeFiltering.CollaborativeFilteringAlgorithm;
import com.recnav.parser.content_based.ContentBasedAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class RecommendationAlgorithm {

    @Autowired
    ContentBasedAlgorithm contentBasedAlgorithm;

    @Autowired
    CollaborativeFilteringAlgorithm collaborativeFilteringAlgorithm;

    @Scheduled(cron = "15 17 * * * *")
    public void reportCurrentTime() throws Exception {
        System.out.println("\n\n******************** Starting Content Based Algorithm *********************");
        AlgorithmAdapter adapter = new AlgorithmAdapter(contentBasedAlgorithm);
        double start = System.currentTimeMillis();
        adapter.startProcess();
        double end = System.currentTimeMillis();
        System.out.println("End of algorithm in : " + (end - start));
    }

    @Scheduled(cron = "0 * * * * *")
    public void startCollaborativeFiltering() throws Exception {
       AlgorithmAdapter adapter = new AlgorithmAdapter(collaborativeFilteringAlgorithm);

       System.out.println("\n\n******************** Starting Collaborative Filtering Algorithm *********************");
       double start = System.currentTimeMillis();
       adapter.startProcess();
       double end = System.currentTimeMillis();
       System.out.println("End of algorithm in : " + (end - start));

    }

}
