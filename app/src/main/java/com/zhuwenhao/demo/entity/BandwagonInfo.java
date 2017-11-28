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

    @SerializedName("is_cpu_throttled")
    private String isCpuThrottled;

    @SerializedName("ssh_port")
    private String sshPort;

    @SerializedName("load_average")
    private String loadAverage;

    private String hostname;

    @SerializedName("node_ip")
    private String nodeIp;

    @SerializedName("node_alias")
    private String nodeAlias;

    @SerializedName("node_location")
    private String nodeLocation;

    @SerializedName("location_ipv6_ready")
    private boolean locationIpv6Ready;

    private String plan;

    @SerializedName("plan_monthly_data")
    private long planMonthlyData;

    @SerializedName("plan_disk")
    private long planDisk;

    @SerializedName("plan_ram")
    private long planRam;

    @SerializedName("plan_swap")
    private long planSwap;

    @SerializedName("plan_max_ipv6s")
    private int planMaxIpv6s;

    private String os;

    private String email;

    @SerializedName("data_counter")
    private long dataCounter;

    @SerializedName("data_next_reset")
    private long dataNextReset;

    @SerializedName("ip_addresses")
    private String[] ipAddresses;

    @SerializedName("rdns_api_available")
    private boolean rdnsApiAvailable;

    private boolean suspended;

    @SerializedName("mem_available_kb")
    private long memAvailableB;

    @SerializedName("swap_total_kb")
    private long swapTotalB;

    @SerializedName("swap_available_kb")
    private long swapAvailableB;

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

    public String getIsCpuThrottled() {
        return isCpuThrottled;
    }

    public void setIsCpuThrottled(String isCpuThrottled) {
        this.isCpuThrottled = isCpuThrottled;
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

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp;
    }

    public String getNodeAlias() {
        return nodeAlias;
    }

    public void setNodeAlias(String nodeAlias) {
        this.nodeAlias = nodeAlias;
    }

    public String getNodeLocation() {
        return nodeLocation;
    }

    public void setNodeLocation(String nodeLocation) {
        this.nodeLocation = nodeLocation;
    }

    public boolean isLocationIpv6Ready() {
        return locationIpv6Ready;
    }

    public void setLocationIpv6Ready(boolean locationIpv6Ready) {
        this.locationIpv6Ready = locationIpv6Ready;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
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

    public int getPlanMaxIpv6s() {
        return planMaxIpv6s;
    }

    public void setPlanMaxIpv6s(int planMaxIpv6s) {
        this.planMaxIpv6s = planMaxIpv6s;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setIpAddresses(String ipAddresses) {
        if (ipAddresses == null || ipAddresses.isEmpty()) {
            this.ipAddresses = new String[]{};
        } else {
            this.ipAddresses = ipAddresses.split(",");
        }
    }

    public boolean isRdnsApiAvailable() {
        return rdnsApiAvailable;
    }

    public void setRdnsApiAvailable(boolean rdnsApiAvailable) {
        this.rdnsApiAvailable = rdnsApiAvailable;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public long getMemAvailableB() {
        return memAvailableB * 1024;
    }

    public void setMemAvailableB(long memAvailableB) {
        this.memAvailableB = memAvailableB;
    }

    public long getSwapTotalB() {
        return swapTotalB * 1024;
    }

    public void setSwapTotalB(long swapTotalB) {
        this.swapTotalB = swapTotalB;
    }

    public long getSwapAvailableB() {
        return swapAvailableB * 1024;
    }

    public void setSwapAvailableB(long swapAvailableB) {
        this.swapAvailableB = swapAvailableB;
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
