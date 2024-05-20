package core.application.controllers;

import core.domain.application.CandidateRequirements;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.domain.repositories.IntegrityViolationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class SearchJobRequirementsFileController {


    public boolean verifyDegree(String jobOpeningRequirements, String degree) {
        try (BufferedReader br = new BufferedReader(new FileReader(jobOpeningRequirements))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.contains(degree)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
        return false;
    }

    public String selectFile(String file, List files, int fileIndex) {
        if (files.isEmpty() || fileIndex < 0 || fileIndex >= files.size()) {
            System.err.println("Seleção inválida.");
            return null;
        }

        Path selectedFile = (Path) files.get(fileIndex);

        return file + "/" + selectedFile.toString();
    }

    public List<Path> listFiles(String directoryPath) {
        List<Path> fileList = new ArrayList<>();
        Path dirPath = Paths.get(directoryPath);
        int count = 0;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    fileList.add(entry);
                    count++;
                    System.out.println(count + ".   " + entry.toString())
                    ;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao listar os ficheiros no diretório: " + e.getMessage());
        }
        return fileList;
    }


    public static String findDegree(List<String> candidateRequirements) {
        for (String line : candidateRequirements) {
            if (line.contains("Academic Degree:")) {
                return line.substring(line.indexOf("Academic Degree:") + "Academic Degree:".length()).trim();
            }
        }
        return null;
    }

    public List<String> findProgrammingLanguages(List<String> candidateRequirements) {
        List<String> programmingLanguages = new ArrayList<>();
        try {
            for (String line : candidateRequirements) {
                if (line.contains("Programming Languages:")) {

                    String languagesString = line.substring(line.indexOf("Programming Languages:") + "Programming Languages:".length()).trim();

                    String[] languages = languagesString.split(",");

                    for (String language : languages) {
                        programmingLanguages.add(language.trim());
                    }
                }
            }
        } catch (IntegrityViolationException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return programmingLanguages;
    }

    public boolean verifyProgLang(String file, List<String> progLangs) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                for (String progLang : progLangs) {
                    if (linha.contains(progLang)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
        return false;
    }


    public int processarFicheiro(String file) throws IOException {
        int maxIntervalo = 0;
        String file1 = file.toString();
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] datas = extrairDatasDaLinha(linha);
                if (datas != null) {
                    int intervalo = calcularIntervaloEmMeses(datas[0], datas[1]);
                    if (intervalo > maxIntervalo) {
                        maxIntervalo = intervalo;
                    }
                }
            }
        }
        return maxIntervalo;
    }

    public String[] extrairDatasDaLinha(String linha) {
        String[] partes = linha.split(" ");
        if (partes.length == 4) {
            String dataInicio = partes[0] + " " + partes[1];
            String dataFim = partes[2] + " " + partes[3];
            return new String[]{dataInicio, dataFim};
        }
        return null;
    }

    public int calcularIntervaloEmMeses(String mesAnoInicio, String mesAnoFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        YearMonth inicio = YearMonth.parse(mesAnoInicio, formatter);
        YearMonth fim = YearMonth.parse(mesAnoFim, formatter);
        return (int) ChronoUnit.MONTHS.between(inicio, fim);
    }





}
