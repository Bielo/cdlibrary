package pl.sdacademy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Track {

    private String author;
    private int length;
    private String title;
    private String notes;

    public Track() {
    }


}
