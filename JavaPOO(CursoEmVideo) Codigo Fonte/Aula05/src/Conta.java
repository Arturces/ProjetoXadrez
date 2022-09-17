public class Conta {
    public static void main(String[] args) {
    Banco p1 = new Banco();
    p1.setNumConta(1111);
    p1.setDono("Artur");
    p1.abrirConta("CC");

    Banco p2 = new Banco();
    p2.setNumConta(2222);
    p2.setDono("Nena");
    p2.abrirConta("CP");

    p1.depositar(500);
    p2.depositar(150);

    p1.sacar(100);
    p2.sacar(1000);

    p1.fecharConta();

    p1.estadoAtual();
    p2.estadoAtual();
    }
}
