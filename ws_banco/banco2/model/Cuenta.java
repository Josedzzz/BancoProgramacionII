package banco2.model;

public class Cuenta {

    private Banco banco;
    private double saldo;
    private String numero;
    private Cliente clienteTitular;
    private TipoCuenta tipoCuenta;

    //CREO CONSTRUCTORES
    public Cuenta(double saldo, String numero, Cliente clienteTitular, TipoCuenta tipoCuenta, Banco banco) {
        this.banco = banco;
        this.saldo = saldo;
        this.numero = numero;
        this.clienteTitular = clienteTitular;
        this.tipoCuenta = tipoCuenta;
    }    

    public Cuenta() {

    }

    //CREO GETTERS Y SETTERS
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cliente getClienteTitular() {
        return clienteTitular;
    }

    public void setClienteTitular(Cliente clienteTitular) {
        this.clienteTitular = clienteTitular;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
    

    //CREO EL TO STRING
    @Override
    public String toString() {
        return "Cuenta [saldo=" + saldo + ", numero=" + numero + ", clienteTitular=" + clienteTitular + ", tipoCuenta="
                + tipoCuenta + "]";
    }

    //CREO EL HASHCODE Y EL EQUALS
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(saldo);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        result = prime * result + ((clienteTitular == null) ? 0 : clienteTitular.hashCode());
        result = prime * result + ((tipoCuenta == null) ? 0 : tipoCuenta.hashCode());
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
        Cuenta other = (Cuenta) obj;
        if (Double.doubleToLongBits(saldo) != Double.doubleToLongBits(other.saldo))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        if (clienteTitular == null) {
            if (other.clienteTitular != null)
                return false;
        } else if (!clienteTitular.equals(other.clienteTitular))
            return false;
        if (tipoCuenta != other.tipoCuenta)
            return false;
        return true;
    }

    /**
     * 
     * @param dineroConsignado
     */
    public void consignarDinero(double dineroConsignado) {
        double total = 0.0;
        total = this.getSaldo() + dineroConsignado;
        this.setSaldo(total);
    }

    /**
     * 
     * @param dineroRetiro
     * @return
     */
    public boolean verificarSaldo(double dineroRetiro) {
        if(dineroRetiro <= this.getSaldo()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param dineroRetiro
     */
    public void retirarDinero(double dineroRetiro) {
        double total = 0.0;
        total = this.getSaldo() - dineroRetiro;
        this.setSaldo(total);
    }

    /**
     * 
     * @param cuentaComparar
     * @return
     */
    public boolean compararCuenta(Cuenta cuentaComparar) {
        if(this.getSaldo() >= cuentaComparar.getSaldo()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param cuentaDestino
     * @param dinero
     * @throws Exception
     */
    public void transferirDinero(Cuenta cuentaDestino, double dinero) throws Exception {
        String numDestino = cuentaDestino.getNumero();
        String numTransferencia = this.getNumero();
        banco.retirarDinero(numTransferencia, dinero);
        banco.consignarDinero(numDestino, dinero);
    }



    

}
