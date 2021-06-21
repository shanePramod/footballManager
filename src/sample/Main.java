package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {




   private static PremierLeagueManager  newObj = new PremierLeagueManager();
    @Override
    public void start(Stage primaryStage) throws Exception{

        newObj.showGUI();

        }

        public static void main(String[] args) throws Exception {
            while (true) {
                Scanner scan1 = new Scanner(System.in);
                System.out.println("1 > Add a Football Club\n" +
                        "2 > Delete a Football Club\n" +
                        "3 > Display Statistics\n" +
                        "4 > Add a Played Match\n"+
                        "5 > Display Premier League Table\n" +
                        "6 > GUI\n");
                System.out.print("Enter Number : ");
                int userInput1 = scan1.nextInt();

                if (userInput1 == 1) {

                    newObj.createNewFootballClub();

                } else if (userInput1 == 2) {
                    newObj.deleteFootballClub();

                } else if (userInput1 == 3) {
                    newObj.displayStatistics();


                    System.out.println("Enter the club name: ");
                    Scanner scn = new Scanner(System.in);
                    String input = scn.nextLine();
                    for(FootballClub club :  newObj.footballClubArrayList) {
                        if(club.getClubName().equals(input)) {
                            System.out.println("|Name|\t|ID|\t|Goals|\t|Matches|");
                            System.out.println(""+club.getClubName()+"\t "+club.getClubId()+"\t      "+club.getNumOfGoalsScored()+"\t     "+club.getNumOfMatchesPlayed());
                        }
                    }


                }else if (userInput1 == 4) {
                    newObj.addingPlayedMatch();

                } else if (userInput1 == 5) {
                    newObj.displayPremierLeagueTable();

                }else if (userInput1 == 6){
                    launch(args);
                }
                else  {
                    System.out.println("Wrong input! Try again");

                }
            }

        }
    }




