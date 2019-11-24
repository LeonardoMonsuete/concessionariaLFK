package cmd;

import java.util.List;
import java.util.Scanner;


import dao.ClienteDAO;
import entity.Cliente;

public class ClienteMenu {
	
private ClienteDAO dao = new ClienteDAO();
	
	@SuppressWarnings("unused")
	public void menuCliente(Scanner sc) {
		while(true) {
			System.out.println("Menu Cliente");
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
		System.out.println("Exibir informações do Cliente");
		System.out.println("Código:");
		int codigo = sc.nextInt();
		sc.nextLine();
		Cliente c = dao.getCliente(codigo);
		if (c == null) {
			System.out.println("Código do Cliente não encontrado");
			return;
		}
		System.out.println("Código: " + c.getCodigo());
		System.out.println("Nome: " + c.getNome());
		System.out.println("Data de Nascimento: " + c.getDataNasc());
		System.out.println("CPF: " + c.getCpf());
		System.out.println("RG: " + c.getRg());
		
	}

	private void incluir(Scanner sc) throws Exception {
		System.out.println("Incluir Cliente");
		System.out.println("Código:");
		int codigo = sc.nextInt();
		sc.nextLine();
		System.out.println("Nome:");
		String nome = sc.nextLine();
		System.out.println("Data de Nascimento:");
		String datanasc = sc.nextLine();
		System.out.println("CPF:");
		String cpf = sc.nextLine();
		System.out.println("RG:");
		String rg = sc.nextLine();
		
		Cliente c = new Cliente();
		c.setCodigo(codigo);
		c.setNome(nome);
		c.setDataNasc(datanasc);
		c.setCpf(cpf);
		c.setRg(rg);
		
		
		dao.inserir(c);
	}
	
	private void alterar(Scanner sc) throws Exception {
		System.out.println("Alterar Cliente");
		System.out.println("Código:");
		int codigo = sc.nextInt();
		sc.nextLine();
		Cliente c = dao.getCliente(codigo);
		if (c == null) {
			System.out.println("Código do Cliente não encontrado");
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
		
		c = new Cliente();
		c.setCodigo(codigo);
		c.setNome(nome);
		c.setDataNasc(datanasc);
		c.setCpf(cpf);
		c.setRg(rg);
		
		dao.atualizar(c);
	}

	private void excluir(Scanner sc) throws Exception {
		System.out.println("Excluir Cliente");
		System.out.println("Código:");
		int codigo = sc.nextInt();
		sc.nextLine();
		
		Cliente c = dao.getCliente(codigo);
		if (c == null) {
			System.out.println("Código do Cliente não encontrado");
			return;
		}
		dao.excluir(c);
	}

	
	private void listar() throws Exception {
		List<Cliente> lista = dao.listAll();
		for (Cliente c : lista) {
			System.out.println("Código: " + c.getCodigo());
			System.out.println("Nome: " + c.getNome());
			System.out.println("Data de Nascimento: " + c.getDataNasc());
			System.out.println("CPF: " + c.getCpf());
			System.out.println("RG: " + c.getRg());
			System.out.println("==============================");
		}
	}
}
