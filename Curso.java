import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Curso {
    private int codCurso;
    private String nomeCurso;
    private int qtdVagas;
    private ArrayList<Candidato> listaAprovados;
    private Queue<Candidato> filaEspera;
    private double notaCorte;

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

    public void decrementarVagasDisponiveis() {
        if (qtdVagas > 0) {
            qtdVagas--;
        }
    }

    @Override
    public String toString() {
        return "Curso [codCurso=" + codCurso + ", nomeCurso=" + nomeCurso + ", qtdVagas=" + qtdVagas
                + ", listaAprovados=" + listaAprovados + ", filaEspera=" + filaEspera + "]";
    }

    public boolean inserirListaAprovados(Candidato candidato) {
        if (listaAprovados.size() < qtdVagas && !listaAprovados.contains(candidato)) {
            listaAprovados.add(candidato);
            setNotaCorte(candidato.getNotaMedia());
            return true;
        }
        return false;
    }

    public void inserirFilaEspera(Candidato candidato) {
        if (!listaAprovados.contains(candidato) && !filaEspera.contains(candidato)) {
            Queue<Candidato> filaTemp = new LinkedList<>();

            while (!filaEspera.isEmpty() && filaEspera.peek().getNotaMedia() > candidato.getNotaMedia()) {
                filaTemp.add(filaEspera.poll());
            }

            filaTemp.add(candidato);

            filaEspera = filaTemp;
        }
    }

    public void setNotaCorte(double notaCorte) {
        this.notaCorte = notaCorte;
    }

    public double getNotaCorte() {
        if (listaAprovados.isEmpty()) {
            return 0;
        }

        double menorNota = listaAprovados.get(0).getNotaMedia();

        for (Candidato candidato : listaAprovados) {
            if (candidato.getNotaMedia() < menorNota) {
                menorNota = candidato.getNotaMedia();
            }
        }

        return menorNota;
    }

    public void atualizaNotaCorte() {
        if (!listaAprovados.isEmpty()) {
            double menorNota = 0;

            for (Candidato candidato : listaAprovados) {
                if (candidato.getNotaMedia() < menorNota) {
                    menorNota = candidato.getNotaMedia();
                }
            }
            notaCorte = menorNota;
        }
    }

}
