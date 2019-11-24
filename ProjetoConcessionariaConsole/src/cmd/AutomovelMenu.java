package cmd;

import java.util.List;
import java.util.Scanner;

import dao.AutomovelDAO;
import entity.Automovel;



public class AutomovelMenu {
	
	private AutomovelDAO dao = new AutomovelDAO();
	
	@SuppressWarnings("unused")
	public void menuAutomovel(Scanner sc) {
		while(true) {
			System.out.println("Menu Automovel");
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
		System.out.println("Exibir informações do Automovel");
		System.out.println("Código:");
		int codigo = sc.nextInt();
		sc.nextLine();
		Automovel a = dao.getAutomovel(codigo);
		if (a == null) {
			System.out.println("Código do Automovel não encontrado");
			return;
		}
		System.out.println("Código: " + a.getCodigo());
		System.out.println("Modelo: " + a.getModelo());
		System.out.println("Ano: " + a.getAno());
		System.out.println("Cilindrada: " + a.getCilindrada());
		if(a.getTipo() == 1) {
			System.out.println("Status: Novo");
		} else {
			System.out.println("Status: Usado");
		}
	}

	private void incluir(Scanner sc) throws Exception {
		System.out.println("Incluir Automovel");
		System.out.println("Código:");
		int codigo = sc.nextInt();
		sc.nextLine();
		System.out.println("Modelo:");
		String nome = sc.nextLine();
		System.out.println("Ano:");
		int ano = sc.nextInt();
		sc.nextLine();
		System.out.println("Cilindrada:");
		int cilindrada = sc.nextInt();
		sc.nextLine();
		System.out.println("Status(1 para Novo, 2 para Usado):");
		int status = sc.nextInt();
		sc.nextLine();
		
		Automovel a = new Automovel();
		a.setCodigo(codigo);
		a.setModelo(nome);
		a.setAno(ano);
		a.setCilindrada(cilindrada);
		a.setTipo(status);
		
		
		dao.inserir(a);
	}
	
	private void alterar(Scanner sc) throws Exception {
		System.out.println("Alterar Automovel");
		System.out.println("Código:");
		int codigo = sc.nextInt();
		sc.nextLine();
		Automovel a = dao.getAutomovel(codigo);
		if (a == null) {
			System.out.println("Código do Automovel não encontrado");
			return;
		}
		System.out.println("Modelo:");
		String nome = sc.nextLine();
		System.out.println("Ano:");
		int ano = sc.nextInt();
		sc.nextLine();
		System.out.println("Cilindrada:");
		int cilindrada = sc.nextInt();
		sc.nextLine();
		System.out.println("Status(1 para Novo, 2 para Usado):");
		int status = sc.nextInt();
		sc.nextLine();
		
		a = new Automovel();
		a.setCodigo(codigo);
		a.setModelo(nome);
		a.setAno(ano);
		a.setCilindrada(cilindrada);
		a.setTipo(status);
		
		dao.atualizar(a);
	}

	private void excluir(Scanner sc) throws Exception {
		System.out.println("Excluir Automovel");
		System.out.println("Código:");
		int codigo = sc.nextInt();
		sc.nextLine();
		
		Automovel a = dao.getAutomovel(codigo);
		if (a == null) {
			System.out.println("Automovel não encontrado.");
			return;
		}
		dao.excluir(a);
	}

	
	private void listar() throws Exception {
		List<Automovel> lista = dao.listAll();
		for (Automovel a : lista) {
			System.out.println("Código: " + a.getCodigo());
			System.out.println("Modelo: " + a.getModelo());
			System.out.println("Ano: " + a.getAno());
			System.out.println("Cilindrada: " + a.getCilindrada());
			if(a.getTipo() == 1) {
				System.out.println("Status: Novo");
			} else {
				System.out.println("Status: Usado");
			}
			System.out.println("==============================");
		}
	}
}

