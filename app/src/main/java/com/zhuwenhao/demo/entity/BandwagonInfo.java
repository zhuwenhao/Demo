package com.zhuwenhao.demo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BandwagonInfo implements Serializable {

    @SerializedName("vm_type")
    private String vmType;

    @SerializedName("vz_status")
    private BandwagonStatus bandwagonStatus;

    @SerializedName("ve_status")
    private String veStatus;

    @SerializedName("vz_quota")
    private BandwagonQuota bandwagonQuota;

    @SerializedName("ssh_port")
    private String sshPort;

    @SerializedName("load_average")
    private String loadAverage;

    private String hostname;

    @SerializedName("node_location")
    private String nodeLocation;

    @SerializedName("plan_monthly_data")
    private long planMonthlyData;

    @SerializedName("plan_disk")
    private long planDisk;

    @SerializedName("plan_ram")
    private long planRam;

    @SerializedName("plan_swap")
    private long planSwap;

    private String os;

    @SerializedName("data_counter")
    private long dataCounter;

    @SerializedName("data_next_reset")
    private long dataNextReset;

    @SerializedName("ip_addresses")
    private String[] ipAddresses;

    @SerializedName("mem_available_kb")
    private long memAvailableKB;

    @SerializedName("swap_total_kb")
    private long swapTotalKB;

    @SerializedName("swap_available_kb")
    private long swapAvailableKB;

    @SerializedName("ve_used_disk_space_b")
    private long veUsedDiskSpaceB;

    private int error;

    private String message;

    public BandwagonInfo(String nodeLocation, String os, String ipAddresses) {
        this.nodeLocation = nodeLocation;
        this.os = os;
        setIpAddresses(ipAddresses);
    }

    public String getVmType() {
        return vmType;
    }

    public void setVmType(String vmType) {
        this.vmType = vmType;
    }

    public BandwagonStatus getBandwagonStatus() {
        return bandwagonStatus;
    }

    public void setBandwagonStatus(BandwagonStatus bandwagonStatus) {
        this.bandwagonStatus = bandwagonStatus;
    }

    public String getVeStatus() {
        return veStatus;
    }

    public void setVeStatus(String veStatus) {
        this.veStatus = veStatus;
    }

    public BandwagonQuota getBandwagonQuota() {
        return bandwagonQuota;
    }

    public void setBandwagonQuota(BandwagonQuota bandwagonQuota) {
        this.bandwagonQuota = bandwagonQuota;
    }

    public String getSshPort() {
        return sshPort;
    }

    public void setSshPort(String sshPort) {
        this.sshPort = sshPort;
    }

    public String getLoadAverage() {
        return loadAverage;
    }

    public void setLoadAverage(String loadAverage) {
        this.loadAverage = loadAverage;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getNodeLocation() {
        return nodeLocation;
    }

    public void setNodeLocation(String nodeLocation) {
        this.nodeLocation = nodeLocation;
    }

    public long getPlanMonthlyData() {
        return planMonthlyData;
    }

    public void setPlanMonthlyData(long planMonthlyData) {
        this.planMonthlyData = planMonthlyData;
    }

    public long getPlanDisk() {
        return planDisk;
    }

    public void setPlanDisk(long planDisk) {
        this.planDisk = planDisk;
    }

    public long getPlanRam() {
        return planRam;
    }

    public void setPlanRam(long planRam) {
        this.planRam = planRam;
    }

    public long getPlanSwap() {
        return planSwap;
    }

    public void setPlanSwap(long planSwap) {
        this.planSwap = planSwap;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public long getDataCounter() {
        return dataCounter;
    }

    public void setDataCounter(long dataCounter) {
        this.dataCounter = dataCounter;
    }

    public long getDataNextReset() {
        return dataNextReset;
    }

    public void setDataNextReset(long dataNextReset) {
        this.dataNextReset = dataNextReset;
    }

    public String getIpAddresses() {
        if (ipAddresses == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ipAddresses.length; i++) {
            sb.append(ipAddresses[i]);
            if (i < ipAddresses.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private void setIpAddresses(String ipAddresses) {
        if (ipAddresses == null || ipAddresses.isEmpty()) {
            this.ipAddresses = new String[]{};
        } else {
            this.ipAddresses = ipAddresses.split(",");
        }
    }

    public long getMemAvailableKB() {
        return memAvailableKB * 1024;
    }

    public void setMemAvailableKB(long memAvailableKB) {
        this.memAvailableKB = memAvailableKB;
    }

    public long getSwapTotalKB() {
        return swapTotalKB * 1024;
    }

    public void setSwapTotalKB(long swapTotalKB) {
        this.swapTotalKB = swapTotalKB;
    }

    public long getSwapAvailableKB() {
        return swapAvailableKB * 1024;
    }

    public void setSwapAvailableKB(long swapAvailableKB) {
        this.swapAvailableKB = swapAvailableKB;
    }

    public long getVeUsedDiskSpaceB() {
        return veUsedDiskSpaceB;
    }

    public void setVeUsedDiskSpaceB(long veUsedDiskSpaceB) {
        this.veUsedDiskSpaceB = veUsedDiskSpaceB;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
