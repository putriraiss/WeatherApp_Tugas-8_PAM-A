package pt.mobile.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    private weatherAPIService wService;
    private RecyclerView rvData;
    private adaptor adapter;
    TextView tvLocation, tvLongitude, tvLatitude, tvCondition, tvTemperature, tvWind;
    ImageView icon;
    Button btnV;
    private List<dataDaily> dataDaily = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnV= this.findViewById(R.id.btnV);
        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity2.this, MainActivity.class);
                startActivity(i);
            }
        });

        rvData = this.findViewById(R.id.rv1);
        icon = this.findViewById(R.id.ivWeather3);
        tvLatitude= this.findViewById(R.id.tvLatitude);
        tvLongitude= this.findViewById(R.id.tvLongtiude);
        tvLocation= this.findViewById(R.id.tvLocation);
        tvCondition= this.findViewById(R.id.tvCondition);
        tvTemperature= this.findViewById(R.id.tvTemperature);
        tvWind= this.findViewById(R.id.tvWind);

        //retrofit
        Gson gson= new GsonBuilder().create();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.open-meteo.com/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //weatherservice
        wService= retrofit.create(weatherAPIService.class);

        Call<weatherRespon> call= wService.getWeatherForecast(
              -7.98,
              112.63,
                "weathercode",
                true,
                "auto"
        );

        call.enqueue(new Callback<weatherRespon>() {
            @Override
            public void onResponse(Call<weatherRespon> call, Response<weatherRespon> response) {
                if (response.isSuccessful()){
                    weatherRespon weatherRespon= response.body();
                    weatherRespon.cw cweather= weatherRespon.getCw();
                    weatherRespon.dw dailyWeather= weatherRespon.getDw();

                    dataDaily weather= new dataDaily();
                    weather.setCode(cweather.getWc());
                    icon.setImageResource(weather.getIcon());
                    tvWind.setText(cweather.getWs());
                    tvCondition.setText(weather.getCondition());
                    tvTemperature.setText(cweather.getTemperature());
                    tvLongitude.setText(String.valueOf(weatherRespon.getLongitude()));
                    tvLatitude.setText(String.valueOf(weatherRespon.getLatitude()));

                    String [] time= dailyWeather.getTime();
                    int [] weathercode= dailyWeather.getWc();

                    for (int i=0; i<time.length; i++){
                        String waktu= time[i];
                        int code= weathercode [i];
                        dataDaily.add(new dataDaily(waktu, code));
                    }
                    adapter= new adaptor(MainActivity2.this, dataDaily);
                    rvData.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                    rvData.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<weatherRespon> call, Throwable t) {

            }
        });
    }
}