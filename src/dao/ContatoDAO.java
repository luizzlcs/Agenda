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
        try {
            db.open();
            String sql = "INSERT INTO tb_contatos (con_nome, con_fone) VALUES (?, ?)";
            PreparedStatement ps = db.getConnetion().prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getFone());
            ps.executeUpdate();
        } catch(SQLException error) {
            System.out.println("ERRO: " + error);
        } finally {
            db.close();
        }
    }
    
    public void delete(Contato contato) {
        try {
            db.open();
            String sql = "DELETE FROM tb_contatos WHERE con_id = ?";
            PreparedStatement ps = db.getConnetion().prepareStatement(sql);
            ps.setInt(1, contato.getId());
            ps.executeUpdate();
        } catch(SQLException error) {
            System.out.println("ERRO: " + error);
        } finally {
            db.close();
        }
    }
    
    public void update(Contato contato) {
        try {
            db.open();
            String sql = "UPDATE tb_contatos SET con_nome = ?, con_fone = ? WHERE con_id = ?";
            PreparedStatement ps = db.getConnetion().prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getFone());
            ps.setInt(3, contato.getId());
            ps.executeUpdate();
        } catch(SQLException error) {
            System.out.println("ERRO: " + error);
        } finally {
            db.close();
        }
    }
    
    public List<Contato> selectAll() {
        List<Contato> contatos = new ArrayList();
        try {
            db.open();
            String sql = "SELECT * FROM tb_contatos";
            PreparedStatement ps = db.getConnetion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getInt("con_id"));
                contato.setNome(rs.getString("con_nome"));
                contato.setFone(rs.getString("con_fone"));
                contatos.add(contato);
            }
        } catch(SQLException error) {
            System.out.println("ERRO: " + error);
        } finally {
            db.close();
            return null;
        }
    }
    
    public Contato selectById(int id) {
        Contato contato = new Contato();
        try {
            db.open();
            String sql = "SELECT * FROM tb_contatos WHERE con_id = ?";
            PreparedStatement ps = db.getConnetion().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                contato.setId(rs.getInt("con_id"));
                contato.setNome(rs.getString("con_nome"));
                contato.setFone(rs.getString("con_fone"));
            }     
        } catch (SQLException error) {
            System.out.println("ERRO: " + error);
        } finally {
            db.close();
            return contato;
        }
    }
    
    public List<Contato> selectByLike(String texto) {
        return null;
    }
    
}
