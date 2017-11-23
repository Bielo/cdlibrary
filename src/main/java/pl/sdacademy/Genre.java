package pl.sdacademy;

public enum Genre {

    AFRICAN_HEAVY_METAL("African heavy metal"),
    EXPERIMENTAL_MUSIC("Experimental music"),
    ROCK("Rock"),
    POP("Pop"),
    CLASSIC("Classic"),
    DRUM_AND_BASS("Drum'n'bass");

    private String name;

   public String getName(){
        return name;
    }

    Genre(String name) {
        this.name = name;
    }
}
