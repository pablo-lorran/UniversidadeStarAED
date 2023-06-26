public class CelulaCurso {
    public Curso elemento;
    public CelulaCurso prox;

    public CelulaCurso() {
        this(null, null);
    }

    public CelulaCurso(Curso elemento, CelulaCurso prox) {
        this.elemento = elemento;
        this.prox = prox;
    }
}
