package main;
import java.util.Scanner;
import controller.PaisController;

public class Main {
	public static void main(String[] args) {
		/*
		 * Cria um objeto PaisController para nos auxiliar na criação e exibição de
		 * dados referentes aos países
		 */
		PaisController paisController = new PaisController();

		// Cria um objeto scanner para ler as entradas de teclado no console
		Scanner scanner = new Scanner(System.in);

		// Variavel de controle para nossa estrutura de repetição
		int opcao = -1;

		// Estrutura de repetição - Repete o laço enquando o usuário não digitar a opção
		// "0" - sair do sistema
		while (opcao != 0) {

			// Menu da nossa aplicação
			System.out.println("==== Menu ====");
			System.out.println("1. Adicionar país");
			System.out.println("2. Exibir lista de países");
			System.out.println("3. Remover país da lista"); // Nova funcionalidade
			System.out.println("4. Limpar lista"); // Nova funcionalidade
			System.out.println("5. Retorne o primeiro país da lista"); // Nova funcionalidade
			System.out.println("6. Retorne o terceiro país da lista"); // Nova funcionalidade
			System.out.println("7. Retorne o quarto   país da lista"); // Nova funcionalidade
			System.out.println("8. Testar conexão com o Banco de Dados"); // Nova funcionalidade
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");
			
			/*
			 *  Estrutura try-catch
			 *  No bloco try, colocamos o trecho de código que pode lançar uma exceção,
			 *  em outras palavras, colocomos o trecho do código que pode dar algum problema (bug).
			 *  Exemplo: ler um número inteiro "scanner.nextInt()", porém o usuário digita uma letra.
			 *  Nesse momento, o sistema tentará converter uma letra (string) em número inteiro. 
			 */
			try {

				// Lê a opção de menu que foi digitada pelo usuário
				opcao = scanner.nextInt();
	
				// Estrutura condicional - Verifica o conteúdo da variável opcao
				switch (opcao) {
	
				// Opção do Menu 1 - Adicionar país
				case 1:
					
					// Escreva no console a mensagem informativa para o usuário saber o que o sistema está esperando que ele digite
					System.out.print("Digite o nome do país: ");
					// Consumir o '\n' após a leitura do número
					scanner.nextLine();
					// Cria uma String nome que armazena o nome do país que o usuário digitou no console
					String nome = scanner.nextLine();
	
					// Escreva no console a mensagem informativa para o usuário saber o que o sistema está esperando que ele digite
					System.out.print("Digite a capital do país " + nome + ": ");
					// Cria uma String capital que armazena a capital do país que o usuário digitou no console
					String capital = scanner.nextLine();
	
					// Utiliza o método adicionarPais do objeto paisController
					paisController.adicionarPais(nome, capital);
					break;

				case 2:
					// Utiliza o método exibirListaDePaises do objeto paisController
					paisController.exibirListaDePaises();
					break;
	
				case 3:
					// Escreva no console a mensagem informativa para o usuário saber o que o sistema está esperando que ele digite
					System.out.println("Digite o nome do país a ser removido: ");
					// Consumir o '\n' após a leitura do número
					scanner.nextLine();
					// Cria uma String nomePaisRemover que armazena o nome do país que o usuário digitou no console
					String nomePaisRemover = scanner.nextLine();
	
					// Utiliza o método removerPais do objeto paisController
					paisController.removerPais(nomePaisRemover);
					break;

				case 4:
					// Utiliza o método limparListaDePaises do objeto paisController
					paisController.limparListaDePaises();
					break;
					
				case 5:
					// Utiliza o método primeiro do objeto paisController
					paisController.primeiro();
					break;
					
				case 6:
					// Utiliza o método terceiro do objeto paisController
					paisController.terceiro();
					break;
					
				case 7:
					// Utiliza o método quarto do objeto paisController
					paisController.quarto();
					break;
					
				case 8:
					paisController.testaConexao();
					break;
	
				case 0:
					// Exibe mensagem informativa avisando o usuário que o sistema será finalizado
					System.out.println("Saindo...");
					break;
	
				default:
					// Exibe mensagem informativa avisando o usuário que ele digitou uma opção que não existe no menu
					System.out.println("Opção inválida.");
					break;
				}
			} 
			
			/*
			 * Estrutura try-catch
			 * No bloco catch colocamos o tratamento da exceção.
			 * Em outras palavras, quando algum erro (exceção) acontecer dentro do bloco try,
			 * o sistema automaticamente para a execução normal, e executa o bloco catch.
			 * Exemplo: na conversão de uma string em número inteiro, o sistema já executa o catch
			 * e escreve no console "Opção inválida...."
			 */
			catch (java.util.InputMismatchException e) {
				System.out.println("Opção inválida. Por favor, digite um número inteiro válido.");
                scanner.nextLine(); // Limpar o buffer do scanner
			}
		}
	}
}
