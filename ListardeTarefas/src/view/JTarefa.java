package view;

import dao.TarefaDAO;
import model.Tarefa;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JTarefa extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField campoTitulo;
    private JTextField campoDescricao;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaTarefas;

    private ArrayList<Tarefa> tarefas;
    private final TarefaDAO dao = new TarefaDAO();

    public JTarefa() {
        setTitle("📋 Lista de Tarefas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        tarefas = dao.carregarTarefas();

        inicializarComponentes();
        atualizarLista();
    }

    private void inicializarComponentes() {
        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new BoxLayout(painelSuperior, BoxLayout.Y_AXIS));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        painelSuperior.setBackground(new Color(245, 245, 245));

        JLabel tituloLabel = new JLabel("Título:");
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        campoTitulo = new JTextField(25);
        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        campoDescricao = new JTextField(25);

        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.setBackground(new Color(60, 179, 113));
        botaoAdicionar.setForeground(Color.WHITE);
        botaoAdicionar.setFocusPainted(false);
        botaoAdicionar.setFont(new Font("SansSerif", Font.BOLD, 13));

        JButton botaoConcluir = new JButton("Concluir");
        botaoConcluir.setBackground(new Color(70, 130, 180));
        botaoConcluir.setForeground(Color.WHITE);
        botaoConcluir.setFocusPainted(false);
        botaoConcluir.setFont(new Font("SansSerif", Font.BOLD, 13));
        
        JButton botaoLimpar = new JButton(" Limpar");
        botaoLimpar.setBackground(new Color(255, 53, 69));
        botaoLimpar.setForeground(Color.WHITE);
        botaoLimpar.setFocusPainted(false);
        botaoLimpar.setFont(new Font("SansSerif", Font.BOLD, 13));
        botaoLimpar.addActionListener(e -> limparCampos());
        

        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linha1.setBackground(new Color(245, 245, 245));
        linha1.add(tituloLabel);
        linha1.add(campoTitulo);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linha2.setBackground(new Color(245, 245, 245));
        linha2.add(descricaoLabel);
        linha2.add(campoDescricao);

        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha3.setBackground(new Color(245, 245, 245)); 
        linha3.add(botaoAdicionar);
        linha3.add(botaoConcluir);
        linha3.add(botaoLimpar);

        botaoAdicionar.addActionListener(e -> adicionarTarefa());
        botaoConcluir.addActionListener(e -> concluirTarefa());

        painelSuperior.add(linha1);
        painelSuperior.add(linha2);
        painelSuperior.add(Box.createVerticalStrut(10));
        painelSuperior.add(linha3);

        getContentPane().add(painelSuperior, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        listaTarefas = new JList<>(modeloLista);
        listaTarefas.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(listaTarefas);
        scroll.setBorder(BorderFactory.createTitledBorder("Tarefas"));
        getContentPane().add(scroll, BorderLayout.CENTER);
    }

    private void limparCampos() {
    	   int confirmacao = JOptionPane.showConfirmDialog(
    		        this,
    		        "Tem certeza que deseja apagar todas as tarefas?",
    		        "Confirmar Limpeza",
    		        JOptionPane.YES_NO_OPTION
    		    );

    		    if (confirmacao == JOptionPane.YES_OPTION) {
    		        tarefas.clear(); 
    		        dao.salvarTarefas(tarefas);
    		        atualizarLista();
    		    }
    		}



	private void adicionarTarefa() {
        String titulo = campoTitulo.getText().trim();
        String descricao = campoDescricao.getText().trim();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo 'Título' não pode estar vazio.");
            return;
        }

        Tarefa novaTarefa = new Tarefa(titulo, descricao);
        tarefas.add(novaTarefa);
        dao.salvarTarefas(tarefas);
        atualizarLista();

        campoTitulo.setText("");
        campoDescricao.setText("");
    }

    private void concluirTarefa() {
        int indiceSelecionado = listaTarefas.getSelectedIndex();

        if (indiceSelecionado >= 0 && indiceSelecionado < tarefas.size()) {
            tarefas.get(indiceSelecionado).setConcluida(true);
            dao.salvarTarefas(tarefas);
            atualizarLista();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para marcar como concluída.");
        }
    }

    private void atualizarLista() {
        modeloLista.clear();
        for (Tarefa tarefa : tarefas) {
            String status = tarefa.isConcluida() ? "✅ " : "🕓 ";
            modeloLista.addElement(status + tarefa.getTitulo() + " - " + tarefa.getDescricao());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JTarefa().setVisible(true));
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new JTarefa().setVisible(true));

    }
}
   
