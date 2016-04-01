package hu.adamsan.bionica.competition.server.dao.rowmapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface ResultSetMapper<T> {
    T mapRow(ResultSet rs);

    void createRow(T clazz, PreparedStatement preparedStatement);

    boolean updateRow(T clazz, PreparedStatement preparedStatement);

}
