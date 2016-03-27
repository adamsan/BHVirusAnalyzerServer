package hu.adamsan.bionica.competition.server.service;

import java.util.ArrayList;
import java.util.List;

import hu.adamsan.bionica.competition.server.model.CompetitionResult;

public class CompetitionResultService {

    public static final CompetitionResultService INSTANCE = new CompetitionResultService();

    List<CompetitionResult> results = new ArrayList<>();
    private CompetitionResultService() {
    }

    public List<CompetitionResult> getAllResults() {
        // TODO: really read database, for now it's just a dummy:
        return results;
    }

    public void insertNewResult(CompetitionResult competitionResult) {
        // TODO: implement dummy
        results.add(competitionResult);
        results.sort((o1, o2) -> o2.getScore() - o1.getScore());
    }
}
