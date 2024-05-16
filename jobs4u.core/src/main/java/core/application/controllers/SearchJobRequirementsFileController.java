package core.application.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SearchJobRequirementsFileController {


    public static boolean verifyWord(String file, String palavraProcurada) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.contains(palavraProcurada)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
        return false;
    }

    public static int processarFicheiro(String file) throws IOException {
        int maxIntervalo = 0; // Inicializa o maior intervalo como 0
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] datas = extrairDatasDaLinha(linha);
                if (datas != null) {
                    int intervalo = calcularIntervaloEmMeses(datas[0], datas[1]);
                    if (intervalo > maxIntervalo) {
                        maxIntervalo = intervalo; // Atualiza o maior intervalo
                    }
                }
            }
        }
        return maxIntervalo; // Retorna o maior intervalo encontrado
    }

    public static String[] extrairDatasDaLinha(String linha) {
        String[] partes = linha.split(" ");
        if (partes.length == 4) { // Espera-se que a linha tenha exatamente quatro partes: "June", "2020", "August", "2022"
            String dataInicio = partes[0] + " " + partes[1];
            String dataFim = partes[2] + " " + partes[3];
            return new String[]{dataInicio, dataFim};
        }
        return null; // A linha não contém exatamente duas datas
    }

    public static int calcularIntervaloEmMeses(String mesAnoInicio, String mesAnoFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        YearMonth inicio = YearMonth.parse(mesAnoInicio, formatter);
        YearMonth fim = YearMonth.parse(mesAnoFim, formatter);
        return (int) ChronoUnit.MONTHS.between(inicio, fim);
    }



}
