package cmd;

import java.util.Scanner;

import cmd.AutomovelMenu;

public class MainMenu {

	private AutomovelMenu am = new AutomovelMenu();
	
	private ClienteMenu cm = new ClienteMenu();
	
	private MecanicoMenu mm = new MecanicoMenu();
	
	private VendedorMenu vm = new VendedorMenu();
	
	private ServicoMenu sm = new ServicoMenu();
	
	private VendaMenu vvm = new VendaMenu();
	
	
			public static void main(String[] args) {
				MainMenu m = new MainMenu();
				Scanner sc = new Scanner(System.in);
				m.menu(sc);
			}
	
			
		public void menu(Scanner sc) {
				
				
			while(true) {
				System.out.println("Menu Principal");
				System.out.println("1 - Automoveis");
				System.out.println("2 - Clientes");
				System.out.println("3 - Mecanicos");
				System.out.println("4 - Vendedores");
				System.out.println("5 - Servicos");
				System.out.println("6 - Vendas");
				System.out.println("7 - Sair");
				String opcao = sc.nextLine();
				try {
				if(opcao.equals("1")){
					am.menuAutomovel(sc);
				}else if(opcao.equals("2")){
					cm.menuCliente(sc);
				}else if(opcao.equals("3")){
					mm.menuMecanico(sc);
				}else if(opcao.equals("4")){
					vm.menuVendedor(sc);
				}else if(opcao.equals("5")){
					sm.menuServico(sc);
				}else if(opcao.equals("6")){
					vvm.menuVenda(sc);
				}else if(opcao.equals("7")){
					System.out.println("Saindo...");
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
				
			
						
}
