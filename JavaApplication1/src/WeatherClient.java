package meteocal.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


import java.sql.Timestamp;
import meteocal.entity.WeatherData;



/**
 *
 * @author Nemanja
 */
public class WeatherClient {

    private final String API_KEY = "9729dca762d8bae8edbd698333eb40d4";
    private final String API_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    //current
    //api.openweathermap.org/data/2.5/weather?q=London,uk
    //3 hour in next 5 days
    //api.openweathermap.org/data/2.5/forecast?q=London,us
    //dayly, next 16 days
    //api.openweathermap.org/data/2.5/forecast/daily?q=London&cnt=16
    private AsyncHttpClient client;

    public WeatherClient() {
        this.client = new AsyncHttpClient();
    }

    private String addToUrl(String url, String addedText) {
        return url + addedText;
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    private String prepareURLParameters(String url) {
        return url + "?";
    }

    private String addToUrlApiKey(String url) {
        return url + "APPID=" + API_KEY;
    }

    private String addToUrlSearchTerm(String url, String searchTerm) {
        try {
            return url + "&q=" + URLEncoder.encode(searchTerm, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "UnsupportedEncodingException";
        }
    }
    private String addToUrlCountedDays(String url, String countedDays) {
        return url + "&cnt="+countedDays;
    }
    public ArrayList<WeatherData> getWeatherDataDaily(String searchTerm) {
        //api.openweathermap.org/data/2.5/forecast/daily?q=London&cnt=16
        String url = getAbsoluteUrl("forecast/daily");
        //?APPID=<key>&q={search-term}
        url = prepareURLParameters(url);
        url = addToUrlApiKey(url);
        url = addToUrlSearchTerm(url, searchTerm);
        url = addToUrlCountedDays(url, "16");

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        Future<Response> f;
        try {
            f = asyncHttpClient.prepareGet(url).execute();
            Response r = f.get();
            String responseString = processInputStream(r.getResponseBodyAsStream());
            JSONObject body = new JSONObject(responseString);
            //Get the movies json array
            JSONArray items = body.getJSONArray("list");
	        //Parse json array into array of model objects
            ArrayList<WeatherData> dataList = new ArrayList<WeatherData>();
            for (int i = 0; i < items.length(); i++) {
                JSONObject jobj = items.getJSONObject(i);
                WeatherData wd = WeatherClient.fromJsonDaily(jobj);
                dataList.add(wd);
            }
            return dataList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WeatherData fromJsonDaily(org.json.JSONObject jsonObject) {
        WeatherData wd = new WeatherData();
        try {
            wd.setTemperature(jsonObject.getJSONObject("temp").getDouble("day"));
        } catch (Exception e) {
            wd.setTemperature(-9999.9);
        }
        try {
            wd.setCloudPercentage(Double.valueOf(Integer.toString(jsonObject.getInt("clouds"))+".0"));
        } catch (Exception e) {
            wd.setCloudPercentage(-9999.9);
        }
        try {
            wd.setPreasure(jsonObject.getDouble("pressure"));
        } catch (Exception e) {
            wd.setPreasure(-9999.9);
        }
        try {
            wd.setWindSpeed(jsonObject.getDouble("speed"));
        } catch (Exception e) {
            wd.setWindSpeed(-9999.9);
        }
        try {
            Timestamp dt = new Timestamp(jsonObject.getInt("dt"));
            wd.setDate(dt);
            wd.setHour(dt.getHours());
        } catch (Exception e) {
            wd.setDate(new Timestamp(1970, 1, 1, 0, 0, 0, 0));
            wd.setHour(1);
        }
        // Return new object
        return wd;
    }

    public String processInputStream(InputStream in) {
        try {

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            return responseStrBuilder.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "InputStream error";
    }
    public static void main(String[] args) {
     WeatherClient client = new WeatherClient();
     ArrayList<WeatherData> dataList = client.getWeatherDataDaily("London");
     System.out.println("London weather:");
        for (WeatherData wd : dataList) {
            System.out.println(wd.getDate().toString()+"hr:"+wd.getHour()+" cl:"+
                    wd.getCloudPercentage().toString()+" pr:"+wd.getPreasure()+" tmp:"+
                    wd.getTemperature()+" wnd:"+wd.getWindSpeed());
        }
       
     System.exit(0);
     }
}
