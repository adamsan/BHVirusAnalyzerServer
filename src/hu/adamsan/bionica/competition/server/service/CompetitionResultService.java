package hu.adamsan.bionica.competition.server.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.adamsan.bionica.competition.server.model.CompetitionResult;

public class CompetitionResultService {

    public static final CompetitionResultService INSTANCE = new CompetitionResultService();

    List<CompetitionResult> results = new ArrayList<>();
    private CompetitionResultService() {
        CompetitionResult cr;
        cr = new CompetitionResult();
        cr.setId(1);
        cr.setIpAddress("127.0.0.1");
        cr.setScore(10);
        cr.setStartSubmitTime(Date.from(Instant.now()));
        cr.setEndSubmitTime(Date.from(Instant.now().plus(100, ChronoUnit.SECONDS)));
        cr.setTeamName("Test Team");
        cr.setTeamCode("TT0001");
        results.add(cr);
        cr = new CompetitionResult();
        cr.setId(1);
        cr.setIpAddress("127.0.0.1");
        cr.setScore(10);
        cr.setStartSubmitTime(Date.from(Instant.now()));
        cr.setEndSubmitTime(Date.from(Instant.now().plus(100, ChronoUnit.SECONDS)));
        cr.setTeamName("Test Team");
        cr.setTeamCode("TT0001");
        results.add(cr);
        cr = new CompetitionResult();
        cr.setId(1);
        cr.setIpAddress("127.0.0.1");
        cr.setScore(10);
        cr.setStartSubmitTime(Date.from(Instant.now()));
        cr.setEndSubmitTime(Date.from(Instant.now().plus(100, ChronoUnit.SECONDS)));
        cr.setTeamName("Test Team");
        cr.setTeamCode("TT0001");
        results.add(cr);
        cr = new CompetitionResult();
        cr.setId(1);
        cr.setIpAddress("127.0.0.1");
        cr.setScore(10);
        cr.setStartSubmitTime(Date.from(Instant.now()));
        cr.setEndSubmitTime(Date.from(Instant.now().plus(100, ChronoUnit.SECONDS)));
        cr.setTeamName("Test Team");
        cr.setTeamCode("TT0001");
        results.add(cr);

    }

    public List<CompetitionResult> getAllResults() {
        // TODO: really read database, for now it's just a dummy:
        return results;
    }

    public void insertNewResult(CompetitionResult competitionResult) {
        // TODO: implement dummy
        results.add(competitionResult);
    }
}
