package hu.adamsan.bionica.competition.server;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.adamsan.bionica.competition.server.model.CompetitionResult;
import hu.adamsan.bionica.competition.server.service.CompetitionResultService;

/**
 * Servlet implementation class ResultsServlet
 */
@WebServlet(urlPatterns = { "/results", "/index", "/index.html", "/index.jsp" })
public class ResultsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    // To run this without tomcat:
    // Build webapp-runner:
    // mvn -Dmaven.test.skip=true package
    // Package this to war file:
    // mvn clean build package
    // copy the jar and the war file to the same location, and run:
    // java -jar webapp-runner.jar --port 80 BHVirusAnalyzerServer-0.0.1-SNAPSHOT.war
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("hello", "Hello everybody " + new Random().nextInt(100));
        List<CompetitionResult> results = CompetitionResultService.INSTANCE.getAllResults();
        request.setAttribute("resultSize", results.size());
        request.setAttribute("results", results);
        getServletContext().getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
    }
}
