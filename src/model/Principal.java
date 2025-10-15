package model;

import dao.JogoDao;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("-----------------------Bem Vindo à Central de Jogos-----------------------");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar os Jogos");
            System.out.println("3. Excluir um Jogo");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine(); // consome o \n deixado pelo nextInt()

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar Jogo");

                    System.out.print("Insira o nome do Jogo: ");
                    String nome = sc.nextLine();

                    System.out.print("Insira o gênero do Jogo: ");
                    String genero = sc.nextLine();

                    System.out.print("Insira a data de lançamento (no formato yyyy-MM-dd): ");
                    String data = sc.nextLine();

                    System.out.print("Insira a nota do Jogo: ");
                    String notaStr = sc.nextLine().replace(",", "."); // permite digitar com vírgula
                    double nota = Double.parseDouble(notaStr);

                    // Criar objeto Jogo
                    Jogo jogoNovo = new Jogo();
                    jogoNovo.setNome(nome);
                    jogoNovo.setGenero(genero);
                    jogoNovo.setDataLancamento(java.sql.Date.valueOf(data)); // converte String para Date
                    jogoNovo.setNota(nota);

                    // DAO para cadastrar
                    JogoDao dao = new JogoDao();
                    dao.inserirJogo(jogoNovo);

                    System.out.println("Jogo cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("Listar Jogo");
                    break;

                case 3:
                    System.out.println("Excluir Jogo");
                    break;

                case 4:
                    System.out.println("Saindo do Jogo... Bye!");
                    break;

                default:
                    System.out.println("Opção Inválida, Jogador. Tente Novamente!");
                    break;
            }

            System.out.println();
        }

        sc.close();
    }
}
