package hu.adamsan.bionica.competition.server.dao;

import java.util.ArrayList;
import java.util.List;

import hu.adamsan.bionica.competition.server.model.CompetitionResult;

public class CompetitionResultArrayListDao implements DAO<CompetitionResult> {

    public static CompetitionResultArrayListDao INSTANCE = new CompetitionResultArrayListDao();

    private CompetitionResultArrayListDao() {}

    private List<CompetitionResult> results = new ArrayList<>();

    @Override
    public List<CompetitionResult> findAll() {
        return results;
    }

    @Override
    public boolean delete(CompetitionResult entity) {
        results.remove(entity);
        return true;
    }

    @Override
    public CompetitionResult find(long id) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean save(CompetitionResult entity) {
        results.add(entity);
        results.sort((a, b) -> b.getScore() - a.getScore());
        return true;
    }

}
