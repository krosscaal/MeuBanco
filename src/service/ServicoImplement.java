package service;

import entities.Conta;
import entities.P_fisica;
import entities.P_juridica;

import java.util.List;
import java.util.Scanner;

public class ServicoImplement implements Servicos {
    @Override
    public P_fisica newContaPF(int ag, int numConta) {
        Scanner sc = new Scanner(System.in);
        int cpf;
        double saldo = 0.0;
        double limite = 0.0;
        System.out.println("============================================");
        do {
            System.out.print("DIGITE CPF CLIENTE(somente números): ");
            cpf = sc.nextInt();
        } while (cpf <= 0);

        do {
            System.out.println();
            System.out.print("DIGITE LIMITE DO CHEQUE ESPECIAL SE FOR O CASO: ");
            limite = sc.nextDouble();
        }while (limite < 0.0);

        Conta contapf = new P_fisica(ag,numConta,cpf,saldo,limite);

        return (P_fisica) contapf;
    }

    public P_juridica newContaPJ(int ag, int numConta) {
        Scanner sc = new Scanner(System.in);
        int cnpj, opCartao;
        boolean cartao = false;
        double saldo = 0.0;
        double limite = 0.0;
        System.out.println("============================================");
        do {
            System.out.print("DIGITE CNPJ CLIENTE(somente números): ");
            cnpj = sc.nextInt();
        } while (cnpj <= 0);

        do {
            System.out.println("CONTA COM CARTÁO CREDITO (DIGITE 1) ");
            System.out.print("CONTA SEM CARTÁO CREDITO (DIGITE 0) ");
            opCartao = sc.nextInt();
        }while (opCartao!=1 && opCartao!=0);

        if(opCartao ==1) {
            cartao = true;
            do {
                System.out.println();
                System.out.print("DIGITE LIMITE DO CARTÃO DE CREDITO: ");
                limite = sc.nextDouble();
            } while (limite <= 0.0);
        }else cartao = false;

        Conta contaPJ = new P_juridica(ag,numConta,cnpj,saldo,cartao,limite);

        return (P_juridica) contaPJ;
    }


    @Override
    public void transfer(int origem, int destino, double valor, int tipo, List<P_fisica> listPF, List<P_juridica> listPJ) {
        int indexOr, inexDest;
        if(tipo==1){ //PF -> PF
            indexOr = buscaIndicePF(listPF,origem);
            inexDest = buscaIndicePF(listPF,destino);
            if (indexOr > -1 && inexDest > -1) {
                if (listPF.get(indexOr).saque(valor)) {
                    listPF.get(inexDest).deposito(valor);
                } else {
                    System.out.println();
                    System.out.println("Saldo insuficiente para esta transferência!");
                }
            } else {
                System.out.println();
                System.out.println("ERRO EM CONTA ORIGEM/DESTINO VERIFICAR!");
            }
        }else if(tipo==2){ //PF->PJ
            indexOr = buscaIndicePF(listPF,origem);
            inexDest = buscaIndicePJ(listPJ,destino);
            if (indexOr > -1 && inexDest > -1) {
                if (listPF.get(indexOr).saque(valor)) {
                    listPJ.get(inexDest).deposito(valor);
                } else {
                    System.out.println();
                    System.out.println("Saldo insuficiente para esta transferência!");
                }
            } else {
                System.out.println();
                System.out.println("ERRO EM CONTA ORIGEM/DESTINO VERIFICAR!");
            }

        }else if(tipo==3){ //PJ->PJ
            indexOr = buscaIndicePJ(listPJ,origem);
            inexDest = buscaIndicePJ(listPJ,destino);
            if (indexOr > -1 && inexDest > -1) {
                if (listPJ.get(indexOr).saque(valor)) {
                    listPJ.get(inexDest).deposito(valor);
                } else {
                    System.out.println();
                    System.out.println("Saldo insuficiente para esta transferência!");
                }
            } else {
                System.out.println();
                System.out.println("ERRO EM CONTA ORIGEM/DESTINO VERIFICAR!");
            }

        }else { //PJ -> PF
            indexOr = buscaIndicePJ(listPJ,origem);
            inexDest = buscaIndicePF(listPF,destino);
            if (indexOr > -1 && inexDest > -1) {
                if (listPJ.get(indexOr).saque(valor)) {
                    listPF.get(inexDest).deposito(valor);
                } else {
                    System.out.println();
                    System.out.println("Saldo insuficiente para esta transferência!");
                }
            } else {
                System.out.println();
                System.out.println("ERRO EM CONTA ORIGEM/DESTINO VERIFICAR!");
            }
        }
    }

    @Override
    public int buscaIndicePF (List<P_fisica> lista, int numero ){
        for (int i=0;i<lista.size();i++){
            if(lista.get(i).getConta() == numero){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int buscaIndicePJ (List<P_juridica> lista, int numero ){
        for (int i=0;i<lista.size();i++){
            if(lista.get(i).getConta() == numero){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void imprimeListaPF(List<P_fisica> listaConta) {
        if (!listaConta.isEmpty()) {
            System.out.println("LISTA CONTAS PESSOA FÍSICA");
            for (P_fisica li : listaConta) {
                System.out.println(
                        "Ag: " + li.getAgencia() + " Conta: " + li.getConta() +" CPF: "+li.getCod_cliente()+" Saldo: " + li.getSaldo()+" Limite Cheq Especial: "+li.getLimiteCheque());
            }
        }
        else {
            System.out.println();
            System.out.println("NÃO TEM CONTAS CADASTRADAS PESSOA FÍSICA!!");
        }
    }

    @Override
    public void imprimeListaPJ(List<P_juridica> listaConta) {
        if (!listaConta.isEmpty()) {
            System.out.println("LISTA CONTAS PESSOA JURÍDICA");
            for (P_juridica li : listaConta) {
                System.out.println(
                        "Ag: " + li.getAgencia() + " Conta: " + li.getConta() +" CNPJ: "+li.getCod_cliente()+" Saldo: " + li.getSaldo()+" Limite Cartão Crédito: "+li.getLimiteCartao());
            }
        }else {
            System.out.println();
            System.out.println("NÃO TEM CONTAS CADASTRADAS PESSOA JURÍDICA!!");
        }
    }

    @Override
    public void imprimeExtratoPF(int tipo, int conta, List<P_fisica> listPF){
        if(buscaIndicePF(listPF,conta) > -1){
            System.out.println();
            System.out.println("EXTRATO CONTA PESSOA FÍSICA");
            System.out.println("---------------------------");
            System.out.println(listPF.get(buscaIndicePF(listPF,conta)).toString());
        }else {
            System.out.println("ERRO EM LOCALIZAR A CONTA!");
        }
    }
    @Override
    public void imprimeExtratoPJ(int tipo, int conta, List<P_juridica> listPJ){
        if(buscaIndicePJ(listPJ,conta) > -1){
            System.out.println();
            System.out.println("EXTRATO CONTA PESSOA FÍSICA");
            System.out.println("---------------------------");
            System.out.println(listPJ.get(buscaIndicePJ(listPJ,conta)).toString());
        }else {
            System.out.println("ERRO EM LOCALIZAR A CONTA!");
        }
    }

}
