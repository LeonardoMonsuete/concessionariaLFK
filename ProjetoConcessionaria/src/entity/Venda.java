package entity;

import java.util.List;
import java.util.Scanner;

public class Venda {

	private String data;
	private int valor;
	private int numVenda;
	private Automovel Auto;
	private Vendedor Vend;
	private Cliente Clien;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
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
	public Automovel getAuto() {
		return Auto;
	}
	public void setAuto(Automovel auto) {
		Auto = auto;
	}
	public Vendedor getVend() {
		return Vend;
	}
	public void setVend(Vendedor vend) {
		Vend = vend;
	}
	public Cliente getClien() {
		return Clien;
	}
	public void setClien(Cliente clien) {
		Clien = clien;
	}
	


	
}
