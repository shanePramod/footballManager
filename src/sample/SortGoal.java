package sample;

import java.util.Comparator;

public class SortGoal implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub o1, FootballClub o2) {
        return o1.getNumOfGoalsReceived() - o2.getNumOfGoalsReceived();
    }
}

