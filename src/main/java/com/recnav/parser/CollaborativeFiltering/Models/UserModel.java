package com.recnav.parser.CollaborativeFiltering.Models;

public class UserModel {

    private int userId;
    private double fractionalMembership;
    private double distanceFromCentroid;
    private int[] clicks;

    public UserModel(int userId, double distanceFromCentroid, int[] clicks)
    {
        this.userId = userId;
        this.distanceFromCentroid = distanceFromCentroid;
        this.clicks = clicks;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public double getFractionalMembership()
    {
        return fractionalMembership;
    }

    public void setFractionalMembership(double fractionalMembership)
    {
        this.fractionalMembership = fractionalMembership;
    }

    public int[] getClicks()
    {
        return clicks;
    }

    public void setClicks(int[] clicks)
    {
        this.clicks = clicks;
    }

    public double getDistanceFromCentroid() {
        return distanceFromCentroid;
    }

    public void setDistanceFromCentroid(double distanceFromCentroid) {
        this.distanceFromCentroid = distanceFromCentroid;
    }
}
