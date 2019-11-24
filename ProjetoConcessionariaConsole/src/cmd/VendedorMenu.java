package cmd;

import java.util.List;
import java.util.Scanner;

import dao.VendedorDAO;
import entity.Vendedor;

public class VendedorMenu {
	
private VendedorDAO dao = new VendedorDAO();
	
	@SuppressWarnings("unused")
	public void menuVendedor(Scanner sc) {
		while(true) {
			System.out.println("Menu Vendedor");
			System.out.println("1 - Listar");
			System.out.println("2 - Incluir");
			System.out.println("3 - Alterar");
			System.out.println("4 - Excluir");
			System.out.println("5 - Exibir informações");
			System.out.println("6 - Sair");
			try {
				String opcao = sc.nextLine();
				if (opcao.equals("1")) {
					listar();
				} else if (opcao.equals("2")) {
					incluir(sc);
				} else if (opcao.equals("3")) {
					alterar(sc);
				} else if (opcao.equals("4")) {
					excluir(sc);
				} else if (opcao.equals("5")) {
					exibir(sc);
				} else if (opcao.equals("6")) {
					break;
				}else {
					System.out.println("Opção não reconhecida");
				}
			} catch (Exception ex) {
				System.out.println("Erro ao executar:" + ex.getMessage());
				ex.printStackTrace();
			}
		}
		
	}
	
	private void exibir(Scanner sc) throws Exception {
		System.out.println("Exibir informações do Vendedor");
		System.out.println("Numero de Registro:");
		int numRegistro = sc.nextInt();
		sc.nextLine();
		Vendedor v = dao.getVendedor(numRegistro);
		if (v == null) {
			System.out.println("Código do Vendedor não encontrado");
			return;
		}
		System.out.println("Numero de Registro: " + v.getNumRegistro());
		System.out.println("Nome: " + v.getNome());
		System.out.println("Data de Nascimento: " + v.getDataNasc());
		System.out.println("CPF: " + v.getCpf());
		System.out.println("RG: " + v.getRg());
		System.out.println("Salario: " + v.getSalario());
		
	}

	private void incluir(Scanner sc) throws Exception {
		System.out.println("Incluir Vendedor");
		System.out.println("Numero de Registro:");
		int numRegistro = sc.nextInt();
		sc.nextLine();
		System.out.println("Nome:");
		String nome = sc.nextLine();
		System.out.println("Data de Nascimento:");
		String datanasc = sc.nextLine();
		System.out.println("CPF:");
		String cpf = sc.nextLine();
		System.out.println("RG:");
		String rg = sc.nextLine();
		System.out.println("Salario:");
		int salario = sc.nextInt();
		sc.nextLine();
		
		Vendedor v = new Vendedor();
		v.setNumRegistro(numRegistro);
		v.setNome(nome);
		v.setDataNasc(datanasc);
		v.setCpf(cpf);
		v.setRg(rg);
		v.setSalario(salario);
		
		
		dao.inserir(v);
	}
	
	private void alterar(Scanner sc) throws Exception {
		System.out.println("Alterar Mecanico");
		System.out.println("Numero de Registro:");
		int numRegistro = sc.nextInt();
		sc.nextLine();
		Vendedor v = dao.getVendedor(numRegistro);
		if (v == null) {
			System.out.println("Código do Vendedor não encontrado");
			return;
		}
		System.out.println("Nome:");
		String nome = sc.nextLine();
		System.out.println("Data de Nascimento:");
		String datanasc = sc.nextLine();
		System.out.println("CPF:");
		String cpf = sc.nextLine();
		System.out.println("RG:");
		String rg = sc.nextLine();
		System.out.println("Salario:");
		int salario = sc.nextInt();
		sc.nextLine();
		
		v = new Vendedor();
		v.setNumRegistro(numRegistro);
		v.setNome(nome);
		v.setDataNasc(datanasc);
		v.setCpf(cpf);
		v.setRg(rg);
		v.setSalario(salario);
		
		
		dao.atualizar(v);
	}

	private void excluir(Scanner sc) throws Exception {
		System.out.println("Excluir Vendedor");
		System.out.println("Numero de Registro:");
		int numRegistro = sc.nextInt();
		sc.nextLine();
		Vendedor v = dao.getVendedor(numRegistro);
		if (v == null) {
			System.out.println("Código do Vendedor não encontrado");
			return;
		}
		dao.excluir(v);
	}

	
	private void listar() throws Exception {
		List<Vendedor> lista = dao.listAll();
		for (Vendedor v : lista) {
			System.out.println("Numero de Registro: " + v.getNumRegistro());
			System.out.println("Nome: " + v.getNome());
			System.out.println("Data de Nascimento: " + v.getDataNasc());
			System.out.println("CPF: " + v.getCpf());
			System.out.println("RG: " + v.getRg());
			System.out.println("Salario: " + v.getSalario());
			System.out.println("==============================");
		}
	}
}
