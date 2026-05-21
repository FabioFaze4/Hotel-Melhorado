package hotel;

public class ProdutoFrigobar {
    private String nomeProduto;
    private double preco;
    private int quantidade;

    public ProdutoFrigobar() 
    { }

    public ProdutoFrigobar(String nomeProduto, double preco, int quantidade) {
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public double calcularTotal() {
        return this.preco * this.quantidade;
    }

    public void exibirProduto() {
        System.out.println("--- Produto do Frigobar ---");
        System.out.println("Produto: " + this.nomeProduto);
        System.out.println("Preço unitário: R$ " + this.preco);
        System.out.println("Quantidade disponível: " + this.quantidade);
        System.out.println("Valor total em estoque: R$ " + this.calcularTotal());
        System.out.println("---------------------------");
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}