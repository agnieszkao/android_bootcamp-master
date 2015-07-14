package com.droidonroids.weatherbootcamp.data.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ForecastResponse {
	@SerializedName("list")
	private ArrayList<WeatherResponse> weatherResponses;

	public ArrayList<WeatherResponse> getWeatherResponses() {
		return this.weatherResponses;
	}

	public void setWeatherResponses(ArrayList<WeatherResponse> weatherResponses) {
		this.weatherResponses = weatherResponses;
	}

	@Override public String toString() {
		return "ForecastResponse{" +
			"weatherResponses=" + weatherResponses +
			'}';
	}
}
