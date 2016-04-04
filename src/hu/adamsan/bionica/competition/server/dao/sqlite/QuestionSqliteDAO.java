package hu.adamsan.bionica.competition.server.dao.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import hu.adamsan.bionica.competition.server.dao.DAO;
import hu.adamsan.bionica.competition.server.dao.rowmapper.QuestionMapper;
import hu.adamsan.bionica.competition.server.model.Question;

public class QuestionSqliteDAO implements DAO<Question> {

    public static final QuestionSqliteDAO INSTANCE = new QuestionSqliteDAO();
    public static final String SELECT_QUESTION = "select * from question where id = ?";
    public static final String INSERT_QUESTION = "insert into question (question,correct_answer, point_value) values (?,?,?)";

    @Override
    public List<Question> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(Question entity) {
        //
        return false;
    }

    @Override
    public Question find(long id) {
        Question question = null;
        try (Connection con = SQLiteDAOManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(SELECT_QUESTION)) {
                stmt.setInt(1, (int) id);
                try (ResultSet rsQuestion = stmt.executeQuery();) {
                    QuestionMapper questionMapper = new QuestionMapper();
                    while (rsQuestion.next()) {
                        question = questionMapper.mapRow(rsQuestion);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    @Override
    public boolean save(Question entity) {
        if(entity.getId()!=null && entity.getId()>0) {
            return update(entity);
        }else {
            return insert(entity);
        }

    }

    private boolean insert(Question entity) {
        try (Connection con = SQLiteDAOManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(INSERT_QUESTION, Statement.RETURN_GENERATED_KEYS)) {
            QuestionMapper mapper = new QuestionMapper();
            mapper.createRow(entity, pstmt, null);
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

    private boolean update(Question entity) {
        // TODO Auto-generated method stub
        return false;
    }

}
