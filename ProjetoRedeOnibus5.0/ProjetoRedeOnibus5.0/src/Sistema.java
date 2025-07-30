
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Sistema {

    //Listas que armazenam os Objetos de cada tipo
    private List<Viagem> viagens;
    private List<Onibus> onibus;
    private List<Linha> linhas;

    //Construtor da classe Sistema. Ele recebe três listas como parâmetros e as inicializa.
    public Sistema(List<Viagem> viagens, List<Onibus> onibus, List<Linha> linhas) {
        this.viagens = viagens;
        this.onibus = onibus;
        this.linhas = linhas;
        carregarDados(); // Carregar dados ao iniciar o sistema
    }

    //Metodo para carregar os dados de Viagens, Onibus e Linhas ao iniciar o sistema
    private void carregarDados() {
        carregarViagens();
        carregarOnibus();
        carregarLinhas();
    }

    //Metodo para carregar as viagens ao iniciar o sistema a partir do TXT
    private void carregarViagens() {
        try (BufferedReader reader = new BufferedReader(new FileReader("viagens.txt"))) {
            //Buffer para ler o arquivo "viagens.txt"
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(", ");
                String horarioSaida = dados[0].split(": ")[1];
                String dataSaida = dados[1].split(": ")[1];
                String dataChegada = dados[2].split(": ")[1];
                String valorPassagem = dados[3].split(": ")[1];
                int quantidadePassageiros = Integer.parseInt(dados[4].split(": ")[1]);

                //Cria um Objeto com os dados recolhidos                
                Viagem viagem = new Viagem(horarioSaida, dataSaida, dataChegada, valorPassagem);

                //Define a quantidade de passageiros que subiram na viagem
                viagem.setQuantidadePassageiros(quantidadePassageiros);

                //Adiciona o Objeto ao arrayList Onibus
                viagens.add(viagem);
            }
        } catch (IOException e) {

            //Caso o arquivo nao pode ser lido mostra mensagem de erro
            System.out.println("Erro ao carregar os dados das viagens: " + e.getMessage());
        }
    }

    //Metodo para carregar os onibus ao iniciar o sistema a partir do TXT
    private void carregarOnibus() {
        try (BufferedReader reader = new BufferedReader(new FileReader("onibus.txt"))) {
            //Buffer para ler o arquivo "onibus.txt"
            String linha;
            while ((linha = reader.readLine()) != null) {
                //Recolhe os dados do arquivo
                String[] dados = linha.split(", ");
                String placa = dados[0].split(": ")[1];
                String marca = dados[1].split(": ")[1];
                String modelo = dados[2].split(": ")[1];
                String cor = dados[3].split(": ")[1];
                int capacidade = Integer.parseInt(dados[4].split(": ")[1]);

                //Cria um Objeto com os dados recolhidos
                Onibus onibus = new Onibus(placa, marca, modelo, cor, capacidade);

                //Adiciona o Objeto ao arrayList Onibus
                this.onibus.add(onibus);
            }
        } catch (IOException e) {

            //Caso o arquivo nao pode ser lido mostra mensagem de erro
            System.out.println("Erro ao carregar os dados dos ônibus: " + e.getMessage());
        }
    }

    //Metodo para carregar as linhas ao iniciar o sistema a partir do TXT
    private void carregarLinhas() {
        try (BufferedReader reader = new BufferedReader(new FileReader("linhas.txt"))) {
            //Buffer para ler o arquivo "linhas.txt"
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(", ");
                int id = Integer.parseInt(dados[0].split(": ")[1]);
                String nomeRota = dados[1].split(": ")[1];
                int quantidadeParadas = Integer.parseInt(dados[2].split(": ")[1]);
                String inicio = dados[3].split(": ")[1];
                String fim = dados[4].split(": ")[1];

                //Cria um Objeto com os dados recolhidos
                Linha linhaObj = new Linha(id, nomeRota, quantidadeParadas, inicio, fim);

                //Adiciona o Objeto ao arrayList Onibus
                linhas.add(linhaObj);
            }
        } catch (IOException e) {

            //Caso o arquivo nao pode ser lido mostra mensagem de erro
            System.out.println("Erro ao carregar os dados das linhas: " + e.getMessage());
        }
    }

    //Metodo para Adicionar uma nova viagem ao sistema
    public void adicionarViagem(Scanner scanner) {
        try {
            //Solicitando ao usuario as informaçoes da viagem
            System.out.print("Horário de saida: ");
            String horarioSaida = scanner.nextLine().trim();
            //Correção de erro, caso o usuario nao coloque a informação o sistema mostra mensagem de erro
            if (horarioSaida.isEmpty()) {
                throw new IllegalArgumentException("Horário não pode ser vazio.");
            }
            System.out.print("Data de saída: ");
            String dataSaida = scanner.nextLine().trim();
            //Correção de erro, caso o usuario nao coloque a informação o sistema mostra mensagem de erro
            if (dataSaida.isEmpty()) {
                throw new IllegalArgumentException("Data de saída não pode ser vazia.");
            }
            System.out.print("Data de chegada: ");
            String dataChegada = scanner.nextLine().trim();
            //Correção de erro, caso o usuario nao coloque a informação o sistema mostra mensagem de erro
            if (dataChegada.isEmpty()) {
                throw new IllegalArgumentException("Data de chegada não pode ser vazia.");
            }
            System.out.print("Valor da passagem: ");
            String valorPassagem = scanner.nextLine().trim();
            valorPassagem = valorPassagem.replace(",", "."); // Substitui vírgula por ponto

            //Cria um Objeto com as informaçoes que o usuario informou
            Viagem novaViagem = new Viagem(horarioSaida, dataSaida, dataChegada, valorPassagem);

            //adiciona o Objeto ao arrayList de Viagens
            viagens.add(novaViagem);

            // Salvar dados automaticamente apos adicionar uma viagem
            salvarDados();

            //correção de erros
        } catch (NumberFormatException e) {
            System.err.println("Erro: Valor da passagem deve ser um número.");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    //Metodo para Adicionar um novo Onibus ao sistema
    public void adicionarOnibus(Scanner scanner) {
        try {
            //Solicitando ao usuario as informaçoes do Onibus
            System.out.print("Placa do ônibus: ");
            String placa = scanner.nextLine().trim();
            //Correção de erro, caso o usuario nao coloque a informação o sistema mostra mensagem de erro
            if (placa.isEmpty()) {
                throw new IllegalArgumentException("Placa não pode ser vazia.");
            }
            System.out.print("Marca do ônibus: ");
            String marca = scanner.nextLine().trim();
            //Correção de erro, caso o usuario nao coloque a informação o sistema mostra mensagem de erro
            if (marca.isEmpty()) {
                throw new IllegalArgumentException("Marca não pode ser vazia.");
            }
            System.out.print("Modelo do ônibus: ");
            String modelo = scanner.nextLine().trim();
            //Correção de erro, caso o usuario nao coloque a informação o sistema mostra mensagem de erro
            if (modelo.isEmpty()) {
                throw new IllegalArgumentException("Modelo não pode ser vazio.");
            }
            System.out.print("Cor do ônibus: ");
            String cor = scanner.nextLine().trim();
            //Correção de erro, caso o usuario nao coloque a informação o sistema mostra mensagem de erro
            if (cor.isEmpty()) {
                throw new IllegalArgumentException("Cor não pode ser vazia.");
            }
            System.out.print("Capacidade do ônibus: ");
            int capacidade = Integer.parseInt(scanner.nextLine().trim());
            //Correção de erro, caso o usuario nao coloque a informação o sistema mostra mensagem de erro
            if (capacidade <= 0) {
                throw new IllegalArgumentException("Capacidade deve ser um número positivo.");
            }

            //Cria um Objeto com as informaçoes que o usuario informou
            onibus.add(new Onibus(placa, marca, modelo, cor, capacidade));

            //Salvar dados automaticamente apos adicionar um onibus
            salvarDados();

            //Correção de erros
        } catch (NumberFormatException e) {
            System.err.println("Erro: Capacidade deve ser um número.");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    //Metodo para Adicionar uma nova Linha ao sistema
    public void adicionarLinha(Scanner scanner) {
        try {
            //Solicitando ao usuario as informaçoes da Linha
            System.out.print("ID da linha: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Nome da rota: ");
            String nomeRota = scanner.nextLine().trim();
            //Correção de erro, caso o usuario nao coloque a informação o sistema mostra mensagem de erro
            if (nomeRota.isEmpty()) {
                throw new IllegalArgumentException("Nome da rota não pode ser vazio.");
            }
            System.out.print("Quantidade de paradas: ");
            int quantidadeParadas = Integer.parseInt(scanner.nextLine().trim());
            //Correção de erro, caso o usuario nao coloque a informação o sistema mostra mensagem de erro
            if (quantidadeParadas <= 0) {
                throw new IllegalArgumentException("Quantidade de paradas deve ser um número positivo.");
            }
            System.out.print("Início da Linha: ");
            String inicio = scanner.nextLine().trim();
            //Correção de erro, caso o usuario nao coloque a informação o sistema mostra mensagem de erro
            if (inicio.isEmpty()) {
                throw new IllegalArgumentException("Início não pode ser vazio.");
            }
            System.out.print("Final da Linha: ");
            String fim = scanner.nextLine().trim();
            //Correção de erro, caso o usuario nao coloque a informação o sistema mostra mensagem de erro
            if (fim.isEmpty()) {
                throw new IllegalArgumentException("Final não pode ser vazio.");
            }

            //Cria um Objeto com as informaçoes que o usuario informou
            linhas.add(new Linha(id, nomeRota, quantidadeParadas, inicio, fim));

            // Salvar dados automaticamente apos adicionar uma linha
            salvarDados();

            //Correção de erros
        } catch (NumberFormatException e) {
            System.err.println("Erro: ID e Quantidade de paradas devem ser números.");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }

    }

    //Metodo para salvar os dados nas listas em arquivo de texto
    public void salvarDados() {
        salvarListaEmArquivo("viagens.txt", viagens);
        salvarListaEmArquivo("onibus.txt", onibus);
        salvarListaEmArquivo("linhas.txt", linhas);
        System.out.println("Dados salvos com sucesso!");
    }

    //Metodo que salva as informações nos arquivos usando <T>, que aceita qualquer tipo de lista
    private <T> void salvarListaEmArquivo(String nomeArquivo, List<T> lista) {
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            for (T item : lista) { //Coleta cada informação e salva no arquivo
                writer.write(item.toString() + "\n"); //Converte o Item em String para salvar
            }
            //Correção de erro, exibe mensagem de erro
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    //Metodo para Iniciar Viagem, Solicitando ao usuario se ele quer uma nova viagem ou uma ja feita
    public void iniciarViagem(Scanner scanner) {

        //Exibe ao usuario as opções
        System.out.println("1. Nova Viagem");
        System.out.println("2. Viagem Anterior");

        //Lê a opção que o usuario escolheu
        int opcao = Integer.parseInt(scanner.nextLine());

        //Verifica se o arrayList do Onibus e da Linha estão Vazio
        if (onibus.isEmpty() || linhas.isEmpty()) {
            System.out.println("Não há linhas ou ônibus suficientes.");
            return;
        }

        //Usa a escolha do usuário para saber qual ação seguir
        switch (opcao) {
            case 1:
                //Adicionar informações da Viagem
                adicionarViagem(scanner);

                //Mostrar quais onibus estão disponiveis
                System.out.println("Ônibus disponíveis:");
                for (int i = 0; i < onibus.size(); i++) {
                    System.out.println((i + 1) + ". " + onibus.get(i));
                }
                //Solicita ao usuario que escolha algum deles
                System.out.print("Escolha o ônibus (número): ");
                int onibusEscolhido = Integer.parseInt(scanner.nextLine()) - 1;

                //Mostrar quais onibus estão disponiveis
                System.out.println("Linhas disponíveis:");
                for (int i = 0; i < linhas.size(); i++) {
                    System.out.println((i + 1) + ". " + linhas.get(i));
                }
                //Solicita ao usuario que escolha algum deles
                System.out.print("Escolha a linha (número): ");
                int linhaEscolhida = Integer.parseInt(scanner.nextLine()) - 1;

                //Verificar se as escolhas existem
                if (onibusEscolhido < 0 || onibusEscolhido >= onibus.size() || linhaEscolhida < 0 || linhaEscolhida >= linhas.size()) {
                    System.out.println("Escolha inválida.");
                    return;
                }

                //Atribui o Onibus e Linha escolhido a nova viagem
                Viagem novaViagem = viagens.get(viagens.size() - 1);
                novaViagem.setOnibus(onibus.get(onibusEscolhido));
                novaViagem.setLinha(linhas.get(linhaEscolhida));

                //Criando uma instancia da classe SimulacaoViagem e chamando o metodo correto
                SimulacaoViagem simulacao = new SimulacaoViagem();

                //Chama o metodo que simula os passageiros entrando e saindo
                simulacao.simularPassageiros(novaViagem);
                break;
            case 2:
                //Se o usuário escolher "2"
                //Verifica se o Array Viagens esta vazio, se Vazio mostra mensagem
                if (viagens.isEmpty()) {
                    System.out.println("Não há viagens feitas suficientes.");
                    return;
                }
                //Mostra as viagens ja feitas para ele escolher
                System.out.println("Escolha a Viagem repetida:");
                for (int i = 0; i < viagens.size(); i++) {
                    System.out.println((i + 1) + ". " + viagens.get(i));
                }
                //Solicita que o usuario escolha alguma delas
                System.out.print("Escolha uma das viagens (número): ");
                int viagemEscolhida = Integer.parseInt(scanner.nextLine()) - 1;

                //Mostrar quais onibus estão disponiveis
                System.out.println("Ônibus disponíveis:");
                for (int i = 0; i < onibus.size(); i++) {
                    System.out.println((i + 1) + ". " + onibus.get(i));
                }
                //Solicita ao usuario que escolha algum deles
                System.out.print("Escolha o ônibus (número): ");
                int onibusEscolhido2 = Integer.parseInt(scanner.nextLine()) - 1;

                //Mostrar quais linhas estão disponiveis
                System.out.println("Linhas disponíveis:");
                for (int i = 0; i < linhas.size(); i++) {
                    System.out.println((i + 1) + ". " + linhas.get(i));
                }
                //Solicita ao usuario que escolha alguma delas
                System.out.print("Escolha a linha (número): ");
                int linhaEscolhida2 = Integer.parseInt(scanner.nextLine()) - 1;

                //Verificar se as escolhas existem
                if (onibusEscolhido2 < 0 || onibusEscolhido2 >= onibus.size() || linhaEscolhida2 < 0 || linhaEscolhida2 >= linhas.size()) {
                    System.out.println("Escolha inválida.");
                    return;
                }

                //Atribui o Onibus e Linha escolhido a nova viagem
                Viagem viagem = viagens.get(viagens.size() - 1);
                viagem.setOnibus(onibus.get(onibusEscolhido2));
                viagem.setLinha(linhas.get(linhaEscolhida2));

                //Lê a escolha do usuário sobre qual viagem repetir
                int ViagemEscolhida = Integer.parseInt(scanner.nextLine()) - 1;

                //Verifica se a viagem escolhida existe
                if (ViagemEscolhida < 0 || ViagemEscolhida >= viagens.size()) {
                    //Correção de erro para caso a viagem nao existir
                    System.out.println("Viagem inválida.");
                    return;
                }

                //Criando uma instancia da classe SimulacaoViagem e chamando o metodo correto
                SimulacaoViagem simulacao2 = new SimulacaoViagem();

                //Chama o metodo que simula os passageiros entrando e saindo
                simulacao2.simularPassageiros(viagem);
                break;
            default:
                //Correção de erro, caso a opção escolhida nao exista.
                System.out.println("Opção inválida.");
        }
    }

    //Metodo que simula a entrada e saida de passageiros
    public class SimulacaoViagem {

        //Atributos da classe para armazenar os totais
        private int totalPassageirosEntraram = 0;
        private int totalPassageirosNaoEntraram = 0;

        //Metodo para simular a entrada e saída de passageiros
        public void simularPassageiros(Viagem viagem) {
            //Random para gerar numeros aleatorios de passageiros
            Random random = new Random();
            //Coleta a quantidade de paradas da Linha
            int paradas = linhas.get(0).getQuantidadeParadas();
            //Coleta a capacidade do Onibus
            int capacidadeOnibus = onibus.get(0).getCapacidade();
            int passageirosNoOnibus = 0;
            int passageirosNaoEntraram = 0;

            //Loop que simula cada parada
            for (int i = 1; i <= paradas; i++) {
                // Numero aleatorio de passageiros entrando
                int passageirosEntrando = random.nextInt(10);
                //Numero aleatorio de passageiros saindo
                int passageirosSaindo = random.nextInt(passageirosNoOnibus + 1);

                //Atualiza a quantidade de passageiros que estão no Onibus
                passageirosNoOnibus -= passageirosSaindo;

                //Verifica se a quantidade de passageiros entrando é maior que a capacidade do Onibus
                if (passageirosNoOnibus + passageirosEntrando > capacidadeOnibus) {
                    //Calcula quantos passageiros não consiguiram entrar 
                    passageirosNaoEntraram = (passageirosNoOnibus + passageirosEntrando) - capacidadeOnibus;

                    //Ajusta os passageiros que conseguiram entrar no Onibus
                    passageirosEntrando = capacidadeOnibus - passageirosNoOnibus;

                    //Onibus esta Cheio
                    passageirosNoOnibus = capacidadeOnibus;

                    //Atualizando o contador
                    totalPassageirosNaoEntraram += passageirosNaoEntraram;
                    totalPassageirosEntraram += passageirosEntrando;

                    //Exibe as informações da parada
                    System.out.println("Parada " + i + ": " + passageirosEntrando
                            + " passageiros entraram, " + passageirosSaindo
                            + " passageiros saíram. Total: " + passageirosNoOnibus);
                    System.out.println(passageirosNaoEntraram + " passageiros não conseguiram entrar.");
                } else {
                    passageirosNoOnibus += passageirosEntrando;
                    //Atualizando o contador
                    totalPassageirosEntraram += passageirosEntrando;
                    System.out.println("Parada " + i + ": " + passageirosEntrando
                            + " passageiros entraram, " + passageirosSaindo
                            + " passageiros saíram. Total: " + passageirosNoOnibus);
                }
            }

            //Finalizando a viagem com onibus vazio
            if (passageirosNoOnibus > 0) {
                System.out.println("Chegada final: Todos os " + passageirosNoOnibus + " passageiros saíram.");
                passageirosNoOnibus = 0;
            }

            //Atualizar a quantidade de passageiros na viagem
            viagem.setQuantidadePassageiros(totalPassageirosEntraram);
        }

        //Metodo para exibir o total de passageiros que entraram e não entraram
        public void exibirQuantidadeDePassageirosPorViagem() {
            System.out.println("Total de passageiros que entraram na viagem: " + totalPassageirosEntraram);
            System.out.println("Total de passageiros que não conseguiram entrar: " + totalPassageirosNaoEntraram);
        }
    }
}
