public class Teste {
    public static void main(String[] args) {

        Vestibular processoSeletivo = new Vestibular();
        processoSeletivo.arquivoEntrada("C:/Users/ledad/OneDrive/justatest.txt");
        processoSeletivo.calcularResultado();
        processoSeletivo.arquivoSaida("resultadofinal.txt");

    }
}