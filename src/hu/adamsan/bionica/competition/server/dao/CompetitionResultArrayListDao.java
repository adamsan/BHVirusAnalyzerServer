package hu.adamsan.bionica.competition.server.dao;

import java.util.ArrayList;
import java.util.List;

import hu.adamsan.bionica.competition.server.model.CompetitionResult;

public class CompetitionResultArrayListDao implements DAO<CompetitionResult> {

    public static CompetitionResultArrayListDao INSTANCE = new CompetitionResultArrayListDao();

    private CompetitionResultArrayListDao() {}

    private List<CompetitionResult> results = new ArrayList<>();

    @Override
    public synchronized List<CompetitionResult> findAll() {
        return results;
    }

    @Override
    public synchronized boolean delete(CompetitionResult entity) {
        results.remove(entity);
        return true;
    }

    @Override
    public synchronized CompetitionResult find(long id) {
        return results.stream().filter((res) -> id == res.getId()).findFirst().orElse(null);
    }

    @Override
    public synchronized boolean save(CompetitionResult entity) {
        entity.setId(results.size() + 1);
        results.add(entity);
        results.sort((a, b) -> b.getScore() - a.getScore());
        return true;
    }

}
