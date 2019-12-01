package web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.VendedorDAO;
import entity.Vendedor;


@ManagedBean (name="vendedorMBean")
@SessionScoped
public class VendedorMBean {
	/** Variáveis acessadas nas páginas. */
	private Vendedor novo = new Vendedor();

	/** Variáveis acessadas nas páginas. */
	private Vendedor selecionado;
	
	private List<Vendedor> lista;
	
	private VendedorDAO dao = new VendedorDAO();
	
	
	
	
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
	                        ex.getMessage(), "Erro ao Incluir um Vendedor"));
	        return "";
		}
		
		// Limpar a pessoa selecionada
		novo = new Vendedor();
		return "consultar-vendedor.xhtml";
	}


	/**
	 * Processamento do Login.
	 * @return retorna 
	 */
	public String alterarPage(int id) {
		try {
			selecionado = dao.getVendedor(id);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Alterar um Vendedor"));
	        return "";
		}
		
		return "atualizar-vendedor.xhtml";
	}
	
	public String alterarAction() {
		try {
			dao.atualizar(selecionado);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Alterar um Vendedor"));
	        return "";
		}
		
		return "consultar-vendedor.xhtml";
	}

	/**
	 * Retirar do banco a pessoa selecionada
	 * @return PessoaListar se ok.
	 */
	public String excluirAction(int id) {
		try {
			selecionado = dao.getVendedor(id);
			dao.excluir(selecionado);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao excluir um Vendedor"));
	        return "";
		}
		return "consultar-vendedor.xhtml";
	}

	

	public List<Vendedor> getLista() {
		try {
			lista = dao.listAll();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Vendedor"));
		}
		
		return lista;
	}


	public Vendedor getNovo() {
		return novo;
	}


	public void setNovo(Vendedor novo) {
		this.novo = novo;
	}


	public Vendedor getSelecionado() {
		return selecionado;
	}


	public void setSelecionado(Vendedor selecionado) {
		this.selecionado = selecionado;
	}


	public VendedorDAO getDao() {
		return dao;
	}


	public void setDao(VendedorDAO dao) {
		this.dao = dao;
	}


	public void setLista(List<Vendedor> lista) {
		this.lista = lista;
	}
	
	
	
	

	
	
}
