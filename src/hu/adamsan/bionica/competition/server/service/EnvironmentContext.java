package hu.adamsan.bionica.competition.server.service;

import hu.adamsan.bionica.competition.server.dao.CompetitionResultArrayListDao;
import hu.adamsan.bionica.competition.server.dao.DAO;
import hu.adamsan.bionica.competition.server.model.CompetitionResult;

public class EnvironmentContext {

    public static DAO<CompetitionResult> getCompetitionResultDAO() {
        // TODO: give different DAOs back based on the environment
        return CompetitionResultArrayListDao.INSTANCE;
    }

}
