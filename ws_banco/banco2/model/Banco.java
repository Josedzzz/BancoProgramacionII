package banco2.model;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nombre;
    private List<Cuenta> listaCuentas;
    private List<Cliente> listaClientes;
    

    //CREO LOS CONSTRUCTORES
    public Banco(String nombre) {
        this.nombre = nombre;
        this.listaCuentas = new ArrayList<Cuenta>();
        this.listaClientes = new ArrayList<Cliente>();
    }

    public Banco() {

    }

    //CREO LOS GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(List<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public List<Cliente> getListaCliente() {
        return listaClientes;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaClientes = listaCliente;
    }

    //CREO EL TO SRING
    @Override
    public String toString() {
        return "Banco [nombre=" + nombre + ", listaCuentas=" + listaCuentas + ", listaCliente=" + listaClientes + "]";
    }

    //CREO EL HASHCODE Y EL EQUALS
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Banco other = (Banco) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    /**
     * 
     * @param documento
     * @return
     */
    public boolean verificarCliente(String documento) {
		boolean verificado = false;		
		for (Cliente cliente : listaClientes) {
			if(cliente.getDocumento().equals(documento)) {
                verificado = true;
                return verificado;
            }
        }
        return verificado;
    }

    /**
     * 
     * @param numeroCuenta
     * @return
     */
    public boolean verificarCuenta(String numeroCuenta) {
        boolean verificado = false;
        for(Cuenta cuenta : listaCuentas) {
            if(cuenta.getNumero().equals(numeroCuenta)) {
                verificado = true;
                return verificado;
            }
        }
        return verificado;
    }

    /**
     * 
     * @param numCuenta
     * @return
     */
    public Cuenta obtenerCuenta(String numCuenta) {
        Cuenta cuentaEncontrada = null;
        for(Cuenta cuenta : listaCuentas) {
            if(cuenta.getNumero().equals(numCuenta)) {
                cuentaEncontrada = cuenta;
            }
        }
        return cuentaEncontrada;
    }

    /**
     * 
     * @param documento
     * @return
     */
    public Cliente obtenerCliente(String documento) {
        Cliente clienteEncontrado = null;
        for(Cliente cliente : listaClientes) {
            if(cliente.getDocumento().equals(documento)) {
                clienteEncontrado = cliente;
            }
        }
        return clienteEncontrado;
    }

    /**
     * 
     * @param nombre
     * @param apellido
     * @param documento
     * @return
     * @throws Exception
     */
    public Cliente crearCliente(String nombre, String apellido, String documento)  throws Exception {
        boolean clienteEncontrado = verificarCliente(documento);
        if(clienteEncontrado == true) {
            throw new Exception("El cliente ya existe!");
        } else {
            Cliente cliente = new Cliente(nombre, apellido, documento);
            listaClientes.add(cliente);
            System.out.println(listaClientes.toString());
            return cliente;
        }
    }

    /**
     * 
     * @param saldo
     * @param numero
     * @param cliente
     * @param tipoCuenta
     * @return
     * @throws Exception
     */
    public String crearCuenta(String numero, Cliente cliente, TipoCuenta tipoCuenta, Banco banco) throws Exception {
        String mensaje = "La cuenta ha sido creada";
        boolean cuentaEncontrada = verificarCuenta(numero);
        if(cuentaEncontrada == true) {
            throw new Exception("La cuenta ya existe");
        } else {
            Cuenta cuenta = new Cuenta(0.0, numero, cliente, tipoCuenta, banco);
            listaCuentas.add(cuenta);
            System.out.println(listaCuentas.toString());
            return mensaje;
        }
    }

    /**
     * 
     * @param documento
     * @param numeroCuenta
     * @throws Exception
     */
    public void eliminarCuentaCliente(String documento, String numeroCuenta) throws Exception {
        Cuenta cuentaEncontrada = obtenerCuenta(numeroCuenta);
        Cliente clienteEncontrado = obtenerCliente(documento);
        if(cuentaEncontrada == null || clienteEncontrado == null) {
            throw new Exception("La cuenta o el cliente no existen");
        }
        listaCuentas.remove(cuentaEncontrada);
        listaClientes.remove(clienteEncontrado);
    }

    /**
     * 
     * @param nombre
     * @param apellido
     * @param documento
     * @return
     * @throws Exception
     */
    public String actualizarCliente(String nombre, String apellido, String documento) throws Exception {
        Cliente clienteEncontrado = obtenerCliente(documento);
        if(clienteEncontrado == null) {
            throw new Exception("El cliente no existe");
        }
        clienteEncontrado.setNombre(nombre);
        clienteEncontrado.setApellido(apellido);

        return "Los datos han sido actualizados";
    }

    /**
     * 
     * @param numero
     * @param tipoCuenta
     * @return
     * @throws Exception
     */
    public String actualizarCuenta(String numero, TipoCuenta tipoCuenta) throws Exception {
        Cuenta cuentaEncontrada = obtenerCuenta(numero);
        if(cuentaEncontrada == null) {
            throw new Exception("La cuenta no existe");
        }
        cuentaEncontrada.setTipoCuenta(tipoCuenta);

        return "La cuenta ha sido actualizada";
    }

    /**
     * 
     * @param numero
     * @return
     * @throws Exception
     */
    public String consultarSaldoCuenta(String numero) throws Exception {
        String mensajeSaldo = "El saldo de la cuenta bancaria es: ";
        Cuenta cuentaEncontrada = obtenerCuenta(numero);
        if(cuentaEncontrada == null) {
            throw new Exception("La cuenta no existe");
        }
        return mensajeSaldo + cuentaEncontrada.getSaldo() + "\n del titular: " 
        + cuentaEncontrada.getClienteTitular().getNombre();
    }

    /**
     * 
     * @param numero
     * @param dineroConsignado
     * @return
     * @throws Exception
     */
    public String consignarDinero(String numero, double dineroConsignado) throws Exception {
        String mensaje = "El dinero ha sido consignado";
        Cuenta cuentaEncontrada = obtenerCuenta(numero);
        if(cuentaEncontrada == null) {
            throw new Exception("La cuenta bancaria no existe");
        }
        cuentaEncontrada.consignarDinero(dineroConsignado);
        System.out.println(listaCuentas.toString());
        return mensaje + " a la cuenta de " + cuentaEncontrada.getClienteTitular().getNombre();
    }

    /**
     * 
     * @param numero
     * @param dineroRetiro
     * @return
     * @throws Exception
     */
    public String retirarDinero(String numero, double dineroRetiro) throws Exception {
        String mensaje = "El dinero ha sido  retirado: ";
        Cuenta cuentaEncontrada = obtenerCuenta(numero);
        if(cuentaEncontrada == null) {
            throw new Exception("La cuenta bancaria no existe");
        }
        if(cuentaEncontrada.verificarSaldo(dineroRetiro)) {
            cuentaEncontrada.retirarDinero(dineroRetiro);
            System.out.println(listaCuentas.toString());
        } else {
            throw new Exception("La cuenta no cuenta con el saldo solicitado");
        }
        return mensaje + " de la cuenta de " + cuentaEncontrada.getClienteTitular().getNombre();
    }

    /**
     * 
     * @param numCuentaActual
     * @param numCuentaComparar
     * @return
     * @throws Exception
     */
    public boolean darCuentaMayor(String numCuentaActual, String numCuentaComparar) throws Exception {
        Cuenta cuentaActual = obtenerCuenta(numCuentaActual);
        Cuenta cuentaComparar = obtenerCuenta(numCuentaComparar);
        if(cuentaActual == null) {
            throw new Exception("La cuenta no existe");
        } 
        if(cuentaComparar == null) {
            throw new Exception("La cuenta no existe");
        }
        return cuentaActual.compararCuenta(cuentaComparar);
    }

    public String transferirDinero(String numCuentaDestino, String numCuentaTransferencia, double dinero) throws Exception {
        String mensaje = "El dinero ha sido transferido correctamente";
        Cuenta cuentaDestino = obtenerCuenta(numCuentaDestino);
        Cuenta cuentaTransferencia = obtenerCuenta(numCuentaTransferencia);
        if(cuentaDestino == null || cuentaTransferencia == null) {
			throw new Exception("La cuenta bancaria no existe");
		}	
		try {
			cuentaTransferencia.transferirDinero(cuentaDestino, dinero);
			return mensaje + " de la cuenta de " + cuentaTransferencia.getClienteTitular().getNombre() + 
			"\n a la cuenta de " + cuentaDestino.getClienteTitular().getNombre();
		} catch(Exception e) {
			mensaje = "El dinero no pudo ser transferido correctamente";
			return mensaje;
		}
    }


    

    

    
    

    

}
