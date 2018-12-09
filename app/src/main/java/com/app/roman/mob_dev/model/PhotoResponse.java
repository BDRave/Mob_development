package com.app.roman.mob_dev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PhotoResponse {

    @SerializedName("photos")
    @Expose
    private PhotoData data;
    @SerializedName("stat")
    @Expose
    private String status;

    public PhotoData getData() {
        return data;
    }

    public void setData(PhotoData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

