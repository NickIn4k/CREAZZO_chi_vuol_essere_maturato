import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Player {
    private String username;
    private int rispoteCorrette;
    private boolean bigliettino;
    private boolean suggerimento;

    public Player(String username, int rispoteCorrette, boolean bigliettino, boolean suggerimento) {
        this.username = username;
        this.rispoteCorrette = rispoteCorrette;
        this.bigliettino = bigliettino;
        this.suggerimento = suggerimento;
    }

    public String toString() {
        return username + " ha risposto correttamente a " + rispoteCorrette + " domande";
    }

    public void salva(){
        Gson gson = new Gson();
        try(FileWriter fw = new FileWriter("statistiche.json", true)){
            fw.write(gson.toJson(this));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
