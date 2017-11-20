    import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.Scanner;


public class Dinamico {

	  public static void main(String[] args) {
	    Scanner ler = new Scanner(System.in);

	    System.out.printf("Informe o nome de arquivo texto:\n");
	    String nome = ler.nextLine();

//C:\Users\robson\Google Drive\DA_ Faculdade\8° semestre_\N583 - Projeto e analise de algoritmos _\Projetos_8_Semestre-Projeto_e_analise_de_algoritmo\Trabalho NP2\Projeto Alg. Dinamido Multiplicacao de Matriz\src
	    
	    System.out.printf("\nConteúdo do arquivo texto:\n");
	    try {
	      FileReader arq = new FileReader(nome);
	      BufferedReader lerArq = new BufferedReader(arq);

	      String linha = lerArq.readLine(); // lê a primeira linha
	// a variável "linha" recebe o valor "null" quando o processo
	// de repetição atingir o final do arquivo texto
	      
	      
	      
	      while (linha != null) {
	        //System.out.printf("%s\n", linha);

	        linha = lerArq.readLine(); // lê da segunda até a última linha
	        int[] A = retornarArrayDaInstacia(linha);
	        selecaoDinamico(A);
	      }

	      arq.close();
	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }

	    System.out.println();
	  }

	  static int[] retornarArrayDaInstacia(String instacia) {
		  String B[] = instacia.split(" ");
		  int A[] = new int[B.length - 1];
		  for(int i = 1;i < B.length;i++) 
			  A[i - 1] = Integer.parseInt(B[i]);
		  return A;
	  }
	  
	  static void selecaoDinamico(int[] a) {
		    int n = a.length - 1;
			int[][] c = new int[n][n];
			int[][] s = new int[n][n];
			//to do
			
			for(int l = 1;l < n;l++){
				for(int i = 0; i < n - l;i++){
					int j = i + l;
					c[i][j] = 10000000;
					for(int k = i;k < j;k++){
						int q = c[i][k] + c[k + 1][j] + a[i]*a[k+1]*a[j+1];
						if(q < c[i][j]){
							c[i][j] = q;
							s[i][j] = k;
						}
					}
				}
			}
			
			//impresaoDinamico(s,0,a.length - 1);
			/*for(int i = 0;i < a.length - 1;i++) {
				for(int j = 0;j < a.length - 1;j++)
					System.out.printf("%s ", s[i][j]);
				System.out.println();
			}*/
			boolean[] inAResult = new boolean[s.length];
	        printOptimalParenthesizations(s, 0, s.length - 1, inAResult);
		}

	    static void printOptimalParenthesizations(int[][]s, int i, int j,  /* for pretty printing: */ boolean[] inAResult) {
	        if(i == j)
	        	System.out.printf("A%s", i);
	        else if(i != j) {
	        	System.out.printf("(");
	            printOptimalParenthesizations(s, i, s[i][j], inAResult);
	            printOptimalParenthesizations(s, s[i][j] + 1, j, inAResult);
	            System.out.printf(")");
	        }
	    }
	  
	  
	  static void impresaoDinamico(int[][] A,int i,int j) {
		  if(i == j)
			  System.out.printf("A%s", i);
		  else {
			  System.out.printf("(");
			  impresaoDinamico(A, i, A[i][j]);
			  impresaoDinamico(A, A[i][j] + 1,j);
			  System.out.printf(")");
		  }
	  }
	  
}
