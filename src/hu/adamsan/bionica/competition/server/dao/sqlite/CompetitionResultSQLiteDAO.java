package hu.adamsan.bionica.competition.server.dao.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.adamsan.bionica.competition.server.dao.DAO;
import hu.adamsan.bionica.competition.server.dao.rowmapper.AnswerResultMapper;
import hu.adamsan.bionica.competition.server.dao.rowmapper.CompetitionResultMapper;
import hu.adamsan.bionica.competition.server.dao.rowmapper.QuestionMapper;
import hu.adamsan.bionica.competition.server.model.Answer;
import hu.adamsan.bionica.competition.server.model.CompetitionResult;
import hu.adamsan.bionica.competition.server.model.Question;

public class CompetitionResultSQLiteDAO implements DAO<CompetitionResult> {

    public static CompetitionResultSQLiteDAO INSTANCE = new CompetitionResultSQLiteDAO();
    public static final String SELECT_ALL = "select * from competitionresult order by score desc";
    public static final String SELECT_COMPETITIONRESULT = "select * from competitionresult where id=?";
    public static final String INSERT_COMPETITIONRESULT = "insert into competitionresult (ip_address,team_name,team_code,score,start_submit_time,end_submit_time)values(?,?,?,?,?,?)";
    public static final String SELECT_ANSWERS_BY_RESULT = "select * from answer where competitionresult_id = ?";
    public static final String SELECT_QUESTION = "select * from question where id = ?";
    public static final String INSERT_ANSWER = "insert into answer (answer_order,question_id,given_answer,competitionresult_id) values (?,?,?,?)";
    public static final String INSERT_QUESTION = "insert into question (question,correct_answer, point_value) values (?,?,?)";

    private CompetitionResultSQLiteDAO() {}

    @Override
    public List<CompetitionResult> findAll() {
        List<CompetitionResult> result = new ArrayList<>();
        try (Connection con = SQLiteDAOManager.getConnection(); Statement stmt = con.createStatement();) {
            ResultSet resultSet = stmt.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                CompetitionResult competitionResult = new CompetitionResultMapper().mapRow(resultSet);
                System.out.println(competitionResult);

                ArrayList<Answer> answers = new ArrayList<>();
                competitionResult.setGivenAnswers(answers);// TODO: load questions and responses
                getAnswersToCompetitionResult(con, competitionResult, answers);

                result.add(competitionResult);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void getAnswersToCompetitionResult(Connection con, CompetitionResult competitionResult, List<Answer> answers) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_ANSWERS_BY_RESULT)) {
            pstmt.setInt(1, competitionResult.getId());
            try (ResultSet rs = pstmt.executeQuery()) {
                AnswerResultMapper mapper = new AnswerResultMapper();
                while (rs.next()) {
                    Answer answer = mapper.mapRow(rs);
                    answers.add(answer);
                    int questionId = rs.getInt("question_id");//
                    try (PreparedStatement pstmt2 = con.prepareStatement(SELECT_QUESTION)) {
                        pstmt2.setInt(1, questionId);
                        try (ResultSet rsQuestion = pstmt2.executeQuery();) {
                            QuestionMapper questionMapper = new QuestionMapper();
                            while (rsQuestion.next()) {
                                Question question = questionMapper.mapRow(rsQuestion);
                                answer.setQuestion(question);
                            }
                        }
                    }
                }

            }
        }
    }

    @Override
    public boolean delete(CompetitionResult entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public CompetitionResult find(long id) {
        CompetitionResult result = null;
        try (Connection con = SQLiteDAOManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SELECT_COMPETITIONRESULT);) {
            pstmt.setInt(1, (int) id);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                CompetitionResultMapper mapper = new CompetitionResultMapper();
                result = mapper.mapRow(rs);
                List<Answer> answers = new ArrayList<>();
                getAnswersToCompetitionResult(con, result, answers);
                result.setGivenAnswers(answers);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
                    saveAnswer(con, entity, given);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveAnswer(Connection con, CompetitionResult entity, Answer given) {
        saveQuestion(con, given.getQuestion());
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_ANSWER, Statement.RETURN_GENERATED_KEYS)) {
            AnswerResultMapper mapper = new AnswerResultMapper();
            mapper.createRow(given, pstmt, entity.getId());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                rs.next();
                given.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveQuestion(Connection con, Question question) {
        // question,correct_answer, point_value
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_QUESTION, Statement.RETURN_GENERATED_KEYS)) {
            QuestionMapper mapper = new QuestionMapper();
            mapper.createRow(question, pstmt, null);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                rs.next();
                question.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
