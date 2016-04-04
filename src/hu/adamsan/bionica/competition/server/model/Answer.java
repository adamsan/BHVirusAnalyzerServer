package hu.adamsan.bionica.competition.server.model;

//server side of GivenAnswer
public class Answer {
    private Integer id;
    private int answerOrder;
    private Question question;
    private String givenAnswer;
    private CompetitionResult competitionResult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnswerOrder() {
        return answerOrder;
    }

    public void setAnswerOrder(int answerOrder) {
        this.answerOrder = answerOrder;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public CompetitionResult getCompetitionResult() {
        return competitionResult;
    }

    public void setCompetitionResult(CompetitionResult competitionResult) {
        this.competitionResult = competitionResult;
    }

}
