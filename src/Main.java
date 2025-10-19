import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApiClient api = new ApiClient();
        List<APIQuestions> appoggio;
        int n_risp = 0;
        boolean bigliettino = false; // Dimezza
        boolean suggerimento =  false; // % risposta

        String ans;

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
        System.out.println("Tranquillo, se non sai rispondere puoi sempre chiedere aiuto! Scrivi 'BIGLIETTINO' oppure 'SUGGERIMENTO'" );

        // Fetch 1
        System.out.println("\n============================ DOMANDE FACILI ============================\n");
        appoggio = api.fetchQuestions(5, "easy", "multiple");
        for(APIQuestions qst : appoggio) {
            Main.printQuestion(qst);
            ans = sc.nextLine();

            if(Main.checkAnswer(ans.charAt(0), qst)){
                n_risp++;
                System.out.println("Risposta esatta!!");
            }
        }

        // Fetch 2
        System.out.println("\n============================ DOMANDE MEDIE ============================\n");
        appoggio = api.fetchQuestions(3, "medium", "multiple");
        for(APIQuestions qst : appoggio) {
            Main.printQuestion(qst);
            ans = sc.nextLine();

            if(Main.checkAnswer(ans.charAt(0), qst)){
                n_risp++;
                System.out.println("Risposta esatta!!");
            }
        }

        // Fetch 3
        System.out.println("\n============================ DOMANDE DIFFICILI ============================\n");
        appoggio = api.fetchQuestions(2, "hard", "multiple");
        for(APIQuestions qst : appoggio) {
            Main.printQuestion(qst);

            ans = sc.nextLine();

            if(Main.checkAnswer(ans.charAt(0), qst)){
                n_risp++;
                System.out.println("Risposta esatta!!");
            }
        }

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
}