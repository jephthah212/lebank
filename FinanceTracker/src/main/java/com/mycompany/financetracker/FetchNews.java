package com.mycompany.financetracker;



import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author onyighichijephthah
 */
public class FetchNews {
    private final String API_KEY = "015d45aae59348298ddb742d9321ba1a";

    public String getFinanceHeadlines(String keyword) {
        StringBuilder result = new StringBuilder();
        try {
            String endpoint = "https://newsapi.org/v2/everything?q=" + keyword +
                              "&sortBy=publishedAt&language=en&apiKey=" + API_KEY;
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNextLine()) {
                result.append(scanner.nextLine());
            }
            scanner.close();

            JSONObject json = new JSONObject(result.toString());
            JSONArray articles = json.getJSONArray("articles");

            StringBuilder headlines = new StringBuilder();
            for (int i = 0; i < Math.min(5, articles.length()); i++) {
                JSONObject article = articles.getJSONObject(i);
                headlines.append("• ").append(article.getString("title")).append("\n\n");
            }
            return headlines.toString();

        } catch (Exception e) {
            return "Failed to fetch news for \"" + keyword + "\".";
        }
    }
}
