import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Curso {
    private int codCurso;
    private String nomeCurso;
    private int qtdVagas;
    private ArrayList<Candidato> listaAprovados;
    private Queue<Candidato> filaEspera;

    public Curso() {
        listaAprovados = new ArrayList<>();
        filaEspera = new LinkedList<>();
    }

    public Curso(int codCurso, String nomeCurso, int qtdVagas) {
        this.codCurso = codCurso;
        this.nomeCurso = nomeCurso;
        this.qtdVagas = qtdVagas;
        listaAprovados = new ArrayList<>();
        filaEspera = new LinkedList<>();
    }

    public boolean inserirListaAprovados(Candidato candidato) {
        if (listaAprovados.size() < qtdVagas) {
            listaAprovados.add(candidato);
            return true;
        }
        return false;
    }

    public void inserirFilaEspera(Candidato candidato) {
        filaEspera.add(candidato);
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

    public Queue<Candidato> getFilaEspera() {
        return filaEspera;
    }

    public void setFilaEspera(Queue<Candidato> filaEspera) {
        this.filaEspera = filaEspera;
    }

    @Override
    public String toString() {
        return "Curso [codCurso=" + codCurso + ", nomeCurso=" + nomeCurso + ", qtdVagas=" + qtdVagas
                + ", listaAprovados=" + listaAprovados + ", filaEspera=" + filaEspera + "]";
    }

    public void decrementarVagasDisponiveis() {
        qtdVagas--;
    }

    public double getNotaCorte() {
        if (listaAprovados.isEmpty()) {
            return 0;
        }
        double somaTotal = 0;

        for (Candidato candidato : listaAprovados) {
            somaTotal += candidato.getNotaMedia();
        }

        if (listaAprovados.size() > 1) {
            boolean criterioDesempate = true;
            double notaCorte = listaAprovados.get(0).getNotaMedia();

            for (Candidato candidato : listaAprovados) {
                if (candidato.getNotaMedia() != notaCorte) {
                    criterioDesempate = false;
                }
            }

            if (criterioDesempate) {
                double maiorNotaRed = 0;
                for (Candidato candidato : listaAprovados) {
                    double notaRed = candidato.getNotaRed();

                    if (notaRed > maiorNotaRed) {
                        maiorNotaRed = notaRed;
                    }
                }

                somaTotal += maiorNotaRed;
            }
        }

        return somaTotal / listaAprovados.size();
    }

}
