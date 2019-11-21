package web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.ClienteDAO;
import entity.Cliente;

public class VendadorMBean {
	/** Variáveis acessadas nas páginas. */
	private Cliente novo = new Cliente();

	/** Variáveis acessadas nas páginas. */
	private Cliente selecionado;
	
	private List<Cliente> lista;
	
	private ClienteDAO dao = new ClienteDAO();
	
	
	
	
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
	                        ex.getMessage(), "Erro ao Incluir um Cliente"));
	        return "";
		}
		
		// Limpar a pessoa selecionada
		novo = new Cliente();
		return "consultar.xhtml";
	}


	/**
	 * Processamento do Login.
	 * @return retorna 
	 */
	public String alterarPage(int id) {
		try {
			selecionado = dao.getCliente(id);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Alterar um Cliente"));
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
	                        ex.getMessage(), "Erro ao Alterar um Cliente"));
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
			selecionado = dao.getCliente(id);
			dao.excluir(selecionado);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao excluir um Cliente"));
	        return "";
		}
		return "consultar.xhtml";
	}

	

	public List<Cliente> getLista() {
		try {
			lista = dao.listAll();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Clientes"));
		}
		
		return lista;
	}
	
	
	
	

	public Cliente getNovo() {
		return novo;
	}

	public void setNovo(Cliente novo) {
		this.novo = novo;
	}

	public Cliente getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Cliente selecionado) {
		this.selecionado = selecionado;
	}


	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	public ClienteDAO getDao() {
		return dao;
	}

	public void setDao(ClienteDAO dao) {
		this.dao = dao;
	}
	
}
