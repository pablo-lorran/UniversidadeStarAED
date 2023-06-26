public class Teste {
    public static void main(String[] args) {

        Vestibular processoSeletivo = new Vestibular();
        processoSeletivo.arquivoEntrada("C:/Users/ledad/OneDrive/arqtrab.txt");
        processoSeletivo.calcularResultado();
        processoSeletivo.arquivoSaida("resultadoProcessoSeletivo.txt");

    }
}