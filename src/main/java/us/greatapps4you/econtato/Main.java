package us.greatapps4you.econtato;

import us.greatapps4you.econtato.services.GoogleCsvService;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        System.out.println("____________________________");
        System.out.println("| GreatApps4you LLC        |");
        System.out.println("| All rights reserved      |");
        System.out.println("| Copyright 2021           |");
        System.out.println("| https://greatapps4you.us |");
        System.out.println("|__________________________|");
        System.out.println();

        if (args.length == 0) {
            System.out.println("!!! Informe o arquivo csv");
            System.out.println();
            System.out.println();
            return;
        }

        String csvFile = args[0];

        if (!new File(csvFile).exists()) {
            System.out.println("!!! Arquivo não encontrado: " + csvFile);
            System.out.println();
            System.out.println();
            return;
        }

        String googleCsv;

        try {
            googleCsv = GoogleCsvService.generate(csvFile);
        } catch (Exception e) {
            System.out.println("!!! O arquivo csv deve conter tres colunas: NOME | CURSO | TELEFONE");
            System.out.println();
            System.out.println();
            return;
        }

        if (googleCsv == null) {
            System.out.println("!!! O arquivo " + csvFile
                    + " não pôde ser lido.");
            System.out.println();
            System.out.println();
            return;
        }

        try {
            String googleCsvFile = GoogleCsvService.write(googleCsv);
            System.out.println("Arquivo " +
                    googleCsvFile +
                    " salvo com sucesso!");
            System.out.println();
            System.out.println();

        } catch (Exception e) {
            System.out.println("!!! O arquivo " + csvFile
                    + " foi lido, mas o resultado não pôde ser salvo. " +
                    "Verifique se tem permissão para salvar arquivos neste diretório.");
            e.printStackTrace();

        }
    }
}
