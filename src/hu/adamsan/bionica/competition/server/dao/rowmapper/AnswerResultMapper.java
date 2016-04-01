package hu.adamsan.bionica.competition.server.dao.rowmapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hu.adamsan.bionica.competition.server.model.Answer;

public class AnswerResultMapper implements ResultSetMapper<Answer> {

    @Override
    public Answer mapRow(ResultSet rs) {
        Answer answer = new Answer();
        try {
            answer.setId(rs.getInt("id"));
            answer.setAnswerOrder(rs.getInt("answer_order"));
            // answer.setQuestion();
            answer.setGivenAnswer(rs.getString("given_answer"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public void createRow(Answer answer, PreparedStatement preparedStatement, Integer parentId) {
        // answer_order,question_id,given_answer,competitionresult_id
        try {
            preparedStatement.setInt(1, answer.getAnswerOrder());
            preparedStatement.setInt(2, answer.getQuestion().getId());
            preparedStatement.setString(3, answer.getGivenAnswer());
            preparedStatement.setInt(4, parentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateRow(Answer answe, PreparedStatement preparedStatement) {
        return true;
    }


}
