package com.zhuwenhao.demo.entity;

import java.io.Serializable;

public class Bandwagon implements Serializable {

    private Integer id;

    private String title;

    private String veId;

    private String apiKey;

    private Integer sort;

    private Integer position;

    private BandwagonInfo bandwagonInfo;

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

    public BandwagonInfo getBandwagonInfo() {
        return bandwagonInfo;
    }

    public void setBandwagonInfo(BandwagonInfo bandwagonInfo) {
        this.bandwagonInfo = bandwagonInfo;
    }
}
