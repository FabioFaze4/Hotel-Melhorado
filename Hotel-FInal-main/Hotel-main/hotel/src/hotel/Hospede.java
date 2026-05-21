package hotel;


public class Hospede {
    private String nome;
    private String email;
    private String telefone;
    
    public Hospede ()
    { }
    
    public Hospede (String nome, String email, String telefone)
    {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
    
    public String getNome ()
    {
        return this.nome;
    }
    public void setNome (String nome)
    {
        this.nome = nome;
    }
    
    public String getEmail ()
    {
        return this.email;
    }
    public void setEmail (String email)
    {
        this.email = email;
    }
    
    public String getTelefone ()
    {
        return this.telefone;
    }
    public void setTelefone (String telefone)
    {
        this.telefone = telefone;
    }
    
    public void exibirDados ()
    {
        System.out.println("Dados do Hóspede " + this.getNome());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Telefone: " + this.getTelefone());
    }
}
