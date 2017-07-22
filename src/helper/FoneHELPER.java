package helper;

public class FoneHELPER {

    public static String formatar(String fone) {
        int qtd = fone.length();
        String novoFone = "";
        if (qtd == 10) {
            novoFone = "(";
            novoFone += fone.substring(0, 2);
            novoFone += ") ";
            novoFone += fone.substring(2, 6);
            novoFone += "-";
            novoFone += fone.substring(6);
        } else {
            novoFone = "(";
            novoFone += fone.substring(0, 2);
            novoFone += ") ";
            novoFone += fone.substring(2, 7);
            novoFone += "-";
            novoFone += fone.substring(7);
        }
        return novoFone;
    }
    
    public static String limpar(String fone) {
        return fone.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
    }
    
}
