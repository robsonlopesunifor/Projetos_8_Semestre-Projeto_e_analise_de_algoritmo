    import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.Scanner;


public class Dinamico {

	  public static void main(String[] args) {
	    Scanner ler = new Scanner(System.in);

	    System.out.printf("Informe o nome de arquivo texto:\n");
	    String nome = ler.nextLine(); //

//C:\Users\robson\Google Drive\DA_ Faculdade\8° semestre_\N583 - Projeto e analise de algoritmos _\Projetos_8_Semestre-Projeto_e_analise_de_algoritmo\Trabalho NP2\Projeto Alg. Dinamido Multiplicacao de Matriz\src
	    // Arquivo existe 
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
	        int[] A = retornarArrayDaInstacia(linha); // transforma a lisnha em uma array de numeros
	        selecaoDinamico(A); // faz a seleção dinamica 
	      }

	      arq.close();
	    } catch (IOException e) { // caso o arquivo não exista 
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }

	    System.out.println();
	  }

	// pega a lisnha que tem os valores da instacia e o transforma em um array 
	  static int[] retornarArrayDaInstacia(String instacia) { 
		  String B[] = instacia.split(" "); // explode a string em uma array caracteres 
		  int A[] = new int[B.length - 1]; // cria uma array de inteiros 
		  for(int i = 1;i < B.length;i++)  // converte o array de caracteres em int, e preenche o array de inteiros 
			  A[i - 1] = Integer.parseInt(B[i]);
		  return A;
	  }
	  
	  static void selecaoDinamico(int[] a) {
		    int n = a.length - 1; // numero de matrizes a serem multiplicadas1'								
			int[][] c = new int[n][n]; // matrix dos custos calculados 
			int[][] s = new int[n][n]; // matrix dos k
			//to do
			
			for(int l = 1;l < n;l++){ // 
				for(int i = 0; i < n - l;i++){ // inicia com cadeia de duas matrizes: l=2 e vai aumentando
					int j = i + l;
					c[i][j] = 10000000; 
					for(int k = i;k < j;k++){ // obtencao de m[i,j] por meio de solucao de recursao: step 2 pag.374 livro Cormen 
						int q = c[i][k] + c[k + 1][j] + a[i]*a[k+1]*a[j+1]; // aqui entra programacao dinamica, pois varios valores da arvore de recursao ja foram calculados e sao trazidos de m
						if(q < c[i][j]){ // pega o menor custo 
							c[i][j] = q; // obtencao de m otimo para multiplicacao da cadeia de matrizes de i a j
							s[i][j] = k; // onde ocorreu a divisao otima da cadeia de matrizes de i a j
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
	                impresaoDinamico(s, 0, s.length - 1);
		}

	    static void impresaoDinamico(int[][]s, int i, int j) {
	        if(i == j)
	        	System.out.printf("A%s", i); 
	        else if(i != j) {
	        	System.out.printf("(");
	            	impresaoDinamico(s, i, s[i][j]);
	            	impresaoDinamico(s, s[i][j] + 1, j);
	            	System.out.printf(")");
	        }
	    }
}
