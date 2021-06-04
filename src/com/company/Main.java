
public class Main {     

    public static void main(String[] args) {

        // ESTES ABAIXOS SÃO OS EXEMPLOS DE MATRIZES UTILIZADOS PARA A CRIAÇÃO DESTE CÓDIGO SIMPLEX,
        // SE FOR USAR UM, DEVE-SE COMENTAR OS DEMAIS


        //PPLs COM SOLUÇÃO ÓTIMA
//        float[][] matriz = {
//                {3, 6, 1, 0, 60},
//                {4, 2, 0, 1, 32},
//                {-20, -24, 0, 0, 0}
//       };
//         float[][] matriz = {
//                 {4, 6, 1, 0, 0, 24},
//                 {4, 2, 0, 1, 0, 16},
//                 {0, 1, 0, 0, 1, 3},
//                 {-80, -60, 0, 0, 0, 0}
//     };

        //PPL ILIMITADO
//        float[][] matriz = {
//                {1, -2, 1, 0, 4},
//                {-1, 1, 0, 1, 3},
//                {-1, -3, 0, 0, 0}
//        };

        //PPL COM MÚLTIPLAS SOLUÇÕES ÓTIMAS
//        float[][] matriz = {
//               {1, 2, 1, 0, 4},
//               {-1, 1, 0, 1, 1},
//               {-2, -4, 0, 0, 0}
//        };


//PPL USANDO BIG M
float[][] matriz = {
                {1, 1, -1, 0, 0, 2},
                {-1, 1, 0, -1, 0, 1},
                {0, 1, 0, 0, 1, 3},
                {1, -2, 0, 0, 0, 0},
};






    BigM b1 = new BigM();
    b1.metodoBigM(matriz);

    }
}
