package entity;

import java.util.List;
import java.util.Scanner;

public class Servico {

	private String data;
	private int custo;
	private int numServico;
	private Automovel Auto;
	private Mecanico Mec;
	private Cliente Clien;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getCusto() {
		return custo;
	}
	public void setCusto(int custo) {
		this.custo = custo;
	}
	public int getNumServico() {
		return numServico;
	}
	public void setNumServico(int numServico) {
		this.numServico = numServico;
	}
	public Automovel getAuto() {
		return Auto;
	}
	public void setAuto(Automovel auto) {
		Auto = auto;
	}
	public Mecanico getMec() {
		return Mec;
	}
	public void setMec(Mecanico mec) {
		Mec = mec;
	}
	public Cliente getClien() {
		return Clien;
	}
	public void setClien(Cliente clien) {
		Clien = clien;
	}
	
	
	
	
	

	
	
	
}
	

