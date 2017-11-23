package pl.sdacademy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LibraryTest {

    // Wyszukanie gatunku na nowo utworzonej bibliotece (bez plyty CD) nie powinno znalezc zadnej plyty
    @Test
    void searchByGenreOnEmptyCDShouldReturnEmptyList() {
        Library library = new Library();
        List<CD> result = library.searchByGenre(Genre.ROCK);
        assertTrue(result.isEmpty());
    }

    // wyszukanie garunku na bibliotece z plyutami cd nie powinn nic znalezc, jezeli nie istneiej szukany gatunek
    @Test
    void searchByGenreWithNonExistingGenreShouldReturnEmptyList() {
        Library library = new Library();
        Set<Genre> genres = new HashSet<>();
        genres.add(Genre.ROCK);
        genres.add(Genre.POP);
        CD cd = new CD();
        cd.setGenres(genres);
        List<CD> CDs = new ArrayList<>();
        CDs.add(cd);
        library.setCDs(CDs);
        List<CD> result = library.searchByGenre(Genre.AFRICAN_HEAVY_METAL);
        assertTrue(result.isEmpty());
    }

    // wyszukanie garunku powinno znalezc plyty o podanym gatunku
    @Test
    void searchByGenreShouldReturnSomeResults() {
        Library library = new Library();
        CD cd1 = createCDWithGenre(Genre.AFRICAN_HEAVY_METAL);
        CD cd2 = createCDWithGenre(Genre.ROCK, Genre.DRUM_AND_BASS);
        CD cd3 = createCDWithGenre(Genre.POP, Genre.ROCK);
        List<CD> CDs = new ArrayList<>();
        CDs.add(cd1);
        CDs.add(cd2);
        CDs.add(cd3);
        library.setCDs(CDs);
        List<CD> result = library.searchByGenre(Genre.ROCK);
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).getGenres().contains(Genre.ROCK));
        assertTrue(result.get(1).getGenres().contains(Genre.ROCK));
    }

    //public List<CD> searchByCDTitle(String title)

    @Test
    void searchByCDTitleOnEmptyLibraryShouldReturnEmptyList() {
        Library library = new Library();
        List<CD> result = library.searchByCDTitle("title");
        assertTrue(result.isEmpty());
    }

    @Test
    void searchByCDTitleShouldReturn2CDsWithTheSameTitle() {
        Library library = new Library();
        CD cd1 = new CD();
        cd1.setTitle("title");
        CD cd2 = new CD();
        cd2.setTitle("title");
        List<CD> CDs = new ArrayList<>();
        CDs.add(cd1);
        CDs.add(cd2);
        library.setCDs(CDs);
        List<CD> result = library.searchByCDTitle("title");
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).getTitle().contains("title"));
        assertTrue(result.get(1).getTitle().contains("title"));
    }

    @Test
    void shouldFindNoCDs() {
        Library library = new Library();
        List<CD> cdList = new ArrayList<>();
        CD cd1 = new CD();
        CD cd2 = new CD();
        CD cd3 = new CD();
        cd1.setTitle("title1");
        cd2.setTitle("title2");
        cd3.setTitle("title3");
        cdList.add(cd1);
        cdList.add(cd2);
        cdList.add(cd3);
        library.setCDs(cdList);
        List<CD> result = library.searchByCDTitle("new");
        assertTrue(result.isEmpty());
    }

    private CD createCDWithGenre(Genre... genres) {
        CD cd = new CD();
        Set<Genre> setOFGenres = new HashSet<>();
        for (Genre genre : genres) {
            setOFGenres.add(genre);
        }
        cd.setGenres(setOFGenres);
        return cd;
    }

    @Test
    void searchByTrackTitleOnEmptyLibraryShouldReturnEmptyList() {
        Library library = new Library();
        List<CD> result = library.searchbyTrackTitle("title");
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindNoTracks() {
        List<CD> cdList = new ArrayList<>();
        cdList.add(createTrackList("title 1"));
        cdList.add(createTrackList("title 2"));
        Library library = new Library();
        library.setCDs(cdList);
        List<CD> result = library.searchbyTrackTitle("new");
        assertTrue(result.isEmpty());

    }

    @Test
    void shouldFindTwoTracks() {
        List<CD> cdList = new ArrayList<>();
        cdList.add(createTrackList("title 1"));
        cdList.add(createTrackList("title 2"));
        Library library = new Library();
        library.setCDs(cdList);
        List<CD> result = library.searchbyTrackTitle("tle");
        assertTrue(result.size() == 2);
        assertTrue(!result.get(0).searchByTrackTitle("tle").isEmpty());
        assertTrue(!result.get(1).searchByTrackTitle("tle").isEmpty());
    }

    private CD createTrackList(String title) {
        CD cd = new CD();
        Track track = new TrackBuilder().setTitle(title).build();
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        cd.setTracks(tracks);
        return cd;
    }

    @Test
    void shouldReturnOneCD(){
        Library library = new Library();
        CD cd = new CD();
        library.addCD(cd);
        assertTrue(library.getCDs().size() == 1);
    }

    @Test
    void shouldReturnEmptyList(){
        Library library = new Library();
        CD cd = new CD();
        library.addCD(cd);
        library.deleteCD(0);
        assertTrue(library.getCDs().isEmpty());
    }

}
