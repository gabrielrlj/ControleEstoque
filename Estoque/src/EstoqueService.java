import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class EstoqueService {
    public static String cabecalho = "EMPRESA DE IMPORTAÇÃO DE PRODUTOS LTDA.\n" +
            "SISTEMA DE CONTROLE DE ESTOQUE\n";

     public void renderizarMenu(){

        System.out.println(cabecalho);
        System.out.println("MENU PRINCIPAL");
        System.out.println("1 - CADASTRO DE PRODUTOS\n" +
                "2 - MOVIMENTAÇÃO\n" +
                "3 - REAJUSTE DE PREÇOS\n" +
                "4 - RELATÓRIOS\n" +
                "0 - FINALIZAR\n");

        System.out.print("OPÇÃO: ");
    }

    public void renderizarCadastroProduto(){
        System.out.println(cabecalho);

        System.out.println("1 - INCLUSÃO\n" +
                "2 - ALTERAÇÃO\n" +
                "3 - CONSULTA\n" +
                "4 - EXCLUSÃO\n" +
                "0 - RETORNAR\n");

        System.out.print("OPÇÃO: ");
    }

    public int decidirCrud(int cont2, List<Produto> produtos) {
         if (cont2 == 0){
            return 1;
         }

         if (cont2 == 1){
             int cont = this.incluirProduto(produtos);
             return cont;
         }

         if (cont2 == 2){
             int cont = this.alterarProduto(produtos);
             return cont;
         }

         if (cont2 == 3){
             int cont = this.consultarProduto(produtos);
             return cont;
         }

        if (cont2 == 4){
            int cont = this.excluirProduto(produtos);
            return cont;
        }
         return 1;
     }

    private int excluirProduto(List<Produto> produtos) {
        System.out.println(cabecalho);
        System.out.println("EXCLUSÃO DE PRODUTO");
        Scanner sc = new Scanner(System.in);

        boolean sair = false;

        while (!sair){

            boolean repetir = true;
            while (repetir){
                System.out.print("DIGITE O NOME DO PRODUTO A SER EXCLUÍDO: ");
                String nomeProdExcluido = sc.nextLine();
                boolean achouProduto = false;

                Produto prodExcluido = null;
                for(Produto produto : produtos){
                    if (produto.getNome().equalsIgnoreCase(nomeProdExcluido)){
                        prodExcluido =  produto;
                        achouProduto = true;
                        break;
                    }
                }

                if (!achouProduto){
                    System.out.println("PRODUTO NÃO CADASTRADO.");
                    return 1;
                }

                this.mostrarDadosProduto(prodExcluido);
                int indexRemover = 0;
                for (int i = 0; i < produtos.size(); i++){
                    Produto produto = produtos.get(i);
                    if (produto.getNome().equalsIgnoreCase(nomeProdExcluido)){
                        indexRemover = i;
                        break;
                    }
                }
                System.out.print("CONFIRMA EXCLUSÃO (S/N)?");
                String confirma = sc.next();
                if (confirma.equalsIgnoreCase("S")){
                    produtos.remove(indexRemover);
                    System.out.println("PRODUTO REMOVIDO");
                }

                repetir = this.verificarSeQuerRepetir();
                if (!repetir) sair = true;
            }

        }

        return 1;
    }

    private int consultarProduto(List<Produto> produtos) {
        System.out.println(cabecalho);
        System.out.println("CONSULTA DE PRODUTO");
        Scanner sc = new Scanner(System.in);

        boolean sair = false;

        while (!sair){

            boolean repetir = true;
            while (repetir){
                System.out.print("DIGITE O NOME DO PRODUTO A SER CONSULTADO: ");
                String nomeProdAlterado = sc.nextLine();
                boolean achouProduto = false;

                Produto prodAlterado = null;
                for(Produto produto : produtos){
                    if (produto.getNome().equalsIgnoreCase(nomeProdAlterado)){
                        prodAlterado =  produto;
                        achouProduto = true;
                        break;
                    }
                }

                if (!achouProduto){
                    System.out.println("PRODUTO NÃO CADASTRADO.");
                    return 1;
                }

                this.mostrarDadosProduto(prodAlterado);
                repetir = this.verificarSeQuerRepetir();
                if (!repetir) sair = true;
            }

        }

        return 1;
    }

    private boolean verificarSeQuerRepetir() {
        Scanner sc = new Scanner(System.in);
        System.out.println("REPETIR OPERAÇÃO (S/N)?");
        String opt = sc.next();
        if (opt.equalsIgnoreCase("S")){
            return true;
        }else{
            return false;
        }
    }

    private int alterarProduto(List<Produto> produtos) {
        System.out.println(cabecalho);
        System.out.println("ALTERAÇÃO DE PRODUTO");

        Scanner sc = new Scanner(System.in);

        boolean sair = false;

        while (!sair){

            boolean repetir = true;
            while (repetir){
                System.out.print("DIGITE O NOME DO PRODUTO A SER ALTERADO: ");
                String nomeProdAlterado = sc.nextLine();
                boolean achouProduto = false;

                Produto prodAlterado = null;
                for(Produto produto : produtos){
                    if (produto.getNome().equalsIgnoreCase(nomeProdAlterado)){
                        prodAlterado =  produto;
                        achouProduto = true;
                        break;
                    }
                }

                if (!achouProduto){
                    System.out.println("PRODUTO NÃO CADASTRADO.");
                    return 1;
                }

                this.mostrarDadosProduto(prodAlterado);
                repetir = this.pedirDadosProduto(prodAlterado);
                if (!repetir) sair = true;
            }

        }

         return 1;
    }

    private boolean pedirDadosProduto(Produto prodAlterado) {
         Scanner sc = new Scanner(System.in);

        AtomicBoolean repetirTravaPreco = new AtomicBoolean(true);
        while (repetirTravaPreco.get()){
            System.out.print("PREÇO: ");
            Double preco = sc.nextDouble();
            if (preco <= 0){
                repetirTravaPreco.set(true);
                System.out.println("PREÇO DEVE SER MAIOR QUE ZERO, INSIRA OUTRO VALOR.");
            }else{
                prodAlterado.setPreco(preco);
                repetirTravaPreco.set(false);
            }
        }

        System.out.print("UNIDADE: ");
        String unidade = sc.next();
        prodAlterado.setUnidade(unidade);

        AtomicBoolean repetirTravaQtd = new AtomicBoolean(true);
        while (repetirTravaQtd.get()){
            System.out.print("QUANTIDADE: ");
            Integer qtd = sc.nextInt();
            if (qtd < 0){
                repetirTravaQtd.set(true);
                System.out.println("QUANTIDADE DEVE SER MAIOR OU IGUAL A ZERO, INSIRA OUTRO VALOR.");
            }else{
                prodAlterado.setQuantidade(qtd);
                repetirTravaQtd.set(false);
            }
        }

        System.out.println("REPETIR OPERAÇÃO (S/N)?");
        String opt = sc.next();
        if (opt.equalsIgnoreCase("S")){
            return true;
        }else{
            return false;
        }
    }

    private void mostrarDadosProduto(Produto produto){
         if (produto == null) return;
         System.out.println("DADOS DO PRODUTO");
         System.out.print("NOME: ");
         System.out.println(produto.getNome());
         System.out.print("PREÇO: ");
         System.out.println(produto.getPreco());
         System.out.print("UNIDADE: ");
         System.out.println(produto.getUnidade());
         System.out.print("QUANTIDADE: ");
         System.out.println(produto.getQuantidade());

    }

    private int incluirProduto(List<Produto> produtos) {
        System.out.println(cabecalho);
        Scanner sc = new Scanner(System.in);
        Produto novoProduto = new Produto();

        System.out.println("INCLUSÃO DE PRODUTO");
        AtomicBoolean repetirTravaNome = new AtomicBoolean(true);
        while (repetirTravaNome.get()){
            System.out.print("NOME: ");
            String nome = sc.nextLine();
            novoProduto.setNome(nome);

            produtos.forEach(produto -> {
                if (produto.equals(novoProduto)){
                    repetirTravaNome.set(true);
                    System.out.println("PRODUTO REPETIDO, INSIRA OUTRO NOME.");
                }else{
                    repetirTravaNome.set(false);
                }
            });

            if (produtos.isEmpty()){
                repetirTravaNome.set(false);
            }
        }

        AtomicBoolean repetirTravaPreco = new AtomicBoolean(true);
        while (repetirTravaPreco.get()){
            System.out.print("PREÇO: ");
            Double preco = sc.nextDouble();
            if (preco <= 0){
                repetirTravaPreco.set(true);
                System.out.println("PREÇO DEVE SER MAIOR QUE ZERO, INSIRA OUTRO VALOR.");
            }else{
                novoProduto.setPreco(preco);
                repetirTravaPreco.set(false);
            }
        }


        System.out.print("UNIDADE: ");
        novoProduto.setUnidade(sc.next().toUpperCase());

        AtomicBoolean repetirTravaQtd = new AtomicBoolean(true);
        while (repetirTravaQtd.get()){
            System.out.print("QUANTIDADE: ");
            Integer qtd = sc.nextInt();
            if (qtd < 0){
                repetirTravaQtd.set(true);
                System.out.println("QUANTIDADE DEVE SER MAIOR OU IGUAL A ZERO, INSIRA OUTRO VALOR.");
            }else{
                novoProduto.setQuantidade(qtd);
                repetirTravaQtd.set(false);
            }
        }


        System.out.print("CONFIRMA INCLUSÃO(S/N)? ");
        boolean continuar = true;

        while (continuar){
            String fim = sc.nextLine();
            if (fim.equalsIgnoreCase("S")){
                continuar = false;
                produtos.add(novoProduto);
                return 1;
            }
            if (fim.equalsIgnoreCase("N")){
                continuar = false;
                return 1;
            }

        }
        return 1;
    }


    public void renderizarMovimentacaoProduto() {
        System.out.println(cabecalho);

        System.out.println("MOVIMENTAÇÃO");

        System.out.println("1 - ENTRADA\n" +
                "2 - SAÍDA\n" +
                "0 - RETORNAR\n");

        System.out.print("OPÇÃO: ");
    }

    public int decidirOperacaoMovimentacao(int cont3, List<Produto> produtos) {

            if (cont3 == 0){
                return 1;
            }

            if (cont3 == 1){
                int cont = this.darEntrada(produtos);
                return cont;
            }

            if (cont3 == 2){
                int cont = this.darSaida(produtos);
                return cont;
            }
        return 1;
    }

    private int darSaida(List<Produto> produtos) {
        System.out.println(cabecalho);
        System.out.println("SAÍDA DE PRODUTO");

        Scanner sc = new Scanner(System.in);

        boolean sair = false;

        while (!sair){

            boolean repetir = true;
            while (repetir){
                System.out.print("DIGITE O NOME DO PRODUTO A TER ESTOQUE ATUALIZADO: ");
                String nomeProdAlterado = sc.nextLine();
                boolean achouProduto = false;

                Produto prodAlterado = null;
                for(Produto produto : produtos){
                    if (produto.getNome().equalsIgnoreCase(nomeProdAlterado)){
                        prodAlterado =  produto;
                        achouProduto = true;
                        break;
                    }
                }

                if (!achouProduto){
                    System.out.println("PRODUTO NÃO CADASTRADO.");
                    return 1;
                }

                this.mostrarDadosProduto(prodAlterado);
                repetir = this.pedirDadosSaida(prodAlterado);
                if (!repetir) sair = true;
            }

        }

        return 1;
    }

    private int darEntrada(List<Produto> produtos) {
        System.out.println(cabecalho);
        System.out.println("ENTRADA DE PRODUTO");

        Scanner sc = new Scanner(System.in);

        boolean sair = false;

        while (!sair){

            boolean repetir = true;
            while (repetir){
                System.out.print("DIGITE O NOME DO PRODUTO A TER ESTOQUE ATUALIZADO: ");
                String nomeProdAlterado = sc.nextLine();
                boolean achouProduto = false;

                Produto prodAlterado = null;
                for(Produto produto : produtos){
                    if (produto.getNome().equalsIgnoreCase(nomeProdAlterado)){
                        prodAlterado =  produto;
                        achouProduto = true;
                        break;
                    }
                }

                if (!achouProduto){
                    System.out.println("PRODUTO NÃO CADASTRADO.");
                    return 1;
                }

                this.mostrarDadosProduto(prodAlterado);
                repetir = this.pedirDadosEntrada(prodAlterado);
                if (!repetir) sair = true;
            }

        }

        return 1;
    }

    private boolean pedirDadosEntrada(Produto prodAlterado) {
        Scanner sc = new Scanner(System.in);

        Integer qtd = prodAlterado.getQuantidade();
        AtomicBoolean repetirTravaQtd = new AtomicBoolean(true);
        while (repetirTravaQtd.get()){
            System.out.print("QTDE ENTRADA: ");
            qtd = sc.nextInt();
            if (qtd < 0){
                repetirTravaQtd.set(true);
                System.out.println("QUANTIDADE DEVE SER MAIOR OU IGUAL A ZERO, INSIRA OUTRO VALOR.");
            }else{
                repetirTravaQtd.set(false);
            }
        }

        System.out.print("CONFIRMA ENTRADA (S/N)? ");
        String confirma = sc.next();
        if (confirma.equalsIgnoreCase("S")){
            Integer novaQtd = prodAlterado.getQuantidade() + qtd;
            prodAlterado.setQuantidade(novaQtd);
            System.out.println("ENTRADA REGISTRADA.");
            System.out.print("QTDE ATUALIZADA: ");
            System.out.println(prodAlterado.getQuantidade());
        }

        return this.verificarSeQuerRepetir();
     }


    private boolean pedirDadosSaida(Produto prodAlterado) {
        Scanner sc = new Scanner(System.in);

        Integer qtd = prodAlterado.getQuantidade();
        AtomicBoolean repetirTravaQtd = new AtomicBoolean(true);
        while (repetirTravaQtd.get()){
            System.out.print("QTDE SAÍDA: ");
            qtd = sc.nextInt();
            if (qtd < 0){
                repetirTravaQtd.set(true);
                System.out.println("QUANTIDADE DEVE SER MAIOR OU IGUAL A ZERO, INSIRA OUTRO VALOR.");
            }else{
                repetirTravaQtd.set(false);
            }
        }

        System.out.print("CONFIRMA SAÍDA (S/N)? ");
        String confirma = sc.next();
        if (confirma.equalsIgnoreCase("S")){
            Integer novaQtd = prodAlterado.getQuantidade() - qtd;
            prodAlterado.setQuantidade(novaQtd);
            System.out.println("ENTRADA REGISTRADA.");
            System.out.print("QTDE ATUALIZADA: ");
            System.out.println(prodAlterado.getQuantidade());
        }

        return this.verificarSeQuerRepetir();
    }

    public void renderizarReajustePreco() {
        System.out.println(cabecalho);

        System.out.println("REAJUSTE DE PREÇO");

        System.out.println("1 - ESCOLHER PRODUTO\n" +
                "0 - RETORNAR\n");

        System.out.print("OPÇÃO: ");
    }

    public int decidirOperacaoReajuste(int cont4, List<Produto> produtos) {

        if (cont4 == 0){
            return 1;
        }

        if (cont4 == 1){
            int cont = this.reajustarPreco(produtos);
            return cont;
        }

        return 1;
    }

    private int reajustarPreco(List<Produto> produtos) {
        System.out.println(cabecalho);
        System.out.println("REAJUSTE DE PREÇO");

        Scanner sc = new Scanner(System.in);

        boolean sair = false;

        while (!sair){

            boolean repetir = true;
            while (repetir){
                System.out.print("DIGITE O NOME DO PRODUTO A TER O PREÇO ATUALIZADO: ");
                String nomeProdAlterado = sc.nextLine();
                boolean achouProduto = false;

                Produto prodAlterado = null;
                for(Produto produto : produtos){
                    if (produto.getNome().equalsIgnoreCase(nomeProdAlterado)){
                        prodAlterado =  produto;
                        achouProduto = true;
                        break;
                    }
                }

                if (!achouProduto){
                    System.out.println("PRODUTO NÃO CADASTRADO.");
                    return 1;
                }

                this.mostrarDadosProduto(prodAlterado);
                repetir = this.pedirDadosReajuste(prodAlterado);
                if (!repetir) sair = true;
            }

        }

        return 1;
    }

    private boolean pedirDadosReajuste(Produto prodAlterado) {
        Scanner sc = new Scanner(System.in);

        Double preco = prodAlterado.getPreco();
        AtomicBoolean repetirTravaPreco = new AtomicBoolean(true);
        while (repetirTravaPreco.get()){
            System.out.print("PREÇO NOVO: ");
            preco = sc.nextDouble();
            if (preco <= 0){
                repetirTravaPreco.set(true);
                System.out.println("PREÇO DEVE SER MAIOR QUE ZERO, INSIRA OUTRO VALOR.");
            }else{
                repetirTravaPreco.set(false);
            }
        }

        System.out.print("CONFIRMA REAJUSTE (S/N)? ");
        String confirma = sc.next();
        if (confirma.equalsIgnoreCase("S")){
            prodAlterado.setPreco(preco);
            System.out.println("PREÇO REAJUSTADO.");
            System.out.print("NOVO PREÇO: ");
            System.out.println(prodAlterado.getPreco());
        }

        return this.verificarSeQuerRepetir();
    }

    public void renderizarRelatorio(List<Produto> produtos) {
        System.out.println(cabecalho);
        System.out.println("RELATÓRIO DE ESTOQUE");
        System.out.println("--------------------------------------------------------------");

        final Object[][] table = new String[produtos.size()][];


        for (int i = 0; i < produtos.size(); i++){
            table[i] = new String[] { produtos.get(i).getNome(), produtos.get(i).getPreco().toString(), produtos.get(i).getUnidade(), produtos.get(i).getQuantidade().toString() };
        }

        final Object[][] cabecalhoTabela = new String[1][];
        cabecalhoTabela[0] = new String[] { "PRODUTO", "PRECO", "UNIDADE", "QUANTIDADE" };

        for (final Object[] row : cabecalhoTabela) {
            System.out.format("%15s%15s%15s%15s%n", row);
        }

        for (final Object[] row : table) {
            System.out.format("%15s%15s%15s%15s%n", row);
        }
        System.out.println("\n");
    }
}
