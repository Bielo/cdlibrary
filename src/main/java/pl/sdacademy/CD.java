package pl.sdacademy;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.sdacademy.TimeUtils.formattedLength;

/**
 * Klasa CD przechowuje informacje o jednej plycie, m.in. nazwe wykonawcy, tytul,utwory, gatunki...
 * @author MB
 * @version 16.11.2017
 */

@Data
public class CD {

    /**
     * Nazwa wykonawcy
     */
    private String band;
    /**
     * Tytul plyty
     */
    private String title;
    /**
     * Wydawca plyty
     */
    private String publisher;
    /**
     * Data wydania plyty
     */
    private LocalDate relaseDate;
    /**
     * Gatunki plyty
     */
    private Set<Genre> genres;
    /**
     * Lista utworow
     */
    private List<Track> tracks;

    public CD(String band, String title, String publisher, LocalDate relaseDate){
        this.band=band;
        this.title=title;
        this.publisher=publisher;
        this.relaseDate=relaseDate;
        this.genres = new HashSet<>();
        this.tracks = new ArrayList<>();
    }

    /**
     * Konstruktor tworzy nowy obiekt CD z pusta lista genres oraz pusta lista tracks
     */

    public CD(){
        this.genres = new HashSet<>();
        this.tracks = new ArrayList<>();
    }


    /**
     * Metoda dodaje do płyty gatunek
     *
     * @param genre gatunek dodawany do płyty
     */

    public void addGenre(Genre genre){
        genres.add(genre);
    }

    /**
     * Usuwa podany gatunek z płyty
     * @see Genre
     *
     * @param genre gatunek do usunięcia
     */

    public void deleteGenre(Genre genre){
        genres.remove(genre);
    }

    public void addTrack(Track track){
        tracks.add(track);
    }

    public void deleteTrack(int trackNumber){
        tracks.remove(trackNumber);
    }

    public int getLength(){
        int sum = 0;
        for (Track track :tracks) {
            sum +=track.getLength();
        }
      return sum;

//        tracks.stream().mapToInt(tracks ->tracks.getLength()).sum();
    }

    public int getTrackCount(){
        return tracks.size();
    }

    public List<Track> findTracksByTitle(String title){
        return tracks.stream().filter(track -> track.getTitle().contains(title)).collect(Collectors.toList());
    }

    public List<Track> searchByTrackTitle(String title) {
        return tracks.stream()
                .filter(track -> track.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public String toString(){
        return band +", "
                + title+", "
                +relaseDate+", "
                + formattedLength(getLength())+", "
                +genresToString()+"\n"
                +tracksToString();
    }

    private String tracksToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0; i <tracks.size(); i++){
            stringBuilder.append("\t").append(i+1).append(". ").append(tracks.get(i));
            if(i < tracks.size() -1){
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private String genresToString() {
        return genres.stream()
                .map(genre -> genre.getName())
                .collect(Collectors.joining(", "));
    }

}
