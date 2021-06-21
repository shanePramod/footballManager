package sample;

import java.io.Serializable;
import java.util.Date;

public class UniversityFootballClub extends FootballClub implements Serializable {


    private String universityName;
    private String universityYear;

    public UniversityFootballClub(String clubName, String clubLocation,int clubID,int numOfWins, int numOfDraws, int numOfDefeats, int numOfGoalsScored, int numOfGoalsReceived, int numOfPoints, int numOfMatchesPlayed, String universityName, String universityYear) {
        super(clubName,clubLocation,clubID,numOfWins,numOfDraws,numOfDefeats,numOfGoalsScored,numOfGoalsReceived,numOfMatchesPlayed,numOfPoints);

        this.universityName = universityName;
        this.universityYear = universityYear;
    }



    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityYear() {
        return universityYear;
    }

    public void setUniversityYear(String universityYear) {
        this.universityYear = universityYear;
    }

    @Override
    public String toString() {
        return "UniversityFootballClub{" +
                "universityName='" + universityName + '\'' +
                ", universityYear='" + universityYear + '\'' +
                '}';
    }
}

