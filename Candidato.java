
public class Candidato {
    private String nomeCandidato;
    private double notaRed;
    private double notaMat;
    private double notaLing;
    private int codCursoOp1;
    private int codCursoOp2;
    public double notaMedia;

    public Candidato(String nomeCandidato, double notaRed, double notaMat, double notaLing, int codCursoOp1,
            int codCursoOp2) {
        this.nomeCandidato = nomeCandidato;
        this.notaRed = notaRed;
        this.notaMat = notaMat;
        this.notaLing = notaLing;
        this.codCursoOp1 = codCursoOp1;
        this.codCursoOp2 = codCursoOp2;
    }

    public String getNomeCandidato() {
        return nomeCandidato;
    }

    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public double getNotaRed() {
        return notaRed;
    }

    public void setNotaRed(double notaRed) {
        this.notaRed = notaRed;
    }

    public double getNotaMat() {
        return notaMat;
    }

    public void setNotaMat(double notaMat) {
        this.notaMat = notaMat;
    }

    public double getNotaLing() {
        return notaLing;
    }

    public void setNotaLing(double notaLing) {
        this.notaLing = notaLing;
    }

    public int getCodCursoOp1() {
        return codCursoOp1;
    }

    public void setCodCursoOp1(int codCursoOp1) {
        this.codCursoOp1 = codCursoOp1;
    }

    public int getCodCursoOp2() {
        return codCursoOp2;
    }

    public void setCodCursoOp2(int codCursoOp2) {
        this.codCursoOp2 = codCursoOp2;
    }

    public void setMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public double getNotaMedia() {
        notaMedia = (notaRed + notaMat + notaLing) / 3;
        return notaMedia;
    }

    public String toString() {
        return nomeCandidato;
    }

}
