public class Main {

    private static final int tamanhoDaMatriz = 9;

    public static void main(String[] args) {

        int[][] matriz = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        if (resolvendoMatriz(matriz)) {
            System.out.println("Resolvido com êxito!\n");
        }
        else {
            System.out.println("Falha :(\n");
        }

        printMatriz(matriz);

    }


    private static void printMatriz(int[][] matriz) {
        for (int linha = 0; linha < tamanhoDaMatriz; linha++) {
            if (linha % 3 == 0 && linha != 0) {
                System.out.println("-----------");
            }
            for (int coluna = 0; coluna < tamanhoDaMatriz; coluna++) {
                if (coluna % 3 == 0 && coluna != 0) {
                    System.out.print("|");
                }
                System.out.print(matriz[linha][coluna]);
            }
            System.out.println();
        }
    }


    private static boolean validacaoNumero(int[][] matriz, int numero, int linha) {
        for (int i = 0; i < tamanhoDaMatriz; i++) {
            if (matriz[linha][i] == numero) {
                return true;
            }
        }
        return false;
    }

    private static boolean numeroNaColuna(int[][] matriz, int numero, int coluna) {
        for (int i = 0; i < tamanhoDaMatriz; i++) {
            if (matriz[i][coluna] == numero) {
                return true;
            }
        }
        return false;
    }

    private static boolean numeroNaCaixa(int[][] matriz, int numero, int linha, int coluna) {
        int linhaDaMatriz = linha - linha % 3;
        int colunaDaMatriz = coluna - coluna % 3;

        for (int i = linhaDaMatriz; i < linhaDaMatriz + 3; i++) {
            for (int j = colunaDaMatriz; j < colunaDaMatriz + 3; j++) {
                if (matriz[i][j] == numero) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean posicionamentoValido(int[][] matriz, int numero, int linha, int coluna) {
        return !validacaoNumero(matriz, numero, linha) &&
                !numeroNaColuna(matriz, numero, coluna) &&
                !numeroNaCaixa(matriz, numero, linha, coluna);
    }

    private static boolean resolvendoMatriz(int[][] matriz) {
        for (int linha = 0; linha < tamanhoDaMatriz; linha++) {
            for (int coluna = 0; coluna < tamanhoDaMatriz; coluna++) {
                if (matriz[linha][coluna] == 0) {
                    for (int numberToTry = 1; numberToTry <= tamanhoDaMatriz; numberToTry++) {
                        if (posicionamentoValido(matriz, numberToTry, linha, coluna)) {
                            matriz[linha][coluna] = numberToTry;

                            if (resolvendoMatriz(matriz)) {
                                return true;
                            }
                            else {
                                matriz[linha][coluna] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}