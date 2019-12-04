package entity;

import java.util.List;
import java.util.Scanner;

public class Servico {

	private String dia;
	private int custo;
	private int numServico;
	private Automovel Automovel = new Automovel();
	private Mecanico Mecanico = new Mecanico();
	private Cliente Cliente = new Cliente();
	
	
	public Automovel getAutomovel() {
		return Automovel;
	}
	public void setAutomovel(Automovel automovel) {
		Automovel = automovel;
	}
	public Mecanico getMecanico() {
		return Mecanico;
	}
	public void setMecanico(Mecanico mecanico) {
		Mecanico = mecanico;
	}
	public Cliente getCliente() {
		return Cliente;
	}
	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
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
	
	
	
	
	

	
	
	
}
	

