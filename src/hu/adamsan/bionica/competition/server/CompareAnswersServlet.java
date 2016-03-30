package hu.adamsan.bionica.competition.server;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.adamsan.bionica.competition.server.model.CompetitionResult;
import hu.adamsan.bionica.competition.server.model.Question;
import hu.adamsan.bionica.competition.server.service.CompetitionResultService;

/**
 * Servlet implementation class compareAnswersServlet
 */
@WebServlet("/compare")
public class CompareAnswersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids = request.getParameterValues("ids");
        if (ids != null) {
            List<CompetitionResult> resultsToCompare = Arrays.stream(ids)
                    .mapToInt(Integer::parseInt)
                    .mapToObj((id) -> CompetitionResultService.INSTANCE.find(id))
                    .collect(Collectors.toList());
            request.setAttribute("resultsToCompare", resultsToCompare);
            request.setAttribute("resultsToCompareSize", resultsToCompare.size());

            List<Question> questions = resultsToCompare.get(0).getGivenAnswers().stream()
                    .map((answer) -> answer.getQuestion())
                    .collect(Collectors.toList());

            request.setAttribute("questions", questions);
            // Map<String, List<Answer>> = new HashMap<>();

        } else {
            request.setAttribute("resultsToCompareSize", 0);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/compare.jsp").forward(request, response);
    }

}
