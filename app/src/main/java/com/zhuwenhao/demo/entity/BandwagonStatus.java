package com.zhuwenhao.demo.entity;

import com.google.gson.annotations.SerializedName;

public class BandwagonStatus {

    private String status;

    private String hostname;

    @SerializedName("load_average")
    private String loadAverage;

    @SerializedName("nproc")
    private String npRoc;

    @SerializedName("nproc_b")
    private String npRocB;

    @SerializedName("kmemsize")
    private String kmEmSize;

    @SerializedName("kmemsize_b")
    private String kmEmSizeB;

    @SerializedName("privvmpages")
    private String priVvmPages;

    @SerializedName("privvmpages_b")
    private String priVvmPagesB;

    @SerializedName("oomguarpages")
    private String oomguarPages;

    @SerializedName("oomguarpages_b")
    private String oomguarPagesB;

    @SerializedName("numtcpsock")
    private String numTcpSock;

    @SerializedName("numtcpsock_b")
    private String numTcpSockB;

    @SerializedName("numfile")
    private String numFile;

    @SerializedName("numfile_b")
    private String numFileB;

    @SerializedName("swappages")
    private String swapPages;

    @SerializedName("swappages_b")
    private String swapPagesB;

    @SerializedName("physpages")
    private String physPages;

    @SerializedName("physpages_l")
    private String physPagesL;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
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

    public String getNpRocB() {
        return npRocB;
    }

    public void setNpRocB(String npRocB) {
        this.npRocB = npRocB;
    }

    public String getKmEmSize() {
        return kmEmSize;
    }

    public void setKmEmSize(String kmEmSize) {
        this.kmEmSize = kmEmSize;
    }

    public String getKmEmSizeB() {
        return kmEmSizeB;
    }

    public void setKmEmSizeB(String kmEmSizeB) {
        this.kmEmSizeB = kmEmSizeB;
    }

    public String getPriVvmPages() {
        return priVvmPages;
    }

    public void setPriVvmPages(String priVvmPages) {
        this.priVvmPages = priVvmPages;
    }

    public String getPriVvmPagesB() {
        return priVvmPagesB;
    }

    public void setPriVvmPagesB(String priVvmPagesB) {
        this.priVvmPagesB = priVvmPagesB;
    }

    public String getOomguarPages() {
        return oomguarPages;
    }

    public void setOomguarPages(String oomguarPages) {
        this.oomguarPages = oomguarPages;
    }

    public String getOomguarPagesB() {
        return oomguarPagesB;
    }

    public void setOomguarPagesB(String oomguarPagesB) {
        this.oomguarPagesB = oomguarPagesB;
    }

    public String getNumTcpSock() {
        return numTcpSock;
    }

    public void setNumTcpSock(String numTcpSock) {
        this.numTcpSock = numTcpSock;
    }

    public String getNumTcpSockB() {
        return numTcpSockB;
    }

    public void setNumTcpSockB(String numTcpSockB) {
        this.numTcpSockB = numTcpSockB;
    }

    public String getNumFile() {
        return numFile;
    }

    public void setNumFile(String numFile) {
        this.numFile = numFile;
    }

    public String getNumFileB() {
        return numFileB;
    }

    public void setNumFileB(String numFileB) {
        this.numFileB = numFileB;
    }

    public String getSwapPages() {
        return swapPages;
    }

    public void setSwapPages(String swapPages) {
        this.swapPages = swapPages;
    }

    public String getSwapPagesB() {
        return swapPagesB;
    }

    public void setSwapPagesB(String swapPagesB) {
        this.swapPagesB = swapPagesB;
    }

    public String getPhysPages() {
        return physPages;
    }

    public void setPhysPages(String physPages) {
        this.physPages = physPages;
    }

    public String getPhysPagesL() {
        return physPagesL;
    }

    public void setPhysPagesL(String physPagesL) {
        this.physPagesL = physPagesL;
    }
}
