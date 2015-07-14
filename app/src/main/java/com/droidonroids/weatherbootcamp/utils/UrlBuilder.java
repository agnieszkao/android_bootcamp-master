package com.droidonroids.weatherbootcamp.utils;

public class UrlBuilder {

    public static String getImageUrl(String iconName) {
        return Constants.ICON_URL_PREFIX + "/" + iconName + Constants.EXTENSION;
    }
}
