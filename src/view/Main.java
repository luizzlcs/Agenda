package view;

import dao.ContatoDAO;
import model.Contato;

public class Main {

    public static void main(String[] args) {
        
//        Contato contato = new Contato();
//        contato.setId(2);
//        contato.setNome("Luiz Carlos");
//        contato.setFone("84988772241");
        
        ContatoDAO dao = new ContatoDAO();
//        dao.update(contato);
        
        System.out.println(dao.selectById(2));
    }
    
}
