package model;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcao = 0;

		// enquanto o usuário não escolher 4, o menu se repete
		while (opcao != 4) {
			System.out.println("-----------------------Bem Vindo à Central de Jogos-----------------------");
			System.out.println("1. Cadastrar");
			System.out.println("2. Listar os Jogos");
			System.out.println("3. Excluir um Jogo");
			System.out.println("4. Sair");
			System.out.print("Escolha uma opção: ");

			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				System.out.println("Cadastrar Jogo");
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

	}
}
