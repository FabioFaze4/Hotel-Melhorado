package hotel;

public class Quarto {
    private int numero;
    private Boolean ocupado;
    private Hospede hospede;
    private double valorConsumido;
    
    public Quarto(int numero) {
        this.numero = numero;
        this.ocupado = false;
        this.hospede = null; 
        this.valorConsumido = 0.0;
    }
    
    public void reservarQuarto(Hospede novoHospede) 
    {
        if (!this.ocupado) {
            this.hospede = novoHospede;
            this.ocupado = true;
            this.valorConsumido = 0.0; 
            System.out.println("Quarto " + numero + " reservado com sucesso!");
        } 
        else 
            System.out.println("Não foi possível reservar. O quarto " + numero + " já está ocupado.");
    }
    
    public void cancelarReserva() {
        if (this.ocupado) {
            this.ocupado = false;
            this.hospede = null;
            this.valorConsumido = 0.0;
            System.out.println("Reserva do quarto " + numero + " cancelada/fechada com sucesso.");
        } 
        else if (this.valorConsumido != 0)
        {
            System.err.println("Não é Possível Cancelar uma Reserva com Armazenamento em Frigobar");
            System.err.println("A Opção mais Aceitável é o CheckOut");
                
        }
        else
            System.out.println("O quarto " + numero + " já está desocupado.");
    }
    
    public void consultarHospede() {
        if (this.ocupado && this.hospede != null) 
            this.hospede.exibirDados();
        else 
            System.out.println("O quarto " + numero + " está desocupado no momento.");
    }
    
    public void adicionarConsumo(double valor) {
        if (this.ocupado) {
            this.valorConsumido += valor;
            System.out.println("R$ " + valor + " adicionados ao consumo do quarto " + numero);
        } 
        else 
            System.out.println("Não é possível adicionar consumo. O quarto está desocupado.");
    }
    
    public void exibirResumo() 
    {
        System.out.println("===== RESUMO DO QUARTO " + numero + " =====");
        System.out.println("Status: " + (ocupado ? "Ocupado" : "Disponível"));
        if (ocupado && hospede != null) 
        {
            System.out.println("Hóspede Atual: " + hospede.getNome());
            System.out.println("Total Consumido: R$ " + valorConsumido);
        }
        System.out.println("=================================");
    }
    
    public int getNumero() { 
        return numero; 
    }
    public void setNumero(int numero) { 
        this.numero = numero; 
    }

    public boolean isOcupado() { 
        return ocupado; 
    } 
    public void setOcupado(boolean ocupado) { 
        this.ocupado = ocupado; 
    }

    public Hospede getHospede() { 
        return hospede; 
    }
    public void setHospede(Hospede hospede) { 
        this.hospede = hospede; 
    }

    public double getValorConsumido() { 
        return valorConsumido; 
    }
    public void setValorConsumido(double valorConsumido) { 
        this.valorConsumido = valorConsumido; 
    }
}
