  <h1>📝 Lista de Tarefas em Java (Swing + Arquivo .txt)</h1>

  <p>Este é um aplicativo simples de lista de tarefas com interface gráfica feito em <strong>Java</strong> utilizando <strong>Swing</strong>. As tarefas são armazenadas em um arquivo <code>tarefas.txt</code>, permitindo persistência dos dados entre sessões.</p>

  <h2>🎯 Funcionalidades</h2>
  <ul>
    <li>✅ Adicionar tarefas com título e descrição</li>
    <li>✅ Marcar tarefas como concluídas</li>
    <li>✅ Limpar todas as tarefas com confirmação</li>
    <li>✅ Persistência em arquivo (<code>tarefas.txt</code>)</li>
    <li>✅ Interface moderna com <code>NimbusLookAndFeel</code></li>
  </ul>

  <h2>🗂️ Estrutura do Projeto</h2>
  <pre><code>/src
├── model
│   └── Tarefa.java         # Classe que representa uma tarefa
├── dao
│   └── TarefaDAO.java      # Responsável por salvar/carregar tarefas do arquivo
└── view
    └── JTarefa.java        # Interface gráfica principal do sistema
  </code></pre>

  <h2>📦 Requisitos</h2>
  <ul>
    <li>Java 8 ou superior</li>
    <li>IDE como IntelliJ, Eclipse, NetBeans ou execução via terminal</li>
  </ul>
