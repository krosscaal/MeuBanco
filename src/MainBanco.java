import entities.P_fisica;
import entities.P_juridica;
import service.ServicoImplement;
import utils.CountConta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainBanco {
    public static void main(String[] args) {

        ServicoImplement serv = new ServicoImplement();
        Scanner sc = new Scanner(System.in);
        CountConta numInit = new CountConta();
        List<P_fisica> listaPF = new ArrayList<>();
        List<P_juridica> listaPJ = new ArrayList<>();
        numInit.setCountContaPF(0);
        numInit.setCountContaPJ(0);
        int op, num, numC,numMenu,indexOr, inexDest,numOri, numDes, indiceNaLista;
        double valor;
        do {
            op=0;
            num=0;

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("++           MENU BANCO                   ++");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+                                          +");
            System.out.println("+# Criar Conta P. Fisica (digite 1)        +");
            System.out.println("+# Criar Conta P. Jurídica (digite 2)      +");
            System.out.println("+# Depositar em Conta (digite 3)           +");
            System.out.println("+# Transferência entre Contas (digite 4)   +");
            System.out.println("+# Saque Conta (digite 5)                  +");
            System.out.println("+# Listar Contas(digite 6)                 +");
            System.out.println("+# Imprime Saldo Conta (digite 7)          +");
            System.out.println("+# Sair (digite 0) :                       +");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print(" : ");
            numMenu = sc.nextInt();

            switch (numMenu){
                case 1: //cria conta PF
                    numC = numInit.getCountContaPF();
                    P_fisica  novaConta = serv.newContaPF(1,numC);
                    numInit.setCountContaPF(numInit.getCountContaPF());
                    System.out.println("Conta Pessoa Física criada com sucesso!");
                    System.out.println(novaConta.toString());
                    listaPF.add(novaConta);
                    break;
                case 2: //cria conta PJ
                    numC = numInit.getCountContaPJ();
                    P_juridica  novaContaPJ = serv.newContaPJ(1,numC);
                    numInit.setCountContaPJ(numInit.getCountContaPF());
                    System.out.println("Conta Pessoa Jurídica criada com sucesso!");
                    System.out.println(novaContaPJ.toString());
                    listaPJ.add(novaContaPJ);
                    break;
                case 3: //depositar
                    op=0;
                    num=0;
                    valor = 0.0;
                    indiceNaLista=-1;
                    do {
                        System.out.println();
                        System.out.println("Deposito em conta Pessoa Fisica (digite 1)");
                        System.out.print("Deposito em conta Pessoa Jurŕidica (digite 2): ");
                        op = sc.nextInt();
                        System.out.println();
                        System.out.print("Número da conta:");
                        num = sc.nextInt();
                        System.out.println();
                        System.out.print("Valor depósito R$: ");
                        valor = sc.nextDouble();
                    }while (op != 1 && op !=2 && num > 0 && valor > 0);
                    if(op == 1){ //tratamento deposito P Fisica
                        if(serv.buscaIndicePF(listaPF,num) > -1){
                            indiceNaLista = serv.buscaIndicePF(listaPF,num);
                            listaPF.get(indiceNaLista).deposito(valor); // efetua depósito
                            System.out.println("Depósito efetuado!");
                            System.out.println();
                            System.out.println(listaPF.get(indiceNaLista).toString());
                        } else System.out.println("Conta Pessoa Física não encontrada com número : "+num);
                    }else { // tratamento deposito P Juridica
                        if(serv.buscaIndicePJ(listaPJ,num) > -1){
                            indiceNaLista = serv.buscaIndicePJ(listaPJ,num);
                            listaPJ.get(indiceNaLista).deposito(valor); // efetua depósito
                            System.out.println("Depósito efetuado!");
                            System.out.println();
                            System.out.println(listaPJ.get(indiceNaLista).toString());
                        } else System.out.println("Conta Pessoa Jurídica não encontrada com número : "+num);
                    }
                    break;
                case 4:
                    indexOr=0;
                    inexDest=0;
                    do {
                        System.out.println("Trasnferência P.Fisica para P.Física(digite 1)");
                        System.out.println("Trasnferência P.Fisica para P.Jurídica(digite 2)");
                        System.out.println("Transferência P.Jurídica para P.Jurídica(digite 3)");
                        System.out.print("Transferência P.Jurídica para P.Física(digite 4): ");
                        op = sc.nextInt();
                    }while (op >= 5 && op <= 0);
                    numOri=0;
                    numDes=0;
                    valor=0.0;
                    do{
                        System.out.println();
                        System.out.print("Digite número da Conta Origem: ");
                        numOri = sc.nextInt();
                        System.out.println();
                        System.out.print("Digite número da Conta Destino: ");
                        numDes = sc.nextInt();
                        System.out.println();
                        System.out.print("Valor a transferir: ");
                        valor = sc.nextDouble();
                    }while (numOri < 0 && numDes < 0);
                    serv.transfer(numOri,numDes,valor,op,listaPF,listaPJ);

                    break;
                case 5:
                    op=0;
                    num=0;
                    valor=0.0;
                    do {
                        System.out.println("Saque Conta P. Física(digite 1)");
                        System.out.print("Saque Conta P. Jurídica(digite 2): ");
                        op = sc.nextInt();
                        System.out.println();
                        System.out.print("Número da Conta: ");
                        num = sc.nextInt();
                        System.out.println();
                        System.out.print("Valor do saque: ");
                        valor = sc.nextDouble();
                    } while (op!=1 && op!=2 && valor > 0 && num > 0);

                    if(op==1){
                        if(serv.buscaIndicePF(listaPF,num) > -1){
                            int indiceLista = serv.buscaIndicePF(listaPF,num);
                            if (listaPF.get(indiceLista).saque(valor)) {
                                System.out.println("SAQUE EFETUADO!");
                            } else {
                                System.out.println("SALDO INSUFICIENTE");
                            }
                        }
                    }else {
                        if(serv.buscaIndicePJ(listaPJ,num) > -1){
                            int indiceLista = serv.buscaIndicePJ(listaPJ,num);
                            if (listaPJ.get(indiceLista).saque(valor)) {
                                System.out.println("SAQUE EFETUADO CONTA P. FISICA!");
                            } else {
                                System.out.println("SALDO INSUFICIENTE EM CONTA");
                            }
                        }
                    }
                    break;
                case 6:
                    op=0;
                    do{
                        System.out.println("Listar Contas Pessoa Física(digite 1)");
                        System.out.print("Listar Contas Pessoa Jurídica(digite 2): ");
                        op = sc.nextInt();
                    }while (op!=1 && op!=2);
                    if(op==1)
                        serv.imprimeListaPF(listaPF);
                    else
                        serv.imprimeListaPJ(listaPJ);
                    break;

                case 7:
                    op=0;
                    num=0;
                    do {
                        System.out.println("Extrato conta Pessoa Física (digite 1)");
                        System.out.print("Extrato conta Pessoa Física (digite 2): ");
                        op = sc.nextInt();
                        System.out.println();
                        System.out.print("Número da Conta: ");
                        num = sc.nextInt();
                    }while (op!=1 && op!=2 && num > 0);
                    if(op==1){
                        serv.imprimeExtratoPF(op,num,listaPF);
                    }else {
                        serv.imprimeExtratoPJ(op,num,listaPJ);
                    }

            }
        } while (numMenu != 0);

    }
}