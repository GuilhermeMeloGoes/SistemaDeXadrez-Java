# SistemaDeXadrez-Java

Um sistema em Java que simula um jogo de xadrez, permitindo movimentação das peças, detecção de movimentos válidos e condições de xeque e xeque-mate.

## 🧩 Estrutura do Projeto

```
SistemaDeXadrez-Java/
│
├── src/
│   ├── model/           # Classes que representam o tabuleiro e as peças
│   ├── controller/      # Lógica de movimentação e validação
│   └── view/            # Interface de usuário (console ou GUI)
│
├── tests/               # (Opcional) Testes unitários
│
├── lib/                 # (Opcional) Bibliotecas externas
│
├── .gitignore
└── README.md
```

## ⚙️ Tecnologias

- **Java 8+** (ou versão compatível)

## 🚀 Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/GuilhermeMeloGoes/SistemaDeXadrez-Java.git
   ```
2. Entre na pasta:
   ```bash
   cd SistemaDeXadrez-Java
   ```
3. Compile os arquivos `.java`:
   ```bash
   javac -d out src/**/*.java
   ```
4. Execute a classe principal:
   ```bash
   java -cp out seu.pacote.Main
   ```

Caso use **IDE** (IntelliJ, Eclipse, NetBeans), importe o projeto e execute via `Main.java`.

## ✅ Funcionalidades

- Representação do tabuleiro (8×8 casas)
- Movimento de peças (Peão, Torre, Cavalo, Bispo, Rainha, Rei)
- Validação de regras (o que é permitido ou não)
- Detecção de xeque e xeque-mate
- Interface simplificada (console ou GUI básica)

## 📝 Exemplo de uso (modo console)

```
Bem-vindo ao Xadrez Java!
Turno do jogador Branco.
Digite a movimentação (ex: e2 e4):
e2 e4
...
Xeque!
```

## 🤝 Contribuição

 - Fique a vontadade para compartilhar melhorias e trazer novidades para o projeto, até mais.
 - Caso queria entrar em contato para qualquer dúvida:
 - Email: [guimelogoesDev@gmail.com]
 - Telefone: [79 999805-0709]
