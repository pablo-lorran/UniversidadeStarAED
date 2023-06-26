public class ListaCursos {
    private CelulaCurso primeiro;
    private CelulaCurso ultimo;

    public ListaCursos() {
        primeiro = new CelulaCurso();
        ultimo = primeiro;
    }

    public void inserirFim(Curso x) {
        CelulaCurso novaCelula = new CelulaCurso();
        novaCelula.elemento = x;
        ultimo.prox = novaCelula;
        ultimo = novaCelula;
    }

    public String exibirCursos() {
        String exibe = "";
        CelulaCurso tmp = primeiro.prox;

        while (tmp != null) {
            exibe += tmp.elemento.toString();
            tmp = tmp.prox;
        }
        return exibe;
    }

    public Curso pesquisar(int codigoCurso) {
        CelulaCurso tmp = primeiro.prox;
        while (tmp != null) {
            if (tmp.elemento != null && tmp.elemento.getCodCurso() == codigoCurso) {
                return tmp.elemento;
            }
            tmp = tmp.prox;
        }
        return null;
    }
}
