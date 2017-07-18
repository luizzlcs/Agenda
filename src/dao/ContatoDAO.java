package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Contato;
import util.DataBase;

public class ContatoDAO {

    private final DataBase db;
    
    public ContatoDAO() {
        db = new DataBase();
    }
    
    public void insert(Contato contato) {
        String sql = "INSERT INTO tb_contatos (con_nome, con_fone) VALUES (?, ?)";
        try {
            db.open();
            PreparedStatement ps = db.getConnetion().prepareStatement(sql);
            ps.setString(0, contato.getNome());
            ps.setString(1, contato.getFone());
            ps.executeUpdate();
        } catch(SQLException error) {
            System.out.println("ERRO: " + error);
        } finally {
            db.close();
        }
    }
    
    public List<Contato> selectAll() {
        String sql = "SELECT * FROM tb_contatos";
        try {
            db.open();
            PreparedStatement ps = db.getConnetion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Contato> contatos = new ArrayList<>();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getInt("con_id"));
                contato.setNome(rs.getString("con_nome"));
                contato.setFone(rs.getString("con_fone"));
                contatos.add(contato);
            }
            db.close();
            return contatos;
        } catch(SQLException error) {
            System.out.println("ERRO: " + error);
        }
        db.close();
        return null;
    }
    
}
