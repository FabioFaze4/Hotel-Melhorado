package hotel;

public class Hotel {
    private Quarto quartos[] = new Quarto[100];
    private ProdutoFrigobar produtos[] = new ProdutoFrigobar[20];
    
    public Hotel(int qtdQuartos, int qtdProdutos) {
        this.quartos = new Quarto[qtdQuartos];
        this.produtos = new ProdutoFrigobar[qtdProdutos];
    }
    
    public Quarto[] getQuartos() { 
        return quartos; 
    }
    public void setQuartos(Quarto[] quartos) { 
        this.quartos = quartos; 
    }

    public ProdutoFrigobar[] getProdutosFrigobar() { 
        return produtos;
    }
    public void setProdutosFrigobar(ProdutoFrigobar[] produtosFrigobar) { 
        this.produtos = produtosFrigobar; 
    }
    
    
    private Quarto encontrarQuarto(int numero) {
        for (Quarto q : quartos) {
            if (q != null && q.getNumero() == numero) {
                return q;
            }
        }
        return null;
    }
    private ProdutoFrigobar encontrarProduto(String nome) {
        for (ProdutoFrigobar p : produtos) {
            if (p != null && p.getNomeProduto().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }
    
    
    
    public void reservarQuarto (int numeroQuarto, Hospede hospede)
    {
        Quarto quarto = encontrarQuarto (numeroQuarto);
        if (quarto != null)
            quarto.reservarQuarto(hospede);
        else
            System.out.println("Quarto " + numeroQuarto + " não existe no hotel.");
    }
    
    public void cancelarReserva(int numeroQuarto) {
        Quarto q = encontrarQuarto(numeroQuarto);
        if (q != null) 
            q.cancelarReserva();
        else 
            System.out.println("Quarto " + numeroQuarto + " não existe no hotel.");
    }
    
    public void listarReservas() {
        System.out.println("====== LISTA DE RESERVAS ATIVAS ======");
        boolean encontrou = false;
        for (Quarto q : quartos) {
            if (q != null && q.isOcupado()) {
                q.exibirResumo();
                encontrou = true;
            }
        }
        if (!encontrou) 
            System.out.println("Nenhuma reserva ativa no momento.");
    }
    
    public void consultarHospede(int numeroQuarto) {
        Quarto q = encontrarQuarto(numeroQuarto);
        if (q != null) 
            q.consultarHospede();
        else 
            System.out.println("Quarto " + numeroQuarto + " não cadastrado.");
    }
    
    public void editarHospede(int numeroQuarto, String novoNome, String novoEmail, String novoTelefone) {
        Quarto q = encontrarQuarto(numeroQuarto);
        if (q != null && q.isOcupado() && q.getHospede() != null) {
            Hospede h = q.getHospede();
            h.setNome(novoNome);
            h.setEmail(novoEmail);
            h.setTelefone(novoTelefone);
            System.out.println("Dados do hóspede do quarto " + numeroQuarto + " atualizados com sucesso!");
        } 
        else 
            System.out.println("Não foi possível editar. Quarto não encontrado ou está desocupado.");
    }
    
    
    
    
    public void listarProdutosFrigobar() {
        System.out.println("====== PRODUTOS NO FRIGOBAR ======");
        for (ProdutoFrigobar p : produtos) {
            if (p != null) {
                p.exibirProduto();
            }
        }
    }
    
    public void registrarConsumoFrigobar(int numeroQuarto, String nomeProduto, int quantidade) {
        Quarto q = encontrarQuarto(numeroQuarto);
        ProdutoFrigobar p = encontrarProduto(nomeProduto);

        if (q == null) {
            System.out.println("Quarto " + numeroQuarto + " não encontrado.");
            return;
        }
        if (p == null) {
            System.out.println("Produto '" + nomeProduto + "' não existe no frigobar.");
            return;
        }

        ConsumoFrigobar consumo = new ConsumoFrigobar(q, p, quantidade);
        consumo.registrarConsumo();
    }
    
    public void calcularTotalQuarto(int numeroQuarto) {
        Quarto q = encontrarQuarto(numeroQuarto);
        if (q != null) {
            System.out.println("O valor acumulado no Quarto " + numeroQuarto + " é: R$ " + q.getValorConsumido());
        } else {
            System.out.println("Quarto não encontrado.");
        }
    }
}
