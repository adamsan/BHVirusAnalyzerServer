package hu.adamsan.bionica.competition.server.dao.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.adamsan.bionica.competition.server.dao.DAO;
import hu.adamsan.bionica.competition.server.dao.rowmapper.CompetitionResultMapper;
import hu.adamsan.bionica.competition.server.model.Answer;
import hu.adamsan.bionica.competition.server.model.CompetitionResult;

public class CompetitionResultSQLiteDAO implements DAO<CompetitionResult> {

    public static CompetitionResultSQLiteDAO INSTANCE = new CompetitionResultSQLiteDAO();
    public static final String SELECT_ALL = "select * from competitionresult order by score desc";
    public static final String SELECT_COMPETITIONRESULT = "select * from competitionresult where id=?";
    public static final String INSERT_COMPETITIONRESULT = "insert into competitionresult (ip_address,team_name,team_code,score,start_submit_time,end_submit_time)values(?,?,?,?,?,?)";

    private CompetitionResultSQLiteDAO() {}

    @Override
    public List<CompetitionResult> findAll() {
        List<CompetitionResult> result = new ArrayList<>();
        try (Connection con = SQLiteDAOManager.getConnection(); Statement stmt = con.createStatement();) {
            ResultSet resultSet = stmt.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                CompetitionResult competitionResult = new CompetitionResultMapper().mapRow(resultSet);
                System.out.println(competitionResult);
                List<Answer> answers = AnswerSqliteDAO.INSTANCE.findAll(competitionResult.getId());
                answers.forEach(a -> a.setCompetitionResult(competitionResult));
                competitionResult.setGivenAnswers(answers);
                result.add(competitionResult);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(CompetitionResult entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public CompetitionResult find(long id) {
        try (Connection con = SQLiteDAOManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SELECT_COMPETITIONRESULT);) {
            pstmt.setInt(1, (int) id);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                CompetitionResultMapper mapper = new CompetitionResultMapper();
                CompetitionResult result = mapper.mapRow(rs);
                List<Answer> answers = AnswerSqliteDAO.INSTANCE.findAll(result.getId());
                answers.forEach(a -> a.setCompetitionResult(result));
                result.setGivenAnswers(answers);
                return result;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(CompetitionResult entity) {
        if (entity.getId() == null) {
            insert(entity);
        } else {
            // update(entity);
        }
        return true;
    }

    private void insert(CompetitionResult entity) {
        try (Connection con = SQLiteDAOManager.getConnection();
                PreparedStatement stmt = con.prepareStatement(INSERT_COMPETITIONRESULT, Statement.RETURN_GENERATED_KEYS);) {
            new CompetitionResultMapper().createRow(entity, stmt, null);
            stmt.executeUpdate();
            try (ResultSet resultSet = stmt.getGeneratedKeys()) {
                resultSet.next();
                entity.setId(resultSet.getInt(1));
                for (Answer given : entity.getGivenAnswers()) {
                    QuestionSqliteDAO.INSTANCE.save(given.getQuestion());
                    given.setCompetitionResult(entity);
                    AnswerSqliteDAO.INSTANCE.save(given);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
