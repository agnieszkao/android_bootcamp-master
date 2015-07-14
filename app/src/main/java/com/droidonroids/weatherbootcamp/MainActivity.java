package com.droidonroids.weatherbootcamp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.droidonroids.weatherbootcamp.adapters.ListAdapter;
import com.droidonroids.weatherbootcamp.data.network.ApiService;
import com.droidonroids.weatherbootcamp.data.network.entities.City;
import com.droidonroids.weatherbootcamp.utils.Constants;
import com.droidonroids.weatherbootcamp.data.network.entities.ForecastResponse;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;

import java.util.ArrayList;


import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

	private ListView mListView;
    private ListAdapter mAdapter;
	private Button mSendWeatherRequestButton;
	private EditText mCityNameEditText;
	private ApiService mApiService;
	private ArrayList<WeatherResponse> mWeatherResponses = new ArrayList<>();
    private RequestInterceptor mRequestInterceptor;
	private Callback<ForecastResponse> mCallback;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mRequestInterceptor = new RequestInterceptor() {
			@Override
			public void intercept(RequestFacade request) {
				request.addQueryParam("units", "metric");
			}
		};

        mApiService = getRestAdapter(Constants.ENDPOINT).create(ApiService.class);

		mCallback = new Callback<ForecastResponse>() {
			@Override
			public void success(ForecastResponse forecastResponse, Response response) {
				showForecastDetails(forecastResponse);
                City city = forecastResponse.getCity();
                String cityName = city.getName();
                mCityNameEditText.setText("This is the forecast for " + cityName);
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
                if(cityName.length() > 0) {
                    mApiService.getWeatherWithCallback(cityName, mCallback);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter a city name, please", Toast.LENGTH_SHORT).show();
                }
			}
		});
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();

		mListView = (ListView)findViewById(R.id.list_view);
		mAdapter = new ListAdapter(getApplicationContext(), mWeatherResponses);
		mListView.setAdapter(mAdapter);
	}

    public RestAdapter getRestAdapter(String endpoint) {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog(Constants.TAG))
                .setRequestInterceptor(mRequestInterceptor)
                .setEndpoint(endpoint).build();
    }

    private void showForecastDetails(ForecastResponse forecastResponse) {
			ArrayList<WeatherResponse> weatherResponsesTemp = forecastResponse.getWeatherResponses();
			this.mWeatherResponses.clear();
			for (WeatherResponse weatherResponse : weatherResponsesTemp) {
				this.mWeatherResponses.add(weatherResponse);
			}
			mAdapter.notifyDataSetChanged();
		}
}
