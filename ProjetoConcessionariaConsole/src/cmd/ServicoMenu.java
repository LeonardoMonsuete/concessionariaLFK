package cmd;

import java.util.List;
import java.util.Scanner;


import dao.ServicoDAO;
import entity.Automovel;
import entity.Cliente;
import entity.Mecanico;
import entity.Servico;

public class ServicoMenu {
	
private ServicoDAO dao = new ServicoDAO();
	
	@SuppressWarnings("unused")
	public void menuServico(Scanner sc) {
		while(true) {
			System.out.println("Menu Servico");
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
		System.out.println("Exibir informações do Servico");
		System.out.println("Numero do Servico:");
		int numServico = sc.nextInt();
		sc.nextLine();
		Servico s = dao.getServico(numServico);
		if (s == null) {
			System.out.println("Código do Servico não encontrado");
			return;
		}
		System.out.println("Numero do Servico: " + s.getNumServico());
		System.out.println("Dia: " + s.getDia());
		System.out.println("Custo: " + s.getCusto());
		System.out.println("=====Mecanico====");
		System.out.println("Numero de Registro: " + s.getMecanico().getNumRegistro());
		System.out.println("Nome: " + s.getMecanico().getNome());
		System.out.println("Data de Nascimento: " + s.getMecanico().getDataNasc());
		System.out.println("CPF: " + s.getMecanico().getCpf());
		System.out.println("RG: " + s.getMecanico().getRg());
		System.out.println("Salario: " + s.getMecanico().getSalario());
		System.out.println("=====Cliente====");
		System.out.println("Código: " + s.getCliente().getCodigo());
		System.out.println("Nome: " + s.getCliente().getNome());
		System.out.println("Data de Nascimento: " + s.getCliente().getDataNasc());
		System.out.println("CPF: " + s.getCliente().getCpf());
		System.out.println("RG: " + s.getCliente().getRg());
		System.out.println("=====Automovel====");
		System.out.println("Código: " + s.getAutomovel().getCodigo());
		System.out.println("Modelo: " + s.getAutomovel().getModelo());
		System.out.println("Ano: " + s.getAutomovel().getAno());
		System.out.println("Cilindrada: " + s.getAutomovel().getCilindrada());
		if(s.getAutomovel().getTipo() == 1) {
			System.out.println("Status: Novo");
		} else {
			System.out.println("Status: Usado");
		}
	}

	private void incluir(Scanner sc) throws Exception {
		System.out.println("Incluir Servico");
		System.out.println("Numero do Servico:");
		int numServico = sc.nextInt();
		sc.nextLine();
		System.out.println("Dia:");
		String dia = sc.nextLine();
		System.out.println("Custo:");
		int custo = sc.nextInt();
		sc.nextLine();
		System.out.println("Numero de Registro do Mecanico:");
		int numRegistro = sc.nextInt();
		sc.nextLine();
		System.out.println("Codigo do Automovel:");
		int codigoAuto = sc.nextInt();
		sc.nextLine();
		System.out.println("Codigo do Cliente:");
		int codigoCliente = sc.nextInt();
		sc.nextLine();
		
		Servico s = new Servico();
		s.setNumServico(numServico);
		s.setDia(dia);
		s.setCusto(custo);
		Mecanico m = new Mecanico();
		m.setNumRegistro(numRegistro);
		s.setMecanico(m);
		Cliente c = new Cliente();
		c.setCodigo(codigoCliente);
		s.setCliente(c);
		Automovel a = new Automovel();
		a.setCodigo(codigoAuto);
		s.setAutomovel(a);
		
		
		dao.inserir(s);
	}
	
	private void alterar(Scanner sc) throws Exception {
		System.out.println("Alterar Mecanico");
		System.out.println("Numero do Servico:");
		int numServico = sc.nextInt();
		sc.nextLine();
		Servico s = dao.getServico(numServico);
		if (s == null) {
			System.out.println("Código do Servico não encontrado");
			return;
		}
		System.out.println("Dia:");
		String dia = sc.nextLine();
		System.out.println("Custo:");
		int custo = sc.nextInt();
		sc.nextLine();
		System.out.println("Numero de Registro do Mecanico:");
		int numRegistro = sc.nextInt();
		sc.nextLine();
		System.out.println("Codigo do Automovel:");
		int codigoAuto = sc.nextInt();
		sc.nextLine();
		System.out.println("Codigo do Cliente:");
		int codigoCliente = sc.nextInt();
		sc.nextLine();
		
		s = new Servico();
		s.setNumServico(numServico);
		s.setDia(dia);
		s.setCusto(custo);
		Mecanico m = new Mecanico();
		m.setNumRegistro(numRegistro);
		s.setMecanico(m);
		Cliente c = new Cliente();
		c.setCodigo(codigoCliente);
		s.setCliente(c);
		Automovel a = new Automovel();
		a.setCodigo(codigoAuto);
		s.setAutomovel(a);
		
		
		dao.atualizar(s);
	}

	private void excluir(Scanner sc) throws Exception {
		System.out.println("Excluir Servico");
		System.out.println("Numero do Servico:");
		int numServico = sc.nextInt();
		sc.nextLine();
		Servico s = dao.getServico(numServico);
		if (s == null) {
			System.out.println("Código do Servico não encontrado");
			return;
		}
		dao.excluir(s);
	}

	
	private void listar() throws Exception {
		List<Servico> lista = dao.listAll();
		for (Servico s : lista) {
			System.out.println("Numero do Servico: " + s.getNumServico());
			System.out.println("Dia: " + s.getDia());
			System.out.println("Custo: " + s.getCusto());
			System.out.println("=====Mecanico====");
			System.out.println("Numero de Registro: " + s.getMecanico().getNumRegistro());
			System.out.println("Nome: " + s.getMecanico().getNome());
			System.out.println("Data de Nascimento: " + s.getMecanico().getDataNasc());
			System.out.println("CPF: " + s.getMecanico().getCpf());
			System.out.println("RG: " + s.getMecanico().getRg());
			System.out.println("Salario: " + s.getMecanico().getSalario());
			System.out.println("=====Cliente====");
			System.out.println("Código: " + s.getCliente().getCodigo());
			System.out.println("Nome: " + s.getCliente().getNome());
			System.out.println("Data de Nascimento: " + s.getCliente().getDataNasc());
			System.out.println("CPF: " + s.getCliente().getCpf());
			System.out.println("RG: " + s.getCliente().getRg());
			System.out.println("=====Automovel====");
			System.out.println("Código: " + s.getAutomovel().getCodigo());
			System.out.println("Modelo: " + s.getAutomovel().getModelo());
			System.out.println("Ano: " + s.getAutomovel().getAno());
			System.out.println("Cilindrada: " + s.getAutomovel().getCilindrada());
			if(s.getAutomovel().getTipo() == 1) {
				System.out.println("Status: Novo");
			} else {
				System.out.println("Status: Usado");
			}
			System.out.println("==============================");
		}
	}
}
