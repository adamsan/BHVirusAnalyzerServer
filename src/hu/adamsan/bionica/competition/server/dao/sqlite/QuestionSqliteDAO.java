package hu.adamsan.bionica.competition.server.dao.sqlite;

import java.util.List;

import hu.adamsan.bionica.competition.server.dao.DAO;
import hu.adamsan.bionica.competition.server.model.Question;

public class QuestionSqliteDAO implements DAO<Question> {

    public static final QuestionSqliteDAO INSTANCE = new QuestionSqliteDAO();

    @Override
    public List<Question> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(Question entity) {
        //
        return false;
    }

    @Override
    public Question find(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean save(Question entity) {
        // TODO Auto-generated method stub
        return false;
    }

}
