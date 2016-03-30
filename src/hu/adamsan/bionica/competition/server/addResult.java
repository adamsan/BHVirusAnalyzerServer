package hu.adamsan.bionica.competition.server;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

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
        // http://localhost:8080/BHVirusAnalyzerServer/addResult?teamName=TestTeam&teamCode=TT01&score=23&startSubmitTime=2016-03-26 18:43:22
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        mapper.setDateFormat(df);
        String json = request.getParameter("json");
        CompetitionResult competitionResult = mapper.readValue(json, CompetitionResult.class);
        String ipAddress = request.getRemoteAddr();
        competitionResult.setIpAddress(ipAddress);
        competitionResult.setEndSubmitTime(new Date());
        CompetitionResultService.INSTANCE.insertNewResult(competitionResult);
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
