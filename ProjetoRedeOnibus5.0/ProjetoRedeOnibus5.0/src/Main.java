import java.util.ArrayList;
import java.util.Scanner;

//A classe principal que contém o método main
public class Main {

    public static void main(String[] args) {
        //Criando listas vazias para armazenar objetos do tipo Viagem, Onibus e Linha
        ArrayList<Viagem> viagens = new ArrayList<>();
        ArrayList<Onibus> onibus = new ArrayList<>();
        ArrayList<Linha> linhas = new ArrayList<>();

        //Criando um objeto do tipo Sistema, passando as listas como parâmetros
        Sistema sistema = new Sistema(viagens, onibus, linhas);

        //Criando um objeto Scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        //Variável booleana para controlar o loop do menu
        boolean iniciado = true;

        //Laço de repetição que mantém o menu em execução até que o usuário escolha sair
        while (iniciado) {
            //Exibe o menu de opções para o usuário
            System.out.println("Menu de Opções:");
            System.out.println("1. Adicionar Ônibus");
            System.out.println("2. Adicionar Linha");
            System.out.println("3. Iniciar Viagem Nova ou Antiga");
            System.out.println("4. Exibir Quantidade de Passageiros por Viagem");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            
            //Lê a escolha do usuário
            String escolha = scanner.nextLine();

            //Usa a escolha do usuário para saber qual ação seguir
            switch (escolha) {
                case "1":
                    //Se o usuário escolher "1", chama o método para adicionar um onibus
                    sistema.adicionarOnibus(scanner);
                    break;
                case "2":
                    //Se o usuario escolher "2", chama o metodo para adicionar uma linha
                    sistema.adicionarLinha(scanner);
                    break;
                case "3":
                    //Se o usuario escolher "3", chama o metodo para iniciar uma viagem
                    sistema.iniciarViagem(scanner);
                    break;
                case "4":
                    //Se o usuario escolher "4", exibe as viagens e suas quantidades de passageiros
                    for (Viagem viagem : viagens) {
                        System.out.println("Viagem: " + viagem);
                    }
                    break;
                case "5":
                    //Se o usuário escolher "5", sai do loop e encerra o programa
                    iniciado = false;
                    break;
                default:
                    //Se o usuário digitar uma opção invalida, exibe uma mensagem
                    System.out.println("Opção inválida!");
            }
        }

        //Fecha o scanner quando o programa termina
        scanner.close();
    }
}
