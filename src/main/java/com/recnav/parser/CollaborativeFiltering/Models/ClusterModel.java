package com.recnav.parser.CollaborativeFiltering.Models;

import java.util.*;

public class ClusterModel {

    private int clusterId;
    private UserModel centroid;
    private HashMap<Integer, UserModel> users;

    public ClusterModel(int clusterId) {
        users = new HashMap<>();
        this.clusterId = clusterId;
    }

    public int getClusterId() {
        return clusterId;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    public HashMap<Integer, UserModel> getUsers() {
        return users;
    }

    public void setUsers(UserModel user) {
        this.users.put(user.getUserId(), user);
    }

    public float minDistanceFromCenteroid(Integer userId, int[] clicks) {
        return this.calculateJackardDistance(centroid.getClicks(), clicks);
    }

    private float calculateJackardDistance(int[] set1, int[] set2) {
        int intersection = 0;
        int union = 0;

        for (int i = 0; i < set1.length; i++) {
            for (int j = 0; j < set2.length; j++) {
                if (set1[i] == set2[j]) {
                    intersection++;
                    break;
                }
            }
        }

        union = (set1.length + set2.length) - intersection;
        float coefficent = (float) intersection / union;
        if (coefficent > 1) {
            System.out.println("");
        }
        return 1 - coefficent;
    }

    public void removeUserIfExist(int userId) {
        users.remove(userId);
    }

    public UserModel getCentroid() {
        return centroid;
    }

    public void setCentroid(UserModel centroid) {
        this.centroid = centroid;
    }

    public void calculateCentroids() {
        double minDerivation = Integer.MAX_VALUE;
        UserModel centroid = null;
        double average = this.findAverage();


        // get both key-value entity
        for (Map.Entry<Integer, UserModel> entry : users.entrySet()) {
            // do not check with his self

            double membership = entry.getValue().getDistanceFromCentroid();
            double derivation = Math.abs(average - membership);
            if (minDerivation > derivation) {
                minDerivation = derivation;
                centroid = entry.getValue();
            }


        }
        if (centroid != null) {
            this.centroid = centroid;
            this.centroid.setDistanceFromCentroid(0);
        }
    }

    private double findAverage() {
        double sum = 0;
        for (Map.Entry<Integer, UserModel> entry : users.entrySet()) {
            sum += entry.getValue().getDistanceFromCentroid();
        }

        return (double) sum / this.users.size();
    }
}
