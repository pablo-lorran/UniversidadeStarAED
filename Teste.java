public class Teste {
    public static void main(String[] args) {

        Vestibular processoSeletivo = new Vestibular();
        processoSeletivo.arquivoEntrada("C:/Users/ledad/OneDrive/000.txt");
        processoSeletivo.calcularResultado();
        processoSeletivo.arquivoSaida("resultadoProcessoSeletivo1.txt");

    }
}