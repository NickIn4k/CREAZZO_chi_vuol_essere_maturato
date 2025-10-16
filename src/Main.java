import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApiClient api = new ApiClient();

        System.out.println("Inserisci numero di domande: ");
        int amount = sc.nextInt();

        api.fetchQuestions(amount, "easy", "multiple");
    }
}