package us.greatapps4you.econtato;

import com.opencsv.bean.CsvToBeanBuilder;
import us.greatapps4you.econtato.entities.Student;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    private static String csvHeader = "Name,Given Name,Additional Name,Family Name,Yomi Name,Given Name Yomi,Additional Name Yomi,Family Name Yomi,Name Prefix,Name Suffix,Initials,Nickname,Short Name,Maiden Name,Birthday,Gender,Location,Billing Information,Directory Server,Mileage,Occupation,Hobby,Sensitivity,Priority,Subject,Notes,Language,Photo,Group Membership,Phone 1 - Type,Phone 1 - Value,Organization 1 - Type,Organization 1 - Name,Organization 1 - Yomi Name,Organization 1 - Title,Organization 1 - Department,Organization 1 - Symbol,Organization 1 - Location,Organization 1 - Job Description\n";

    public static void main(String[] args) throws FileNotFoundException {

        String fileName = "/Users/jose/softdev/projects/dyone/nutricao.csv";

        List<Student> students = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(Student.class)
                .withSeparator(';')
                .withSkipLines(1)
                .build()
                .parse();

        System.out.print(csvHeader);

        students.forEach(s -> {
            System.out.println(toGoogleCsv(s));
        });

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
}
