import com.google.gson.Gson;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    private final HttpClient client = HttpClient.newHttpClient();

    // Qui si farà la richiesta, ottenere il body e stamparlo
    public String fetchQuestions(int amount, String difficulty, String type){
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
            return "Error: " + e.getMessage();
        }

        Gson gson = new Gson();
        // .class prende la struttura della classe
        // fromJson => da json a classe
        APIResponse apiResponse = gson.fromJson(response.body(), APIResponse.class);

        if(response.statusCode() == 200){
            // foreach di ogni results
            for(APIQuestions question: apiResponse.results){
                System.out.println(question.question);
                System.out.println(question.correct_answer);
            }
        }

        return response.body();
    }
}
