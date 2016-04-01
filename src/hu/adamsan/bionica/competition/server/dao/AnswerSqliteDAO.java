package hu.adamsan.bionica.competition.server.dao;

import java.util.List;

import hu.adamsan.bionica.competition.server.model.Answer;

public class AnswerSqliteDAO implements DAO<Answer> {

    public static final AnswerSqliteDAO INSTANCE = new AnswerSqliteDAO();

    @Override
    public List<Answer> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(Answer entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Answer find(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean save(Answer entity) {
        // TODO Auto-generated method stub
        return false;
    }

}
