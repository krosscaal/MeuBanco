package entities;

import java.text.DecimalFormat;

public class P_fisica extends Conta{
    private double limiteCheque = 0.0;

    public P_fisica() {
    }

    public P_fisica(int agencia, int conta , int cod_cliente, double saldo, double limiteCheque) {

        super(agencia, conta, cod_cliente, saldo);
        this.limiteCheque = limiteCheque;
    }

    public double getLimiteCheque() {
        return limiteCheque;
    }

    public void setLimiteCheque(double limiteCheque) {
        this.limiteCheque = limiteCheque;
    }



    public String toString() {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("R$ ###,##0.00");
        final StringBuffer sb = new StringBuffer("----- EXTRATO CONTA P. FISICA -----\n");
        sb.append("Agencia = ").append(agencia);
        sb.append(" Conta = ").append(conta);
        sb.append("\nCod_cliente= ").append(cod_cliente);
        sb.append("\nSaldo = ").append(df.format(saldo));
        sb.append("\nLimite Cheque = ").append(df.format(limiteCheque));
        sb.append("\n----------------------------------");
        return sb.toString();
    }

}
