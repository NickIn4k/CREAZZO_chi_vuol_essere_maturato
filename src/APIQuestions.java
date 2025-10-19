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

    public void generatePercentages() {
        Random rnd = new Random();
        Integer pCorretta = 50+rnd.nextInt(21); // % tra 50 e 70
        Integer pRimanente = 100 - pCorretta;

        // DA COMPLETARE
    }

    public void halfAnswers(){
        Character correctKey = search();
        List<Character> wrongAnswers = new ArrayList<>();

        for(Character c : answerList.keySet()){
            if(!c.equals(correctKey))
                wrongAnswers.add(c);
        }

        // Scegli una risposta sbagliata casuale da tenere
        Collections.shuffle(wrongAnswers);
        Character wrongToKeep = wrongAnswers.get(0);

        // Crea una nuova mappa con solo la corretta e la sbagliata scelta
        Map<Character, String> newMap = new LinkedHashMap<>();
        newMap.put('A', answerList.get(correctKey));
        newMap.put('B', answerList.get(wrongToKeep));

        // Sostituisci la vecchia mappa
        answerList = newMap;
    }

    private Character search(){
        for(Map.Entry<Character, String> entry : answerList.entrySet()){
            if(entry.getValue().equals(correct_answer)){
                return entry.getKey();
            }
        }
        return null;
    }
}
