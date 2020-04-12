/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paivakirja.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Date: " + this.date.format(DTF) + "\n" + "Length of the training session: "
                + this.length + "\n" + "Your notes from this session: " + this.content;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.date);
        hash = 17 * hash + this.length;
        hash = 17 * hash + Objects.hashCode(this.content);
        hash = 17 * hash + Objects.hashCode(this.user);
        hash = 17 * hash + this.id;
        return hash;
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
