package controller;

import dao.ContatoDAO;
import helper.FoneHELPER;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
            String fone = FoneHELPER.formatar(contato.getFone());
            Object[] linha = {id, nome, fone};
            model.addRow(linha);
        }
        return model;
    }
    
    public DefaultTableModel excluirContatos(DefaultTableModel modelo, JTable tabela) {
        int[] linhas = tabela.getSelectedRows();
        ContatoDAO dao = new ContatoDAO();
        for (int i = (linhas.length - 1); i >= 0; i--) {
            Contato contato = new Contato();
            contato.setId((int) tabela.getValueAt(linhas[i], 0));
            contato.setNome((String) tabela.getValueAt(linhas[i], 1));
            contato.setFone((String) tabela.getValueAt(linhas[i], 2));
            modelo.removeRow(i);
            dao.delete(contato);
        }
        return modelo;
    }

    public void salvar(Contato contato) {
        contato.setNome(contato.getNome().trim().toUpperCase());
        contato.setFone(FoneHELPER.limpar(contato.getFone()).trim());
        if (contato.getNome().equals("") || contato.getFone().equals("")) {
            JOptionPane.showMessageDialog(null, "Campos Obrigatorios");
        } else {
            if (contato.getId() == 0) {
                new ContatoDAO().insert(contato);
            } else {
                new ContatoDAO().update(contato);
            }
        }
    }
    
}
