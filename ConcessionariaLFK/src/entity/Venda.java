package entity;

import java.util.List;
import java.util.Scanner;

public class Venda {

	private String dia;
	private int valor;
	private int numVenda;
	private Automovel Automovel = new Automovel();
	private Vendedor Vendedor = new Vendedor();
	private Cliente Cliente = new Cliente();
	
	
	public Automovel getAutomovel() {
		return Automovel;
	}
	public void setAutomovel(Automovel automovel) {
		Automovel = automovel;
	}
	public Vendedor getVendedor() {
		return Vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		Vendedor = vendedor;
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
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public int getNumVenda() {
		return numVenda;
	}
	public void setNumVenda(int numVenda) {
		this.numVenda = numVenda;
	}
	


	
}
