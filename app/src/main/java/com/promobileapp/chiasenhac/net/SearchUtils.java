package com.promobileapp.chiasenhac.net;

import android.content.Context;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.promobileapp.chiasenhac.R;
import com.promobileapp.chiasenhac.model.Banner;
import com.promobileapp.chiasenhac.net.listener.OnSearchResult;
import com.promobileapp.chiasenhac.utils.AppConstants;
import com.promobileapp.chiasenhac.utils.AppUtils;
import com.promobileapp.chiasenhac.utils.ParseUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchUtils {
    private OnSearchResult onSearchResult;
    private Context context;

    public SearchUtils(Context mContext, OnSearchResult onSearchResult) {
        this.onSearchResult = onSearchResult;
        this.context = mContext;
    }

    public void bannerContent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Banner> lstBanner = new ArrayList<>();
                String urlLoad = AppConstants.BASE_URL;
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, urlLoad, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Document doc = Jsoup.parse(response);
                        Elements elmMain = doc.select(".main");
                        Element elm = elmMain.select(".swiper-slide").first();
                        Elements elmContent = elm.select("#pills-home");
                        Elements elmx = elmContent.select(".owl-carousel.owl-theme.slider_home.pt-3");
                        for (Element content : elmx.select("a")) {
                            String url = content.attr("href");
                            Elements elmThumb = content.select(".item");
                            String thumb = ParseUtils.subString(elmThumb.attr("style").toString());
                            String albumName = content.select(".name_song.mb-1").text();
                            String single = content.select(".name_singer.mb-1.author").text();
                            String quality = content.select(".loss.text-pink.mb-0").text();
                            Banner banner = new Banner(albumName, url, thumb, single, quality);
                            lstBanner.add(banner);

//                            Log.e("url", url + "\nThumb: " + thumb +
//                                    "\nName: " + albumName +
//                                    "\nSingle: " + single +
//                                    "\nQuality: " + quality);
                        }
                        onSearchResult.onSearchSuccessful(lstBanner);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyError", error.toString());
                        if (!AppUtils.isOnline(context)) {
                            Toast.makeText(context, context.getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, context.getString(R.string.error), Toast.LENGTH_SHORT).show();
                        }
                        onSearchResult.onSearchFailed("");
                    }
                }) {
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("User-Agent", "Mozilla/5.0 (Windows Phone 10.0; Android 6.0.1; Microsoft; Lumia 650) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Mobile Safari/537.36 Edge/15.15063");
                        return headers;
                    }
                };
                stringRequest.setShouldRetryServerErrors(true);
                VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
            }
        }).start();
    }

    public void bxh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Banner> lstBanner = new ArrayList<>();
                String urlLoad = AppConstants.BASE_URL;
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, urlLoad, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Document doc = Jsoup.parse(response);
                        Elements elmMain = doc.select(".main");
                        Element elm = elmMain.select(".swiper-slide").first();
                        Elements elmContent = elm.select("#pills-home");
                        Elements elmContainer = elmContent.select(".container");
                        Element elmBxh = elmContainer.select(".block.block_detail_chude").first();
                        Element item = elmBxh.select(".row").first();
                        for (Element content : elmBxh.select("a")) {
                            String url = content.attr("href");
                            Elements elmThumb = content.select(".item");
                            String thumb = ParseUtils.subString(elmThumb.attr("style").toString());
                            String albumName = content.select(".name_song.mb-1").text();
                            String single = content.select(".name_singer.mb-1.author").text();
                            String quality = content.select(".loss.text-pink.mb-0").text();
                            Banner banner = new Banner(albumName, url, thumb, single, quality);
                            lstBanner.add(banner);

                            Log.e("url", url + "\nThumb: " + thumb +
                                    "\nName: " + albumName +
                                    "\nSingle: " + single +
                                    "\nQuality: " + quality);
                        }
                        Log.e("BXH", item.toString());
//                        onSearchResult.onSearchSuccessful(lstBanner);
                        writeStringAsFile(item.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyError", error.toString());
                        if (!AppUtils.isOnline(context)) {
                            Toast.makeText(context, context.getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, context.getString(R.string.error), Toast.LENGTH_SHORT).show();
                        }
                        onSearchResult.onSearchFailed("");
                    }
                }) {
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("User-Agent", "Mozilla/5.0 (Windows Phone 10.0; Android 6.0.1; Microsoft; Lumia 650) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Mobile Safari/537.36 Edge/15.15063");
                        return headers;
                    }
                };
                stringRequest.setShouldRetryServerErrors(true);
                VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
            }
        }).start();
    }

    /*nhac.vn*/
//    public void bannerContent() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                String urlLoad = AppConstants.BASE_URL;
//                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                        .permitAll().build();
//                StrictMode.setThreadPolicy(policy);
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, urlLoad, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Document doc = Jsoup.parse(response);
//                        Elements elements = doc.select(".main-content");
//                        Elements banner = elements.select(".main-slide");
//                        Log.e("elm", banner.toString());
//                        onSearchResult.onSearchSuccessful(banner.toString());
//                        writeStringAsFile(banner.toString());
//
////                        Log.e("elm", doc.toString());
////                        onSearchResult.onSearchSuccessful(doc.toString());
////                        writeStringAsFile(doc.toString());
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("VolleyError", error.getMessage());
//                        if (!AppUtils.isOnline(context)) {
//                            Toast.makeText(context, context.getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(context, context.getString(R.string.error), Toast.LENGTH_SHORT).show();
//                        }
//                        onSearchResult.onSearchFailed("");
//                    }
//                }) {
//                    public Map<String, String> getHeaders() {
//                        Map<String, String> headers = new HashMap<String, String>();
//                        headers.put("User-Agent", "Mozilla/5.0 (Windows Phone 10.0; Android 6.0.1; Microsoft; Lumia 650) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Mobile Safari/537.36 Edge/15.15063");
//                        return headers;
//                    }
//                };
//                stringRequest.setShouldRetryServerErrors(true);
//                VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
//            }
//        }).start();
//    }

    public void writeStringAsFile(final String fileContents) {
        try {
            FileWriter out = new FileWriter(new File(Environment.getExternalStorageDirectory().getPath(), "CSN.html"));
            out.write(fileContents);
            out.close();
            Toast.makeText(context, "done", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("Eror", e.getMessage());
        }
    }

    public String getDuration(String duration) {
        String regex1 = duration.replaceAll("\\.", "");
        String[] separated = regex1.split(":");
        String time = "";
        for (int i = 1; i < separated.length; i++) {
            if (i == separated.length - 1)
                time += separated[i];
            else
                time += separated[i] + ":";
        }
        return time;
    }
}
