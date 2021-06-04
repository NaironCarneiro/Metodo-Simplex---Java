
import java.util.ArrayList;
import java.text.DecimalFormat;

public class BigM {

    DecimalFormat formatador = new DecimalFormat("0.00");
    ArrayList<Integer> colunmvetorBigM = new ArrayList<>();
    ArrayList<Integer> linevetorBigM = new ArrayList<>();
    ArrayList<Integer>contador = new ArrayList<>();

    int foCM,foLM;
    float maior = 0, total =0;
    float [][]matriz3;
    int cont0 = 0;
    int count = 0;
    int test = 0;

    boolean isBig = true;
    public void metodoBigM(float[][]matriz){
        int line1 = matriz.length;
        for (int i = (line1 - 1); i < matriz.length; i++) {
            for (int j = 0; j < (matriz[0].length-1); j++) {
                if (matriz[i][j] == 0) {
                        colunmvetorBigM.add(j); 
                    }else{
                        if(maior < matriz[i][j]){
                            maior = matriz[i][j];
                           
                        }
                    }
                }                                                       
        }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < (matriz[0].length-1); j++) {
                if(colunmvetorBigM.contains(j)){
                    if(matriz[i][j] == -1){
                        count++;
                    }else{
                        isBig = false;
                    
                        }
                    }
                }
            }      
            if(!isBig && count == 0){
                Simplex sim = new Simplex(matriz);
                sim.isOtima();

            }else{
         
                verificaBigM(matriz);
            }
    }
    
    public void verificaBigM(float [][] matriz){
        int colun = matriz[0].length;
        int line = matriz.length;
        float [][]matriz3 = new float[line][colun+count];
         for (int n = 0; n < matriz3.length; n++) {
            for (int m = 0; m < matriz3[0].length; m++) {
            
            if(m < (colun-1)){               //pegar valores anteriores 
                matriz3[n][m] = matriz[n][m];   
                }
                                  
                        if(colunmvetorBigM.contains(m)){   //array dos valores que 
                            if(matriz3[n][m] == -1){
                                
                                                //identificar e adicionar novas colunas 
                            matriz3[n][m+3] = 1;
                            matriz3[line-1][m+3] = maior * 10;  
                            
                                 }
                            
                          }
                          
                          
            
              
                if(m == (matriz3[0].length-1)){           //inserir dados na ultima coluna
                    matriz3[n][m] = matriz[n][colun-1];  
                }
            }
        } 
        total = maior*10;
        //////////////////////////////////////
        for (int i = 0; i < matriz3.length; i++) {
            for (int j = 0; j < matriz3[0].length; j++) {
                System.out.print(formatador.format(matriz3[i][j]) + "  |  ");
            }
            System.out.println(" ");
        }
        valorpivotante(matriz3);
    }
    
    public void valorpivotante(float[][] matriz3){
            int ultLinha = matriz3.length;
            int cont1 = 0;
        for (int i = ( ultLinha -1 ); i < matriz3.length; i++) {
            for (int j = 0; j < matriz3[0].length; j++) {
                if(!colunmvetorBigM.contains(j)){
                    if(matriz3[i][j] == total){              
                        for(int x = 0; x < matriz3.length; x++){
                            if(matriz3[x][j] == 1 && cont1 == 0 && !contador.contains(x)){
                                foCM = j;
                                foLM = x;
                                cont1++;
                                contador.add(x);
                            }
                        }
                    }
                }
          } cont0++;
    }
    pivotBigM(matriz3);
    }
    
    public void pivotBigM(float [][]matriz3){
        for(int x = 0; x < matriz3[0].length; x++){
            matriz3[3][x] = (matriz3[foLM][x] * (-total) ) + matriz3[3][x]; 
        }
    
        System.err.println("\n");
        if(test >= contador.size()-1){
            valorpivotante(matriz3);
            test++;
        }

        if(test == contador.size()-1){
        System.out.println("------------------RESOLVIDO PELO MÉTODO BIG M----------------------------\n");
        Simplex s2 = new Simplex(matriz3);
        s2.isOtima();
        }
        
    }
    
}