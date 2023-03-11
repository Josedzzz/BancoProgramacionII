package banco2.test;



import javax.swing.JOptionPane;

import banco2.model.Banco;
import banco2.model.Cliente;
import banco2.model.TipoCuenta;

public class test {
    
    public static void main(String[] args) {
        Banco banco = new Banco("uq");
        

        //CREO LOS CLIENTES Y LAS CUENTAS
        try {
            System.out.println("Cuenta creada");
            Cliente cliente1 = banco.crearCliente("Jose", "Amaya", "12345");
            banco.crearCuenta("123", cliente1, TipoCuenta.CORRIENTE, banco);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        try {
            System.out.println("Cuenta creada");
            Cliente cliente2 = banco.crearCliente("Pablo", "Hurtado", "12346");
            banco.crearCuenta("124", cliente2, TipoCuenta.AHORROS, banco);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


        //Consignar dinero en cuentas
		try {
			String consignar = banco.consignarDinero("123", 400000.0);
			JOptionPane.showMessageDialog(null, consignar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		try {
			String consignar = banco.consignarDinero("124", 20000.0);
			JOptionPane.showMessageDialog(null, consignar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
		//Consultar saldo en las cuentas
		try {
			String saldo = banco.consultarSaldoCuenta("123");
			JOptionPane.showMessageDialog(null, saldo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
		try {
			String saldo = banco.consultarSaldoCuenta("124");
			JOptionPane.showMessageDialog(null, saldo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
		//Retirar dinero de las cuentas
		try {
			String retirar = banco.retirarDinero("123", 4000.0);
			JOptionPane.showMessageDialog(null, retirar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		try {
			String retirar = banco.retirarDinero("124", 2000.0);
			JOptionPane.showMessageDialog(null, retirar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
		//Transferir dinero de una cuenta a otra
		try {
			String transferir = banco.transferirDinero("124", "123", 2000);
			JOptionPane.showMessageDialog(null, transferir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
		//Función booleana de mayor
		try {
			boolean esCuentaMayor = banco.darCuentaMayor("123", "124");
			JOptionPane.showMessageDialog(null, esCuentaMayor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

    }

}
