import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);

        CurrencyAPIClient API = new CurrencyAPIClient();

        optionLoop: while (true) {
            System.out.println("==============================================" +
                    "\nSeja bem-vindo ao Conversor de Moedas!\n" +
                    "\n1) Dólar -> Real" +
                    "\n2) Real -> Dólar" +
                    "\n3) Euro -> Real" +
                    "\n4) Real -> Euro" +
                    "\n5) Peso Mexicano -> Real" +
                    "\n6) Real -> Peso Mexicano" +
                    "\n7) Sair\n" +
                    "\nEscolha a opção desejada:" +
                    "\n==============================================");

            int option = input.nextInt();
            if (option >= 1 && option <= 6) {
                System.out.print("Valor a ser convertido: ");
                double value = input.nextDouble();

                String from = "";
                String to = "";
                switch (option){
                    case 1 -> { from = "USD"; to = "BRL"; }
                    case 2 -> { from = "BRL"; to = "USD"; }
                    case 3 -> { from = "EUR"; to = "BRL"; }
                    case 4 -> { from = "BRL"; to = "EUR"; }
                    case 5 -> { from = "MXN"; to = "BRL"; }
                    case 6 -> { from = "BRL"; to = "MXN"; }
                }

                try {
                    double convertedValue = API.convert(value, from, to);
                    System.out.printf("\nConversão: %.2f [%s] = %.2f [%s]\n", value, from, convertedValue, to);
                } catch (Exception e) {
                    System.out.println("Erro ao converter moeda: " + e.getMessage());
                }
            } else if (option == 7) {
                System.out.println("Encerrando o programa... Até mais!");
                break optionLoop;
            } else {
                System.out.println("Opção inválida. Tente novamente :(");
            }
        }

        input.close();
    }
}