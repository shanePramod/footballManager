package sample;



import java.io.Serializable;
import java.util.Date;

public class FootballClub extends SportsClub implements Serializable {
    private static final long serialVersionUID=1L;





    private int numOfWins;
    private int numOfDraws;
    private int numOfDefeats;
    private int numOfGoalsScored;
    private int numOfGoalsReceived;
    private int numOfPoints;
    private int numOfMatchesPlayed;



    public FootballClub() {
        super();
    }
    public FootballClub(String clubName,String clubLocation,int clubID, int numOfWins, int numOfDraws, int numOfDefeats, int numOfGoalsScored, int numOfGoalsReceived, int numOfPoints, int numOfMatchesPlayed) {

        super(clubName,clubLocation,clubID);
        this.numOfWins = numOfWins;
        this.numOfDraws = numOfDraws;
        this.numOfDefeats = numOfDefeats;
        this.numOfGoalsScored = numOfGoalsScored;
        this.numOfGoalsReceived = numOfGoalsReceived;
        this.numOfPoints = numOfPoints;
        this.numOfMatchesPlayed = numOfMatchesPlayed;
    }

    public FootballClub(String clubName, int numOfWins, int numOfDraws, int numOfDefeats, int numOfGoalsScored, int numOfGoalsReceived, int numOfPoints, int numOfMatchesPlayed) {
        super(clubName  );
        this.numOfWins = numOfWins;
        this.numOfDraws = numOfDraws;
        this.numOfDefeats = numOfDefeats;
        this.numOfGoalsScored = numOfGoalsScored;
        this.numOfGoalsReceived = numOfGoalsReceived;
        this.numOfPoints = numOfPoints;
        this.numOfMatchesPlayed = numOfMatchesPlayed;
    }

    public FootballClub(String clubName, String clubLocation,int clubID, Date date, int numOfWins, int numOfDraws, int numOfDefeats, int numOfGoalsScored, int numOfGoalsReceived, int numOfPoints, int numOfMatchesPlayed) {

        super(clubName,clubLocation,clubID);
        this.numOfWins = numOfWins;
        this.numOfDraws = numOfDraws;
        this.numOfDefeats = numOfDefeats;
        this.numOfGoalsScored = numOfGoalsScored;
        this.numOfGoalsReceived = numOfGoalsReceived;
        this.numOfPoints = numOfPoints;
        this.numOfMatchesPlayed = numOfMatchesPlayed;
    }


    public int getNumOfWins() {
        return numOfWins;
    }

    public void setNumOfWins(int numOfWins) {
        this.numOfWins = numOfWins;
    }

    public int getNumOfDraws() {
        return numOfDraws;
    }

    public void setNumOfDraws(int numOfDraws) {
        this.numOfDraws = numOfDraws;
    }

    public int getNumOfDefeats() {
        return numOfDefeats;
    }

    public void setNumOfDefeats(int numOfDefeats) {
        this.numOfDefeats = numOfDefeats;
    }

    public int getNumOfGoalsScored() {
        return numOfGoalsScored;
    }

    public void setNumOfGoalsScored(int numOfGoalsScored) {
        this.numOfGoalsScored = numOfGoalsScored;
    }

    public int getNumOfGoalsReceived() {
        return numOfGoalsReceived;
    }

    public void setNumOfGoalsReceived(int numOfGoalsReceived) {
        this.numOfGoalsReceived = numOfGoalsReceived;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }

    public void setNumOfPoints(int numOfPoints) {
        this.numOfPoints = numOfPoints;
    }

    public int getNumOfMatchesPlayed() {
        return numOfMatchesPlayed;
    }

    public void setNumOfMatchesPlayed(int numOfMatchesPlayed) {
        this.numOfMatchesPlayed = numOfMatchesPlayed;
    }

    

    @Override
    public String toString() {
        return "FootballClub{" +
                ", numOfWins=" + numOfWins +
                ", numOfDraws=" + numOfDraws +
                ", numOfDefeats=" + numOfDefeats +
                ", numOfGoalsScored=" + numOfGoalsScored +
                ", numOfGoalsReceived=" + numOfGoalsReceived +
                ", numOfPoints=" + numOfPoints +
                ", numOfMatchesPlayed=" + numOfMatchesPlayed +
                '}';
    }
}

