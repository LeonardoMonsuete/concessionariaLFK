package web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.ServicoDAO;
import entity.Servico;

public class ServicoMBean {
	/** Variáveis acessadas nas páginas. */
	private Servico novo = new Servico();

	/** Variáveis acessadas nas páginas. */
	private Servico selecionado;
	
	private List<Servico> lista;
	
	private ServicoDAO dao = new ServicoDAO();
	
	
	
	
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
	                        ex.getMessage(), "Erro ao Incluir um Servico"));
	        return "";
		}
		
		// Limpar a pessoa selecionada
		novo = new Servico();
		return "consultar.xhtml";
	}


	/**
	 * Processamento do Login.
	 * @return retorna 
	 */
	public String alterarPage(int id) {
		try {
			selecionado = dao.getServico(id);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Alterar um Servico"));
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
	                        ex.getMessage(), "Erro ao Alterar um Servico"));
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
			selecionado = dao.getServico(id);
			dao.excluir(selecionado);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao excluir um Servico"));
	        return "";
		}
		return "consultar.xhtml";
	}

	

	public List<Servico> getLista() {
		try {
			lista = dao.listAll();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Servicos"));
		}
		
		return lista;
	}
	
	
	
	

	public Servico getNovo() {
		return novo;
	}

	public void setNovo(Servico novo) {
		this.novo = novo;
	}

	public Servico getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Servico selecionado) {
		this.selecionado = selecionado;
	}


	public void setLista(List<Servico> lista) {
		this.lista = lista;
	}

	public ServicoDAO getDao() {
		return dao;
	}

	public void setDao(ServicoDAO dao) {
		this.dao = dao;
	}
	
}
