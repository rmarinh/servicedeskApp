package base.server.motorfluxoatividades.fluxoatividades.utils;

public class NiveisPrioridade {

    /*       0       1       2       3      4     5
    Niveis	0-20%  20-40%  40-60%  60-80% 80-100% 100+%
  4    5	 9	     19	     20 	 23	    25	   30
  3    4	 8	     14      18	     21	    24	   29
  2    3	 4	     10	     15	     17	    22	   28
  1    2	 2	      5	     11	     12	    16	   27
  0    1	 1	      3	      6	      7 	13	   26
     */
    public static int[][] TABELA_PRIORIDADES;

    public NiveisPrioridade() {
        TABELA_PRIORIDADES = new int[5][6];

        TABELA_PRIORIDADES[0][0] = 1;
        TABELA_PRIORIDADES[0][1] = 3;
        TABELA_PRIORIDADES[0][2] = 6;
        TABELA_PRIORIDADES[0][3] = 7;
        TABELA_PRIORIDADES[0][4] = 13;
        TABELA_PRIORIDADES[0][5] = 26;
        TABELA_PRIORIDADES[1][0] = 2;
        TABELA_PRIORIDADES[1][1] = 5;
        TABELA_PRIORIDADES[1][2] = 11;
        TABELA_PRIORIDADES[1][3] = 12;
        TABELA_PRIORIDADES[1][4] = 16;
        TABELA_PRIORIDADES[1][5] = 27;
        TABELA_PRIORIDADES[2][0] = 4;
        TABELA_PRIORIDADES[2][1] = 10;
        TABELA_PRIORIDADES[2][2] = 15;
        TABELA_PRIORIDADES[2][3] = 17;
        TABELA_PRIORIDADES[2][4] = 22;
        TABELA_PRIORIDADES[2][5] = 28;
        TABELA_PRIORIDADES[3][0] = 8;
        TABELA_PRIORIDADES[3][1] = 14;
        TABELA_PRIORIDADES[3][2] = 18;
        TABELA_PRIORIDADES[3][3] = 21;
        TABELA_PRIORIDADES[3][4] = 24;
        TABELA_PRIORIDADES[3][5] = 29;
        TABELA_PRIORIDADES[4][0] = 9;
        TABELA_PRIORIDADES[4][1] = 19;
        TABELA_PRIORIDADES[4][2] = 20;
        TABELA_PRIORIDADES[4][3] = 23;
        TABELA_PRIORIDADES[4][4] = 25;
        TABELA_PRIORIDADES[4][5] = 30;
    }

    public int getPriority(int percentagem, int nivelCriticidade) {

        int coluna = 0;
        int linha = 0;

        if (percentagem < 20) {
            coluna = 0;
        }else if (percentagem < 40) {
            coluna = 1;
        }else if (percentagem < 60) {
            coluna = 2;
        }else if (percentagem < 80) {
            coluna = 3;
        } else if (percentagem < 100) {
            coluna = 4;
        }else if (percentagem >= 100) {
            coluna = 5;
        }


        if (nivelCriticidade == 5) {
            linha = 4;
        }
        if (nivelCriticidade == 4) {
            linha = 3;
        }
        if (nivelCriticidade == 3) {
            linha = 2;
        }
        if (nivelCriticidade == 2) {
            linha = 1;
        }
        if (nivelCriticidade == 1) {
            linha = 0;
        }

        return TABELA_PRIORIDADES[linha][coluna];
    }

}
