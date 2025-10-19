import java.util.*;

public class APIQuestions {
    public String type;
    public String difficulty;
    public String category;
    public String question;
    public String correct_answer;
    public String[] incorrect_answers;

    public Map<Character, String> answerList = new HashMap<>();
    public Map<Character, Integer> answerPercentage = new LinkedHashMap<>();

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
        int pCorretta = 50+rnd.nextInt(21); // % tra 50 e 70
        int pRimanente = 100 - pCorretta;

        Character correctKey = search();

        // Salva risposte sbagliate
        List<Character> wrongAnswers = new ArrayList<>();
        for(Character c : answerList.keySet()){
            if(!c.equals(correctKey))
                wrongAnswers.add(c);
        }

        // Generazione percentuali delle risposte sbagliate
        int somma = 0;
        List<Integer> percentualiSbagliate = new ArrayList<>();
        for (int i = 0; i < wrongAnswers.size() - 1; i++) {
            int val = rnd.nextInt(pRimanente - somma);
            percentualiSbagliate.add(val);
            somma += val;
        }

        percentualiSbagliate.add(pRimanente - somma);

        // Mescola le percentuali
        Collections.shuffle(percentualiSbagliate);

        answerPercentage.put(correctKey, pCorretta);

        for (int i = 0; i < wrongAnswers.size(); i++)
            answerPercentage.put(wrongAnswers.get(i), percentualiSbagliate.get(i));
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
        Character wrongToKeep = wrongAnswers.getFirst();

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
