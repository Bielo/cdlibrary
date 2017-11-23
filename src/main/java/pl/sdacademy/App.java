package pl.sdacademy;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {

    private static final String FILENAME = "library.txt";

    private Scanner scanner;
    private Library library;

    public App() {
        scanner = new Scanner(System.in);
        library = new Library();
    }

    public void processMainMenu() {
        showMainMenu();
        int input;
        do {
            input = readInt(1, 6);
            switch (input) {
                case 1:
                    showAllCDs();
                    break;
                case 2:
                    showCDsByGenre();
                    break;
                case 3:
                    showCDsByTitle();
                    break;
                case 4:
                    showCDsByTrackTitle();
                    break;
                case 5:
                    addCD();
                    break;
            }
        } while (input != 6);
    }

    private void addCD() {
        CD cd = new CD();
        addMainInfo(cd);
        addGenres(cd);
        addTracks(cd);
        library.addCD(cd);
    }

    private void addTracks(CD cd) {
        int input;
        do {
            System.out.println("1. Dodaj utwor");
            System.out.println("2. Wyjscie do glownego menu");
            input = readInt(1, 2);
            if (input == 1) {
                addOneTrack(cd);
            }
        } while (input < 2);
    }

    private void addOneTrack(CD cd) {
        System.out.println("Podaj tytul utworu: ");
        String title = readLine();
        System.out.println("Podaj autora: ");
        String author = readLine();
        System.out.println("Podaj długość: ");
        int length = readInt(1, 4800);
        System.out.println("Podaj opis: ");
        String notes = scanner.nextLine().trim();
        Track track = new Track(author, length, title, notes);
        cd.addTrack(track);
    }

    private void addGenres(CD cd) {
        Genre genre[] = Genre.values();
        for (int i = 0; i < genre.length; i++) {
            System.out.println((i + 1) + ". " + genre[i].getName());
        }
        int exit = genre.length + 1;
        System.out.println(exit + ". Wyjscie");
        int input;

        do {
            input = readInt(1, exit);
            if (input < exit) {
                cd.addGenre(genre[input - 1]);
            }
        } while (input < exit);
    }

    private void addMainInfo(CD cd) {
        System.out.println("Podaj wykonawce: ");
        String band = readLine();
        System.out.println("Podaj tytul: ");
        String title = readLine();
        System.out.println("Podaj wytwornie: ");
        String publisher = readLine();
        LocalDate relaseDate = readDate();
        cd.setBand(band);
        cd.setTitle(title);
        cd.setPublisher(publisher);
        cd.setRelaseDate(relaseDate);
    }

    private LocalDate readDate() {
        System.out.println("Podaj rok: ");
        int year = readInt(1950, 2050);
        return LocalDate.of(year, 1, 1);
    }

    private void showCDsByTrackTitle() {

    }

    private void showCDsByTitle() {
        System.out.println("Podaj tytul do wyszukania: ");
        String title = readLine();
        List<CD> result = library.searchByCDTitle(title);
        System.out.println("Znalezione plyty");
        showCdsFromList(result);

    }

    private void showCDsByGenre() {

    }

    private void showAllCDs() {
        System.out.println("Plyty z biblioteki");
        showCdsFromList(library.getCDs());
    }

    private void showCdsFromList(List<CD> CDs) {
        if (CDs.isEmpty()) {
            System.out.println("Brak plyt");
            return;
        }
        for (int i = 0; i < CDs.size(); i++) {
            System.out.println((i + 1) + ". " + CDs.get(i));
        }
    }


    private String readLine() {
        String line;
        do {
            line = scanner.nextLine();
        } while (line.trim().length() == 0);
        return line;
    }

    private int readInt(int min, int max) {
        boolean success;
        int a = 0;
        System.out.println("Wpisz liczbae od " + min + " do " + max + ": ");
        do {
            success = true;
            try {
                a = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("nie podaleś cyfry");
                success = false;
            }
        } while (!success || a < min || a > max);
        return a;
    }


    public void showMainMenu() {
        System.out.println(" _     _ _                          \n" +
                "| |   (_) |                         \n" +
                "| |    _| |__  _ __ __ _ _ __ _   _ \n" +
                "| |   | | '_ \\| '__/ _` | '__| | | |\n" +
                "| |___| | |_) | | | (_| | |  | |_| |\n" +
                "\\_____/_|_.__/|_|  \\__,_|_|   \\__, |\n" +
                "                               __/ |\n" +
                "                              |___/ ");
        System.out.println();


        System.out.println("1.Wyswietl liste wszystkich plyt");
        System.out.println("2.Wyswiietl liste plyty po gatunku");
        System.out.println("3.Wyswietl liste plyty po tytule");
        System.out.println("4.Wyswietl liste plyt po tytule utworu");
        System.out.println("5.Dodaj plyte");
        System.out.println("6. Wyjdz z aplikacji");
    }


    public static void main(String[] args) {
        App app = new App();
        app.loadLibraryFromFile();
        app.processMainMenu();
        app.saveLibraryToFile();
    }

    private void loadLibraryFromFile() {
        try {
            FileReader fileReader = new FileReader(FILENAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int numberOfCDs = Integer.parseInt(line);
            for (int i = 0; i < numberOfCDs; i++) {
                readCDFromFile(bufferedReader);
            }
            bufferedReader.close();
        } catch (IOException exception) {
            System.out.println("Nie udalo sie odczytac pliku " + FILENAME);
        }
    }

    private void readCDFromFile(BufferedReader bufferedReader) throws IOException {
        String band = bufferedReader.readLine();
        String title = bufferedReader.readLine();
        String publisher = bufferedReader.readLine();
        String releaseDate = bufferedReader.readLine();
        CD cd = new CD(band, title, publisher, LocalDate.parse(releaseDate));
        readGenresFromFile(bufferedReader, cd);
        readTracksFromFile(bufferedReader, cd);
        library.addCD(cd);

    }

    private void readTracksFromFile(BufferedReader bufferedReader, CD cd) throws IOException {
        String line = bufferedReader.readLine();
        int numberOfTracks = Integer.parseInt(line);
        for (int i = 0; i < numberOfTracks; i++) {
            String author = bufferedReader.readLine();
            int length = Integer.parseInt(bufferedReader.readLine());
            String title = bufferedReader.readLine();
            String notes = bufferedReader.readLine();
            cd.addTrack(new Track(author, length, title, notes));
        }

    }

    private void readGenresFromFile(BufferedReader bufferedReader, CD cd) throws IOException {
        String line = bufferedReader.readLine();
        int numberOfGenres = Integer.parseInt(line);
        for (int i = 0; i < numberOfGenres; i++) {
            line = bufferedReader.readLine();
            cd.addGenre(Genre.valueOf(line));
        }
    }


    private void saveLibraryToFile() {
        try {
            File file = new File(FILENAME);
            PrintWriter printWriter = new PrintWriter(file);

            printWriter.println(library.getCDs().size());
            for (CD cd : library.getCDs()) {
                saveCDToFile(printWriter, cd);
            }
            printWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nie udalo sie zapisac pliku " + FILENAME);
        }
    }

    private void saveCDToFile(PrintWriter printWriter, CD cd) {
        printWriter.println(cd.getBand());
        printWriter.println(cd.getTitle());
        printWriter.println(cd.getPublisher());
        printWriter.println(cd.getRelaseDate());
        saveGenresToFile(printWriter, cd.getGenres());
        saveTracksToFile(printWriter, cd.getTracks());
    }

    private void saveTracksToFile(PrintWriter printWriter, List<Track> tracks) {
        printWriter.println(tracks.size());
        for (Track track : tracks) {
            printWriter.println(track.getAuthor());
            printWriter.println(track.getLength());
            printWriter.println(track.getTitle());
            printWriter.println(track.getNotes());
        }

    }

    private void saveGenresToFile(PrintWriter printWriter, Set<Genre> genres) {
        printWriter.println(genres.size());
        for (Genre genre : genres) {
            printWriter.println(genre.name());
        }
    }
}
