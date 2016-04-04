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
import hu.adamsan.bionica.competition.server.model.Answer;
import hu.adamsan.bionica.competition.server.model.Question;

public class AnswerSqliteDAO implements DAO<Answer> {

    public static final AnswerSqliteDAO INSTANCE = new AnswerSqliteDAO();

    public static final String SELECT_ANSWERS_BY_RESULT = "select * from answer where competitionresult_id = ?";
    public static final String INSERT_ANSWER = "insert into answer (answer_order,question_id,given_answer,competitionresult_id) values (?,?,?,?)";

    public List<Answer> findAll(int competitionId) {
        List<Answer> answers = new ArrayList<>();
        try (Connection con = SQLiteDAOManager.getConnection();
                PreparedStatement stmt = con.prepareStatement(SELECT_ANSWERS_BY_RESULT)) {
            stmt.setInt(1, competitionId);
            try (ResultSet rs = stmt.executeQuery()) {
                AnswerResultMapper mapper = new AnswerResultMapper();
                while (rs.next()) {
                    Answer answer = mapper.mapRow(rs);
                    answers.add(answer);
                    int questionId = rs.getInt("question_id");//
                    Question question = QuestionSqliteDAO.INSTANCE.find(questionId);
                    answer.setQuestion(question);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

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
        if (entity.getId() != null && entity.getId() > 0) {
            return update(entity);
        } else {
            return insert(entity);
        }
    }

    private boolean insert(Answer entity) {
        try (Connection con = SQLiteDAOManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(INSERT_ANSWER, Statement.RETURN_GENERATED_KEYS)) {
            AnswerResultMapper mapper = new AnswerResultMapper();
            mapper.createRow(entity, pstmt, entity.getCompetitionResult().getId());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                rs.next();
                entity.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean update(Answer entity) {
        // TODO Auto-generated method stub
        return false;
    }

}
