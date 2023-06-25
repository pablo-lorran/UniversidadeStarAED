import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Curso {
    private int codCurso;
    private String nomeCurso;
    private int qtdVagas;
    private ArrayList<Candidato> listaAprovados;
    private Queue<Candidato> listaEspera;
  
    public Curso(){
        listaAprovados = new ArrayList<>();
        listaEspera = new LinkedList<>();
    }

    public Curso(int codCurso, String nomeCurso, int qtdVagas) {
        this.codCurso = codCurso;
        this.nomeCurso = nomeCurso;
        this.qtdVagas = qtdVagas;
        listaAprovados = new ArrayList<>();
        listaEspera = new LinkedList<>();
    }

    public boolean inserirListaAprovados(Candidato candidato){
        if(listaAprovados.size() < qtdVagas){
            listaAprovados.add(candidato);
            return true;
        }
        return false;
    }

    public void inserirFilaEspera(Candidato candidato){
        listaEspera.add(candidato);
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
