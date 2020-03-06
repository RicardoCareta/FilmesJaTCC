package Controle;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Constantes.ConsAtivo;
import DAO.DAOContrato;
import Modelo.ModelContrato;

public class ControlContrato {
	
	private List<ModelContrato> listaContrato;
	
	private DAOContrato daoContrato;
	
	//Nome das colunas
	private static final String VENCIMENTO = "Vencimento";
	private static final String VALOR_FATURA = "Valor(BTC)";
	private static final String STATUS = "Estado";
	
	//Status e mensagens
	private static final int PAGO_N = 2;
	private static final String PAGO_T = "PAGO";
	
	private static final int ABERTO_N = 1;
	private static final String ABERTO_T = "ABERTO";
	
	private static final int CANCELADO_N = 0;
	private static final String CANCELADO_T = "CANCELADO";
	
	public ControlContrato(){
		daoContrato = new DAOContrato();
		listaContrato = daoContrato.Selecionar();
	}
	
	public int getQntTelas(int IDCliente){
		for (ModelContrato modelContrato : listaContrato) {
			if(modelContrato.getCliente().getID() == IDCliente && modelContrato.getAtivo() == ConsAtivo.ATIVO){
				return modelContrato.getPlano().getQntTelas();
			}
		}
		
		return 0;
	}
	
	public boolean canLogin(int IDCliente){
		for (ModelContrato modelContrato : listaContrato) {
			if(modelContrato.getCliente().getID() == IDCliente && modelContrato.getAtivo() == ConsAtivo.ATIVO){
				if(daoContrato.DataBD().before(modelContrato.getVencimento()) && !daoContrato.DataBD().after(modelContrato.getVencimento())){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public DefaultTableModel getTableModel(int ID){
		DefaultTableModel retorno = new DefaultTableModel(new Object[][] {}, new String[] { VENCIMENTO, VALOR_FATURA, STATUS });
		
		for (ModelContrato modelContrato : listaContrato) {
			if(modelContrato.getCliente().getID() == ID){
				String status = "Moio q n acho";
				
				if(modelContrato.getAtivo() == PAGO_N){
					status  = PAGO_T;
				}
				
				else if(modelContrato.getAtivo() == ABERTO_N){
					status = ABERTO_T;
				}
				
				else if(modelContrato.getAtivo() == CANCELADO_N){
					status = CANCELADO_T;
				}
				
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				
				retorno.addRow(new Object[]{df.format(modelContrato.getVencimento()), String.valueOf(modelContrato.getPreco()), status});
			}
		}
		
		return retorno;
	}
	
}
