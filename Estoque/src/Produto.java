public class Produto {
    private String nome;
    private Double preco;
    private String unidade;
    private Integer quantidade;

    public Produto(String nome, Double preco, String unidade, Integer quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.unidade = unidade;
        this.quantidade = quantidade;
    }

    public Produto(){

    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", unidade='" + unidade + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


}
