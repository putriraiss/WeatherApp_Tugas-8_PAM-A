package pt.mobile.weatherapp;

import com.google.gson.annotations.SerializedName;

public class weatherRespon {

    @SerializedName("current_weather")
    private cw cw;

    @SerializedName("daily")
    private dw dw;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("latitude")
    private double latitude;

    //SET
    public void setCw(weatherRespon.cw cw) {
        this.cw = cw;
    }

    public void setDw(dw dw) {
        this.dw = dw;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    //GET
    public cw getCw() {
        return cw;
    }

    public dw getDw() {
        return dw;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    //class
    public static class cw{
        @SerializedName("weathercode")
        private int wc;

        @SerializedName("temperature")
        private String temperature;

        @SerializedName("windspeed")
        private String ws;

        //SET
        public void setWc(int wc) {
            this.wc = wc;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public void setWs(String ws) {
            this.ws = ws;
        }

        //GET
        public int getWc() {
            return wc;
        }

        public String getTemperature() {
            return temperature;
        }

        public String getWs() {
            return ws;
        }
    }

    public static class dw{
        @SerializedName("time")
        private String[] time;

        @SerializedName("weathercode")
        private int[] wc;

        //SET
        public void setTime(String[] time) {
            this.time = time;
        }

        public void setWc(int[] wc) {
            this.wc = wc;
        }

        //GET
        public String[] getTime() {
            return time;
        }

        public int[] getWc() {
            return wc;
        }
    }
}
