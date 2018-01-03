package com.zhuwenhao.demo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BandwagonStatus implements Serializable {

    private String status;

    @SerializedName("load_average")
    private String loadAverage;

    @SerializedName("nproc")
    private String npRoc;

    @SerializedName("oomguarpages")
    private long oomguarPages;

    @SerializedName("swappages")
    private long swapPages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoadAverage() {
        return loadAverage;
    }

    public void setLoadAverage(String loadAverage) {
        this.loadAverage = loadAverage;
    }

    public String getNpRoc() {
        return npRoc;
    }

    public void setNpRoc(String npRoc) {
        this.npRoc = npRoc;
    }

    public long getOomguarPages() {
        return oomguarPages;
    }

    public long getUsedRam() {
        return oomguarPages * 4 * 1024;
    }

    public void setOomguarPages(long oomguarPages) {
        this.oomguarPages = oomguarPages;
    }

    public long getSwapPages() {
        return swapPages;
    }

    public long getUsedSwap() {
        return swapPages * 4 * 1024;
    }

    public void setSwapPages(long swapPages) {
        this.swapPages = swapPages;
    }
}