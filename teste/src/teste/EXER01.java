package teste;
import java.util.Scanner;

public class EXER01 {
	public static void main (String[] args) {
		
		//Criando as variaveis
		
		Scanner input = new Scanner(System.in);
		int num1, num2, soma;
		
		// Salvando valores do teclado
		
		System.out.print("Insira um Valor: ");
		num1 = input.nextInt();
		
		System.out.print("Insira outro Valor: ");
		num2 = input.nextInt();
		
		// Realizando a soma e inserindo na tela
		
		soma = num1 + num2;
		
		System.out.print("Soma: " + soma);
		
		input.close();
	}
}