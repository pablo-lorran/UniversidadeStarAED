import java.util.ArrayList;
import java.util.Queue;

public class Curso {
    private int codCurso;
    private String nomeCurso;
    private int qtdVagas;
    private ArrayList<Candidato> listaAprovados;
    private Queue<Candidato> listaEspera;
  
    

    public Curso(int codCurso, String nomeCurso, int qtdVagas, ArrayList<Candidato> listaAprovados,
            Queue<Candidato> listaEspera) {
        this.codCurso = codCurso;
        this.nomeCurso = nomeCurso;
        this.qtdVagas = qtdVagas;
        this.listaAprovados = listaAprovados;
        this.listaEspera = listaEspera;
    }

    public Curso(String[] valorN, int codCurso2, String nomeCurso2, double qtdVagas2) {
    }

    public void inserirFilaEspera(Candidato candidato){

    }

    public void inserirListaAprovados(Candidato candidato){

    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getQtdVagas() {
        return qtdVagas;
    }

    public void setQtdVagas(int qtdVagas) {
        this.qtdVagas = qtdVagas;
    }

    public ArrayList<Candidato> getListaAprovados() {
        return listaAprovados;
    }

    public void setListaAprovados(ArrayList<Candidato> listaAprovados) {
        this.listaAprovados = listaAprovados;
    }

    public Queue<Candidato> getListaEspera() {
        return listaEspera;
    }

    public void setListaEspera(Queue<Candidato> listaEspera) {
        this.listaEspera = listaEspera;
    }

    @Override
    public String toString() {
        return "Curso [codCurso=" + codCurso + ", nomeCurso=" + nomeCurso + ", qtdVagas=" + qtdVagas
                + ", listaAprovados=" + listaAprovados + ", listaEspera=" + listaEspera + "]";
    }

    
    

}
