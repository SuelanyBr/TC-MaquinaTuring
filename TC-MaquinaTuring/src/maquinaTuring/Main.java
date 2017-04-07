
package maquinaTuring;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
    	
    	MaquinaTurring TM1 = Main.MenuDadosMT();
		
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("================== MAQUINA TURRING ==================");
    	System.out.println();
    	System.out.println("Digite a entrada (Coloque '#' no meio da palavra): ");
    	String entrada = scanner.nextLine(); 
    	
		boolean resultado = TM1.Run(entrada, false);
		if (resultado == true)
		{
			System.out.println("A entrada foi aceita.");
		}
		else
		{
			System.out.println("A entrada foi rejeitada.");
		}	
    }
    
    public static MaquinaTurring MenuDadosMT() throws Exception{
    	MaquinaTurring maquinaTurring = new MaquinaTurring();
    	 
		Scanner scanner = new Scanner(System.in);

        int max = 100;
        String[] estados = new String[max];
        String[] estadosFinais = new String[max];
       
        int qtEstados, qtEstadosF, qtAlfabeto;
        String[] alfabeto = new String[max];
        int qtTransicao;
      

        // Quantidade de estados da maquina de Turing
        System.out.print("Digite a quantidade de Estados: ");
        qtEstados = scanner.nextInt();

        // Pedindo o nome dos estados.
        scanner.nextLine(); // "Limpar" uma linha inteira do buffer
        for (int i = 0; i < qtEstados; i++) {


            System.out.print("Digite o nome do " + (i + 1) + "º estado: ");

            estados[i] = scanner.nextLine();
            maquinaTurring.addState(estados[i]);
          
        }

        System.out.println("------------------------------------------------------------------------------------");
        // Mostrar os valores contidos no vetor
        for (int i = 0; i < qtEstados; i++) {

            System.out.println("Estado " + (i + 1) + " = " + estados[i]);

        }
        System.out.println("------------------------------------------------------------------------------------");

        // Pedir o estado inicial
        System.out.println("Digite o estado Inicial: ");
        String estadoInic = scanner.nextLine();
        if (!maquinaTurring.setStartState(estadoInic)){
        	throw new Exception("Nao existe o estado: "+ estadoInic);
        }
    	
        System.out.println("------------------------------------------------------------------------------------");
        // Pedir a quantidade de estados finais

        System.out.println("Digite a quantidade de estados de aceitacao: ");

        qtEstadosF = scanner.nextInt();
        scanner.nextLine();
        System.out.println("------------------------------------------------------------------------------------");

        if (qtEstadosF > qtEstados) {
        	throw new Exception("Erro: a quantidade de estados finais eh maior ou iqual a quantidade de estados.");
        	
        } else { //pede para informar quais sao os estados finais
        	
            for (int i = 0; i < qtEstadosF; i++) {
                System.out.print("Digite os estados de Aceitacao: ");
                estadosFinais[i] = scanner.nextLine();
                if(!maquinaTurring.setAcceptState(estadosFinais[i])){

                	throw new Exception("Erro na adicao do estado de aceitacao");

                }
            }

            System.out.println("------------------------------------------------------------------------------------");
            
            // Mostrar quais sao os estados de aceitacao
            for (int i = 0; i < qtEstadosF; i++) {
                System.out.println("Estado de Aceitacao: " + estadosFinais[i]);
            }

            System.out.println("------------------------------------------------------------------------------------\n");
            
            //Pede os estados de rejeicao
   
            estadosFinais = new String[max];
            System.out.println("Digite a quantidade de estados de rejeicao: ");
            int qtEstadosR = scanner.nextInt();
            
            for (int i = 0; i < qtEstadosR; i++) {
                System.out.print("Digite os estados de rejeicao: ");

                scanner.nextLine();

                estadosFinais[i] = scanner.nextLine();
                maquinaTurring.setRejectState(estadosFinais[i]);
            }

            System.out.println("------------------------------------------------------------------------------------\n");
            
            // Mostrar quais sÃƒÂ£o os estados de rejeicao
            for (int i = 0; i < qtEstadosF; i++) {
                System.out.println("Estado de Rejeicao: " + estadosFinais[i]);
            }
            System.out.println("------------------------------------------------------------------------------------");

        }

        // Pedir a quantiade de simbolos do alfabeto da maquina de turing
         
        System.out.print("Digite a quantidade de simbolos do alfabeto: ");
        qtAlfabeto = scanner.nextInt();
        scanner.nextLine();
        
        // Pedir os simbolos do alfabeto
        for (int i = 0; i < qtAlfabeto; i++) {


            System.out.print("Digite o " + (i + 1) + "º simbolo do Alfabeto: ");

            alfabeto[i] = scanner.nextLine();

        }
        System.out.print("Alfabeto:");
        
        System.out.print(alfabeto[0]);

        //Mostrando o Alfabeto

        for (int i = 1; i < qtAlfabeto; i++) {
            System.out.print("," + alfabeto[i]);
        }
 
        System.out.println();
        
        // Transicoes
        System.out.print("Quantas transicoes existem: ");
        qtTransicao = scanner.nextInt();
        scanner.nextLine();
        String estado;
        String proxEstado;
        char simboloLido;
        char  simboloEscrito;
        String verifica;
        String direcao;
        
        for (int i = 0; i < qtTransicao; i++) {

            System.out.print("Digite o nome do " + (i + 1) + "º estado: ");
            estado = scanner.nextLine(); 
          

            System.out.print("Digite o nome do proximo estado: ");
            proxEstado = scanner.nextLine(); 

 
            System.out.print("Digite o nome do simbolo a ser Lido: ");
            simboloLido = scanner.nextLine().charAt(0);

            System.out.print("Digite o nome do simbolo a ser Escrito: ");
            simboloEscrito = scanner.nextLine().charAt(0); 
            
            System.out.print("Digite a direcao (direita/esquerda) ");
            
            direcao = scanner.nextLine(); 
     
            
            System.out.println("------------------------------------------------------------------------------------\n");
            
            
            
            //Funcoes de Transicao
    		maquinaTurring.addTransition(estado, simboloLido, proxEstado, simboloEscrito, direcao);	
        }

		return maquinaTurring;		

    }
}