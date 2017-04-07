package maquinaTuring;


public class Transicao {
	private String lerEstado;
	private char lerSimbolo;
	private String escreverEstado;
	private char escreverSimbolo;
	private boolean direcao;	//true is right, false is left
	
	public Transicao(String lerEstado, char lerSimbolo, String escreverEstado, char escreverSimbolo, boolean direcao){
		this.lerEstado = lerEstado;
		this.lerSimbolo = lerSimbolo;
		this.escreverEstado = escreverEstado;	
		this.escreverSimbolo = escreverSimbolo;
		this.direcao = direcao;
	}
	
	public boolean isConflicting(String estado, char simbolo){
		if (estado.equals(lerEstado) && simbolo == lerSimbolo){
			return true;
		}
		
		else{
			return false;			
		}
	}	
	
	public String getLerEstado() {
		return lerEstado;
	}
	public void setLerEstado(String lerEstado) {
		this.lerEstado = lerEstado;
	}
	public char getLerSimbolo() {
		return lerSimbolo;
	}
	public void setLerSimbolo(char lerSimbolo) {
		this.lerSimbolo = lerSimbolo;
	}
	public String getEscreverEstado() {
		return escreverEstado;
	}
	public void setEscreverEstado(String escreverEstado) {
		this.escreverEstado = escreverEstado;
	}
	public char getEscreverSimbolo() {
		return escreverSimbolo;
	}
	public void setEscreverSimbolo(char escreverSimbolo) {
		this.escreverSimbolo = escreverSimbolo;
	}
	public boolean isDirecao() {
		return direcao;
	}
	public void setDirecao(boolean direcao) {
		this.direcao = direcao;
	}
	
}
