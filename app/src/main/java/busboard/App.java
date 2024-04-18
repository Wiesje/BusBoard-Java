package busboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {
    public static void main(String[] args) {
        try {
            // Specify the ID of the stop point
            String stopPointId = "490008660N";

            // Construct the URL with the stop point ID
            URL url = new URL("https://api.tfl.gov.uk/StopPoint/" + stopPointId + "/Arrivals");

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Set timeouts
            connection.setConnectTimeout(5000); // 5 seconds
            connection.setReadTimeout(5000); // 5 seconds

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Check if the response code is successful
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response data
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the response
                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("Error: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
