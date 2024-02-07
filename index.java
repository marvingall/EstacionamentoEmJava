import java.time.LocalTime;
import java.util.Scanner;

public class Estacionamento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tipo de veículo (carro ou moto): ");
        String tipoVeiculo = scanner.nextLine();

        double valorHora;
        double valorMinuto;

        if (tipoVeiculo.equalsIgnoreCase("carro")) {
            valorHora = 30.0;
            valorMinuto = 1.0;
        } else if (tipoVeiculo.equalsIgnoreCase("moto")) {
            valorHora = 18.0;
            valorMinuto = 0.30;
        } else {
            System.out.println("Tipo de veículo inválido.");
            scanner.close();
            return;
        }

        System.out.print("Digite o horário de entrada (HH:mm): ");
        String entradaStr = scanner.nextLine();
        LocalTime entrada = LocalTime.parse(entradaStr);

        System.out.print("Digite o horário de saída (HH:mm): ");
        String saidaStr = scanner.nextLine();
        LocalTime saida = LocalTime.parse(saidaStr);

        // Verifica se o horário de saída é posterior ao horário de entrada
        if (saida.isBefore(entrada)) {
            System.out.println("Horário de saída anterior ao horário de entrada.");
            scanner.close();
            return;
        }

        // Verifica se o horário de saída ultrapassa o limite de 20 horas
        LocalTime limiteSaida = LocalTime.of(20, 0);
        if (saida.isAfter(limiteSaida)) {
            System.out.println("Horário de saída além do limite permitido.");
            scanner.close();
            return;
        }

        // Verifica se o horário de entrada é anterior ao limite de 7 horas
        LocalTime limiteEntrada = LocalTime.of(7, 0);
        if (entrada.isBefore(limiteEntrada)) {
            System.out.println("Horário de entrada anterior ao limite permitido.");
            scanner.close();
            return;
        }

        long minutosPermanencia = entrada.until(saida, java.time.temporal.ChronoUnit.MINUTES);
        double valorAPagar = (minutosPermanencia / 60) * valorHora + (minutosPermanencia % 60) * valorMinuto;

        System.out.println("Valor a pagar: R$ " + valorAPagar);

        scanner.close();
    }
}
