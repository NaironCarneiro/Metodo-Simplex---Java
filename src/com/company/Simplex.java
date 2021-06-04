
import java.util.ArrayList;


import java.text.DecimalFormat;

public class Simplex {
    DecimalFormat formatador = new DecimalFormat("0.00");
    ArrayList<Integer> linePercorrida = new ArrayList<>();
    ArrayList<Integer> vetor = new ArrayList<>();

    int foC, foL, tam;
    float[][] matriz;

    public Simplex(float[][] matriz) {
        this.matriz = matriz;
    }

    public void isOtima() {
        float menor = 0;
        int cont = 0;
        tam = matriz.length;
        for (int i = (tam - 1); i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] < 0) {
                    if (menor > matriz[i][j]) {
                        menor = matriz[i][j];
                        foL = i;
                        foC = j;
                    }   
                    cont++;
                }
            }
        }
        if (cont > 0) {
            saiBase();
        } else {
            imprimiMatriz();
            System.out.print("\n////////////////////////////// SOLUÇÃO ÓTIMA DA PPL ////////////////////////////////");
            printSol();
            ifinitasSolucoes();
        }
        
    }

    public void ifinitasSolucoes() {
        boolean isIfinita = true;
        boolean isIfinita1 = false;
        int localZero =0;
        int tam = matriz.length;
        for (int i = (tam - 1); i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (!vetor.contains(j)) {                //não vou usar as colunas que já estão zeradas
                    if (matriz[i][j] == 0) {
                        localZero = j;
                        for (int x = 0; x < matriz.length; x++) {
                            if (matriz[x][localZero] == 1) {
                                isIfinita = false;
                            }
                        }
                        isIfinita1 = true;
                    }
                }
            }
        }
        vetor.add(localZero);
        
        if (isIfinita1 && isIfinita) {
            System.out.print("\n////////////////////////////// SOLUÇÃO ÓTIMA DA PPL ////////////////////////////////");
            printSol();
            System.out.println("\n------------------POSSUI MAIS OUTRAS SOLUÇÕES------------------------");
        }
    }

    public void saiBase() {
        float result = 0;
        int cont = 0;
        float compara = 0;
        int ultimaCol = matriz[0].length;
        for (int i = 0; i < matriz.length; i++) {
            if (i != foL && matriz[i][foC] > 0) {                               //pega todas as linhas exceto FO e
                cont++;                                                         //realiza o cálculo da razão, assim decidindo qual linha entra na base
                result = matriz[i][ultimaCol - 1] / matriz[i][foC];
                if (result >= 0) {
                    if (compara == 0) {
                        compara = result;
                        foL = i;
                    } else {
                        if (compara > result) {
                            compara = result;
                            foL = i;
                        }
                    }
                }
            }
        }
        if (cont > 0) {
            pivotamento1();
        } else {
            System.out.println("-------------------------------PPL ILIMITADA-------------------------------------");
            imprimiMatriz();
        }
    }

    public void pivotamento1() {
        float valorPivot = matriz[foL][foC];
        for (int j = 0; j < matriz[0].length; j++) {
            matriz[foL][j] /= valorPivot;
        }
        linePercorrida.add(foL);
        pivotamento2();
    }

    public void pivotamento2() {
        for (int i = 0; i < matriz.length; i++) {
            float valorPivot = matriz[i][foC] * (-1);
            if (!linePercorrida.contains(i)) {
                for (int j = 0; j < matriz[0].length; j++) {
                    matriz[i][j] = ((matriz[foL][j] * (valorPivot)) + matriz[i][j]);
                    linePercorrida.add(i);
                }
            }
        }
        vetor.add(foC);
        linePercorrida.clear();   //limpar o array de posições de linhas e verifica se a solução é ótima
        isOtima();
    }

    public void imprimiMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(formatador.format(matriz[i][j]) + "  |  ");
            }
            System.out.println(" ");
        }
    }

    public void printSol() {
        int ultima = matriz[0].length;
        System.out.println("\n");
        for (int i = (tam - 1); i < matriz.length; i++) {
            System.out.print("FO --> " + formatador.format(matriz[i][ultima - 1]) +"\n ");
            for (int j = 0; j < matriz.length; j++) {
                if (j != (tam - 1)) {
                   System.out.print("  --> " + formatador.format(matriz[j][ultima - 1]));
                }
                System.out.println(" ");
                if (!vetor.contains(j)){
                    System.out.print("   --> " + formatador.format(matriz[i][j]));
                }
                System.out.print(" ");
            }
        }
    
}


}