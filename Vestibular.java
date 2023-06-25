import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Formatter;
import java.util.Scanner;

public class Vestibular {
    private ListaCursos cursos;
    private Candidato [] candidatos;
    private int qtdCursos;
    private int qtdCandidatos;

    public Vestibular(){
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

    public void ordenarCandidatos(){

    }

    public void arquivoEntrada(String arqEntrada){
                try{
            Scanner arqLeitura = new Scanner(new FileInputStream(arqEntrada),"UTF-8");
            
            String lendoMN = arqLeitura.nextLine();
            String [] NM = lendoMN.split(";");
            int qtdCursos = Integer.parseInt(NM[0]);
            int qtdCandidatos = Integer.parseInt(NM[1]);
            
            
            for(int i = 0; i < qtdCursos; i++){
                String linhaN = arqLeitura.nextLine();
                String [] valorN = linhaN.split(";");

                int codCurso = Integer.parseInt(valorN[0]);
                String nomeCurso = valorN[1];
                int qtdVagas = Integer.parseInt(valorN[2]);
                cursos.inserirFim(new Curso(codCurso, nomeCurso, qtdVagas));
            }

            for(int j = 0; j < qtdCandidatos; j++){
                String linhaM = arqLeitura.nextLine();
                String [] valorM = linhaM.split(";");

                String nomeCandidato = valorM[0];
                double notaRed = Double.parseDouble(valorM[1]);
                double notaMat = Double.parseDouble(valorM[2]);
                double notaLing = Double.parseDouble(valorM[3]);
                int codCursoOp1 = Integer.parseInt(valorM[4]);
                int codCursoOp2 = Integer.parseInt(valorM[5]);

                candidatos[j] = new Candidato(nomeCandidato, notaRed, notaMat, notaLing, codCursoOp1, codCursoOp2);

            }
            
        } catch (IOException e) {
            System.out.println("ERROR!");
        }
    }
    
    public void arquivoSaida(String arqEntrada){
        try{
            Formatter arqSaida = new Formatter(arqEntrada, "UTF-8");
            
            for(int i = 0; i < qtdCursos; i++){
                Curso curso = cursos.pesquisar(i + 1);
                arqSaida.format(curso.getNomeCurso(), curso.getListaAprovados().get(curso.getListaAprovados().size() - 1).getnotaMedia());
            }

            for(int j = 0; j < qtdCandidatos; j++){
                arqSaida.format(candidatos[j].getNomeCandidato(), candidatos[j].getnotaMedia());
            }

            arqSaida.close();
        } catch (IOException e){
            System.out.println("ERRO!");
        }
    }

    public void ordenaDados(){

    }

    public void calcularResultado(){
        for(int i = 0; i < qtdCandidatos; i++){
            Candidato candidato = candidatos[i];
            Curso op1Curso = cursos.pesquisar(candidato.getCodCursoOp1());
            Curso op2Curso = cursos.pesquisar(candidato.getCodCursoOp2());

            if(op1Curso.inserirListaAprovados(candidato)){
                if(op2Curso.inserirListaAprovados(candidato)){
                    op2Curso.getListaAprovados().remove(candidato);
                    op1Curso.inserirListaAprovados(candidato);
                }
            } else{
                op1Curso.inserirFilaEspera(candidato);
                op2Curso.inserirFilaEspera(candidato);
            }
        }
    }

}
