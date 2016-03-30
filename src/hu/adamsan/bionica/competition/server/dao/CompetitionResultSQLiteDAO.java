package hu.adamsan.bionica.competition.server.dao;

import java.util.List;

import hu.adamsan.bionica.competition.server.model.CompetitionResult;

public class CompetitionResultSQLiteDAO implements DAO<CompetitionResult> {

    public static CompetitionResultSQLiteDAO INSTANCE = new CompetitionResultSQLiteDAO();

    private CompetitionResultSQLiteDAO() {}

    @Override
    public List<CompetitionResult> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(CompetitionResult entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public CompetitionResult find(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean save(CompetitionResult entity) {
        // TODO Auto-generated method stub
        return false;
    }

}
