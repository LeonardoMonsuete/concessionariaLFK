package web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.AutomovelDAO;
import dao.ClienteDAO;
import dao.MecanicoDAO;
import dao.ServicoDAO;
import entity.Automovel;
import entity.Cliente;
import entity.Mecanico;
import entity.Servico;


@ManagedBean (name="servicoMBean")
@SessionScoped
public class ServicoMBean {
	/** Variáveis acessadas nas páginas. */
	private Servico novo = new Servico();

	/** Variáveis acessadas nas páginas. */
	private Servico selecionado;
	
	private Automovel automovel = new Automovel();
	
	private Mecanico mecanico = new Mecanico();
	
	private Cliente cliente = new Cliente();
	
	private List<Servico> lista;
	
	private List<Automovel> listaautomovel;
	
	private List<Mecanico> listamecanico;
	
	private List<Cliente> listacliente;
	
	private ServicoDAO dao = new ServicoDAO();
	
	private AutomovelDAO daoautomovel = new AutomovelDAO();
	
	private MecanicoDAO daomecanico = new MecanicoDAO();
	
	private ClienteDAO daocliente = new ClienteDAO();
	
	
	
	
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
		return "consultar-servico.xhtml";
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
		return "consultar-servico.xhtml";
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


	public Automovel getAutomovel() {
		return automovel;
	}


	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<Automovel> getListaautomovel() {
		try {
			listaautomovel = daoautomovel.listAll();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Servicos"));
		}
		
		return listaautomovel;
	}


	public void setListaautomovel(List<Automovel> listaautomovel) {
		this.listaautomovel = listaautomovel;
	}

	public List<Cliente> getListacliente() {
		try {
			listacliente = daocliente.listAll();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Servicos"));
		}
		
		return listacliente;
	}


	public void setListacliente(List<Cliente> listacliente) {
		this.listacliente = listacliente;
	}


	public AutomovelDAO getDaoautomovel() {
		return daoautomovel;
	}


	public void setDaoautomovel(AutomovelDAO daoautomovel) {
		this.daoautomovel = daoautomovel;
	}


	public ClienteDAO getDaocliente() {
		return daocliente;
	}


	public void setDaocliente(ClienteDAO daocliente) {
		this.daocliente = daocliente;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	public void setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public List<Mecanico> getListamecanico() {
		try {
			listamecanico = daomecanico.listAll();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Servicos"));
		}
		
		return listamecanico;
	}

	public void setListamecanico(List<Mecanico> listamecanico) {
		this.listamecanico = listamecanico;
	}

	public MecanicoDAO getDaomecanico() {
		return daomecanico;
	}

	public void setDaomecanico(MecanicoDAO daomecanico) {
		this.daomecanico = daomecanico;
	}
	
}
