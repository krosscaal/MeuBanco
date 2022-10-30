package service;

import entities.P_fisica;
import entities.P_juridica;

import java.util.List;

public interface Servicos {
    public P_fisica newContaPF(int ag, int numConta);
    public P_juridica newContaPJ(int ag, int numConta);
    public int buscaIndicePF (List<P_fisica> lista, int numero);
    public int buscaIndicePJ (List<P_juridica> lista, int numero);
    public void transfer(int origem, int destino, double valor, int tipo, List<P_fisica> listPF, List<P_juridica> listPJ);
    public void imprimeListaPF(List<P_fisica> listaConta);
    public void imprimeListaPJ(List<P_juridica> listaConta);
    public void imprimeExtratoPF(int tipo, int conta, List<P_fisica> listPF);
    public void imprimeExtratoPJ(int tipo, int conta, List<P_juridica> listPJ);

}
