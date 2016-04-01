package hu.adamsan.bionica.competition.server.dao.rowmapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hu.adamsan.bionica.competition.server.model.Question;

public class QuestionMapper implements ResultSetMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs) {
        Question q = new Question();
        try {
            q.setId(rs.getInt("id"));
            q.setCorrectAnswer(rs.getString("correct_answer"));
            q.setQuestion(rs.getString("question"));
            q.setPointValue(rs.getInt("point_value"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return q;
    }

    @Override
    public void createRow(Question question, PreparedStatement preparedStatement, Integer parentId) {
        try {
            preparedStatement.setString(1, question.getQuestion());
            preparedStatement.setString(2, question.getCorrectAnswer());
            preparedStatement.setInt(3, question.getPointValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateRow(Question clazz, PreparedStatement preparedStatement) {
        // TODO Auto-generated method stub
        return false;
    }

}
