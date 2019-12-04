package web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.AutomovelDAO;
import dao.ClienteDAO;
import dao.VendaDAO;
import dao.VendedorDAO;
import entity.Automovel;
import entity.Cliente;
import entity.Venda;
import entity.Vendedor;


@ManagedBean (name="vendaMBean")
@SessionScoped

public class VendaMBean {
	/** Variáveis acessadas nas páginas. */
	private Venda novo = new Venda();

	/** Variáveis acessadas nas páginas. */
	private Venda selecionado;
	
	private Automovel automovel = new Automovel();
	
	private Vendedor vendedor = new Vendedor();
	
	private Cliente cliente = new Cliente();
	
	private List<Venda> lista;
	
	private List<Automovel> listaautomovel;
	
	private List<Vendedor> listavendedor;
	
	private List<Cliente> listacliente;
	
	private VendaDAO dao = new VendaDAO();
	
	private AutomovelDAO daoautomovel = new AutomovelDAO();
	
	private VendedorDAO daovendedor = new VendedorDAO();
	
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
	                        ex.getMessage(), "Erro ao Incluir um Venda"));
	        return "";
		}
		
		// Limpar a pessoa selecionada
		novo = new Venda();
		return "consultar-venda.xhtml";
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
	                        ex.getMessage(), "Erro ao excluir uma Venda"));
	        return "";
		}
		return "consultar-venda.xhtml";
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



	public Vendedor getVendedor() {
		return vendedor;
	}



	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
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
	                        ex.getMessage(), "Erro ao pegar a Lista de Vendas"));
		}
		
		return listaautomovel;
	}



	public void setListaautomovel(List<Automovel> listaautomovel) {
		this.listaautomovel = listaautomovel;
	}



	public List<Vendedor> getListavendedor() {
		try {
			listavendedor = daovendedor.listAll();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Vendas"));
		}
		
		
		return listavendedor;
	}



	public Automovel getAutomovel() {
		return automovel;
	}



	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}



	public void setListavendedor(List<Vendedor> listavendedor) {
		this.listavendedor = listavendedor;
	}



	public List<Cliente> getListacliente() {
		try {
			listacliente = daocliente.listAll();
		} catch (Exception ex) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        ex.getMessage(), "Erro ao pegar a Lista de Vendas"));
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



	public VendedorDAO getDaovendedor() {
		return daovendedor;
	}



	public void setDaovendedor(VendedorDAO daovendedor) {
		this.daovendedor = daovendedor;
	}



	public ClienteDAO getDaocliente() {
		return daocliente;
	}



	public void setDaocliente(ClienteDAO daocliente) {
		this.daocliente = daocliente;
	}
	
}
