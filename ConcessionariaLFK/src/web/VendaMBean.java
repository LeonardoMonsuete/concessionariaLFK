package web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.AutomovelDAO;
import dao.VendaDAO;
import entity.Automovel;
import entity.Venda;


@ManagedBean
@SessionScoped

public class VendaMBean {
	/** Variáveis acessadas nas páginas. */
	private Venda novo = new Venda();

	/** Variáveis acessadas nas páginas. */
	private Venda selecionado;
	
	private Automovel autoselecionado;
	
	private List<Venda> lista;
	
	private List<Automovel> listaauto;
	
	private VendaDAO dao = new VendaDAO();
	
	private AutomovelDAO daoauto = new AutomovelDAO();
	
	
	
	/**
	 * Processamento do Login.
	 * @return retorna 
	 */
	public String incluirAction() {
		try {
			dao.inserir(novo);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Incluir um Venda"));
	        return "";
		}
		
		// Limpar a pessoa selecionada
		novo = new Venda();
		return "consultar.xhtml";
	}


	/**
	 * Processamento do Login.
	 * @return retorna 
	 */
	public String venderPage(int codigoauto) {
		try {
			autoselecionado = daoauto.getAutomovel(codigoauto);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Alterar um Venda"));
	        return "";
		}
		
		return "vender-automovel.xhtml";
	}
	

	/* public String alterarAction() {
		try {
			dao.atualizar(selecionado);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Alterar um Venda"));
	        return "";
		}
		
		return "vender-automovel.xhtml";
	}
	*/
	

	/**
	 * Retirar do banco a pessoa selecionada
	 * @return PessoaListar se ok.
	 */
	public String excluirAction(int id) {
		try {
			selecionado = dao.getVenda(id);
			dao.excluir(selecionado);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao excluir uma Venda"));
	        return "";
		}
		return "consultar.xhtml";
	}

	

	public List<Venda> getLista() {
		try {
			lista = dao.listAll();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Vendas"));
		}
		
		return lista;
	}
	
	public List<Automovel> getListaauto() {
		try {
			listaauto = daoauto.listAll();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Vendas"));
		}
		
		return listaauto;
	}
	
	
	

	public Venda getNovo() {
		return novo;
	}

	public void setNovo(Venda novo) {
		this.novo = novo;
	}

	public Venda getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Venda selecionado) {
		this.selecionado = selecionado;
	}


	public void setLista(List<Venda> lista) {
		this.lista = lista;
	}

	public VendaDAO getDao() {
		return dao;
	}

	public void setDao(VendaDAO dao) {
		this.dao = dao;
	}
	
}
