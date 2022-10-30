package utils;

public class CountConta {
    private int countContaPF;
    private int countContaPJ;

    public CountConta() {
    }

    public CountConta(int countContaPF, int countContaPJ) {
        this.countContaPF = countContaPF;
        this.countContaPJ = countContaPJ;
    }

    public int getCountContaPF() {
        return countContaPF;
    }

    public void setCountContaPF(int countContaPF) {
        this.countContaPF +=1 ;
    }

    public int getCountContaPJ() {
        return countContaPJ;
    }

    public void setCountContaPJ(int countContaPJ) {
        this.countContaPJ += 1;
    }
    public void increCount(int num){

    }

}
