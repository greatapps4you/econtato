package us.greatapps4you.econtato;

import us.greatapps4you.econtato.services.GoogleCsvService;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Informe o arquivo csv");
            return;
        }

        String csvFile = args[0];

        if (!new File(csvFile).exists()) {
            System.out.println("Arquivo não encontrado: " + csvFile);
            return;
        }

        String googleCsv;

        try {
            googleCsv = GoogleCsvService.generate(csvFile);
        } catch (Exception e) {
            System.out.println("O arquivo csv deve conter tres colunas: NOME | CURSO | TELEFONE");
            return;
        }

        if (googleCsv == null) {
            System.out.println("O arquivo " + csvFile
                    + " não pôde ser lido.");
            return;
        }

        try {
            GoogleCsvService.write(googleCsv);
        } catch (Exception e) {
            System.out.println("O arquivo " + csvFile
                    + " foi lido, mas o resultado não pôde ser salvo. " +
                    "Verifique se tem permissão para salvar arquivos neste diretório.");

        }
    }
}
