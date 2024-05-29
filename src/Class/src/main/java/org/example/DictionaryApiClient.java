package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class DictionaryApiClient {
    String word;

   public DictionaryApiClient(){};
    public static DictionaryResult fetchDefinitionAndAudioLink(String word) {
        String definition = null;
        String audioLink = null;

        try {
            // API URL
            String apiUrl = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;

            // Create a URL object from the API URL
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Read the response from the API
            BufferedReader reader;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            // Process the response
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            // Parse JSON response
            JSONArray jsonArray = new JSONArray(response.toString());

            // Get the second phonetic audio link
            if (jsonArray.length() > 0) {
                JSONObject firstEntry = jsonArray.getJSONObject(0);
                JSONArray phonetics = firstEntry.getJSONArray("phonetics");
                JSONObject secondPhonetic = phonetics.getJSONObject(0);
                audioLink = secondPhonetic.getString("audio");
                if(audioLink==null){
                     secondPhonetic = phonetics.getJSONObject(0);
                    audioLink = secondPhonetic.getString("audio");
                }


                // Get the first definition
                JSONArray meanings = firstEntry.getJSONArray("meanings");
                if (meanings.length() > 0) {
                    JSONObject firstMeaning = meanings.getJSONObject(0);
                     definition = firstMeaning.getJSONArray("definitions").getJSONObject(0).getString("definition");
                }
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new DictionaryResult(definition, audioLink);
    }
}






