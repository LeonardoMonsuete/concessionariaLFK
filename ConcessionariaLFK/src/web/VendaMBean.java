package web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.VendaDAO;
import entity.Venda;

public class VendaMBean {
	/** Variáveis acessadas nas páginas. */
	private Venda novo = new Venda();

	/** Variáveis acessadas nas páginas. */
	private Venda selecionado;
	
	private List<Venda> lista;
	
	private VendaDAO dao = new VendaDAO();
	
	
	
	
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
	public String alterarPage(int id) {
		try {
			selecionado = dao.getVenda(id);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Alterar um Venda"));
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
	                        ex.getMessage(), "Erro ao Alterar um Venda"));
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
			selecionado = dao.getVenda(id);
			dao.excluir(selecionado);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao excluir um Venda"));
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
