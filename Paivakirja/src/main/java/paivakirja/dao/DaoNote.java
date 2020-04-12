package paivakirja.dao;

import paivakirja.domain.Note;
import paivakirja.domain.User;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DaoNote {

    Note create(LocalDate date, int min, String content, User user) throws SQLException;

    List<Note> getAll(User user) throws SQLException;

    int totalTimeWasted(User user) throws SQLException;

    boolean deleteNote(LocalDate date, User user) throws SQLException;

}
