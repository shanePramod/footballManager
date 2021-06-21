package sample;


import java.io.Serializable;

public class SportsClub implements Serializable {

     String clubName;
     String clubLocation;
     int clubId;

    public SportsClub() {

    }

    public SportsClub(String clubName, String clubLocation, int clubID) {
        this.clubId = clubID;
        this.clubName = clubName;
        this.clubLocation = clubLocation;
    }


    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public SportsClub(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }


    public SportsClub(String clubName, String clubLocation) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
    }

    @Override
    public String toString() {
        return "SportsClub{" +
                "clubName='" + clubName + '\'' +
                ", clubLocation='" + clubLocation + '\'' +
                ", clubId=" + clubId +
                '}';
    }
}



