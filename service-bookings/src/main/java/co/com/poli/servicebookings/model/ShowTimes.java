package co.com.poli.servicebookings.model;

import lombok.Data;
import java.sql.Date;

@Data
public class ShowTimes {
    private Long id;
    private Date dateMovie;
    private int movies;
}
