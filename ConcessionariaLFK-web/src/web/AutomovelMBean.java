package web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.AutomovelDAO;
import entity.Automovel;

public class AutomovelMBean {

	/** Variáveis acessadas nas páginas. */
	private Automovel novo = new Automovel();

	/** Variáveis acessadas nas páginas. */
	private Automovel selecionado;
	
	private List<Automovel> lista;
	
	private AutomovelDAO dao = new AutomovelDAO();
	
	
	
	
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
	                        ex.getMessage(), "Erro ao Incluir um Carro"));
	        return "";
		}
		
		// Limpar a pessoa selecionada
		novo = new Automovel();
		return "consultar.xhtml";
	}


	/**
	 * Processamento do Login.
	 * @return retorna 
	 */
	public String alterarPage(int id) {
		try {
			selecionado = dao.getAutomovel(id);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Alterar um Carro"));
	        return "";
		}
		
		return "atualizar.xhtml";
	}
	
	public String alterarAction() {
		try {
			dao.atualizar(selecionado);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Alterar um Carro"));
	        return "";
		}
		
		return "consultar.xhtml";
	}

	/**
	 * Retirar do banco a pessoa selecionada
	 * @return PessoaListar se ok.
	 */
	public String excluirAction(int id) {
		try {
			selecionado = dao.getAutomovel(id);
			dao.excluir(selecionado);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao excluir um Carro"));
	        return "";
		}
		return "consultar.xhtml";
	}

	

	public List<Automovel> getLista() {
		try {
			lista = dao.listAll();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Carros"));
		}
		
		return lista;
	}
	
	
	
	

	public Automovel getNovo() {
		return novo;
	}

	public void setNovo(Automovel novo) {
		this.novo = novo;
	}

	public Automovel getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Automovel selecionado) {
		this.selecionado = selecionado;
	}


	public void setLista(List<Automovel> lista) {
		this.lista = lista;
	}

	public AutomovelDAO getDao() {
		return dao;
	}

	public void setDao(AutomovelDAO dao) {
		this.dao = dao;
	}
	
	
	
	
	
}
