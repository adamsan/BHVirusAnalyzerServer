package hu.adamsan.bionica.competition.server.model;

import java.util.Date;
import java.util.List;

//server side of SubmissionData
public class CompetitionResult {
    private Integer id;
    private String ipAddress;
    private String teamName;
    private String teamCode;
    private int score;
    private Date startSubmitTime;
    private Date endSubmitTime;
    private List<Answer> givenAnswers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }


    public Date getStartSubmitTime() {
        return startSubmitTime;
    }

    public void setStartSubmitTime(Date startSubmitTime) {
        this.startSubmitTime = startSubmitTime;
    }

    public Date getEndSubmitTime() {
        return endSubmitTime;
    }

    public void setEndSubmitTime(Date endSubmitTime) {
        this.endSubmitTime = endSubmitTime;
    }

    public List<Answer> getGivenAnswers() {
        return givenAnswers;
    }

    public void setGivenAnswers(List<Answer> answers) {
        this.givenAnswers = answers;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "CompetitionResult [id=" + id + ", ipAddress=" + ipAddress + ", teamName=" + teamName + ", teamCode=" + teamCode + ", score=" + score
                + ", startSubmitTime=" + startSubmitTime + ", endSubmitTime=" + endSubmitTime + ", answers=" + givenAnswers + "]";
    }

}
