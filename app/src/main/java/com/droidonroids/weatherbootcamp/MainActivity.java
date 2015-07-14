package com.droidonroids.weatherbootcamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.droidonroids.weatherbootcamp.adapters.ListAdapter;
import com.droidonroids.weatherbootcamp.data.network.ApiService;
import com.droidonroids.weatherbootcamp.utils.Constants;
import com.droidonroids.weatherbootcamp.data.network.entities.ForecastResponse;
import com.droidonroids.weatherbootcamp.data.network.entities.Main;
import com.droidonroids.weatherbootcamp.data.network.entities.Weather;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;

import java.util.ArrayList;
import java.util.List;


import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

	ListView listView;

	private ImageView weatherIcon;
	private ListAdapter adapter;
	private Button mSendWeatherRequestButton;
	private EditText mCityNameEditText;
	private ApiService mApiService;
	private ArrayList<WeatherResponse> weatherResponses = new ArrayList<>();

	private Callback<ForecastResponse> callback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RequestInterceptor requestInterceptor = new RequestInterceptor() {
			@Override
			public void intercept(RequestFacade request) {
				request.addQueryParam("units", "metric");
			}
		};

		RestAdapter mRestAdapter = new RestAdapter.Builder()
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.setLog(new AndroidLog(Constants.TAG))
				.setRequestInterceptor(requestInterceptor)
				.setEndpoint(Constants.ENDPOINT).build();

		mApiService = mRestAdapter.create(ApiService.class);

		callback = new Callback<ForecastResponse>() {
			@Override
			public void success(ForecastResponse forecastResponse, Response response) {
				showForecastDetails(forecastResponse);
			}

			@Override
			public void failure(RetrofitError error) {
				Log.d(Constants.TAG, error.toString());
			}
		};

		mCityNameEditText = (EditText)findViewById(R.id.cityEditText);
		mSendWeatherRequestButton = (Button)findViewById(R.id.sendWeatherRequestButton);
		mSendWeatherRequestButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String cityName = mCityNameEditText.getText().toString();
				mApiService.getWeatherWithCallback(cityName, callback);
			}
		});
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();

		listView = (ListView)findViewById(R.id.list_view);
		adapter = new ListAdapter(getApplicationContext(), weatherResponses);
		listView.setAdapter(adapter);
	}

	private void showForecastDetails(ForecastResponse forecastResponse) {
			ArrayList<WeatherResponse> weatherResponsesTemp = forecastResponse.getWeatherResponses();
			this.weatherResponses.clear();
			for (WeatherResponse weatherResponse : weatherResponsesTemp) {
				this.weatherResponses.add(weatherResponse);
			}
			adapter.notifyDataSetChanged();
		}
}
