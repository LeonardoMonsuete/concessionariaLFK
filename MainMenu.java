package conc.proj.pooII.lk;

import java.util.Scanner;

public class MainMenu {

			public static void main(String[] args) {
				MainMenu mm = new MainMenu();
				Scanner sc = new Scanner(System.in);
				mm.menu(sc);
			}
	
			
		public void menu(Scanner sc) {
				
				
			while(true) {
				System.out.println("CONCESSIONÁRIA LFK");
				System.out.println("=======MENU=======");
				System.out.println("1 - Automovel");
				System.out.println("2 - Cliente");
				System.out.println("3 - Estoque");
				System.out.println("4 - Relatório");
				System.out.println("5 - Encerrar Programa");
				String opcao = sc.nextLine();
				
				if(opcao.equals("1")){
					MainMenu mm = new MainMenu();
					mm.menuAuto(sc);
				}else if(opcao.equals("2")){
					
				}else if(opcao.equals("3")){
					
				}else if(opcao.equals("4")){
					
				}else if(opcao.equals("5")){
					
				}
				
			}
		}
				
			
			
		public void menuAuto(Scanner sc) {
				
			while(true) {
				System.out.println("===Menu Automoveis===");
				System.out.println("1 - Vender");
				System.out.println("2 - Comprar");
				System.out.println("3 - Atualizar");
				System.out.println("4 - Voltar ao menu anterior");
				
				String opc = sc.nextLine();
				
				if(opc.equals("1")){
					Estoque e = new Estoque();
					e.venderAuto();
				}else if(opc.equals("2")) {
					Estoque e = new Estoque();
					e.comprarAuto();
				}else if(opc.equals("3")) {
					Estoque e = new Estoque();
					e.atualizarAuto();
				}else if(opc.equals("4")) {
					break;
				}
				
			}
		}
						
}
