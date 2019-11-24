package cmd;

import java.util.List;
import java.util.Scanner;

import entity.Mecanico;
import dao.MecanicoDAO;

public class MecanicoMenu {
	
	private MecanicoDAO dao = new MecanicoDAO();
	
	@SuppressWarnings("unused")
	public void menuMecanico(Scanner sc) {
		while(true) {
			System.out.println("Menu Mecanico");
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
		System.out.println("Exibir informações do Mecanico");
		System.out.println("Numero de Registro:");
		int numRegistro = sc.nextInt();
		sc.nextLine();
		Mecanico m = dao.getMecanico(numRegistro);
		if (m == null) {
			System.out.println("Código do Mecanico não encontrado");
			return;
		}
		System.out.println("Numero de Registro: " + m.getNumRegistro());
		System.out.println("Nome: " + m.getNome());
		System.out.println("Data de Nascimento: " + m.getDataNasc());
		System.out.println("CPF: " + m.getCpf());
		System.out.println("RG: " + m.getRg());
		System.out.println("Salario: " + m.getSalario());
		
	}

	private void incluir(Scanner sc) throws Exception {
		System.out.println("Incluir Mecanico");
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
		
		Mecanico m = new Mecanico();
		m.setNumRegistro(numRegistro);
		m.setNome(nome);
		m.setDataNasc(datanasc);
		m.setCpf(cpf);
		m.setRg(rg);
		m.setSalario(salario);
		
		
		dao.inserir(m);
	}
	
	private void alterar(Scanner sc) throws Exception {
		System.out.println("Alterar Mecanico");
		System.out.println("Numero de Registro:");
		int numRegistro = sc.nextInt();
		sc.nextLine();
		Mecanico m = dao.getMecanico(numRegistro);
		if (m == null) {
			System.out.println("Código do Mecanico não encontrado");
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
		
		m = new Mecanico();
		m.setNumRegistro(numRegistro);
		m.setNome(nome);
		m.setDataNasc(datanasc);
		m.setCpf(cpf);
		m.setRg(rg);
		m.setSalario(salario);
		
		
		dao.atualizar(m);
	}

	private void excluir(Scanner sc) throws Exception {
		System.out.println("Excluir Mecanico");
		System.out.println("Numero de Registro:");
		int numRegistro = sc.nextInt();
		sc.nextLine();
		Mecanico m = dao.getMecanico(numRegistro);
		if (m == null) {
			System.out.println("Código do Mecanico não encontrado");
			return;
		}
		dao.excluir(m);
	}

	
	private void listar() throws Exception {
		List<Mecanico> lista = dao.listAll();
		for (Mecanico m : lista) {
			System.out.println("Numero de Registro: " + m.getNumRegistro());
			System.out.println("Nome: " + m.getNome());
			System.out.println("Data de Nascimento: " + m.getDataNasc());
			System.out.println("CPF: " + m.getCpf());
			System.out.println("RG: " + m.getRg());
			System.out.println("Salario: " + m.getSalario());
			System.out.println("==============================");
		}
	}
}
