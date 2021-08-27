import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controlador {
    public static void main(String[] args){

        List<Produto> produtos = new ArrayList<>();
        EstoqueService servico = new EstoqueService();

        System.out.println();
        Scanner entrada = new Scanner(System.in);
        int cont = -1;

        while (cont != 0){
            servico.renderizarMenu();
            cont = entrada.nextInt();

            switch (cont){
                case 1:
                    servico.renderizarCadastroProduto();
                    int cont2;
                    cont2 = entrada.nextInt();
                    cont = servico.decidirCrud(cont2, produtos);
                    break;
                case 2:
                    servico.renderizarMovimentacaoProduto();
                    int cont3;
                    cont3 = entrada.nextInt();
                    cont = servico.decidirOperacaoMovimentacao(cont3, produtos);
                    break;
                case 3:
                    servico.renderizarReajustePreco();
                    int cont4;
                    cont4 = entrada.nextInt();
                    cont = servico.decidirOperacaoReajuste(cont4, produtos);
                    break;
                case 4:
                    servico.renderizarRelatorio(produtos);
                    break;
            }

        }

        System.out.println("FINALIZANDO");
    }


}
