package pl.sdacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<CD> CDs;

    public Library() {
        CDs = new ArrayList<>();
    }

    public List<CD> searchByGenre(Genre genre) {

        List<CD> result = new ArrayList<>();

        for (CD cd : CDs) {
            if (cd.getGenres().contains(genre)) {
                result.add(cd);
            }
        }

        return result;
//   Metoda ze streamami i lambda:CDs.stream().filter(cd ->cd.getGenres().contains(genre)).collect(Collectors.toList());

    }

    public List<CD> searchByCDTitle(String title) {
        List<CD> result = new ArrayList<>();

        for (CD cd : CDs) {
            if (cd.getTitle().contains(title)) {
                result.add(cd);
            }
        }
        return result;
    }

    public List<CD> searchbyTrackTitle(String title) {

        List<CD> result = new ArrayList<>();

        for (CD cd : CDs) {
            if (!cd.findTracksByTitle(title).isEmpty()) {
                result.add(cd);
            }
        }
        return result;
    }

    public void addCD(CD cd) {
        CDs.add(cd);

    }

    public void deleteCD(int index) {
        CDs.remove(index);
    }
}
