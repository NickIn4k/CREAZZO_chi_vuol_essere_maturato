import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    private final HttpClient client = HttpClient.newHttpClient();

    // Qui si farà la richiesta, ottenere il body e stamparlo
    public String fetchQuestions(int amount, String difficulty, String type){
        // https://opentdb.com/api.php?amount=5&difficulty=easy&type=multiple
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

        return response.body();
    }
}
