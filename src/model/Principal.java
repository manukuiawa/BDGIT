package model;

import java.util.List;
import java.util.Scanner;

import dao.JogoDao;

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
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar Jogos---------------");

                    System.out.print("Insira o nome do Jogo: ");
                    String nome = sc.nextLine();

                    System.out.print("Insira o gênero do Jogo: ");
                    String genero = sc.nextLine();

                    System.out.print("Insira a data de lançamento (no formato yyyy-MM-dd): "); //o formato do EUA
                    String data = sc.nextLine();

                    System.out.print("Insira a nota do Jogo: ");
                    String notaStr = sc.nextLine().replace(",", "."); // pro usuario digitar com virgula, como sao acostumados
                    double nota = Double.parseDouble(notaStr);

                    // Criar objeto Jogo
                    Jogo jogoNovo = new Jogo();
                    jogoNovo.setNome(nome);
                    jogoNovo.setGenero(genero);
                    jogoNovo.setDataLancamento(java.sql.Date.valueOf(data)); // conversao de string para data
                    jogoNovo.setNota(nota);

                    // DAO para cadastrar
                    JogoDao dao = new JogoDao();
                    dao.inserirJogo(jogoNovo);

                    System.out.println("Jogo cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("Listar Jogos---------------");
                    JogoDao dao2 = new JogoDao();
                    List<Jogo> lista = dao2.listarJogos();
                    
                    if (lista != null && !lista.isEmpty()) { //basicamente verifica se a lista está vazia ou não
                    	for (Jogo j: lista) { //percorre a lista e mostra se tem jogos cadastrados
                    		System.out.println("ID: " + j.getId());
                    		System.out.println("Nome: " + j.getNome());
                    		System.out.println("Gênero: " + j.getGenero());
                            System.out.println("Data de Lançamento: " + j.getDataLancamento());
                            System.out.println("Nota: " + j.getNota());
                            System.out.println("");
                    	} 
                    } else {
                    	System.out.println("Nenhum Jogo Cadastrado!! Cadastre um Novo Jogo.");
                    }
                    
                    break;

                case 3:
                    System.out.println("Excluir Jogo---------------");
                    System.out.println("Insira o ID do jogo que deseja excluir: ");
                    int excluir = sc.nextInt();
                    
                    JogoDao dao3 = new JogoDao();
                    dao3.excluirJogo(excluir);
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
