package us.greatapps4you.econtato.services;

import com.opencsv.bean.CsvToBeanBuilder;
import us.greatapps4you.econtato.entities.Student;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class GoogleCsvService {
    private static String csvHeader = "Name,Given Name,Additional Name,Family Name,Yomi Name,Given Name Yomi,Additional Name Yomi,Family Name Yomi,Name Prefix,Name Suffix,Initials,Nickname,Short Name,Maiden Name,Birthday,Gender,Location,Billing Information,Directory Server,Mileage,Occupation,Hobby,Sensitivity,Priority,Subject,Notes,Language,Photo,Group Membership,Phone 1 - Type,Phone 1 - Value,Organization 1 - Type,Organization 1 - Name,Organization 1 - Yomi Name,Organization 1 - Title,Organization 1 - Department,Organization 1 - Symbol,Organization 1 - Location,Organization 1 - Job Description\n";

    public static String generate(String csvFile) throws FileNotFoundException {
        List<Student> students = new CsvToBeanBuilder(new FileReader(csvFile))
                .withType(Student.class)
                .withSeparator(';')
                .withSkipLines(1)
                .build()
                .parse();

        StringBuilder googleCsv = new StringBuilder(csvHeader);
        students.forEach(s -> {
            googleCsv.append(toGoogleCsv(s));
        });
        return googleCsv.toString();
    }

    private static String toGoogleCsv(Student student) {
        String googleCsv =
                student.getName()
                        + ",,,,,,,,,,,,,,,,,,,,,,,,,,,,* myContacts,Mobile," +
                        student.getPhone() +
                        ",unknown,,," +
                        student.getCourse() +
                        ",,,,";
        return googleCsv;
    }

    public static void write(String googleCsv)
            throws IOException {
        String googleCsvFile = LocalDateTime.now() + "_google.csv";
        FileOutputStream outputStream = new FileOutputStream(googleCsvFile);
        byte[] strToBytes = googleCsv.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

}
