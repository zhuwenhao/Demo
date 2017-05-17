package com.zhuwenhao.demo.entity;

import com.google.gson.annotations.SerializedName;

public class BandwagonQuota {

    @SerializedName("occupied_kb")
    private String occupiedKb;

    @SerializedName("softlimit_kb")
    private String softLimitKb;

    @SerializedName("hardlimit_kb")
    private String hardLimitKb;

    @SerializedName("occupied_inodes")
    private String occupiedINodes;

    @SerializedName("softlimit_inodes")
    private String softLimitINodes;

    @SerializedName("hardlimit_inodes")
    private String hardLimitINodes;

    public String getOccupiedKb() {
        return occupiedKb;
    }

    public void setOccupiedKb(String occupiedKb) {
        this.occupiedKb = occupiedKb;
    }

    public String getSoftLimitKb() {
        return softLimitKb;
    }

    public void setSoftLimitKb(String softLimitKb) {
        this.softLimitKb = softLimitKb;
    }

    public String getHardLimitKb() {
        return hardLimitKb;
    }

    public void setHardLimitKb(String hardLimitKb) {
        this.hardLimitKb = hardLimitKb;
    }

    public String getOccupiedINodes() {
        return occupiedINodes;
    }

    public void setOccupiedINodes(String occupiedINodes) {
        this.occupiedINodes = occupiedINodes;
    }

    public String getSoftLimitINodes() {
        return softLimitINodes;
    }

    public void setSoftLimitINodes(String softLimitINodes) {
        this.softLimitINodes = softLimitINodes;
    }

    public String getHardLimitINodes() {
        return hardLimitINodes;
    }

    public void setHardLimitINodes(String hardLimitINodes) {
        this.hardLimitINodes = hardLimitINodes;
    }
}
