package web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.AutomovelDAO;
import entity.Automovel;

@ManagedBean (name="automovelMBean")
@SessionScoped
public class AutomovelMBean {

	private Automovel novo = new Automovel();

	private Automovel selecionado;
	
	private List<Automovel> lista;
	
	private AutomovelDAO dao = new AutomovelDAO();
	
	
	
	

	public String incluirAction() {
		try {
			dao.inserir(novo);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Incluir um Carro"));
	        return "";
		}
		
		if(novo.getTipo() == 1) {
			novo = new Automovel();
			return "consultar-automovel-novo.xhtml";
		} else {
			novo = new Automovel();
			return "consultar-automovel-usado.xhtml";
		}
	}
	
	public String incluirCompraAction() {
		try {
			novo.setTipo(2);
			dao.inserir(novo);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Incluir um Carro"));
	        return "";
		}
		
		if(novo.getTipo() == 1) {
			novo = new Automovel();
			return "consultar-automovel-novo.xhtml";
		} else {
			novo = new Automovel();
			return "consultar-automovel-usado.xhtml";
		}
	}



	public String alterarPage(int codigo) {
		try {
			selecionado = dao.getAutomovel(codigo);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Selecionar o Carro"));
	        return "";
		}
		
		return "atualizar-automovel.xhtml";
	}
	
	public String venderPage(int codigo) {
		try {
			selecionado = dao.getAutomovel(codigo);
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao Selecionar o Carro"));
	        return "";
		}
		
		return "vender-automovel.xhtml";
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
		
		if(selecionado.getTipo() == 1) {
			return "consultar-automovel-novo.xhtml";
		} else {
			return "consultar-automovel-usado.xhtml";
		}
	}

	public String excluirAction(int codigo) {
		try {
			selecionado = dao.getAutomovel(codigo);
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
	
	public List<Automovel> getListaNovos() {
		try {
			lista = dao.listNovos();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Carros Novos"));
		}
		
		return lista;
	}
	
	public List<Automovel> getListaUsados() {
		try {
			lista = dao.listUsados();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Carros Usados"));
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
