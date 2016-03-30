package hu.adamsan.bionica.competition.server.service;

import java.util.List;

import hu.adamsan.bionica.competition.server.dao.DAO;
import hu.adamsan.bionica.competition.server.model.CompetitionResult;

public class CompetitionResultService {

    public static final CompetitionResultService INSTANCE = new CompetitionResultService();

    private DAO<CompetitionResult> competitionResultDAO = EnvironmentContext.getCompetitionResultDAO();

    private CompetitionResultService() {
    }

    public List<CompetitionResult> getAllResults() {
        // TODO: really read database, for now it's just a dummy:
        return competitionResultDAO.findAll();
    }

    public void insertNewResult(CompetitionResult competitionResult) {
        competitionResultDAO.save(competitionResult);
    }

    public CompetitionResult find(long id) {
        return competitionResultDAO.find(id);
    }
}
