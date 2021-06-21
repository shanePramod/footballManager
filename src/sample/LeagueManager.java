package sample;


import java.io.IOException;

public interface LeagueManager {

    public void createNewFootballClub(FootballClub clubmember);
    void createNewFootballClub();
    void deleteFootballClub() throws IOException;
    public void displayStatistics();
    void displayPremierLeagueTable();
    public void addingPlayedMatch() throws IOException;

}
