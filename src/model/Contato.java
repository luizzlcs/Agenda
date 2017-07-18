package model;

public class Contato {

    private int id;
    private String nome;
    private String fone;
    
    public Contato() {}
    
    public Contato(int id, String nome, String fone) {
        this.id = id;
        this.nome = nome;
        this.fone = fone;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public String getFone() {
        return this.fone;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setFone(String fone) {
        this.fone = fone;
    }
    
    @Override
    public boolean equals(Object obj) {
        return ((Contato) obj).getId() == this.id;
    }
    
    @Override
    public String toString() {
        return "Contato{\n"
                + "id=" + this.id + "\n"
                + "nome="+ this.nome +"\n"
                + "fone="+ this.fone +"\n"
                + "}";
    }
    
}
