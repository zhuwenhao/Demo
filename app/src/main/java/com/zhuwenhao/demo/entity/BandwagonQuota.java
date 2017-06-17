package com.zhuwenhao.demo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BandwagonQuota implements Serializable {

    @SerializedName("occupied_kb")
    private long occupiedKb;

    @SerializedName("softlimit_kb")
    private long softLimitKb;

    @SerializedName("hardlimit_kb")
    private long hardLimitKb;

    @SerializedName("occupied_inodes")
    private String occupiedINodes;

    @SerializedName("softlimit_inodes")
    private String softLimitINodes;

    @SerializedName("hardlimit_inodes")
    private String hardLimitINodes;

    public long getOccupiedKb() {
        return occupiedKb;
    }

    public void setOccupiedKb(long occupiedKb) {
        this.occupiedKb = occupiedKb;
    }

    public long getSoftLimitKb() {
        return softLimitKb;
    }

    public void setSoftLimitKb(long softLimitKb) {
        this.softLimitKb = softLimitKb;
    }

    public long getHardLimitKb() {
        return hardLimitKb;
    }

    public void setHardLimitKb(long hardLimitKb) {
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
