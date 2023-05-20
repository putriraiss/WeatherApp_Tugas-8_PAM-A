package pt.mobile.weatherapp;

public class dataDaily{
    String time;

    int code;
    String condition;

    int icon;

    public dataDaily(){

    }
    public dataDaily (String time, int code){
        this.time= time;
        this.code=code;


    }
    //GETTER
    public String getTime() {

        return time;
    }

    public void setCode(int code) {

        this.code = code;
    }

    public int getCode() {

        return code;
    }

    public String getCondition() {
        switch (code){
            case 0:
                this.condition= "Clear sky";
                break;
            case 1:
                this.condition= "Mainly clear";
                break;
            case 2:
                this.condition= "partly cloudy";
                break;
            case 3:
                this.condition= "overcast";
                break;
            case 45:
                this.condition= "Fog";
                break;
            case 48:
                this.condition= "depositing rime fog";
                break;
            case 51:
                this.condition= "Drizzle: Light";
                break;
            case 53:
                this.condition= "Drizzle:moderate";
                break;
            case 55:
                this.condition= "Drizzle: dense intensity";
                break;
            case 61:
                this.condition= "Rain: Slight";
                break;
            case 63:
                this.condition= "Rain: moderate";
                break;
            case 65:
                this.condition= "Rain: heavy intensity";
                break;
            case 80:
                this.condition= "Rain showers: Slight";
                break;
            case 81:
                this.condition= "Rain showers: moderate";
                break;
            case 82:
                this.condition= "Rain showers: violent";
                break;
        }

        return condition;
    }

    public int getIcon() {
        switch (code){
            case 0:
                this.icon= R.drawable.cs;
                break;
            case 1:
                this.icon= R.drawable.dc;
                break;
            case 2:
                this.icon= R.drawable.dc;
                break;
            case 3:
                this.icon= R.drawable.dc;
                break;
            case 45:
                this.icon= R.drawable.fog;
                break;
            case 48:
                this.icon= R.drawable.fog;
                break;
            case 51:
                this.icon= R.drawable.drizzle;
                break;
            case 53:
                this.icon= R.drawable.drizzle;
                break;
            case 55:
                this.icon= R.drawable.drizzle;
                break;
            case 61:
                this.icon= R.drawable.rain;
                break;
            case 63:
                this.icon= R.drawable.rain;
                break;
            case 65:
                this.icon= R.drawable.rain;
                break;
            case 80:
                this.icon= R.drawable.rs;
                break;
            case 81:
                this.icon= R.drawable.rs;
                break;
            case 82:
                this.icon= R.drawable.rs;
                break;
        }
        return icon;
    }
}
