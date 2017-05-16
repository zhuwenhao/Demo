package com.zhuwenhao.demo.entity;

import com.google.gson.annotations.SerializedName;

public class Bandwagon {

    private Integer id;

    private String title;

    private String veId;

    private String apiKey;

    @SerializedName("node_location")
    private String nodeLocation;

    private String os;

    @SerializedName("ip_addresses")
    private String[] ipAddresses;

    private Integer sort;

    private Integer position;

    public Bandwagon() {
    }

    public Bandwagon(String title, String veId, String apiKey) {
        this.title = title;
        this.veId = veId;
        this.apiKey = apiKey;
    }

    public Bandwagon(int id, String title, String veId, String apiKey) {
        this.id = id;
        this.title = title;
        this.veId = veId;
        this.apiKey = apiKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVeId() {
        return veId;
    }

    public void setVeId(String veId) {
        this.veId = veId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getNodeLocation() {
        return nodeLocation;
    }

    public void setNodeLocation(String nodeLocation) {
        this.nodeLocation = nodeLocation;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
