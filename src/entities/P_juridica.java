package entities;

public class P_juridica extends Conta {
    private boolean cartao;
    private double limiteCartao =0.0;

    public P_juridica() {
    }

    public P_juridica(int agencia, int conta, int cod_cliente, double saldo) {
        super(agencia, conta, cod_cliente, saldo);
    }

    public P_juridica(int agencia, int conta, int cod_cliente, double saldo, boolean cartao, double limiteCartao) {
        super(agencia, conta, cod_cliente, saldo);
        this.cartao = cartao;
        this.limiteCartao = limiteCartao;
    }

    public boolean isCartao() {
        return cartao;
    }

    public double getLimiteCartao() {
        return limiteCartao;
    }

    public void setCartao(boolean cartao) {
        this.cartao = cartao;
    }

    public void setLimiteCartao(double limiteCartao) {
        this.limiteCartao = limiteCartao;
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("----- EXTRATO CONTA P. JURÍDICA -----");
        sb.append("\nAgencia =").append(agencia);
        sb.append(" Conta = ").append(conta);
        sb.append("\nCod. cliente=").append(cod_cliente);
        sb.append("\nSaldo = ").append(saldo);
        sb.append("\nCartao habilitado = ").append(cartao);
        sb.append("\nlimiteCartao = ").append(limiteCartao);
        sb.append("\n--------------------------");
        return sb.toString();
    }

}
