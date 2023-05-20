package pt.mobile.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    private RequestQueue requestQueue;
    private RecyclerView rvData;
    private adaptor adapter;
    Button btnR;

    private List<dataDaily> dataDailyList= new ArrayList<>();

    TextView tvLocation, tvLongitude, tvLatitude, tvCondition, tvTemperature, tvWind;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnR= this.findViewById(R.id.btnR);
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this, MainActivity2.class);
                MainActivity.this.startActivity(i);
            }
        });


        rvData = this.findViewById(R.id.rvWeather);
        icon = this.findViewById(R.id.ivWeather2);
        tvLatitude= this.findViewById(R.id.tvLatitude);
        tvLongitude= this.findViewById(R.id.tvLongtiude);
        tvLocation= this.findViewById(R.id.tvLocation);
        tvCondition= this.findViewById(R.id.tvCondition);
        tvTemperature= this.findViewById(R.id.tvTemperature);
        tvWind= this.findViewById(R.id.tvWind);

        this.requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest sr= new JsonObjectRequest(
                Request.Method.GET,
                "https://api.open-meteo.com/v1/forecast?latitude=-7.98&longitude=112.63&daily=weathercode&current_weather=true&timezone=auto",
                null,
                this,
                this
        );
        this.requestQueue.add(sr);



    }

    @Override
    public void onResponse (JSONObject response){
        try {
            JSONObject allData1 = response.getJSONObject("current_weather");
            JSONObject allData2= response.getJSONObject("daily");

            tvLatitude.setText(response.getString("latitude"));
            tvLongitude.setText(response.getString("longitude"));

            //punyanya current weather
            tvTemperature.setText(allData1.getString("temperature"));
            tvWind.setText(allData1.getString("windspeed"));


            dataDaily weather= new dataDaily();
            weather.setCode(allData1.getInt("weathercode"));
            tvCondition.setText(weather.getCondition());
            icon.setImageResource(weather.getIcon());
            Log.d("c", weather.getCondition());


            //punyanya daily
            JSONArray time= allData2.getJSONArray("time");
            JSONArray weathercode2= allData2.getJSONArray("weathercode");

            for (int i=0; i< time.length(); i++){
                String waktu= time.getString(i);
                int code= weathercode2.getInt(i);
                dataDailyList.add(new dataDaily(waktu, code));
            }

            adapter= new adaptor(this, dataDailyList);
            rvData.setLayoutManager(new LinearLayoutManager(this));
            rvData.setAdapter(adapter);

        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {
    }
}