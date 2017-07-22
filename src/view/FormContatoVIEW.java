package view;

import controller.ContatoCTRL;
import dao.ContatoDAO;
import helper.FoneHELPER;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import model.Contato;

public class FormContatoVIEW extends JFrame {

    private JLabel lbNome, lbFone;
    private JTextField tfNome;
    private JFormattedTextField tfFone;
    private JButton btSalvar;
    private MaskFormatter mascaraFone;
    private DefaultTableModel tmContatos;
    private Contato contato;
    
    public FormContatoVIEW(Contato contato, DefaultTableModel modelo) {
        this.tmContatos = modelo;
        this.contato = contato;
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        setLayout(null);
        setResizable(false);
        setBounds(0, 0, 380, 120);
        
        lbNome = new JLabel("Nome:");
        lbNome.setBounds(10, 10, 80, 25);
        add(lbNome);
        
        lbFone = new JLabel("Fone:");
        lbFone.setBounds(170, 10, 80, 25);
        add(lbFone);
        
        btSalvar = new JButton(new ImageIcon("save.png"));
        btSalvar.setBorder(null);
        btSalvar.setBackground(new Color(238, 238, 238));
        btSalvar.setBounds(320, 35, 32, 32);
        add(btSalvar);
        
        tfNome = new JTextField();
        tfNome.setBounds(10, 35, 150, 32);
        add(tfNome);
        
        try {
            if (contato == null) {
                setIconImage(new ImageIcon("add.png").getImage());
                setTitle("Novo Contato");
                mascaraFone = new MaskFormatter("(##) ####-####");
                tfFone = new JFormattedTextField(mascaraFone);
            } else {
                setIconImage(new ImageIcon("edit.png").getImage());
                setTitle("Editar Contato");
                mascaraFone = new MaskFormatter("(##) ####-####");
                tfFone = new JFormattedTextField(mascaraFone);
                tfNome.setText(contato.getNome());
                tfFone.setText(contato.getFone());
            }
        } catch(ParseException error) {
            System.out.println("Erro: " + error);
        } finally {
            tfFone.setBounds(170, 35, 130, 32);
            add(tfFone);
        }
    }

    private void definirEventos() {
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contato == null) {
                    contato = new Contato();
                }
                contato.setNome(tfNome.getText());
                contato.setFone(tfFone.getText());
                new ContatoCTRL().salvar(contato);
                carregarTabela();
                setVisible(false);
            }
        });
    }
    
    private void carregarTabela() {
        ContatoDAO dao = new ContatoDAO();
        tmContatos.setRowCount(0);
        for (Contato c : dao.selectAll()) {
            Object[] linha = {c.getId(), c.getNome(), FoneHELPER.formatar(c.getFone())};
            tmContatos.addRow(linha);
        }
    }
    
    public void abrir() {
        FormContatoVIEW janela = new FormContatoVIEW(contato, tmContatos);
        Dimension dimensao = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimensao.width - janela.getSize().width) / 2;
        int y = (dimensao.height - janela.getSize().height) / 2;
        janela.setLocation(x, y);
        janela.setVisible(true);
    }
    
}
