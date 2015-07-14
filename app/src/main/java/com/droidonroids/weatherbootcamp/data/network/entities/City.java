package com.droidonroids.weatherbootcamp.data.network.entities;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("name")
    private String cityName;

    public String getName() {
        return this.cityName;
    }

    public void setName(String cityName) {
        this.cityName = cityName;
    }
}
