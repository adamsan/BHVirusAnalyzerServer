package hu.adamsan.bionica.competition.server.dao.rowmapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hu.adamsan.bionica.competition.server.model.CompetitionResult;

public class CompetitionResultMapper implements ResultSetMapper<CompetitionResult> {

    @Override
    public CompetitionResult mapRow(ResultSet rs) {
        CompetitionResult cr = new CompetitionResult();
        try {
            cr.setId(rs.getInt("id"));
            cr.setIpAddress(rs.getString("ip_address"));
            cr.setTeamName(rs.getString("team_name"));
            cr.setTeamCode(rs.getString("team_code"));
            cr.setScore(rs.getInt("score"));
            cr.setStartSubmitTime(rs.getDate("start_submit_time"));
            cr.setEndSubmitTime(rs.getDate("end_submit_time"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cr;
    }

    public void createRow(CompetitionResult cr, PreparedStatement preparedStatement, Integer parentId) {
        // ip_address,team_name,team_code,score,start_submit_time,end_submit_time
        try {
            preparedStatement.setString(1, cr.getIpAddress());
            preparedStatement.setString(2, cr.getTeamName());
            preparedStatement.setString(3, cr.getTeamCode());
            preparedStatement.setInt(4, cr.getScore());
            preparedStatement.setDate(5, new Date(cr.getStartSubmitTime().getTime()));
            preparedStatement.setDate(6, new Date(cr.getEndSubmitTime().getTime()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateRow(CompetitionResult competitionResult, PreparedStatement preparedStatement) {
        return true;
    }

}
