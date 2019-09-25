package entity;

public abstract class Funcionario extends Pessoa {

	protected int salario;
	protected int numRegistro;
	
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	public int getNumRegistro() {
		return numRegistro;
	}
	public void setNumRegistro(int numRegistro) {
		this.numRegistro = numRegistro;
	}
	
}
