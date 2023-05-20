package pt.mobile.weatherapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Callback;

public class adaptor extends RecyclerView.Adapter {

    private Context konteks;
    private List<dataDaily> weathers;

    public adaptor(Context ctx, List<dataDaily> weathers) {
        this.konteks= (Context) ctx;
        this.weathers= weathers;
        Log.d("adapter", String.valueOf(weathers.size()));
    }

    public adaptor(Callback<weatherRespon> weatherResponCallback, ArraySet<dataDaily> dataDailyList) {

    }


    public static class VHcontact extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView condition;
        public ImageView image;

        public VHcontact(View itemView) {
            super(itemView);
            this.date= itemView.findViewById(R.id.date);
            this.condition = itemView.findViewById(R.id.condition);
            this.image = itemView.findViewById(R.id.ivWeather);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VHcontact(LayoutInflater.from(this.konteks)
                .inflate(R.layout.detailweather, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        dataDaily dw = this.weathers.get(position);
        VHcontact vh = (VHcontact) holder;
        vh.date.setText(dw.time);
        vh.condition.setText(dw.getCondition());
        vh.image.setImageResource(dw.getIcon());
    }

    @Override
    public int getItemCount() {
        return this.weathers.size();
    }
}
