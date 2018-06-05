package com.recnav.parser;

import com.recnav.parser.Adapters.AlgorithmAdapter;
import com.recnav.parser.content_based.ContentBasedAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class RecommendationAlgorithm {

    @Autowired
    ContentBasedAlgorithm contentBasedAlgorithm;

    @Scheduled(cron = "0 * * * * *")
    public void reportCurrentTime() throws ParseException {
        System.out.println("\n\n******************** Starting Algorithm *********************");
        AlgorithmAdapter adapter = new AlgorithmAdapter(contentBasedAlgorithm);
        adapter.startProcess();

    }

}
