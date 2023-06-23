public class Vestibular {
    private ListaCursos cursos;
    private Candidato [] candidatos;
    private int qtdCursos;
    private int qtdCandidatos;

    public Vestibular(ListaCursos cursos, Candidato[] candidatos, int qtdCursos, int qtdCandidatos) {
        this.cursos = cursos;
        this.candidatos = candidatos;
        this.qtdCursos = qtdCursos;
        this.qtdCandidatos = qtdCandidatos;
    }

    public void ordenarCandidatos(){

    }

    public void arquivoEntrada(String arqEntrada){
        
    }
    
    public void arquivoSaida(String arqSaida){

    }

    public void ordenaDados(){

    }

    public void calcularNotaCorte(){
        int somaTotal = 0, notaCorte;
        for(int i = 0; i < qtdCandidatos; i++){
            somaTotal += candidatos[i].notaMedia;
        }

        notaCorte = somaTotal/qtdCandidatos;
    }

}
