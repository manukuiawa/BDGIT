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
                sc.nextLine(); 

                
                System.out.print("Insira o nome do Jogo: ");
                String nome = sc.nextLine();

                
                System.out.print("Insira o gênero do Jogo: ");
                String genero = sc.nextLine();

                
                java.sql.Date dataLancamento = null;
                while (dataLancamento == null) {
                    System.out.print("Insira a data de lançamento (yyyy-MM-dd): ");
                    String data = sc.nextLine();
                    try {
                        dataLancamento = java.sql.Date.valueOf(data);
                    } catch (IllegalArgumentException e) { //aqui eu coloquei uma exceção caso digite a data errada
                        System.out.println("Data inválida! Digite no formato yyyy-MM-dd.");
                    }
                }

                
                double nota = -1;
                while (nota < 0 || nota > 10) {
                    System.out.print("Insira a nota do Jogo de 0 a 10: "); //aqui faz a verificação da nota
                    String notaStr = sc.nextLine().replace(",", ".");
                    try {
                        nota = Double.parseDouble(notaStr);
                        if (nota < 0 || nota > 10) System.out.println("Nota inválida! Deve ser entre 0 e 10.");
                    } catch (NumberFormatException e) {
                        System.out.println("Digite um número válido!");
                    }
                }

                // Cria objeto Jogo
                Jogo jogoNovo = new Jogo();
                jogoNovo.setNome(nome);
                jogoNovo.setGenero(genero);
                jogoNovo.setDataLancamento(dataLancamento);
                jogoNovo.setNota(nota);

                // DAO para cadastrar (já verifica duplicados)
                JogoDao dao = new JogoDao();
                Jogo inserido = dao.inserirJogo(jogoNovo);

                if (inserido != null) {
                    System.out.println("Jogo cadastrado com sucesso!");
                }
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
