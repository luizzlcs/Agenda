package controller;

import dao.ContatoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Contato;

public class ContatoCTRL {

    public DefaultTableModel listarTodosContatos(DefaultTableModel dtm, String texto) {
        DefaultTableModel model = dtm;
        model.setRowCount(0);
        String filtro = texto.trim();
        ContatoDAO dao = new ContatoDAO();
        List<Contato> contatos = filtro.equals("") ? dao.selectAll() : dao.selectByLike(filtro);
        for (Contato contato : contatos) {
            int id = contato.getId();
            String nome = contato.getNome();
            String fone = mascaraFone(contato.getFone());
            Object[] linha = {id, nome, fone};
            model.addRow(linha);
        }
        return model;
    }
    
    private String mascaraFone(String fone) {
        int qtd = fone.length();
        String novoFone = "";
        for (int i = 0; i < qtd; i++) {
            if (i == 0) {
                novoFone += "(" + fone.charAt(i);
            } else if (i == 2) {
                novoFone += ") " + fone.charAt(i);
            } else if ((qtd == 10 && i == 6) || (qtd == 11 && i == 7)) {
                novoFone += "-" + fone.charAt(i);
            } else {
                novoFone += fone.charAt(i);
            }
        }
        return novoFone;
    }
    
}
