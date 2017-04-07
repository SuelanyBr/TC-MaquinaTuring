package maquinaTuring;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



public class MaquinaTurring {

	private Set<String> conjuntoDeEstados;
	private Set<Transicao> conjuntoDeFuncoesDeTransicao;
	private String estadoInicial;
	private String conjuntoDeEstadosDeAceitacao;
	private String conjuntoDeEstadosDeRejeicao;
	
	private String fita;
	private String estadoAtual;
	private int simboloAtual;
		
	
	
	public MaquinaTurring(){
		conjuntoDeEstados = new HashSet<String>();
		conjuntoDeFuncoesDeTransicao = new HashSet<Transicao>();
		estadoInicial = new String("");
		conjuntoDeEstadosDeAceitacao = new String("");
		conjuntoDeEstadosDeRejeicao = new String("");
		fita = new String("");
		estadoAtual = new String("");
		simboloAtual = 0;
		
	}
	
	public boolean Run(String entrada, boolean modoSilencioso){
		estadoAtual = estadoInicial;
		fita = entrada;
		
		while(!estadoAtual.equals(conjuntoDeEstadosDeAceitacao) && !estadoAtual.equals(conjuntoDeEstadosDeRejeicao)){
			boolean achouTransicao = false;
			Transicao transicaoAtual = null;
			
			if (modoSilencioso == false){
				if (simboloAtual > 0){
					System.out.println(fita.substring(0, simboloAtual) + " " + estadoAtual + " " + fita.substring(simboloAtual));
				}
				else{
					System.out.println(" " + estadoAtual + " " + fita.substring(simboloAtual));
				}
			}

			Iterator<Transicao> iteradorDeTransicao = conjuntoDeFuncoesDeTransicao.iterator();
			while ( iteradorDeTransicao.hasNext() && achouTransicao == false){
				
				Transicao proxTransicao = iteradorDeTransicao.next();
				if (proxTransicao.getLerEstado().equals(estadoAtual) && proxTransicao.getLerSimbolo() == fita.charAt(simboloAtual)){
					achouTransicao = true;
					transicaoAtual = proxTransicao;
				}						
		    }	
			
			if (achouTransicao == false){
				System.err.println ("Transicao Invalida (estado=" + estadoAtual + ", simbolo=" + fita.charAt(simboloAtual) + ")");
				return false;
			}
			else
			{
				estadoAtual = transicaoAtual.getEscreverEstado();
				char[] fitaAux = fita.toCharArray(); 				
				fitaAux[simboloAtual] = transicaoAtual.getEscreverSimbolo();
				fita =  new String(fitaAux);
				if(transicaoAtual.isDirecao() == true){
					simboloAtual++;
				}
				else{
					simboloAtual--;
				}
				
				if (simboloAtual < 0)
					simboloAtual = 0;
				
				while (fita.length() <= simboloAtual){
					fita = fita.concat("_");
				}
			}
		}
		
		if (estadoAtual.equals(conjuntoDeEstadosDeAceitacao)){
			return true;
		}
		else{
			return false;
		}
		
		
	}
	
	public boolean addState(String novoEstado){
		if (conjuntoDeEstados.contains(novoEstado)){
			return false;
		}
		else{
			conjuntoDeEstados.add(novoEstado);
			return true;
		}
	}
	
	public boolean setStartState(String novoEstado){
		if (conjuntoDeEstados.contains(novoEstado)){
			estadoInicial = novoEstado;
			return true;
		}
		else{
			return false;
		}		
	}
	
	public boolean setAcceptState(String novoEstadoDeAceitacao){
		if (conjuntoDeEstados.contains(novoEstadoDeAceitacao) && !conjuntoDeEstadosDeRejeicao.equals(novoEstadoDeAceitacao)){
			conjuntoDeEstadosDeAceitacao = novoEstadoDeAceitacao;
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public boolean setRejectState(String novoEstadoDeRejeicao){
		if (conjuntoDeEstados.contains(novoEstadoDeRejeicao) && !conjuntoDeEstadosDeAceitacao.equals(novoEstadoDeRejeicao)){
			conjuntoDeEstadosDeRejeicao = novoEstadoDeRejeicao;
			return true;
		}
		else{
			return false;
		}		
	}

	public boolean addTransition(String estado1, char lerSimbolo, String estado2, char escreverSimbolo, String direcao){
		
		if(!conjuntoDeEstados.contains(estado1) || !conjuntoDeEstados.contains(estado2)){
			return false;
		}
			
		boolean conflito = false;
		Iterator<Transicao> iteradorTransicao = conjuntoDeFuncoesDeTransicao.iterator();
		
		while ( iteradorTransicao.hasNext() && !conflito) {
			Transicao proxTransicao = iteradorTransicao.next();
			if (proxTransicao.isConflicting(estado1, lerSimbolo)){
				conflito = true;
			}			
	    }
		
		if (conflito){
			return false;
		}
		
		else{
			
			boolean novaDirecao = false;
			if (direcao.equalsIgnoreCase("Direita")){
				novaDirecao = true;
			}else if(direcao.equalsIgnoreCase("Esquerda")){
				novaDirecao = false;
			}else{
				return novaDirecao;
			}
			
			
			Transicao novaTransicao = new Transicao(estado1, lerSimbolo, estado2, escreverSimbolo, novaDirecao);
			conjuntoDeFuncoesDeTransicao.add(novaTransicao);
			return true;
		}
	}
}
 