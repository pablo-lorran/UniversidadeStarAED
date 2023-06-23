import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Teste {

    private Aluno[] alunos;
    private int qtdAlunos;

    public Teste(){
        alunos = new Aluno[1000];
        qtdAlunos = 0;
    }

    private void quicksortNota(int esq, int dir){
        int i = esq, j = dir;
        double pivo = alunos[(dir+esq)/2].getNotaFinal();
        Aluno temp;
        while(i<=j){
            while(alunos[i].getNotaFinal() < pivo) i++;
            while(alunos[j].getNotaFinal() > pivo) j--;
                if(i<=j){
                    temp = alunos[i];
                    alunos[i] = alunos[j];
                    alunos[j] = temp;
                    i++;
                    j--;
                }
        }

        if(esq < j) quicksortNota(esq, j);
        if(i < dir) quicksortNota(i, dir);
    }

    private void quicksortMatricula(int esq, int dir){
        int i = esq, j = dir;
        double pivo = alunos[(esq+dir)/2].getMatricula();
        Aluno temp;
        while(i<=j){
            while(alunos[i].getMatricula() < pivo) i++;
            while(alunos[j].getMatricula() > pivo) j--;
                if(i <= j){
                    temp = alunos[i];
                    alunos[i] = alunos[j];
                    alunos[j] = temp;
                    i++;
                    j--;
                }
        }

        if(esq < j) quicksortMatricula(esq, j);
        if(i < dir) quicksortMatricula(i, dir);

    }

    public void ordernarCandidatos(){
        quicksortMatricula(0, qtdAlunos - 1);
        quicksortNota(0, qtdAlunos - 1);
    }
    
    public static void leitura(String arqEntrada){
        Scanner arqLeitura;
        ListaCursos listaCursos = new ListaCursos();
        ArrayList<String> listCursos = new ArrayList();

        try{
            arqLeitura = new Scanner(new FileInputStream(arqEntrada),"UTF-8");
            
            String lendoMN = arqLeitura.nextLine();
            String [] NM = lendoMN.split(";");
            int qtdCursos = Integer.parseInt(NM[0]);
            int qtdCandidatos = Integer.parseInt(NM[1]);
            
            
            for(int i = 0; i < qtdCursos; i++){
                String linhaN = arqLeitura.nextLine();
                String [] valorN = linhaN.split(";");

                int codCurso = Integer.parseInt(valorN[0]);
                String nomeCurso = valorN[1];
                double qtdVagas = Integer.parseInt(valorN[2]);
                Curso curso = new Curso(valorN,codCurso,nomeCurso,qtdVagas);
                listCursos.addAll((Collection<? extends String>) curso);
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

            }
            
        } catch (IOException e) {
            System.out.println("ERROR!");
        }
    }

    public static void registro(String arqEntrada){
        Formatter arqSaida;
        try {
            arqSaida = new Formatter(arqEntrada);
        } catch (Exception e) {
            System.out.println("ERROR!");
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        Vestibular resultadVestibular = new Vestibular(null, null, 0, 0);
        Path way = Paths.get("C:/Users/ledad/OneDrive/Área de Trabalho/justatest.txt");
        try{
            byte [] text = Files.readAllBytes(way);
            String leitor = new String(text, StandardCharsets.UTF_8);
            
            leitura(leitor);

            Curso cs = new Curso(args, 0, leitor, 0);
            
            JOptionPane.showMessageDialog(null, leitor);
            
            
        }catch(Exception erro){

        }

    }
}