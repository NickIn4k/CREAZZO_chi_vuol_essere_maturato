import java.util.HashMap;
import java.util.Map;

public class APIQuestions {
    public String type;
    public String difficulty;
    public String category;
    public String question;
    public String correct_answer;
    public String[] incorrect_answers;

    public Map<Character, String> answerList = new HashMap<>();
}
