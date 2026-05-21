package hotel;

public class ConsumoFrigobar {
    private Quarto quarto;
    private ProdutoFrigobar produto;
    private int quantidadeConsumida;

    public ConsumoFrigobar(Quarto quarto, ProdutoFrigobar produto, int quantidadeConsumida) {
        this.quarto = quarto;
        this.produto = produto;
        this.quantidadeConsumida = quantidadeConsumida;
    }

    public void registrarConsumo() {
        if (quarto.isOcupado()) {
            if (produto.getQuantidade() >= this.quantidadeConsumida) {
                double valorTotalConsumo = calcularValorConsumo();
                
                int estoqueAtualizado = produto.getQuantidade() - this.quantidadeConsumida;
                produto.setQuantidade(estoqueAtualizado);
                
                quarto.adicionarConsumo(valorTotalConsumo);
                
                System.out.println("Consumo registrado com sucesso!");
            } 
            else 
                System.out.println("Não há quantidade suficiente de " + produto.getNomeProduto() + " no frigobar.");
        } 
        else 
            System.out.println("Não é possível registrar consumo. O quarto " + quarto.getNumero() + " está desocupado.");
    }

    public double calcularValorConsumo() 
    {
        return produto.getPreco() * this.quantidadeConsumida;
    }

    public void exibirConsumo() {
        System.out.println("===== EXTRATO DE CONSUMO FRIGOBAR =====");
        System.out.println("Quarto: " + quarto.getNumero());
        System.out.println("Produto: " + produto.getNomeProduto());
        System.out.println("Quantidade Consumida: " + this.quantidadeConsumida);
        System.out.println("Valor deste consumo: R$ " + calcularValorConsumo());
        System.out.println("=======================================");
    }

    public Quarto getQuarto() { 
        return quarto; 
    }
    public void setQuarto(Quarto quarto) { 
        this.quarto = quarto;
    }

    public ProdutoFrigobar getProduto() { 
        return produto;
    }
    public void setProduto(ProdutoFrigobar produto) {
        this.produto = produto; 
    }

    public int getQuantidadeConsumida() {
        return quantidadeConsumida; 
    }
    public void setQuantidadeConsumida(int quantidadeConsumida) { 
        this.quantidadeConsumida = quantidadeConsumida; 
    }
}