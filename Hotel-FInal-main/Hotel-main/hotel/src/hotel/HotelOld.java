package hotel;
import java.util.Scanner;
import java.util.Arrays;

public class HotelOld 
{
    private static void throwException (String text)
    {
        System.err.println("Erro: " + text);
    }
    
    private static boolean isEmptyQuarto (int quarto, boolean statusQuarto[])
    {
        return statusQuarto[quarto] == true;
    }
    
    private static void produtos ()
    {
        System.out.println("\n\nOpcoes: ");
        System.out.println("1- Agua");
        System.out.println("2- Refrigetante");
        System.out.println("3- Suco");
        System.out.println("4- Chocolate");
        System.out.println("Outro- Nenhum");
    }
    
    private static void menuGeral ()
    {
        System.out.println("\n\nOpcoes: ");
        System.out.println("1- Reserva de Quarto");
        System.out.println("2- Cancelar Reserva");
        System.out.println("3- Registrar Consumo do Frigobar");
        System.out.println("4- Check-out");
        System.out.println("5- Listar Reservas");
        System.out.println("6- Consultar Hospede");
        System.out.println("7- Editar Hospede");
        System.out.println("Outro- Sair");
    }
    
    private static void reservarQuarto (int quarto, boolean statusQuarto[], String nome, String nomeHospede[], int quartoHospede[])
    {
        if (statusQuarto[quarto] == true)
        {
            nomeHospede[quarto] = nome;
            statusQuarto[quarto] = false;
            quartoHospede[quarto] = quarto;
        }
    }
    
    private static void cancelarReserva (int quarto, String nomeHospede[], int quartoHospede[], boolean statusQuarto[], int consumo[][])
    {
        int consumoTotal = 0;
        for (int i = 0; i < 4; i++)
        {
            consumoTotal += consumo[quarto][i];
        }
        if (statusQuarto[quarto] == false && consumoTotal == 0)
        {
            statusQuarto[quarto] = true;
            quartoHospede[quarto] = 0;
            nomeHospede[quarto] = "";
            quartoHospede[quarto] = 0;
            for (int j = 0; j < 4; j++) 
                consumo[quarto][j] = 0;
        }
        else
        {
            throwException("Não é Possível Cancelar uma Reserva com um Hóspede com Consumo de Frigobar");
            throwException("Para Finalizar a Hospedagem, Faça o Checkout");
        }
    }
    
    private static void registrarConsumo (int quarto, int produto, int quantidade, int[][] consumo)
    {
        consumo[quarto][produto] += quantidade;
    }
    
    private static double totalFrigobarHospede (int quarto, int[][] consumo)
    {
        double[] precosProdutos = {7.99, 5.99, 6.99, 10.90}; 
        double totalFrigobar = 0;
        
        for (int j = 0; j < consumo[quarto].length; j++) 
            totalFrigobar += consumo[quarto][j] * precosProdutos[j];
        return totalFrigobar;
    }
    
    private static void checkOut(int quarto, boolean[] statusQuarto, String nomeHospede[], int[][] consumo, int diarias, int[] quartoHospede)
    {
        if (statusQuarto[quarto] == false)
        {
            double valorDiaria = 150.00;
            double totalHospedagem = diarias * valorDiaria;
            double totalFrigobar = totalFrigobarHospede (quarto, consumo);
            
            System.out.println("\n======= CONTA FINAL =======");
            System.out.println("Hospede: " + nomeHospede[quarto]);
            System.out.printf("Total Diarias: R$ %.2f\n", totalHospedagem);
            System.out.printf("Total Frigobar: R$ %.2f\n", totalFrigobar);
            System.out.printf("TOTAL A PAGAR: R$ %.2f\n", (totalHospedagem + totalFrigobar));
            System.out.println("================================\n");
            
            statusQuarto[quarto] = true;
            nomeHospede[quarto] = "";
            quartoHospede[quarto] = 0;
            for (int j = 0; j < 4; j++) 
                consumo[quarto][j] = 0;
        }
        else
        {
            System.out.println(">> Nenhum Hospede nesse Quarto!");
        }
    }
    
    private static void listarReservas(boolean[] statusQuarto, String nomeHospede[])
    {
        System.out.println("\n======= Todas as Reservas =======");
        System.out.println("Quarto x Hospede");
        for (int quarto = 0; quarto < statusQuarto.length; quarto++)
        {
            if (statusQuarto[quarto] == false)
                System.out.printf("%d x %s\n", quarto+1, nomeHospede[quarto]);
        }
    }
    
    private static void quartosDisponiveis (boolean[] statusQuarto)
    {
        System.out.println("\n======= Todas as Reservas =======");
        for (int quarto = 0; quarto < statusQuarto.length; quarto++)
        {
            if (statusQuarto[quarto] == true)
                System.out.printf("%d x Disponivel\n", quarto+1);
        }
    }
    
    private static void consultarHospede(int quarto, String nomeHospede[], int quartoHospede[], boolean statusQuarto[])
    {
        System.out.println("\n======= Consulta de Ocupacao =======");
        
        if (statusQuarto[quarto] == true)
        {
            System.out.println("Quarto não Ocupado");
            throwException("Ausencia de Dados do Quarto" + quartoHospede[quarto]);
        }
        else
        {
            System.out.printf("Nome: %s\n", nomeHospede[quarto]);
            System.out.printf("Numero da Reserva: %d\n", quartoHospede[quarto]+1);
        }
    }
    
    private static void moverFrigobar (int antigo, int novo, int consumo[][])
    {
        for (int i = 0; i < 4; i++) 
        {
            consumo[novo][i] = consumo[antigo][i];
            consumo[antigo][i] = 0;
        }
    }
    
    public static void Volt(String[] args) 
    {
        int maxQuartos = 100, maxProdutos = 4, maxQuantProduto = 100000, maxDiarias = 10000;
        
        Scanner scanner = new Scanner(System.in);
        String nomeHospede[] = new String[maxQuartos];
        int quartoHospede[] = new int[maxQuartos];
        boolean statusQuarto[] = new boolean[maxQuartos];
        int consumoFrigobar[][] = new int[maxQuartos][maxProdutos];

        Arrays.fill(statusQuarto, true);
        
        int opcao, quarto, produto, quantidade, diarias, novoQuarto, escolha;
        String nome, nomeHotel = "Chaves";
        boolean stop = false;
        
        System.out.printf("Seja Bem Vindo ao Hotel %s\n", nomeHotel);
        System.out.printf("Aqui, sua Alegria e o nosso Presente! \n\n");
        System.out.printf("Regras: ");
        System.out.printf("Bom Tratamento com os Recepcionistas \n");
        System.out.printf("Tenha Bons Modos com os Outros Hospedes\n");
        System.out.printf("O Frigobar e Sensivel a Mudancas, Cuidado!\n");
        System.out.print ("\nVamos em Frente? ");
        scanner.nextLine();
        
        while (true)
        {
            if (stop)
                break;
                       
            menuGeral();
            System.out.print("Digite: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
                            
            switch (opcao)
            {
                case 1:
                    System.out.println("\n\n\nHora de Reservar um Quarto!");
                    quartosDisponiveis (statusQuarto);
                    System.out.print("\nNome do Hospede: ");
                    nome = scanner.nextLine();
                                
                    if (nome.isEmpty())
                    {
                        throwException("Nome Vazio não e Disponivel para esse Sistema!");
                        break;
                    }
                                
                    System.out.print("Numero do Quarto: ");
                    quarto = scanner.nextInt();
                    scanner.nextLine();
                                
                    if (quarto < 1 || quarto > maxQuartos || !isEmptyQuarto(quarto-1, statusQuarto))
                    {
                        throwException(" Quarto Invalido ou Ocupado!");
                        break;
                    }
                                           
                    reservarQuarto(quarto-1, statusQuarto, nome, nomeHospede, quartoHospede);
                    System.out.println(">> Quarto Reservado! ");                                    
                    break;
                            
                case 2:
                    System.out.println("\n\n\nHora de Cancelar uma Reserva!");
                    System.out.print("Numero do Quarto: ");
                    quarto = scanner.nextInt();
                    scanner.nextLine();
                                
                    if (quarto < 1 || quarto > maxQuartos || isEmptyQuarto(quarto-1, statusQuarto))
                    {
                        throwException("Quarto Invalido!");
                        break;
                    }
   
                    cancelarReserva(quarto-1, nomeHospede, quartoHospede, statusQuarto, consumoFrigobar);
                    System.out.println(">> Reserva Cancelada!");
                    break;
                              
                case 3:
                    System.out.println("\n");
                    System.out.print("Numero do Quarto: ");
                    quarto = scanner.nextInt();
                    System.out.println("\n");
                    produtos();
                    scanner.nextLine();
                                
                    if (quarto < 1 || quarto > maxQuartos || statusQuarto[quarto-1] == true)
                    {
                        throwException("Numero do Quarto Invalido!");
                        break;
                    }
                                         
                    System.out.print("Numero do Produto Consumido: ");
                    produto = scanner.nextInt();
                    scanner.nextLine();
                                    
                    if (produto < 1 || produto > 4)
                    {
                        throwException("Produto Invalido!");
                        break;
                    }
                                   
                    System.out.print("Quantidade Consumida: ");
                    quantidade = scanner.nextInt();
                    scanner.nextLine();
                                    
                    if (quantidade < 1 || quantidade > maxQuantProduto)
                    {
                        throwException("Quantidade Invalida!");
                        break;
                    }
                                    
                    registrarConsumo(quarto-1, produto-1, quantidade, consumoFrigobar);
                    System.out.println(">> Registrado no Frigobar");
                    break;
                                
                case 4:
                    System.out.println("\n\n\nHora do Check-Out!");
                    System.out.print("Numero do Quarto: ");
                    quarto = scanner.nextInt();
                    scanner.nextLine();
                                
                    if (quarto < 1 || quarto > maxQuartos || statusQuarto[quarto-1] == true)
                    {
                        throwException("Quarto Invalido!");
                        break;
                    }
                                      
                    System.out.print("Numero de Diarias: ");
                    diarias = scanner.nextInt();
                    scanner.nextLine();

                    if (diarias < 1 || diarias > maxDiarias)
                    {
                        throwException("Quantidade de Diarias Invalida");
                        break;
                    }
                                
                    checkOut(quarto-1, statusQuarto, nomeHospede, consumoFrigobar, diarias, quartoHospede);
                    System.out.println(">> Check-Out Concluido");
                    break;
                        
                case 5:
                    System.out.println("\n\n\nHora de Visualizar todos os Quartos Disponíveis!");
                    listarReservas(statusQuarto, nomeHospede);
                    break;
                            
                case 6:
                    System.out.println("\n\n\nHora de Consultar um Hospede!");
                    System.out.print("Numero do Quarto: ");
                    quarto = scanner.nextInt();
                    scanner.nextLine();
                                
                    if (quarto < 1 || quarto > maxQuartos || statusQuarto[quarto-1] == true)
                    {
                        throwException("Quarto Invalido");
                        break;
                    }
                                
                    consultarHospede(quarto-1, nomeHospede, quartoHospede, statusQuarto);
                    break;
                                
                case 7:
                    System.out.println("\n\n\nHora de Alterar um Hospede!");
                    System.out.print("Numero do Quarto Atual: ");
                    quarto = scanner.nextInt();
                    scanner.nextLine();
                            
                    if (quarto < 1 || quarto > maxQuartos || statusQuarto[quarto-1] == true)
                    {
                        throwException("Quarto Invalido");
                        break;
                    }
                                
                    System.out.println("Hospede: " + nomeHospede[quarto-1]);
                    System.out.print("Deseja mudar [1] Nome, [2] Numero do Quarto, [3] Ambos: ");
                    escolha = scanner.nextInt();
                    scanner.nextLine();
                                
                    if (escolha < 1 || escolha > 3)
                    {
                        throwException("Escolha Invalida!");
                        break;
                    }
                                
                    if (escolha == 1 || escolha == 3)
                    {
                        System.out.print("Novo nome: ");
                        nome = scanner.nextLine();
                        nomeHospede[quarto-1] = nome;
                    }
                                
                   if (escolha == 2 || escolha == 3)
                   {
                        System.out.print("Novo Numero do Quarto: ");
                        novoQuarto = scanner.nextInt();
                        scanner.nextLine();
                                   
                        if (novoQuarto < 1 || novoQuarto > maxQuartos || !isEmptyQuarto(novoQuarto-1, statusQuarto))
                        {
                            throwException(" Quarto Invalido ou Ocupado!");
                            break;
                        }
                                  
                        nome = nomeHospede[quarto-1];
                        statusQuarto[quarto-1] = true;
                        moverFrigobar(quarto-1, novoQuarto-1, consumoFrigobar);
                        nomeHospede[quarto-1] = "";
                        reservarQuarto(novoQuarto-1, statusQuarto, nome, nomeHospede, quartoHospede);
                        System.out.println(">> Alteracao Feita com Sucesso! ");
                   }
                   break;     
                        
                default:
                    stop = true;
                    break;  
            }
            
            if (!stop)
            {
                System.out.print ("\nVamos em Frente? ");
                scanner.nextLine();
            } 
        }
        
        System.out.println("\n\n>> Obrigado por Contribuir com Nosso Hotel! ");
        System.out.println(">> Somos Gratos pelo seu Bem :)");
    }
}
