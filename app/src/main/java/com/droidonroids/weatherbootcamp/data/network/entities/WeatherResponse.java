package com.droidonroids.weatherbootcamp.data.network.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherResponse implements Serializable {
	@SerializedName("weather")
	private ArrayList<Weather> weathers;
	@SerializedName("main")
	private Main main;
    @SerializedName("dt_txt")
    private String time;

	public ArrayList<Weather> getWeathers() {
		return this.weathers;
	}

	public void setWeathers(ArrayList<Weather> weathers) {
		this.weathers = weathers;
	}

	public Main getMain() {
		return this.main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

	@Override public String toString() {
		return "WeatherResponse{" +
			"weathers=" + weathers +
			", main=" + main +
			'}';
	}
}
