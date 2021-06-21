package sample;

import java.util.Comparator;

public class SortPoint implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub o1, FootballClub o2) {

        return o1.getNumOfPoints()- o2.getNumOfPoints();
    }

}

