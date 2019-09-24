package conc.proj.pooII.entity;

import java.util.ArrayList;
import java.util.List;

public class Usados extends Automovel{
		private int quilometragem;
		private int numDonos;
		
		private List<Novos> listaNovos = new ArrayList<Novos>();
		
		public int getQuilometragem() {
			return quilometragem;
		}
		public void setQuilometragem(int quilometragem) {
			this.quilometragem = quilometragem;
		}
		public int getNumDonos() {
			return numDonos;
		}
		public void setNumDonos(int numDonos) {
			this.numDonos = numDonos;
		}
		
		
		
		
}
