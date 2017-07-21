package view;

import controller.ContatoCTRL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class ContatoVIEW extends JFrame {

    private JTextField tfPesquisar;
    private JButton btNovo, btEditar, btRemover;
    private JTable tbContatos;
    private DefaultTableModel tmContatos;
    private JScrollPane spContatos;
    
    public ContatoVIEW() {
        inicializarComponentes();
        definirEventos();
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
        
        carregarTabela();
        
        DefaultTableCellRenderer alinharDireita = new DefaultTableCellHeaderRenderer();
        alinharDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        DefaultTableCellRenderer alinharCentro = new DefaultTableCellHeaderRenderer();
        alinharCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        tbContatos = new JTable(tmContatos);
        tbContatos.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbContatos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbContatos.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbContatos.getTableHeader().setReorderingAllowed(false);
        tbContatos.getTableHeader().setResizingAllowed(false);
        
        tbContatos.getColumnModel().getColumn(0).setCellRenderer(alinharCentro);
        tbContatos.getColumnModel().getColumn(2).setCellRenderer(alinharDireita);
        
        spContatos = new JScrollPane(tbContatos);
        spContatos.setBounds(10, 70, 375, 280);
        add(spContatos);
    }
    
    private void definirEventos() {
        tfPesquisar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                carregarTabela();
            }
        });
    }
    
    private void carregarTabela() {
        tmContatos = new ContatoCTRL().listarTodosContatos(tmContatos, tfPesquisar.getText());
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
