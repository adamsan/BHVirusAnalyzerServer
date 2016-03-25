package hu.adamsan.bionica.competition.server;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("hello", "Hello everybody " + new Random().nextInt(100));
        request.setAttribute("results", CompetitionResultService.INSTANCE.getAllResults());
        getServletContext().getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
	}
}
