package sample;


import java.io.Serializable;
import java.util.Date;

public class Match implements Serializable {

    private FootballClub team1;
    private FootballClub team2;
    private int team1Goal;
    private int team2Goal;
    private Date date;

    public Match(int date, int month, int year, int goalsRandom1, int goalsRandom2, Integer integer, Integer integer1) {
    }

    public int getTeam1Goal() {
        return team1Goal;
    }

    public void setTeam1Goal(int team1Goal) {
        this.team1Goal = team1Goal;
    }

    public int getTeam2Goal() {
        return team2Goal;
    }

    public void setTeam2Goal(int team2Goal) {
        this.team2Goal = team2Goal;
    }

    public Match(Date date, FootballClub team1, FootballClub team2, int team1Goal, int team2Goal) {

        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.team1Goal = team1Goal;
        this.team2Goal = team2Goal;
    }


    public FootballClub getTeam1() {
        return team1;
    }

    public void setTeam1(FootballClub team1) {
        this.team1 = team1;
    }

    public FootballClub getTeam2() {
        return team2;
    }

    public void setTeam2(FootballClub team2) {
        this.team2 = team2;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

