package pl.sdacademy;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;

import static pl.sdacademy.TimeUtils.*;

@Data
@AllArgsConstructor

public class Track {

    private String author;
    private int length;
    private String title;
    private String notes;

    public Track() {
    }

    @Override
    public String toString(){
      String result = author+ ", " + formattedLength(length) + ", " + title;
      if (!Strings.isNullOrEmpty(notes)){
          result +=", " + notes;
      }
      return result;
    }


}
