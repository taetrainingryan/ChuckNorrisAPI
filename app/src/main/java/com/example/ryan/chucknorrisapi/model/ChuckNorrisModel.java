package com.example.ryan.chucknorrisapi.model;

/**
 * Created by Ryan on 22/11/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChuckNorrisModel {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private List<ChuckJoke> value = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ChuckJoke> getValue() {
        return value;
    }

    public void setValue(List<ChuckJoke> value) {
        this.value = value;
    }

}