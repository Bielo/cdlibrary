package pl.sdacademy;

public class TrackBuilder {

    private String author;
    private int length;
    private String title;
    private String notes;


    public TrackBuilder(){

    }

    public TrackBuilder setAuthor (String author){
        this.author = author;
        return this;
    }

    public TrackBuilder setLength (int length){
        this.length= length;
        return this;
    }

    public TrackBuilder setTitle (String title){
        this.title = title;
        return this;
    }

    public TrackBuilder setNotes (String  notes){
        this.notes = notes;
        return this;
    }

    public Track build(){
        return new Track(author,length,title,notes);
    }
}
