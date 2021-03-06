package paivakirja.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Yksittäistä muistiinpanoa kuvaava luokka.
 */
public class Note {

    private LocalDate date;
    private int length;
    private String content;
    private User user;
    private int id;

    public Note(LocalDate date, int length, String content, User user, int id) {

        this.date = date;
        this.length = length;
        this.content = content;
        this.user = user;
        this.id = id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setMinutes(int length) {
        this.length = length;
    }

    public int getMinutes() {
        return this.length;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Date: " + this.date.format(format) + "\n" + "Length of the training session: "
                + this.length / 60 + " hours and " + this.length % 60 + " minutes" + "\n" + "Your notes from the session: " + this.content;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Note)) {
            return false;
        }
        Note other = (Note) obj;
        return id == other.id;
    }

}
