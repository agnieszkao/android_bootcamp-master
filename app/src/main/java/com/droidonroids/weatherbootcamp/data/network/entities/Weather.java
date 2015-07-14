package com.droidonroids.weatherbootcamp.data.network.entities;

import com.google.gson.annotations.SerializedName;

public class Weather {
	@SerializedName("icon") private String icon;
	@SerializedName("description") private String description;

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override public String toString() {
		return "Weather{" +
			"icon='" + icon + '\'' +
				"description='" + description + '\'' +
			'}';
	}
}
