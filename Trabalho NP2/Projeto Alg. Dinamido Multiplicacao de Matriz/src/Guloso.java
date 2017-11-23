package greedyMatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Guloso {

	public static void main(String[] args) {
		
	    Scanner ler = new Scanner(System.in);

	    System.out.printf("Informe o nome de arquivo texto:\n");
	    String nome = ler.nextLine(); 
	    
	  //C:\Users\georg\Algoritmos\entrada.txt
		
	    System.out.printf("\nConteúdo do arquivo texto:\n");
	    
	    try {
	        FileReader arq = new FileReader(nome);
	        BufferedReader lerArq = new BufferedReader(arq);

	        String primeiraLinha = lerArq.readLine(); // lê a primeira linha
	        
	        int nLinhas = Integer.parseInt(primeiraLinha);
	        
	        for(int i=1;i<=nLinhas;i++) {
		      
		        //System.out.printf("%s\n", nLinhas);

		        String linha = lerArq.readLine(); // lê da segunda até a última linha
		        int[] s = retornarArrayDaInstacia(linha); // transforma a linha em uma array de numeros
		        
		        //selecaoDinamico(A); // faz a seleção dinamica 
		        
				//apenas no caso de uma multiplicação entre duas matrizes
				if(s.length-1!=2) {
					System.out.print("(");
				} 
				
				int x = printOptimal(s, 0, s.length-1);
				
				//apenas no caso de uma multiplicação entre duas matrizes
				if(s.length-1!=2) {
					System.out.print(")");
				}
				
				//colocando o número de operações no final
				System.out.println(" " + x);
		        
		      
		      }

		      arq.close();
		    } catch (IOException e) { // caso o arquivo não exista 
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }

		    System.out.println();
		    
		    
		    
		    /*
			int[] s = {2,3,7,2};
			
			//apenas no caso de uma multiplicação entre duas matrizes
			if(s.length-1!=2) {
				System.out.print("(");
			} 
			
			int x = printOptimal(s, 0, s.length-1);
			
			//apenas no caso de uma multiplicação entre duas matrizes
			if(s.length-1!=2) {
				System.out.print(")");
			}
			
			//colocando o número de operações no final
			System.out.println(" " + x);
	    
			*/
		
	}
	
	
	// pega a linha que tem os valores da instacia e o transforma em um array 
	  static int[] retornarArrayDaInstacia(String instacia) { 
		  String B[] = instacia.split(" "); // explode a string em uma array caracteres 
		  int A[] = new int[B.length - 1]; // cria uma array de inteiros 
		  for(int i = 1;i < B.length;i++)  // converte o array de caracteres em int, e preenche o array de inteiros 
			  A[i - 1] = Integer.parseInt(B[i]);
		  return A;
	  }

	
	public static int printOptimal(int[] s, int i, int f){
		//meu algoritmo guloso pega o menor número do vetor (ignorando o primeiro e o último) 
		//e faz a recursão usando esse número como o k
		
		//valores iniciais, só para cálculo do menor número do vetor
		int menor = 11;
		int indiceMenor = 0;
		
		//variavel que vai retornar o número de operações feitas, ao final
		int operacoes = 0;
		
		//caso o vetor só tenha 2 inteiros, ou seja 1 matriz
		if (f-i==1) {
			int numero = i+1;
			//imprime a matriz
			System.out.print("A"+numero);
		} else {
			//caso o vetor só tenha 3 inteiros, ou seja 2 matrizes
			if (f-i==2){
				System.out.print("(");
				int numero = i+1;
				//imprime a primeira(...)
				System.out.print("A"+numero);
				numero = f-1+1;
				//(...)depois a segunda
				System.out.print("A"+numero);
				System.out.print(")");
				//número de operações quando só existem 2 matrizes é calculado 
				//multiplicando número de linhas pelo número de colunas da primeira
				//pelo número de colunas da segunda
				operacoes = s[i]*s[i+1]*s[f];

			} else {
				//quando o vetor possui mais de 3 números / mais de 2 matrizes
				//ele calcula o menor número dentro desse vetor(...)
				for (int j=i+1; j<f;j++) {
					if (s[j] < menor) {
						menor = s[j];
						indiceMenor = j;
					}
				}
				//(...) e faz a recursão, dividindo em duas matrizes:
				//do começo do vetor até o índice menor, e do índice menor até o final do vetor
				//somando com o número de operações calculado para esse intervalo
				return printOptimal(s, i, indiceMenor) + printOptimal(s, indiceMenor, f) + s[i]*s[indiceMenor]*s[f];

			}

		}
		
		return operacoes;
		
	}

}
