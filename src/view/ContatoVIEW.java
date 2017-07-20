package view;

import dao.ContatoDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Contato;

public class ContatoVIEW extends JFrame {

    private JTextField tfPesquisar;
    private JButton btNovo, btEditar, btRemover;
    private JTable tbContatos;
    private DefaultTableModel tmContatos;
    private JScrollPane spContatos;
    
    public ContatoVIEW() {
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        setLayout(null);
        setTitle("Agenda");
        setResizable(false);
        setIconImage(new ImageIcon("contatos.png").getImage());
        setBounds(0, 0, 400, 400);
        
        tfPesquisar = new JTextField();
        tfPesquisar.setBounds(10, 10, 200, 32);
        add(tfPesquisar);
        
        btNovo = new JButton(new ImageIcon("add.png"));
        btNovo.setBorder(null);
        btNovo.setBackground(new Color(238, 238, 238));
        btNovo.setBounds(250, 10, 32, 32);
        add(btNovo);
        
        btEditar = new JButton(new ImageIcon("edit.png"));
        btEditar.setBorder(null);
        btEditar.setBackground(new Color(238, 238, 238));
        btEditar.setBounds(300, 10, 32, 32);
        add(btEditar);
        
        btRemover = new JButton(new ImageIcon("del.png"));
        btRemover.setBorder(null);
        btRemover.setBackground(new Color(238, 238, 238));
        btRemover.setBounds(350, 10, 32, 32);
        add(btRemover);
        
        Object[] cabecalho = {"ID", "NOME", "FONE"};
        tmContatos = new DefaultTableModel(cabecalho, 0) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };
        
        carregar();
        
        tbContatos = new JTable(tmContatos);
        tbContatos.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbContatos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbContatos.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbContatos.getTableHeader().setReorderingAllowed(false);
        tbContatos.getTableHeader().setResizingAllowed(false);
        
        spContatos = new JScrollPane(tbContatos);
        spContatos.setBounds(10, 70, 375, 280);
        add(spContatos);
    }
    
    private void carregar() {
        tmContatos.setRowCount(0);
        for (Contato contato : new ContatoDAO().selectAll()) {
            Object[] linha = {contato.getId(), contato.getNome(), contato.getFone()};
            tmContatos.addRow(linha);
        }
    }
    
    public static void main(String[] args) {
        ContatoVIEW janela = new ContatoVIEW();
        janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension dimensao = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimensao.width - janela.getSize().width) / 2;
        int y = (dimensao.height - janela.getSize().height) / 2;
        janela.setLocation(x, y);
        janela.setVisible(true);
    }
    
}
