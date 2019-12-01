package web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.MecanicoDAO;
import entity.Mecanico;

@ManagedBean (name="mecanicoMBean")
@SessionScoped
public class MecanicoMBean {

	/** Variáveis acessadas nas páginas. */
	private Mecanico novo = new Mecanico();

	/** Variáveis acessadas nas páginas. */
	private Mecanico selecionado;
	
	private List<Mecanico> lista;
	
	private MecanicoDAO dao = new MecanicoDAO();
	
	
	
	
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
	                        ex.getMessage(), "Erro ao Incluir um Mecanico"));
	        return "";
		}
		
		// Limpar a pessoa selecionada
		novo = new Mecanico();
		return "consultar-mecanico.xhtml";
	}


	/**
	 * Processamento do Login.
	 * @return retorna 
	 */
	public String alterarPage(int id) {
		try {
			selecionado = dao.getMecanico(id);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Alterar um Mecanico"));
	        return "";
		}
		
		return "atualizar-mecanico.xhtml";
	}
	
	public String alterarAction() {
		try {
			dao.atualizar(selecionado);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Alterar um Mecanico"));
	        return "";
		}
		
		return "consultar-mecanico.xhtml";
	}

	/**
	 * Retirar do banco a pessoa selecionada
	 * @return PessoaListar se ok.
	 */
	public String excluirAction(int id) {
		try {
			selecionado = dao.getMecanico(id);
			dao.excluir(selecionado);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao excluir um Mecanico"));
	        return "";
		}
		return "consultar-mecanico.xhtml";
	}

	

	public List<Mecanico> getLista() {
		try {
			lista = dao.listAll();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Mecanicos"));
		}
		
		return lista;
	}
	
	
	
	

	public Mecanico getNovo() {
		return novo;
	}

	public void setNovo(Mecanico novo) {
		this.novo = novo;
	}

	public Mecanico getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Mecanico selecionado) {
		this.selecionado = selecionado;
	}


	public void setLista(List<Mecanico> lista) {
		this.lista = lista;
	}

	public MecanicoDAO getDao() {
		return dao;
	}

	public void setDao(MecanicoDAO dao) {
		this.dao = dao;
	}
	
	
}
