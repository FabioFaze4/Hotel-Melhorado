package hotel;

import hotel.*;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    
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
    
    private static void reservarQuarto ()
    {
        
    }
    
    public static void main (String[] args)
    {
        int NUMERO_QUARTOS = 100;
        int NUMERO_PRODUTOS = 20;
        int opcao = 0, numeroQuarto = 0;
        
        
        boolean stop = false;
        
        String nomeHotel = "Bom Sorriso";
        String nome, email, telefone;
        
        Hotel hotel = new Hotel(NUMERO_QUARTOS, NUMERO_PRODUTOS);
        for (int i = 0; i < NUMERO_QUARTOS; i++) 
            hotel.getQuartos()[i] = new Quarto(i + 1);
        
        System.out.printf("Seja Bem Vindo ao Hotel %s\n", nomeHotel);
        System.out.printf("Aqui, sua Alegria e o nosso Presente! \n\n");
        System.out.printf("Regras: ");
        System.out.printf("Bom Tratamento com os Recepcionistas \n");
        System.out.printf("Tenha Bons Modos com os Outros Hospedes\n");
        System.out.printf("O Frigobar e Sensivel a Mudancas, Cuidado!\n");
        System.out.print ("\nVamos em Frente? ");
        sc.nextLine();
        
        while (true)
        {
            if (stop)
                break;
                       
            menuGeral();
            System.out.print("Digite: ");
            opcao = sc.nextInt();
            sc.nextLine();
            
            switch (opcao)
            {
                case 1:
                    System.out.println("\n\n\nHora de Reservar um Quarto!");
                    hotel.listarReservas();
                    System.out.println("\nDigite os Dados do Hospede: ");
                    
                    System.out.print("Nome: ");
                    nome = sc.nextLine();
                    
                    System.out.print("Email: ");
                    email = sc.nextLine();
                    
                    System.out.print("Telefone: ");
                    telefone = sc.nextLine();
                    
                    System.out.print("Número do Quarto: ");
                    numeroQuarto = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.print("\n");
                    Hospede hospede = new Hospede();
                    hospede.setNome(nome);
                    hospede.setEmail(email);
                    hospede.setTelefone(telefone);
                    
                    hotel.reservarQuarto(numeroQuarto, hospede);
                    break;
                    
                default:
                    stop = true;
                    break;
            }
        }
    }
}