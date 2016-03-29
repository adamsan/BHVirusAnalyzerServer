package hu.adamsan.bionica.competition.server;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.adamsan.bionica.competition.server.model.Answers;
import hu.adamsan.bionica.competition.server.model.CompetitionResult;
import hu.adamsan.bionica.competition.server.service.CompetitionResultService;

/**
 * Servlet implementation class addResult
 */
@WebServlet("/addResult")
public class addResult extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompetitionResult competitionResult = new CompetitionResult();
        // http://localhost:8080/BHVirusAnalyzerServer/addResult?teamName=TestTeam&teamCode=TT01&score=23&startSubmitTime=2016-03-26 18:43:22
        String teamName = request.getParameter("teamName");
        String teamCode = request.getParameter("teamCode");
        String ipAddress = request.getRemoteAddr();
        int score = Integer.parseInt(request.getParameter("score"));
        Date startSubmitTime = null;
        try {
            String target = request.getParameter("startSubmitTime"); // "Thu Sep 28 20:29:30 JST 2000";
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            startSubmitTime = df.parse(target);
        } catch (ParseException | NullPointerException ex) {
            ex.printStackTrace();
        }
        Date endSubmitTime = new Date();

        List<Answers> answers = new ArrayList<>();

        competitionResult.setTeamName(teamName);
        competitionResult.setTeamCode(teamCode);
        competitionResult.setIpAddress(ipAddress);
        competitionResult.setScore(score);
        competitionResult.setStartSubmitTime(startSubmitTime);
        competitionResult.setEndSubmitTime(endSubmitTime);
        competitionResult.setAnswers(answers);

        CompetitionResultService.INSTANCE.insertNewResult(competitionResult);
        System.out.println(competitionResult);

        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
