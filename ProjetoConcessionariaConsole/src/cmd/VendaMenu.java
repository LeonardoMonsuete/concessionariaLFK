package cmd;

import java.util.List;
import java.util.Scanner;

import dao.VendaDAO;
import entity.Automovel;
import entity.Cliente;
import entity.Vendedor;
import entity.Venda;

public class VendaMenu {
	
private VendaDAO dao = new VendaDAO();
	
	@SuppressWarnings("unused")
	public void menuVenda(Scanner sc) {
		while(true) {
			System.out.println("Menu Venda");
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
		System.out.println("Numero da Venda:");
		int numVenda = sc.nextInt();
		sc.nextLine();
		Venda v = dao.getVenda(numVenda);
		if (v == null) {
			System.out.println("Código da Venda não encontrado");
			return;
		}
		System.out.println("Numero do Servico: " + v.getNumVenda());
		System.out.println("Dia: " + v.getDia());
		System.out.println("Custo: " + v.getValor());
		System.out.println("=====Vendedor====");
		System.out.println("Numero de Registro: " + v.getVendedor().getNumRegistro());
		System.out.println("Nome: " + v.getVendedor().getNome());
		System.out.println("Data de Nascimento: " + v.getVendedor().getDataNasc());
		System.out.println("CPF: " + v.getVendedor().getCpf());
		System.out.println("RG: " + v.getVendedor().getRg());
		System.out.println("Salario: " + v.getVendedor().getSalario());
		System.out.println("=====Cliente====");
		System.out.println("Código: " + v.getCliente().getCodigo());
		System.out.println("Nome: " + v.getCliente().getNome());
		System.out.println("Data de Nascimento: " + v.getCliente().getDataNasc());
		System.out.println("CPF: " + v.getCliente().getCpf());
		System.out.println("RG: " + v.getCliente().getRg());
		System.out.println("=====Automovel====");
		System.out.println("Código: " + v.getAutomovel().getCodigo());
		System.out.println("Modelo: " + v.getAutomovel().getModelo());
		System.out.println("Ano: " + v.getAutomovel().getAno());
		System.out.println("Cilindrada: " + v.getAutomovel().getCilindrada());
		if(v.getAutomovel().getTipo() == 1) {
			System.out.println("Status: Novo");
		} else {
			System.out.println("Status: Usado");
		}
	}

	private void incluir(Scanner sc) throws Exception {
		System.out.println("Incluir Venda");
		System.out.println("Numero da Venda:");
		int numVenda = sc.nextInt();
		sc.nextLine();
		System.out.println("Dia:");
		String dia = sc.nextLine();
		System.out.println("Valor:");
		int custo = sc.nextInt();
		sc.nextLine();
		System.out.println("Numero de Registro do Vendedor:");
		int numRegistro = sc.nextInt();
		sc.nextLine();
		System.out.println("Codigo do Automovel:");
		int codigoAuto = sc.nextInt();
		sc.nextLine();
		System.out.println("Codigo do Cliente:");
		int codigoCliente = sc.nextInt();
		sc.nextLine();
		
		Venda v = new Venda();
		v.setNumVenda(numVenda);
		v.setDia(dia);
		v.setValor(custo);
		Vendedor ve = new Vendedor();
		ve.setNumRegistro(numRegistro);
		v.setVendedor(ve);
		Cliente c = new Cliente();
		c.setCodigo(codigoCliente);
		v.setCliente(c);
		Automovel a = new Automovel();
		a.setCodigo(codigoAuto);
		v.setAutomovel(a);
		
		
		dao.inserir(v);
	}
	
	private void alterar(Scanner sc) throws Exception {
		System.out.println("Alterar Venda");
		System.out.println("Numero da Venda:");
		int numVenda = sc.nextInt();
		sc.nextLine();
		Venda v = dao.getVenda(numVenda);
		if (v == null) {
			System.out.println("Código da Venda não encontrado");
			return;
		}
		System.out.println("Dia:");
		String dia = sc.nextLine();
		System.out.println("Valor:");
		int custo = sc.nextInt();
		sc.nextLine();
		System.out.println("Numero de Registro do Vendedor:");
		int numRegistro = sc.nextInt();
		sc.nextLine();
		System.out.println("Codigo do Automovel:");
		int codigoAuto = sc.nextInt();
		sc.nextLine();
		System.out.println("Codigo do Cliente:");
		int codigoCliente = sc.nextInt();
		sc.nextLine();
		
		v = new Venda();
		v.setNumVenda(numVenda);
		v.setDia(dia);
		v.setValor(custo);
		Vendedor ve = new Vendedor();
		ve.setNumRegistro(numRegistro);
		v.setVendedor(ve);
		Cliente c = new Cliente();
		c.setCodigo(codigoCliente);
		v.setCliente(c);
		Automovel a = new Automovel();
		a.setCodigo(codigoAuto);
		v.setAutomovel(a);
		
		
		dao.atualizar(v);
	}

	private void excluir(Scanner sc) throws Exception {
		System.out.println("Excluir Venda");
		System.out.println("Numero da Venda:");
		int numVenda = sc.nextInt();
		sc.nextLine();
		Venda v = dao.getVenda(numVenda);
		if (v == null) {
			System.out.println("Código da Venda não encontrado");
			return;
		}
		dao.excluir(v);
	}

	
	private void listar() throws Exception {
		List<Venda> lista = dao.listAll();
		for (Venda v : lista) {
			System.out.println("Numero do Servico: " + v.getNumVenda());
			System.out.println("Dia: " + v.getDia());
			System.out.println("Custo: " + v.getValor());
			System.out.println("=====Vendedor====");
			System.out.println("Numero de Registro: " + v.getVendedor().getNumRegistro());
			System.out.println("Nome: " + v.getVendedor().getNome());
			System.out.println("Data de Nascimento: " + v.getVendedor().getDataNasc());
			System.out.println("CPF: " + v.getVendedor().getCpf());
			System.out.println("RG: " + v.getVendedor().getRg());
			System.out.println("Salario: " + v.getVendedor().getSalario());
			System.out.println("=====Cliente====");
			System.out.println("Código: " + v.getCliente().getCodigo());
			System.out.println("Nome: " + v.getCliente().getNome());
			System.out.println("Data de Nascimento: " + v.getCliente().getDataNasc());
			System.out.println("CPF: " + v.getCliente().getCpf());
			System.out.println("RG: " + v.getCliente().getRg());
			System.out.println("=====Automovel====");
			System.out.println("Código: " + v.getAutomovel().getCodigo());
			System.out.println("Modelo: " + v.getAutomovel().getModelo());
			System.out.println("Ano: " + v.getAutomovel().getAno());
			System.out.println("Cilindrada: " + v.getAutomovel().getCilindrada());
			if(v.getAutomovel().getTipo() == 1) {
				System.out.println("Status: Novo");
			} else {
				System.out.println("Status: Usado");
			}
			System.out.println("==============================");
		}
	}
}
