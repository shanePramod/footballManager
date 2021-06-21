package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class PremierLeagueManager implements LeagueManager {

    Scene menuScene, pltScene, matchScene, searchScene, randomScene;


    String clubName;
    String clubLocation;
    int clubID;
    Date date;
    int numOfWins;
    int numOfDraws;
    int numOfDefeats;
    int numOfGoalsScored;
    int numOfGoalReceived;
    int numOfPoints;
    int numOfMatchesPlayed;
    public ArrayList<FootballClub> footballClubArrayList = new ArrayList<>();
    public ArrayList<Match> matchArrayList = new ArrayList<>();


    @Override
    public void createNewFootballClub(FootballClub clubmember) {
    }


    public void createNewFootballClub() {                                  //creating a new football club by user


        Scanner scan2 = new Scanner(System.in);

        System.out.print("Enter the club name : ");
        clubName = scan2.nextLine();
        System.out.print("Enter club location : ");
        clubLocation = scan2.nextLine();
        System.out.println("Enter Club Id : ");
        clubID = scan2.nextInt();

                                                                        //adding user data to the arraylist
        displayStatistics();
        footballClubArrayList.add(new FootballClub(clubName, clubLocation,clubID, date, numOfWins, numOfDraws, numOfDefeats, numOfGoalsScored, numOfGoalReceived, numOfPoints, numOfMatchesPlayed));

                                                                    //adding arraylist object to the file by fileoutput stream
        try {
            FileOutputStream fOutStr = new FileOutputStream("clubData.txt");
            ObjectOutputStream objOutStr = new ObjectOutputStream(fOutStr);
            objOutStr.writeObject(footballClubArrayList);
            objOutStr.close();
            fOutStr.close();
            System.out.println("Club added Successfully");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void displayStatistics() {                  //displaying statistics by file input stream
        FileInputStream fInStr;
        ObjectInputStream objInStr = null;
        File file = new File("clubData.txt");
        if (file.exists()) {
            try {
                fInStr = new FileInputStream("clubData.txt");
                while ( fInStr.available() > 0) {
                    objInStr = new ObjectInputStream( fInStr);
                    ArrayList<FootballClub> readedArray = (ArrayList<FootballClub>) objInStr.readObject();
                    footballClubArrayList = readedArray;


                }
                if (objInStr != null) {
                    objInStr.close();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

                                                                //adding a played match by goals received
    public void addingPlayedMatch() throws IOException {

        displayStatistics();
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter date (dd.mm.yyyy): ");
        String scanned = scn.nextLine();
        Date date;
        try {
            date = new SimpleDateFormat("dd.mm.yyyy").parse(scanned);
        } catch (ParseException ex) {
            System.out.println("date should in this format!!! >> mm-dd-yyyy");
            return;
        }
        System.out.println("Enter Team 1: ");
        scanned = scn.nextLine();
        FootballClub team1 = null;
        for (FootballClub club : footballClubArrayList) {
            if (club.getClubName().equals(scanned))
                team1 = club;
        }
        if (team1 == null) {
            System.out.println("Wrong club name!!!");
            return;
        }
        System.out.println("Enter Team 2: ");
        scanned = scn.nextLine();
        FootballClub team2 = null;
        for (FootballClub club : footballClubArrayList) {
            if (club.getClubName().equals(scanned))
                team2 = club;
        }
        if (team2 == null) {
            System.out.println("Wrong club name!!!");
            return;
        }

        System.out.println("Team 1 goals: ");
        scanned = scn.nextLine();
        int team1Goal = -1;
        try {
            team1Goal = Integer.parseInt(scanned);
        } catch (Exception e) {
        }
        if (team1Goal == -1) {
            System.out.println("Enter the number of goals");
            return;
        }

        System.out.println("Team 2 goals: ");
        scanned = scn.nextLine();
        int team2Goal = -1;
        try {
            team2Goal = Integer.parseInt(scanned);
        } catch (Exception e) {
        }
        if (team2Goal == -1) {
            System.out.println("Enter the number of goals");
            return;
        }


        Match match = new Match(date,team1,team2,team1Goal,team2Goal);          //setting variables when user enter number of goals
        match.setDate(date);
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setTeam1Goal(team2Goal);
        match.setTeam2Goal(team1Goal);
        matchArrayList.add(match);
        team1.setNumOfGoalsScored(team1.getNumOfGoalsScored() + team1Goal);
        team2.setNumOfGoalsScored(team2.getNumOfGoalsScored() + team2Goal);
        team1.setNumOfGoalsReceived(team1.getNumOfGoalsReceived() + team2Goal);
        team2.setNumOfGoalsReceived(team2.getNumOfGoalsReceived() + team1Goal);
        team1.setNumOfMatchesPlayed(team1.getNumOfMatchesPlayed() + 1);
        team2.setNumOfMatchesPlayed(team2.getNumOfMatchesPlayed() + 1);

        if (team1Goal > team2Goal) {
            team1.setNumOfPoints(team1.getNumOfPoints() + 3);
            team1.setNumOfWins(team1.getNumOfWins() + 1);
            team2.setNumOfDefeats(team2.getNumOfDefeats() + 1);
        } else if (team1Goal < team2Goal) {
            team2.setNumOfPoints(team2.getNumOfPoints() + 3);
            team2.setNumOfWins(team2.getNumOfWins() + 1);
            team1.setNumOfDefeats(team1.getNumOfDefeats() + 1);
        } else {
            team1.setNumOfPoints(team1.getNumOfPoints() + 1);
            team2.setNumOfPoints(team2.getNumOfPoints() + 1);
            team1.setNumOfDraws(team1.getNumOfDraws() + 1);
            team2.setNumOfDraws(team2.getNumOfDraws() + 1);
        }

        FileOutputStream fis = new FileOutputStream("clubData.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fis);
        oos.writeObject(footballClubArrayList);
        oos.close();
        fis.close();

        displayStatistics();
        matchArrayList.add(new Match(date, team1, team2, team1Goal, team2Goal));


        try {
            FileOutputStream fileOut = new FileOutputStream("matchData.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(matchArrayList);
            objectOut.close();
            fileOut.close();
            System.out.println("Match added Successfully");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }



                                                                //Creating GUI
    public void showGUI() {
        Scanner scn = new Scanner(System.in);
        displayStatistics();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Premier League Manager");

        Image homeImg = new Image("1.jpg");
        ImageView homeImage = new ImageView();
        homeImage.setImage(homeImg);
        homeImage.setFitWidth(1366);
        homeImage.setFitHeight(768);


        Label lblHome = new Label("Premier League Manager");
        lblHome.setLayoutX(275);
        lblHome.setLayoutY(50);
        lblHome.setStyle("-fx-font-size:35;-fx-text-fill:E1FEFE");


        Button pltBtn = new Button("Premier League Table");
        pltBtn.setLayoutX(345);
        pltBtn.setLayoutY(130);
        pltBtn.setPrefSize(250, 40);
        pltBtn.setStyle("-fx-font-size:20;-fx-background-color:E1FEFE");
        pltBtn.setOnAction(event -> primaryStage.setScene(pltScene));

        Button searchBtn = new Button("Match Search");
        searchBtn.setLayoutX(366);
        searchBtn.setLayoutY(210);
        searchBtn.setPrefSize(200, 40);
        searchBtn.setStyle("-fx-font-size:20;-fx-background-color:E1FEFE");
        searchBtn.setOnAction(event -> primaryStage.setScene(searchScene));

        Button randomBtn = new Button("Random Match Generator");
        randomBtn.setLayoutX(340);
        randomBtn.setLayoutY(290);
        randomBtn.setPrefSize(260, 40);
        randomBtn.setStyle("-fx-font-size:20;-fx-background-color:E1FEFE");
        randomBtn.setOnAction(event -> primaryStage.setScene(randomScene));

        Button matchesBtn = new Button("Played Matches");
        matchesBtn.setLayoutX(369);
        matchesBtn.setLayoutY(370);
        matchesBtn.setPrefSize(200, 40);
        matchesBtn.setStyle("-fx-font-size:20;-fx-background-color:E1FEFE");
        matchesBtn.setOnAction(event -> primaryStage.setScene(matchScene));

        Button homeExitBtn = new Button("Exit");
        homeExitBtn.setLayoutX(390);
        homeExitBtn.setLayoutY(450);
        homeExitBtn.setPrefSize(150, 40);
        homeExitBtn.setStyle("-fx-font-size:20;-fx-background-color:E1FEFE");
        homeExitBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) homeExitBtn.getScene().getWindow();
                stage.close();


            }
        });


        Pane paneHome = new Pane();
        paneHome.getChildren().addAll(homeImage, randomBtn, searchBtn, matchesBtn, pltBtn, homeExitBtn, lblHome);
        menuScene = new Scene(paneHome, 1366, 768);
        primaryStage.setScene(menuScene);
        primaryStage.show();

        //PREMIER LEAGUE TABLE
        Pane panePlt = new Pane();


        Image pltImg = new Image("1.jpg");
        ImageView pltImage = new ImageView();
        pltImage.setImage(pltImg);
        pltImage.setFitWidth(1000);
        pltImage.setFitHeight(700);
        TableView<FootballClub> table = new TableView<FootballClub>();
        table.setEditable(true);


        TableColumn TClubName = new TableColumn<>("Club Name");
        TClubName.setCellValueFactory(new PropertyValueFactory<FootballClub, String>("clubName"));

        TableColumn TWins = new TableColumn<>("Wins");
        TWins.setCellValueFactory(new PropertyValueFactory<FootballClub, String>("numOfWins"));


        TableColumn TDraws = new TableColumn<>("Draws");
        TDraws.setCellValueFactory(new PropertyValueFactory<FootballClub, String>("numOfDraws"));

        TableColumn TDefeats = new TableColumn<>("Defeats");
        TDefeats.setCellValueFactory(new PropertyValueFactory<FootballClub, String>("numOfDefeats"));

        TableColumn TScored = new TableColumn<>("Scored");
        TScored.setCellValueFactory(new PropertyValueFactory<FootballClub, String>("numOfGoalsScored"));

        TableColumn TReceived = new TableColumn<>("Received");
        TReceived.setCellValueFactory(new PropertyValueFactory<FootballClub, String>("numOfGoalsReceived"));

        TableColumn TPoints = new TableColumn<>("Points");
        TPoints.setCellValueFactory(new PropertyValueFactory<FootballClub, String>("numOfPoints"));

        TableColumn TMatchesPlayed = new TableColumn<>("Matches");
        TMatchesPlayed.setCellValueFactory(new PropertyValueFactory<FootballClub, String>("numOfMatchesPlayed"));

        footballClubArrayList.sort(new SortPoint().reversed());
        for (int i = 0; i < footballClubArrayList.size(); i++) {
            System.out.println(table.getItems().add(new FootballClub(footballClubArrayList.get(i).getClubName(), footballClubArrayList.get(i).getNumOfWins(), footballClubArrayList.get(i).getNumOfDraws(), footballClubArrayList.get(i).getNumOfDefeats(), footballClubArrayList.get(i).getNumOfGoalsScored(), footballClubArrayList.get(i).getNumOfGoalsReceived(), footballClubArrayList.get(i).getNumOfPoints(), footballClubArrayList.get(i).getNumOfMatchesPlayed())));
        }


        table.getColumns().add(TClubName);
        table.getColumns().add(TWins);
        table.getColumns().add(TDraws);
        table.getColumns().add(TDefeats);
        table.getColumns().add(TScored);
        table.getColumns().add(TReceived);
        table.getColumns().add(TPoints);
        table.getColumns().add(TMatchesPlayed);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button pltExitBtn = new Button("Exit");
        pltExitBtn.setLayoutX(245);
        pltExitBtn.setLayoutY(430);
        pltExitBtn.setPrefSize(150, 40);
        pltExitBtn.setStyle("-fx-font-size:20;-fx-background-color:#8686ff");
        pltExitBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) pltExitBtn.getScene().getWindow();
                stage.close();


            }
        });

        Button sortPointBtn = new Button("Sort");
        sortPointBtn.setLayoutX(500);
        sortPointBtn.setLayoutY(430);
        sortPointBtn.setStyle("-fx-font-size:17;-fx-background-color:E1FEFE");
        sortPointBtn.setPrefSize(70, 40);
        sortPointBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.getItems().clear();

                for (FootballClub football : footballClubArrayList) {
                    TClubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
                    TWins.setCellValueFactory(new PropertyValueFactory<>("wins"));
                    TDraws.setCellValueFactory(new PropertyValueFactory<>("draws"));
                    TDefeats.setCellValueFactory(new PropertyValueFactory<>("defeats"));
                    TScored.setCellValueFactory(new PropertyValueFactory<>("scored"));
                    TReceived.setCellValueFactory(new PropertyValueFactory<>("received"));
                    TPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
                    TMatchesPlayed.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));

                    TScored.setSortType(TableColumn.SortType.DESCENDING);
                    table.getSortOrder().add(TScored);
                    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    table.getItems().add(football);


                }
            }
        });
        Button pltBackBtn = new Button("Back");
        pltBackBtn.setLayoutX(40);
        pltBackBtn.setLayoutY(600);
        pltBackBtn.setStyle("-fx-font-size:17;-fx-background-color:E1FEFE");
        pltBackBtn.setPrefSize(70, 40);
        pltBackBtn.setOnAction(event -> primaryStage.setScene(menuScene));

        panePlt.getChildren().addAll(pltImage, pltExitBtn, table, sortPointBtn, pltBackBtn);
        pltScene = new Scene(panePlt, 1000, 700);


                                                                                     //MATCH SEARCHING
        Pane paneSearch = new Pane();

        Image searchImg = new Image("1.jpg");
        ImageView searchImage = new ImageView();
        searchImage.setImage(searchImg);
        searchImage.setFitWidth(1000);
        searchImage.setFitHeight(700);




        ArrayList<Match> detectedMatchesList = new ArrayList<>();


        Label lblSearch = new Label("Enter Date(DD.MM.YYYY)");
        lblSearch.setLayoutX(40);
        lblSearch.setLayoutY(100);
        lblSearch.setStyle("-fx-font-size:17;-fx-text-fill: E1FEFE");

        TextField tfSearchDate = new TextField();
        tfSearchDate.setLayoutX(230);
        tfSearchDate.setLayoutY(100);
        tfSearchDate.setPrefSize(90, 30);

        TextField tfSearchMonth = new TextField();
        tfSearchMonth.setLayoutX(330);
        tfSearchMonth.setLayoutY(100);
        tfSearchMonth.setPrefSize(90, 30);


        TextField tfSearchYear = new TextField();
        tfSearchYear.setLayoutX(430);
        tfSearchYear.setLayoutY(100);
        tfSearchYear.setPrefSize(90, 30);



        Label lbloutput = new Label();
        lbloutput.setLayoutX(40);
        lbloutput.setLayoutY(100);
        lbloutput.setStyle("-fx-font-size:17;-fx-text-fill: E1FEFE");

        TextArea tAsearchedData = new TextArea();
        tAsearchedData.setLayoutX(230);
        tAsearchedData.setLayoutY(300);
        tAsearchedData.setPrefSize(200, 100);


        Button searchBackBtn = new Button("Back");
        searchBackBtn.setLayoutX(40);
        searchBackBtn.setLayoutY(600);
        searchBackBtn.setStyle("-fx-font-size:17;-fx-background-color:E1FEFE");
        searchBackBtn.setPrefSize(70, 40);
        searchBackBtn.setOnAction(event -> primaryStage.setScene(menuScene));


        Button searchEnterBtn = new Button("Search");
        searchEnterBtn.setLayoutX(550);
        searchEnterBtn.setLayoutY(100);
        searchEnterBtn.setStyle("-fx-font-size:17;-fx-background-color:E1FEFE");
        searchEnterBtn.setPrefSize(90, 40);

        searchEnterBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

        int Y = -0000;
        try {
            Y = Integer.parseInt(tfSearchYear.getText());
        } catch (Exception e) {
        }
        if (Y == -0000) {
            System.out.println("You have to enter a year");
            return;
        }


        int M = 0;
        try {
            M = Integer.parseInt(tfSearchMonth.getText());
        } catch (Exception e) {
        }
        if (M == 0) {
            System.out.println("You have to enter a month");
            return;
        }

        String[] months = {
                "",
                "January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December"
        };

        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,30, 31};

        if (M == 2 && isLeapYear(Y)) days[M] = 29;

        System.out.println("    " + months[M] + " " + Y);
        System.out.println("S  M  Tu  W  Th  F  S");

        int d = day(M, 1, Y);
        String space = "";

        for (int i = 0; i < d; i++)
            System.out.print("   ");
        for (int i = 1; i <= days[M]; i++) {
            if (i < 10)
                System.out.print(i + "  ");
            else
                System.out.print(i + " ");
            if (((i + d) % 7 == 0) || (i == days[M])) System.out.println();
        }


        int D = 0;
        try {
            D = Integer.parseInt(tfSearchDate.getText());
        } catch (Exception e) {
        }
        if (D == 0 || days[M] < D) {
            System.out.println("You have t enter day in month");
            return;
        }
                FileInputStream fInStr;
                ObjectInputStream objInStr = null;
                File file = new File("matchData.txt");
                if (file.exists()) {
                    try {
                        fInStr = new FileInputStream("matchData.txt");
                        while ( fInStr.available() > 0) {
                            objInStr = new ObjectInputStream( fInStr);
                            ArrayList<Match> readedArray1 = (ArrayList<Match>) objInStr.readObject();
                            matchArrayList = readedArray1;


                        }
                        if (objInStr != null) {
                            objInStr.close();
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }

        Calendar cal = Calendar.getInstance();
        cal.set(Y, M - 1, D);
        for (Match match : matchArrayList) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(match.getDate());
            if (cal.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) || cal.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
                tAsearchedData.setText(match.getTeam1().getClubName() + " " + match.getTeam1Goal() + " : " + match.getTeam2Goal() + " " + match.getTeam2().getClubName());
            }
        }
    }

    public int day(int M, int D, int Y) {
        int y = Y - (14 - M) / 12;
        int x = y + y / 4 - y / 100 + y / 400;
        int m = M + 12 * ((14 - M) / 12) - 2;
        int d = (D + x + (31 * m) / 12) % 7;
        return d;
    }

    public boolean isLeapYear(int year) {

        if ((year % 4 == 0) && (year % 100 != 0)) return true;
        if (year % 400 == 0) return true;
        return false;



            }

        });



        paneSearch.getChildren().addAll(searchImage,lblSearch,searchBackBtn,searchEnterBtn,tfSearchYear,tfSearchMonth,tfSearchDate,tAsearchedData);
        searchScene = new Scene(paneSearch, 1000, 700);


                                                            //RANDOM MATCH GENERATOR
        Pane paneRandom = new Pane();

        Image randomImg = new Image("1.jpg");
        ImageView randomImage = new ImageView();
        randomImage.setImage(randomImg);
        randomImage.setFitWidth(1000);
        randomImage.setFitHeight(700);

        Button matchGenerBtn = new Button("Click For Generate");
        matchGenerBtn.setLayoutX(250);
        matchGenerBtn.setLayoutY(400);
        matchGenerBtn.setStyle("-fx-font-size:17;-fx-background-color:E1FEFE");
        matchGenerBtn.setPrefSize(150, 40);
        matchGenerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                randomMatch();

            }
        });

        Button randomBackBtn = new Button("Back");
        randomBackBtn.setLayoutX(40);
        randomBackBtn.setLayoutY(400);
        randomBackBtn.setStyle("-fx-font-size:17;-fx-background-color:E1FEFE");
        randomBackBtn.setPrefSize(70, 40);
        randomBackBtn.setOnAction(event -> primaryStage.setScene(menuScene));

        paneRandom.getChildren().addAll(randomImage,randomBackBtn,matchGenerBtn);
        randomScene = new Scene(paneRandom, 1000, 700);


                                                            //PLAYED MATCHES
        Pane paneMatch = new Pane();

        Image matchesImg = new Image("1.jpg");
        ImageView matchesImage = new ImageView();
        matchesImage.setImage(matchesImg);
        matchesImage.setFitWidth(1000);
        matchesImage.setFitHeight(700);

        Button matchesBackBtn = new Button("Back");
        matchesBackBtn.setLayoutX(40);
        matchesBackBtn.setLayoutY(600);
        matchesBackBtn.setStyle("-fx-font-size:17;-fx-background-color:E1FEFE");
        matchesBackBtn.setPrefSize(70, 40);
        matchesBackBtn.setOnAction(event -> primaryStage.setScene(menuScene));

        paneMatch.getChildren().addAll(matchesImage,matchesBackBtn);
        matchScene = new Scene(paneMatch, 1000, 700);




    }









    public void deleteFootballClub() throws IOException {


        displayStatistics();
        System.out.println("Enter the club name: ");
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        for(FootballClub club : footballClubArrayList) {
            if (club.getClubName().equals(input)) {
                footballClubArrayList.remove(club);


                    FileOutputStream fis = new FileOutputStream("clubData.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(fis);
                    oos.writeObject(footballClubArrayList);
                    oos.close();
                    fis.close();
                    System.out.println("Club deleted Successfully");
            }
        }
    }



    public void displayPremierLeagueTable() {
        displayStatistics();
        footballClubArrayList.sort(new SortPoint().reversed());
        System.out.println(" Premier League Table ");
        System.out.println("+---------------+------------------+----------------+-----------------+------------------+------------------------+---------------------------+-------------------+--------------------------+");
        System.out.println("| Club Name     |  Club Location   | Number of Wins | Number of Draws | Number of Defeats| Number of Goals Scored | Number of Goals Received  | Number of Points  | Number of Played Matches |");
        System.out.println("+---------------+------------------+----------------+-----------------+------------------+------------------------+---------------------------+-------------------+--------------------------+");

        for(FootballClub footballclub: footballClubArrayList) {
            System.out.println("  "+footballclub.getClubName()+"                  "+footballclub.getClubLocation()+"                "+footballclub.getNumOfWins()+"                  "+footballclub.getNumOfDraws()+"                    "+footballclub.getNumOfDefeats()+"                  "+footballclub.getNumOfGoalsScored()+"                        "+footballclub.getNumOfGoalsReceived()+"                         "+footballclub.getNumOfPoints()+"                      "+footballclub.getNumOfMatchesPlayed());
        }
        System.out.println("+---------------+------------------+----------------+-----------------+------------------+------------------------+---------------------------+-------------------+--------------------------+");


    }
    public void randomMatch(){
        ArrayList <Integer> arraylistFinal = randomFinder();
        Random random=new Random();
        int goalsRandom1 = random.nextInt(10);
        int goalsRandom2 = random.nextInt(10);
        try {
//            Date randomDate = new Date(01, 02, 2021);
            int date = 20;
            int month = 12;
            int year = 2020;
            Match matchObject = new Match(date,month,year, goalsRandom1, goalsRandom2,arraylistFinal.get(1), arraylistFinal.get(0) );
//            this.addingPlayedMatch(date,month,year,goalsRandom1, goalsRandom2,arraylistFinal.get(1), arraylistFinal.get(0));
            matchArrayList.add(matchObject);
            System.out.println(matchObject);
        }catch (NullPointerException e){
            System.out.println("Not equal club for this id");
        }
        }

    public  ArrayList<Integer> randomFinder(){

        ArrayList<Integer> randomList = new ArrayList<>();
        for (FootballClub football: footballClubArrayList) {
//            footballClubArrayList.add(football.getClubId());
        }
        int randomMatch1 = 0;
        int randomMatch2 = 0;
        Collections.shuffle(randomList);
        randomMatch1 =randomList.get(0);
        randomMatch2 = randomList.get(1);

        ArrayList<Integer>list=new ArrayList<>();
        list.add(randomMatch1);
        list.add(randomMatch2);
        return list;
    }
}




