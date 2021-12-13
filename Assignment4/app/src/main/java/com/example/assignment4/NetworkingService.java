package com.example.assignment4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkingService {
    private String cocktailNameURL = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";
    private String imageURL = "https://www.thecocktaildb.com/images/media/drink/";
    private String imageURL2 = "/preview";

    public static ExecutorService networkExecutorService = Executors.newFixedThreadPool(4);
    public static Handler networkingHandler = new Handler(Looper.getMainLooper());

    interface NetworkingListener{
        void dataListener(String jsonString);
        void imageListener(Bitmap image);
    }

    public NetworkingListener listener;

    public void searchForCocktail(String cocktailChars){
        String urlString = cocktailNameURL + cocktailChars;
        connect(urlString);
    }

    public void getCategoryDataForCocktail(String cocktailName){
        String cocktailCategoryURL = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";
        String urlForCategory = cocktailCategoryURL;
        connect(urlForCategory);
    }

    public void getInstructionsDataForCocktail(String cocktailName){
        String cocktailInstructionsURL = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";
        String urlForInstructions = cocktailInstructionsURL;
        connect(urlForInstructions);
    }

    public void getImageData(String image){
        //image = "2mcozt1504817403.jpg";

        String urlStr = imageURL + image + imageURL2;
        networkExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    URL urlObj = new URL(urlStr);
                    Bitmap bitmap = BitmapFactory.decodeStream((InputStream) urlObj.getContent());
                    networkingHandler.post(new Runnable(){
                        @Override
                        public void run() {
                            listener.imageListener(bitmap);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void connect(String url){
        networkExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("**** new runnable url " + url);
                HttpURLConnection httpURLConnection = null;
                try{
                    String jsonData = "";
                    URL urlObj = new URL(url);
                    httpURLConnection = (HttpURLConnection) urlObj.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
                    InputStream in = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    int inputStreamData = 0;
                    while((inputStreamData = reader.read()) != -1){
                        char current = (char)inputStreamData;
                        jsonData += current;
                    }
                    final String finalData = jsonData;

                    networkingHandler.post(new Runnable(){
                        @Override
                        public void run() {
                            listener.dataListener(finalData);
                        }
                    });
                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
                finally {
                    httpURLConnection.disconnect();
                }
            }
        });
    }
}

