package maquinaTuring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Teste {
	
	 public static void main(String[] args) throws Exception {
	    	
	    	MaquinaTurring TM1 = Teste.teste();
		
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
	    

	public static MaquinaTurring teste(){

		MaquinaTurring maquinaTurring = new MaquinaTurring();
		maquinaTurring.addState("q1");
		maquinaTurring.addState("q2");
		maquinaTurring.addState("q3");
		maquinaTurring.addState("q4");
		maquinaTurring.addState("q5");
		maquinaTurring.addState("q6");
		maquinaTurring.addState("q7");
		maquinaTurring.addState("q8");
		maquinaTurring.addState("qa");
		maquinaTurring.addState("qr");
		maquinaTurring.setStartState("q1");
		maquinaTurring.setAcceptState("qa");
		maquinaTurring.setRejectState("qr");
	
		
		addTransitionArchive(maquinaTurring, "Transicoes.txt");
		return maquinaTurring;		

	}
	
	
	public static void addTransitionArchive(MaquinaTurring mt, String file) {
		File novoFile = new File(file);
		
		try {
			FileInputStream stream = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader br = new BufferedReader(reader);
			
			String linha = br.readLine();
			String[] parametros = linha.split(",");
			
			while(linha != null) {
		  
				String estado1 = parametros[0];
				char simbolo = parametros[1].charAt(0);
				String estado2 = parametros[2];
				char novoSimbolo = parametros[3].charAt(0);
				String direcao = parametros[4];
				
				mt.addTransition(estado1, simbolo, estado2, novoSimbolo, direcao);
				
				
				linha = br.readLine();
			}
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
}
