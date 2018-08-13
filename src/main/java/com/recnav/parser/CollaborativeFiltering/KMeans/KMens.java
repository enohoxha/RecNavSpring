package com.recnav.parser.CollaborativeFiltering.KMeans;

import com.recnav.parser.CollaborativeFiltering.Models.ClusterModel;
import com.recnav.parser.CollaborativeFiltering.Models.UserModel;

import java.util.*;

public class KMens {

    // The K value of K-Means
    private int numberOfClassifiers;

    // Generated Clusters from K-Mens
    private ClusterModel[] clusters;

    // The input dataset
    private HashMap<Integer, int[]> usersSet;

    private int[] clusterFingerprint;

    /**
     * This parameters are taken from main method that will use K-Means
     * @param numberOfClassifiers
     * @param usersSet
     */
    public KMens(int numberOfClassifiers, HashMap<Integer, int[]> usersSet)
    {
        this.numberOfClassifiers = numberOfClassifiers;
        this.usersSet = usersSet;
        clusterFingerprint = new int[usersSet.size()];
        Arrays.fill(clusterFingerprint, -1);
    }


    /**
     * Start clustering data algorithm
     */
    public ClusterModel[] startClusteringData()
    {
        boolean changesInClusters = true;
        int loops = 0;
        int i = 0;

        clusters = createClusters();
        while (changesInClusters && loops < 70) {

            // Suppose that clusters has not changes
            changesInClusters = false;
            loops++;
            i = 0;

            for (Map.Entry<Integer, int[]> entry : usersSet.entrySet()) {

                // get both key-value entity
                Integer userId = entry.getKey();
                int[] clicks = entry.getValue();

                // get nearest cluster with a point
                ClusterModel cluster = getNearestCluster(userId, clicks);

                if (clusterFingerprint[i] != cluster.getClusterId()){

                    this.changeUsers(cluster, userId, clicks, i);
                    changesInClusters = true;
                    //cluster.calculateCentroids();
                }
                i++;
            }
            for (int j = 0; j < clusters.length; j++) {
                clusters[j].calculateCentroids();
            }

        }

        return clusters;
    }

    /**
     * Add user to a cluster
     * @param cluster
     * @param userId
     * @param clicks
     */
    private void changeUsers(ClusterModel cluster, Integer userId, int[] clicks, int i)
    {
        // check if user have been clustered before
        if(clusterFingerprint[i] != -1){
            clusters[clusterFingerprint[i]].removeUserIfExist(userId);
        }

        clusterFingerprint[i] = cluster.getClusterId();
        double minimalCentroidDistance = cluster.minDistanceFromCenteroid(userId, clicks);
        UserModel userModel = new UserModel(userId, minimalCentroidDistance, clicks);
        userModel.setFractionalMembership((double) 1 - minimalCentroidDistance);
        cluster.setUsers(userModel);
    }


    /**
     * Create initial random k clusters
     * @return initial clusters
     */
    private ClusterModel[] createClusters()
    {
        ClusterModel[] clusterModels = new ClusterModel[numberOfClassifiers];
        List<Integer> keysAsArray = new ArrayList(usersSet.keySet());
        Random random = new Random();
        double centroidDistance = 0;
        boolean loop = true;
        int attempt = 0;
        // init clusters
        while (loop && attempt < 10){
            loop = false;
            for (int i= 0; i < numberOfClassifiers; i++){
                clusterModels[i] = new ClusterModel(i);
                int randomId = random.nextInt(keysAsArray.size());
                UserModel userModel = new UserModel(keysAsArray.get(randomId),0, usersSet.get(keysAsArray.get(randomId)));
                userModel.setFractionalMembership(1);
                clusterModels[i].setUsers(userModel);
                clusterModels[i].setCentroid(userModel);
            }

            for (int i= 0; i < numberOfClassifiers - 1; i++){
                for (int j = i + 1; j < numberOfClassifiers; j++){
                    if(clusterModels[i].minDistanceFromCenteroid(clusterModels[i].getCentroid().getUserId(), clusterModels[j].getCentroid().getClicks()) != 1){
                        loop = true;
                        attempt++;
                    }
                }
            }

        }

        return clusterModels;
    }

    /**
     * Return nearest cluster for a point in space
     * @param userId
     * @param clicks
     * @return
     */
    private ClusterModel getNearestCluster(Integer userId, int[] clicks)
    {
        //Cluster null at first
        ClusterModel cluster = null;

        //Get a min value (very large)
        double min = Integer.MAX_VALUE;

        for(int i = 0; i < clusters.length; i++){

            float distance = clusters[i].minDistanceFromCenteroid(userId, clicks);

            if (min > distance){
                min = distance;
                cluster = clusters[i];
            }
        }

        return cluster;
    }




}
