package dev.marco.demo.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadAndDisplayFileContent {

    public static String getCalendarCapitole() {
        String fileURL = "https://ade-production.ut-capitole.fr/jsp/custom/modules/plannings/anonymous_cal.jsp?data=8241fc38732002145f8811789c9c6731bd72d825015315fe66c60d53cab758dbf377b612dec2c5fba5147d40716acb136c03e67b339315cf";
        String rs = "";
        try {
            URL url = new URL(fileURL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lire le contenu de la réponse HTTP
                try (InputStream inputStream = connection.getInputStream();
                     InputStreamReader reader = new InputStreamReader(inputStream);
                     BufferedReader bufferedReader = new BufferedReader(reader)) {

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        //System.out.println(line);
                        rs = rs + line + "<br>";
                    }
                }
            } else {
                System.err.println("La requête HTTP a échoué avec le code de réponse : " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rs;
    }
}
