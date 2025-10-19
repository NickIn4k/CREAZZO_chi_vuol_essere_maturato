import java.util.*;

public class APIQuestions {
    public String type;
    public String difficulty;
    public String category;
    public String question;
    public String correct_answer;
    public String[] incorrect_answers;

    public Map<Character, String> answerList = new HashMap<>();
    public Map<Character, Integer> answerPercent = new LinkedHashMap<>();

    public void shuffleAnswers() {
        List<String> allAnswers = new ArrayList<>();
        allAnswers.add(correct_answer);                // Aggiungi la corretta
        allAnswers.addAll(Arrays.asList(incorrect_answers)); // Aggiungi quelle sbagliate

        Collections.shuffle(allAnswers); // Mescola

        char ltr = 'A';
        for (String ans : allAnswers) {
            answerList.put(ltr, ans); // MAP((A, ans), (B, ans))
            ltr++;
        }
    }

    private void generatePercentages() {
        Random rnd = new Random();
        Integer pCorretta = 50+rnd.nextInt(21); // % tra 50 e 70
        Integer pRimanente = 100 - pCorretta;

        // DA COMPLETARE
    }

}
