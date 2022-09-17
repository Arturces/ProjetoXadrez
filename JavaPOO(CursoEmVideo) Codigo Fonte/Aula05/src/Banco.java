public class Banco {

    public int numConta;
    protected String tipo;
    private String dono;
    private float saldo;
    private boolean status;

    public void estadoAtual (){
        System.out.println("-----------------------");
        System.out.println("Conta: " + this.getNumConta());
        System.out.println("Tipo: " + this.getTipo() );
        System.out.println("Dono: " + this.getDono());
        System.out.println("Saldo: " + this.getSaldo());
        System.out.println("Saldo: " + this.getStatus());
    }

    public void Conta() {
        this.saldo = 0;
        this.status = false;
       // this.setSaldo(0);
       // this.setSaldo(false);
    }

    public void abrirConta(String t) {
        this.setTipo(t);
        this.setStatus(true);
        if (t == "CC") {
            //this.saldo = 50;
            this.setSaldo(50);
        } else if (t == "CP")
            //this.saldo = 150;
        this.setSaldo(150);
        System.out.println("Conta aberta com Sucesso");
    }

    public void fecharConta() {
        if (this.getSaldo() > 0) {
            System.out.println("Conta com Dinheiro");
        } else if (this.getSaldo() < 0){
            System.out.println(" Conta em Debito");
        } else { setStatus(false);
            System.out.println("Conta fechada com sucesso");
        }
    }

    public void depositar(float v) {
        if (this.getStatus()) {
            //this.saldo = this.saldo + v;
            this.setSaldo(getSaldo() + v); //forma usando get e set para acesasar
            System.out.println("Deposito realizado na conta de " + this.getDono());
        } else {
            System.out.println("Impossivel depositar");
        }
    }

    public void sacar(float v) {
        if (this.getStatus()) {
            if (this.getSaldo() >= v) {
                this.setSaldo(this.getSaldo() - v);
                //saldo = saldo - v;
                System.out.println("saque realizado na conta de " + this.getDono());
        } else {
                System.out.println("Saldo insuficiente");
            }
        } else {
            System.out.println("impossivel sacar de uma conta fechada!");
        }
    }

    public void pagarMensal() {
        int v = 0;
        if (this.getTipo() == "CC") {
            v = 12;
        } else if (this.getTipo() == "CP") {
            v = 20;
        }
        if (this.getStatus()) {
            this.setSaldo(this.getSaldo() - v);
            System.out.println("Mensalidade paga com sucesso");
            //saldo = saldo - v;
        } else {
            System.out.println("saldo insuficiente");
        }
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

