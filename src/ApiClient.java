import com.google.gson.Gson;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiClient {
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    // Qui si farà la richiesta, ottenere il body e stamparlo
    public List<APIQuestions> fetchQuestions(int amount, String difficulty, String type){
        String url = "https://opentdb.com/api.php?amount=" + amount + "&difficulty="
                + difficulty + "&type=" + type;

        // Pattern di build dell'oggetto
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json") // Parametri dell'header
                .uri(java.net.URI.create(url))                            // URL
                .GET()                                                    // Metodo HTTP
                .build();                                                 // Creazione dell'oggetto finale

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch(IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

        if(response == null)
            throw new RuntimeException("Errore API response");

        // .class prende la struttura della classe
        // fromJson => da json a classe
        APIResponse apiResponse = gson.fromJson(response.body(), APIResponse.class);

        if(apiResponse == null || apiResponse.results == null)
            throw new RuntimeException("Errore API response");

        for(APIQuestions qst : apiResponse.results)
            qst.shuffleAnswers();

        return apiResponse.results;
    }
}
