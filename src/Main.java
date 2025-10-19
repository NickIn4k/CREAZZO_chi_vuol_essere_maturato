import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static boolean bigliettino = false; // Dimezza
    public static boolean suggerimento =  false; // % risposta

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApiClient api = new ApiClient();
        int n_risp = 0;

        // Titolo in ASCII art
        System.out.println("                                                                                 ,--,                                                                                                                         ,----,                                             ,----,             \n" +
                "                   ,--,                                             ,----..   ,---.'|                                                                                             ____                      ,/   .`|                                           ,/   .`|  ,----..    \n" +
                "  ,----..        ,--.'|   ,---,                                    /   /   \\  |   | :               ,---,.  .--.--.    .--.--.       ,---,.,-.----.       ,---,.                ,'  , `.   ,---,          ,`   .'  :            ,-.----.      ,---,          ,`   .'  : /   /   \\   \n" +
                " /   /   \\    ,--,  | :,`--.' |               ,---.         ,--,  /   .     : :   : |             ,'  .' | /  /    '. /  /    '.   ,'  .' |\\    /  \\    ,'  .' |             ,-+-,.' _ |  '  .' \\       ;    ;     /       ,--, \\    /  \\    '  .' \\       ;    ;     //   .     :  \n" +
                "|   :     :,---.'|  : '|   :  :              /__./|       ,'_ /| .   /   ;.  \\|   ' :           ,---.'   ||  :  /`. /|  :  /`. / ,---.'   |;   :    \\ ,---.'   |          ,-+-. ;   , || /  ;    '.   .'___,/    ,'      ,'_ /| ;   :    \\  /  ;    '.   .'___,/    ,'.   /   ;.  \\ \n" +
                ".   |  ;. /|   | : _' |:   |  '         ,---.;  ; |  .--. |  | :.   ;   /  ` ;;   ; '           |   |   .';  |  |--` ;  |  |--`  |   |   .'|   | .\\ : |   |   .'         ,--.'|'   |  ;|:  :       \\  |    :     |  .--. |  | : |   | .\\ : :  :       \\  |    :     |.   ;   /  ` ; \n" +
                ".   ; /--` :   : |.'  ||   :  |        /___/ \\  | |,'_ /| :  . |;   |  ; \\ ; |'   | |__         :   :  |-,|  :  ;_   |  :  ;_    :   :  |-,.   : |: | :   :  |-,        |   |  ,', |  '::  |   /\\   \\ ;    |.';  ;,'_ /| :  . | .   : |: | :  |   /\\   \\ ;    |.';  ;;   |  ; \\ ; | \n" +
                ";   | ;    |   ' '  ; :'   '  ;        \\   ;  \\ ' ||  ' | |  . .|   :  | ; | '|   | :.'|        :   |  ;/| \\  \\    `. \\  \\    `. :   |  ;/||   |  \\ : :   |  ;/|        |   | /  | |  |||  :  ' ;.   :`----'  |  ||  ' | |  . . |   |  \\ : |  :  ' ;.   :`----'  |  ||   :  | ; | ' \n" +
                "|   : |    '   |  .'. ||   |  |         \\   \\  \\: ||  | ' |  | |.   |  ' ' ' :'   :    ;        |   :   .'  `----.   \\ `----.   \\|   :   .'|   : .  / |   :   .'        '   | :  | :  |,|  |  ;/  \\   \\   '   :  ;|  | ' |  | | |   : .  / |  |  ;/  \\   \\   '   :  ;.   |  ' ' ' : \n" +
                ".   | '___ |   | :  | ''   :  ;          ;   \\  ' .:  | | :  ' ;'   ;  \\; /  ||   |  ./         |   |  |-,  __ \\  \\  | __ \\  \\  ||   |  |-,;   | |  \\ |   |  |-,        ;   . |  ; |--' '  :  | \\  \\ ,'   |   |  ':  | | :  ' ; ;   | |  \\ '  :  | \\  \\ ,'   |   |  ''   ;  \\; /  | \n" +
                "'   ; : .'|'   : |  : ;|   |  '           \\   \\   '|  ; ' |  | ' \\   \\  ',  / ;   : ;           '   :  ;/| /  /`--'  //  /`--'  /'   :  ;/||   | ;\\  \\'   :  ;/|        |   : |  | ,    |  |  '  '--'     '   :  ||  ; ' |  | ' |   | ;\\  \\|  |  '  '--'     '   :  | \\   \\  ',  /  \n" +
                "'   | '/  :|   | '  ,/ '   :  |            \\   `  ;:  | : ;  ; |  ;   :    /  |   ,/            |   |    \\'--'.     /'--'.     / |   |    \\:   ' | \\.'|   |    \\        |   : '  |/     |  :  :           ;   |.' :  | : ;  ; | :   ' | \\.'|  :  :           ;   |.'   ;   :    /   \n" +
                "|   :    / ;   : ;--'  ;   |.'              :   \\ |'  :  `--'   \\  \\   \\ .'   '---'             |   :   .'  `--'---'   `--'---'  |   :   .':   : :-'  |   :   .'        ;   | |`-'      |  | ,'           '---'   '  :  `--'   \\:   : :-'  |  | ,'           '---'      \\   \\ .'    \n" +
                " \\   \\ .'  |   ,/      '---'                 '---\" :  ,      .-./   `---`                       |   | ,'                         |   | ,'  |   |.'    |   | ,'          |   ;/          `--''                     :  ,      .-./|   |.'    `--''                         `---`      \n" +
                "  `---`    '---'                                    `--`----'                                   `----'                           `----'    `---'      `----'            '---'                                      `--`----'    `---'                                               \n" +
                "                                                                                                                                                                                                                                                                                    ");

        // Creazione player
        System.out.println("Ciao! Inserisci il tuo nome: ");
        String nome = sc.nextLine();
        System.out.println("Preparati alle domande!");

        // Regole
        System.out.println("\n============================ REGOLE ============================\n");
        System.out.println("Rispondi alle domande indicando la lettera della risposta corretta!");
        System.out.println("Tranquillo, se non sai rispondere puoi chiedere aiuto! Scrivi 'BIGLIETTINO' oppure 'SUGGERIMENTO'\n Fa attenzione: per non venire scoperto dai docenti potrai chiedere solo una volta questi aiuti!" );
        sc.nextLine();

        // Quiz
        n_risp += askQuestions(api, sc, "easy", 5);
        n_risp += askQuestions(api, sc, "medium", 3);
        n_risp += askQuestions(api, sc, "hard", 2);

        PlayerData toSave = new PlayerData(nome,n_risp,bigliettino,suggerimento);
        // Salvataggio in Append, ma il formato non sarà valido per la gestione futura:
        // Bisognerebbe creare una lista di player leggendo il file, inserire il nuovo giocatore e poi riscrivere il tutto.
        Gson gson = new Gson();
        try(FileWriter fw = new FileWriter("PlayerData.json", true)) {
            gson.toJson(toSave, fw);
            fw.write("");   // Linea di mezzo
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void printQuestion(APIQuestions q) {
        System.out.println(q.question);
        for(Map.Entry<Character, String> rcd : q.answerList.entrySet()) {
            Character key = rcd.getKey();
            String value = rcd.getValue();
            System.out.println(key + ". " + value);
        }
    }

    public static boolean checkAnswer(Character ans, APIQuestions q) {
        char key = Character.toUpperCase(ans);
        return q.correct_answer.equals(q.answerList.get(key));
    }

    public static int askQuestions(ApiClient api, Scanner sc, String difficulty, int amount) {
        System.out.println("\n============================ DOMANDE " + difficulty.toUpperCase() + " ============================\n");
        List<APIQuestions> questions = api.fetchQuestions(amount, difficulty, "multiple");
        int cont = 0;
        String ans;

        for(APIQuestions qst : questions) {
            Main.printQuestion(qst);
            ans = sc.nextLine();

            if(ans.equalsIgnoreCase("BIGLIETTINO") && !Main.bigliettino){
                Main.bigliettino = true;
                qst.halfAnswers();
                Main.printQuestion(qst);
                ans = sc.nextLine();
            }
            else if(ans.equalsIgnoreCase("SUGGERIMENTO") && !Main.suggerimento){
                Main.suggerimento = true;
            }

            if(Main.checkAnswer(ans.charAt(0), qst)){
                cont++;
                System.out.println("Risposta esatta!!");
            }
        }

        return cont;
    }
}