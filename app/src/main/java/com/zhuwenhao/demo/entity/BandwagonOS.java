package com.zhuwenhao.demo.entity;

public class BandwagonOS {

    private int error;

    private String message;

    private String installed;

    private String[] templates;

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getInstalled() {
        return installed;
    }

    public void setInstalled(String installed) {
        this.installed = installed;
    }

    public String[] getTemplates() {
        return templates;
    }

    public void setTemplates(String[] templates) {
        this.templates = templates;
    }
}
