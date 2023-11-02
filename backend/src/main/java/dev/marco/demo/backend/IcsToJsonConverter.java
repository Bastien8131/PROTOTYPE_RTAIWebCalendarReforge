package dev.marco.demo.backend;

import biweekly.Biweekly;
import biweekly.ICalendar;
import org.apache.tomcat.util.json.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class IcsToJsonConverter {

    public static String getICalJson() {
        String fileURL = "https://ade-production.ut-capitole.fr/jsp/custom/modules/plannings/anonymous_cal.jsp?data=8241fc38732002145f8811789c9c6731bd72d825015315fe66c60d53cab758dbf377b612dec2c5fba5147d40716acb136c03e67b339315cf";
        String defaultPath = "C:\\Users\\Bastien\\Downloads\\";
        String fileName = "ICal.json";

        try {
            URL url = new URL(fileURL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (InputStream inputStream = connection.getInputStream();
                     InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                     BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                    // Lire le contenu du fichier .ICS
                    StringBuilder icsContent = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        icsContent.append(line).append("\n");
                        //System.out.println(icsContent);
                    }

                    // Utiliser iCal4j pour analyser le contenu .ICS
                    ICalendar ical = Biweekly.parse(icsContent.toString()).first();



                    // Convertir l'objet ICalendar en format JSON avec Jackson
                    String json = Biweekly.writeJson(ical).go();

                    return json;

                    // Afficher le JSON (vous pouvez l'enregistrer dans un fichier si nécessaire)
                    //System.out.println(json);

                    //File file = new File(defaultPath + fileName);
                    //if (file.createNewFile()) {
                    //    System.out.println("File created: " + file.getName());
                    //} else {
                    //    System.out.println("File already exists.");
                    //}

                    //FileWriter myWriter = new FileWriter(defaultPath + fileName);
                    //myWriter.write(json);
                    //myWriter.close();
                    //System.out.println("Successfully wrote to the file.");

                }
            } else {
                System.err.println("La requête HTTP a échoué avec le code de réponse : " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileURL;
    }
}
