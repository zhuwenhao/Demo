package com.zhuwenhao.demo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BandwagonQuota implements Serializable {

    @SerializedName("occupied_kb")
    private long occupiedKB;

    public long getOccupiedKB() {
        return occupiedKB * 1024;
    }

    public void setOccupiedKB(long occupiedKB) {
        this.occupiedKB = occupiedKB;
    }
}