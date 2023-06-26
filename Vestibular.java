import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;

public class Vestibular {
    private ListaCursos cursos;
    private Candidato[] candidatos;
    private int qtdCursos;
    private int qtdCandidatos;

    public Vestibular() {
        cursos = new ListaCursos();
        candidatos = new Candidato[1000];
        qtdCursos = 0;
        qtdCandidatos = 0;
    }

    public Vestibular(ListaCursos cursos, Candidato[] candidatos, int qtdCursos, int qtdCandidatos) {
        this.cursos = cursos;
        this.candidatos = candidatos;
        this.qtdCursos = qtdCursos;
        this.qtdCandidatos = qtdCandidatos;
    }

    public void arquivoEntrada(String arqEntrada) {
        try {
            Scanner arqLeitura = new Scanner(new FileInputStream(arqEntrada), "UTF-8");

            String lendoMN = arqLeitura.nextLine();
            String[] NM = lendoMN.split(";");
            qtdCursos = Integer.parseInt(NM[0]);
            qtdCandidatos = Integer.parseInt(NM[1]);

            cursos = new ListaCursos();
            candidatos = new Candidato[qtdCandidatos];

            for (int i = 0; i < qtdCursos; i++) {
                String linhaN = arqLeitura.nextLine();
                String[] valorN = linhaN.split(";");

                int codCurso = Integer.parseInt(valorN[0]);
                String nomeCurso = valorN[1];
                int qtdVagas = Integer.parseInt(valorN[2]);
                cursos.inserirFim(new Curso(codCurso, nomeCurso, qtdVagas));
            }

            for (int j = 0; j < qtdCandidatos; j++) {
                String linhaM = arqLeitura.nextLine();
                String[] valorM = linhaM.split(";");

                String nomeCandidato = valorM[0];
                double notaRed = Double.parseDouble(valorM[1]);
                double notaMat = Double.parseDouble(valorM[2]);
                double notaLing = Double.parseDouble(valorM[3]);
                int codCursoOp1 = Integer.parseInt(valorM[4]);
                int codCursoOp2 = Integer.parseInt(valorM[5]);

                candidatos[j] = new Candidato(nomeCandidato, notaRed, notaMat, notaLing, codCursoOp1, codCursoOp2);
            }

            arqLeitura.close();
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo: " + arqEntrada);
        } catch (NumberFormatException e) {
            System.out.println("Erro na conversão de um valor numérico no arquivo de entrada: " + arqEntrada);
        }
    }

    public void ordenaDados(Candidato candidato[], int esq, int dir) {
        int i = esq, j = dir;
        Candidato pivo = candidato[(esq + dir) / 2];
        while (i <= j) {
            while (candidato[i].getNotaMedia() > pivo.getNotaMedia()) {
                i++;
            }
            while (candidato[j].getNotaMedia() < pivo.getNotaMedia()) {
                j--;
            }
            if (i <= j) {
                Candidato tmp = candidato[i];
                candidato[i] = candidato[j];
                candidato[j] = tmp;
                candidato[i].setMedia(candidato[i].getNotaMedia());
                candidato[j].setMedia(candidato[j].getNotaMedia());
                i++;
                j--;
            }
        }
        if (esq < j) {
            ordenaDados(candidato, esq, j);
        }
        if (i < dir) {
            ordenaDados(candidato, i, dir);
        }
    }

    public void calcularResultado() {
    ordenaDados(candidatos, 0, qtdCandidatos - 1);

    for (int i = 0; i < qtdCandidatos; i++) {
        Candidato candidato = candidatos[i];
        Curso op1Curso = cursos.pesquisar(candidato.getCodCursoOp1());
        cursos.adicionaAprovado(candidato.getCodCursoOp1(), candidato);
        Curso op2Curso = cursos.pesquisar(candidato.getCodCursoOp2());

        if (op1Curso != null && op1Curso.getNomeCurso().equals("Física")) {
            if (candidato.getNotaMedia() > op1Curso.getNotaCorte() && !aprovadoOutroCurso(candidato, op1Curso)) {
                if (op1Curso.inserirListaAprovados(candidato)) {
                    op1Curso.decrementarVagasDisponiveis();
                    op1Curso.atualizaNotaCorte();
                }
            } else {
                op1Curso.inserirFilaEspera(candidato);
            }
        }

        if (op2Curso != null && op2Curso.getNomeCurso().equals("Física")) {
            if (candidato.getNotaMedia() > op2Curso.getNotaCorte() && !aprovadoOutroCurso(candidato, op2Curso)) {
                if (op2Curso.inserirListaAprovados(candidato)) {
                    op2Curso.decrementarVagasDisponiveis();
                    op2Curso.atualizaNotaCorte();
                }
            } else {
                op2Curso.inserirFilaEspera(candidato);
            }
        }
    }
}


    private boolean aprovadoOutroCurso(Candidato candidato, Curso cursoAtual) {
        for (int i = 1; i <= qtdCursos; i++) {
            if (i != cursoAtual.getCodCurso()) {
                Curso curso = cursos.pesquisar(i);
                if (curso != null && curso.getListaAprovados().contains(candidato)) {
                    curso.atualizaNotaCorte();
                    return true;
                }
            }
        }
        return false;
    }

    public void arquivoSaida(String arqSaida) {
        try {
            BufferedWriter arqEscrita = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(arqSaida), StandardCharsets.UTF_8));

            for (int i = 1; i <= qtdCursos; i++) {
                Curso curso = cursos.pesquisar(i);
                if (curso != null) {
                    arqEscrita.write(
                            String.format("%s - Nota de corte: %.2f\n", curso.getNomeCurso(), curso.getNotaCorte()));
                    arqEscrita.write("Candidatos Aprovados na Chamada Regular\n");
                    List<Candidato> listaAprovados = curso.getListaAprovados();
                    if (!listaAprovados.isEmpty()) {
                        for (Candidato candidato : listaAprovados) {
                            arqEscrita.write(String.format("%s %.2f\n", candidato.getNomeCandidato(),
                                    candidato.getNotaMedia()));
                        }
                    }
                    arqEscrita.write("Fila de Espera\n");
                    Queue<Candidato> filaEspera = curso.getFilaEspera();
                    if (!filaEspera.isEmpty()) {
                        for (Candidato candidato : filaEspera) {
                            arqEscrita.write(String.format("%s %.2f\n", candidato.getNomeCandidato(),
                                    candidato.getNotaMedia()));
                        }
                    }
                    arqEscrita.write("\n");
                } else {
                    arqEscrita.write(
                            String.format("%s Nenhum candidato aprovado\n\n", cursos.pesquisar(i).getNomeCurso()));
                }
            }

            arqEscrita.close();
            System.out.println("Arquivo de saída gerado com sucesso: " + arqSaida);
        } catch (IOException e) {
            System.out.println("Erro na gravação do arquivo de saída: " + arqSaida);
        }
    }

}
