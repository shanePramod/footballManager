package sample;

import java.io.Serializable;

public class SchoolFootBallClub extends FootballClub implements Serializable {



    private String schoolName;
    private String ageLevel;

    public SchoolFootBallClub(String clubName, String clubLocation,int clubID,int numOfWins, int numOfDraws, int numOfDefeats, int numOfGoalsScored, int numOfGoalsReceived, int numOfPoints, int numOfMatchesPlayed, String schoolName, String ageLevel) {
        super(clubName,clubLocation,clubID, numOfWins,numOfDraws,numOfDefeats,numOfGoalsScored,numOfGoalsReceived,numOfMatchesPlayed,numOfPoints);
        this.schoolName = schoolName;
        this.ageLevel = ageLevel;
    }


    public String getSchoolName()    {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAgeLevel() {
        return ageLevel;
    }

    public void setAgeLevel(String ageLevel) {
        this.ageLevel = ageLevel;
    }

    @Override
    public String toString() {
        return "SchoolFootballClub{" +
                "schoolName='" + schoolName + '\'' +
                ", ageLevel='" + ageLevel + '\'' +
                '}';
    }
}

