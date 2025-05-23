package dao;

import model.Tarefa;

import java.io.*;
import java.util.ArrayList;

public class TarefaDAO {

    private static final String CAMINHO_ARQUIVO = "tarefas.txt";

    
    public void salvarTarefas(ArrayList<Tarefa> tarefas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            for (Tarefa t : tarefas) {
                // título|descrição|concluída
                writer.write(t.getTitulo() + "|" + t.getDescricao() + "|" + t.isConcluida());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Tarefa> carregarTarefas() {
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) return tarefas;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("\\|");
                if (partes.length >= 3) {
                    String titulo = partes[0];
                    String descricao = partes[1];
                    boolean concluida = Boolean.parseBoolean(partes[2]);
                    Tarefa tarefa = new Tarefa(titulo, descricao);
                    tarefa.setConcluida(concluida);
                    tarefas.add(tarefa);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tarefas;
    }
}
