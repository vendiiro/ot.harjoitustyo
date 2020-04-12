package paivakirja.domain;

import java.util.Objects;

/**
 * The class represents the user.
 */
public class User {

    private String name;
    private String username;
    private int id;

    /**
     * Konstruktori.
     *
     * @param name Users real name
     * @param username Created username
     * @param id The users id
     */

    public User(String name, String username, int id) {
        this.name = name;
        this.username = username;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.username);
        hash = 11 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.username, other.username);
    }

}
