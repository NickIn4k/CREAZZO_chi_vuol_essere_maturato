public class PlayerData {
    private String username;
    private int risposteCorrette;
    private boolean bigliettino;
    private boolean suggerimento;

    public PlayerData(String username, int risposteCorrette, boolean bigliettino, boolean suggerimento) {
        this.username = username;
        this.risposteCorrette = risposteCorrette;
        this.bigliettino = bigliettino;
        this.suggerimento = suggerimento;
    }

    public String toString() {
        return username + " ha risposto correttamente a " + risposteCorrette + " domande";
    }
}
