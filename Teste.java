public class Teste {
    public static void main(String[] args) {
        
       Vestibular processoSeletivo = new Vestibular();
       processoSeletivo.arquivoEntrada("dadosprocesso.txt");
       processoSeletivo.calcularResultado();
       processoSeletivo.arquivoSaida("resultadofinal.txt");

    }
}