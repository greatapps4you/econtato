package us.greatapps4you.econtato.entities;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@Builder
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByPosition(position = 1)
    private String course;
    @CsvBindByPosition(position = 2)
    private String phone;

}
