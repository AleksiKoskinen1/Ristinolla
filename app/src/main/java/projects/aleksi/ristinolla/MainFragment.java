package projects.aleksi.ristinolla;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;


public class MainFragment extends Fragment implements View.OnClickListener {

    private Button button [][];
    private Button scoresButton;
    private int owner [][];
    private boolean gameOver;
    private String vaikeusTaso = "Normaali";  //Oletus vaikeustaso

    private int AIScore = 0, playerScore = 0;
    private int score [];

    private int e = 0;
    private int turn = 0;
    private boolean firstMove = false;
    private boolean secondMove = false;
    //private int []corners = new int[];

    private OnItemSelectedListener listener;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        button = new Button[4][3];
        owner = new int[3][3];
        score = new int[8];

        button [0][0] = (Button) view.findViewById(R.id.button);
        button [0][1] = (Button) view.findViewById(R.id.button2);
        button [0][2] = (Button) view.findViewById(R.id.button3);
        button [1][0] = (Button) view.findViewById(R.id.button4);
        button [1][1] = (Button) view.findViewById(R.id.button5);
        button [1][2] = (Button) view.findViewById(R.id.button6);
        button [2][0] = (Button) view.findViewById(R.id.button7);
        button [2][1] = (Button) view.findViewById(R.id.button8);
        button [2][2] = (Button) view.findViewById(R.id.button9);

        button [3][0] = (Button) view.findViewById(R.id.button10); //settings
        button [3][1] = (Button) view.findViewById(R.id.button11); //new game
        button [3][2] = (Button) view.findViewById(R.id.button12); //exit

        for(int i = 0; i<4; i++){
            for(int j = 0; j<3; j++){
                button [i][j].setOnClickListener(this);
            }
        }

        scoresButton = (Button) view.findViewById(R.id.button15); //scores
        scoresButton.setOnClickListener(this);

        score[0] = owner[0][0]+owner[0][1]+owner[0][2]; //Vaakarivit
        score[1] = owner[1][0]+owner[1][1]+owner[1][2];
        score[2] = owner[2][0]+owner[2][1]+owner[2][2];

        score[3] = owner[0][0]+owner[1][0]+owner[2][0];  //Pystyrivit
        score[4] = owner[0][1]+owner[1][1]+owner[2][1];
        score[5] = owner[2][0]+owner[1][2]+owner[2][2];

        score[6] = owner[0][0]+owner[1][1]+owner[2][2];  //Cross rivit
        score[7] = owner[2][0]+owner[1][1]+owner[0][2];


        return view;
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button:
                owner[0][0] = 1; //Ruutu on pelaajan hallussa
                button[0][0].setText("O");
                button[0][0].setEnabled(false); //Jotta nappia ei voi painaa uudestaan, ja peli mennä pilalle
                checkGameOver();
                if(gameOver == false) AItakingTurn();
                break;
            case R.id.button2:
                owner[0][1] = 1; //Ruutu on pelaajan hallussa
                button[0][1].setText("O");
                button[0][1].setEnabled(false); //Jotta nappia ei voi painaa uudestaan, ja peli mennä pilalle
                checkGameOver();
                if(gameOver == false) AItakingTurn();
                break;
            case R.id.button3:
                owner[0][2] = 1; //Ruutu on pelaajan hallussa
                button[0][2].setText("O");
                button[0][2].setEnabled(false); //Jotta nappia ei voi painaa uudestaan, ja peli mennä pilalle
                checkGameOver();
                if(gameOver == false) AItakingTurn();
                break;
            case R.id.button4:
                owner[1][0] = 1; //Ruutu on pelaajan hallussa
                button[1][0].setText("O");
                button[1][0].setEnabled(false); //Jotta nappia ei voi painaa uudestaan, ja peli mennä pilalle
                checkGameOver();
                if(gameOver == false) AItakingTurn();
                break;
            case R.id.button5:
                owner[1][1] = 1; //Ruutu on pelaajan hallussa
                button[1][1].setText("O");
                button[1][1].setEnabled(false); //Jotta nappia ei voi painaa uudestaan, ja peli mennä pilalle
                checkGameOver();
                if(gameOver == false) AItakingTurn();
                break;
            case R.id.button6:
                owner[1][2] = 1; //Ruutu on pelaajan hallussa
                button[1][2].setText("O");
                button[1][2].setEnabled(false); //Jotta nappia ei voi painaa uudestaan, ja peli mennä pilalle
                checkGameOver();
                if(gameOver == false) AItakingTurn();
                break;
            case R.id.button7:
                owner[2][0] = 1; //Ruutu on pelaajan hallussa
                button[2][0].setText("O");
                button[2][0].setEnabled(false); //Jotta nappia ei voi painaa uudestaan, ja peli mennä pilalle
                checkGameOver();
                if(gameOver == false) AItakingTurn();
                break;
            case R.id.button8:
                owner[2][1] = 1; //Ruutu on pelaajan hallussa
                button[2][1].setText("O");
                button[2][1].setEnabled(false); //Jotta nappia ei voi painaa uudestaan, ja peli mennä pilalle
                checkGameOver();
                if(gameOver == false) AItakingTurn();
                break;
            case R.id.button9:
                owner[2][2] = 1; //Ruutu on pelaajan hallussa
                button[2][2].setText("O");
                button[2][2].setEnabled(false); //Jotta nappia ei voi painaa uudestaan, ja peli mennä pilalle
                checkGameOver();
                if(gameOver == false) AItakingTurn();
                break;
            case R.id.button10:  //Settings
                firstMove = false;
                secondMove = false;
                turn = 0;
                listener.changeFragment("settings", "");
                break;
            case R.id.button11:  //New Game

                firstMove = false;
                secondMove = false;
                turn = 0;
                listener.resetMainFragment();
                break;
            case R.id.button12:  //Exit
                listener.endActivity();
            case R.id.button15:  //Scores
                firstMove = false;
                secondMove = false;
                turn = 0;
                listener.changeFragment("scores", "");

                break;

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemSelectedListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void setVaikeusTaso(String v){
        this.vaikeusTaso = v;
    }

    public void AItakingTurn(){

        if(vaikeusTaso.equals("Helppo")) {
            Random rand = new Random();

            int a = rand.nextInt(3);
            int b = rand.nextInt(3);
            while (owner[a][b] != 0) {
                a = rand.nextInt(3);
                b = rand.nextInt(3);
            }
            doMove(a, b);
        }

        if(vaikeusTaso.equals("Normaali")) {

            updateRows();

            int i = 0;
            if (checkForWin() != false)
                i=2;
                else if (checkForDanger() != false)
                i=2;
            else{

                Random rand = new Random();

                int a = rand.nextInt(3);
                int b = rand.nextInt(3);
                while (owner[a][b] != 0) {
                    a = rand.nextInt(3);
                    b = rand.nextInt(3);
                }
                doMove(a, b);
            }

        }

        if(vaikeusTaso.equals("Vaikea")){

            int i;
            updateRows();
            if(turn == 0 && owner[1][1] == 1) turn = 1; //Aloitus keskeltä
            else if(turn == 0 && (owner[0][0] == 1 || owner[0][2] == 1 || owner[2][0] == 1 || owner[2][2] == 1)) turn = 2; //Aloitus kulmasta
            else if(turn == 0 && (owner[0][1] == 1 || owner[1][0] == 1 || owner[1][2] == 1 || owner[2][1] == 1)) turn = 3; //Aloitus sivusta

            if(turn == 1 && (firstMove == false || secondMove == false)) {  //Keskeltä aloitettu
                if (firstMove == false) {
                    firstMove = true;
                    Random rand = new Random();
                    int a = rand.nextInt(4);
                    if (a == 0) doMove(0, 0);
                    if (a == 1) doMove(2, 0);
                    if (a == 2) doMove(0, 2);
                    if (a == 3) doMove(2, 2);

                } else if (secondMove == false) {
                    secondMove = true;
                    /* Jos pelaaja tehnyt siirron sivuille, päättyy peli autom. tasuriin tai tappioon.
                       Jos pelaaja tehnyt siirron kulmaan, pistetään AI tekemään siirto johonkin vapaaseen kulmaan
                       Tämän jälkeen peli päättyy tasuriin tai tappioon.
                     */
                    if (owner[0][0] == 1 || owner[0][2] == 1 || owner[2][0] == 1 || owner[2][2] == 1) {
                        if (checkForDanger() == false) {
                            Random rand = new Random();
                            boolean success = false;

                            while (!success) {
                                int a = rand.nextInt(4);
                                if (a == 0 && owner[0][0] == 0) {
                                    success = true;
                                    doMove(0, 0);
                                }
                                if (a == 1 && owner[2][0] == 0) {
                                    success = true;
                                    doMove(2, 0);
                                }
                                if (a == 2 && owner[0][2] == 0) {
                                    success = true;
                                    doMove(0, 2);
                                }
                                if (a == 3 && owner[2][2] == 0) {
                                    success = true;
                                    doMove(2, 2);
                                }
                            }
                        }
                    } else {

                        if (checkForWin() != false)
                            i = 2;
                        else if (checkForDanger() != false)
                            i = 2;
                        else {
                            Random rand = new Random();
                            int a = rand.nextInt(3);
                            int b = rand.nextInt(3);
                            while (owner[a][b] != 0) {
                                a = rand.nextInt(3);
                                b = rand.nextInt(3);
                            }
                            doMove(a, b);
                        }
                    }
                }
            }

            else if(turn == 2 && (firstMove == false || secondMove == false)){
                if (firstMove == false) {
                    firstMove = true;
                    doMove(1,1);
                }
                else if(secondMove == false){
                    secondMove = true;
                    if(score[6] == 1 || score[7] == 1){ //Jos pelattu vastakkaiseen kulmaan
                        Random rand = new Random();
                        int a = rand.nextInt(4);
                        if (a == 0) doMove(0, 1);
                        if (a == 1) doMove(1, 0);
                        if (a == 2) doMove(1, 2);
                        if (a == 3) doMove(2, 1);
                    }
                    else if(owner[0][0] == 1 && (owner [1][2] == 1 || owner[2][1] == 1))
                        doMove(2,2);
                    else if(owner[0][2] == 1 && (owner [1][0] == 1 || owner[2][1] == 1))
                        doMove(2,0);
                    else if(owner[2][0] == 1 && (owner [0][1] == 1 || owner[1][2] == 1))
                        doMove(0,2);
                    else if(owner[2][2] == 1 && (owner [0][1] == 1 || owner[1][0] == 1))
                        doMove(0,0);
                    else if (checkForWin() != false)
                        i=2;
                    else if (checkForDanger() != false)
                        i=2;
                    else{
                        Random rand = new Random();

                        int a = rand.nextInt(3);
                        int b = rand.nextInt(3);
                        while (owner[a][b] != 0) {
                            a = rand.nextInt(3);
                            b = rand.nextInt(3);
                        }
                        doMove(a, b);
                    }
                }
            }
            else if(turn == 3 && (firstMove == false || secondMove == false)){
                if(firstMove == false){
                    firstMove = true;
                    doMove(1,1);
                }
                else if(secondMove == false) {
                    secondMove = true;
                    Random rand = new Random();
                    if (owner[1][0] == 1 && (owner[0][0] == 0 && owner[2][0] == 0 && owner[1][2] == 0)) {
                        int a = rand.nextInt(2);
                        if (a == 0) doMove(0, 0);
                        if (a == 1) doMove(2, 0);
                    } else if (owner[1][2] == 1 && (owner[0][2] == 0 && owner[2][2] == 0 && owner[1][0] == 0)) {
                        int a = rand.nextInt(2);
                        if (a == 0) doMove(0, 2);
                        if (a == 1) doMove(2, 2);
                    } else if (owner[0][1] == 1 && (owner[0][0] == 0 && owner[0][2] == 0 && owner[2][1] == 0)) {
                        int a = rand.nextInt(2);
                        if (a == 0) doMove(0, 0);
                        if (a == 1) doMove(0, 2);
                    } else if (owner[2][1] == 1 && (owner[2][0] == 0 && owner[2][2] == 0 && owner[0][1] == 0)) {
                        int a = rand.nextInt(2);
                        if (a == 0) doMove(2, 0);
                        if (a == 1) doMove(2, 2);
                    } else if (checkForWin() != false)
                        i = 2;
                    else if (checkForDanger() != false)
                        i = 2;
                    else {

                        int a = rand.nextInt(3);
                        int b = rand.nextInt(3);
                        while (owner[a][b] != 0) {
                            a = rand.nextInt(3);
                            b = rand.nextInt(3);
                        }
                        doMove(a, b);
                    }
                }
            }


            else if (checkForWin() != false)
                i=2;
            else if (checkForDanger() != false)
                i=2;
            else{
                Random rand = new Random();

                int a = rand.nextInt(3);
                int b = rand.nextInt(3);
                while (owner[a][b] != 0) {
                    a = rand.nextInt(3);
                    b = rand.nextInt(3);
                }
                doMove(a, b);
            }
        }
    }

    public boolean checkForWin(){

        if (owner[0][0] == 0 && //Jos kyseinen ruutu on tyhjä (0), katsoo AI onko vieressä omia rukseja
                (score[0] == -2 || score[3] == -2 || score[6] == -2 )) {
            doMove(0, 0);
            return true;
        }
        else if (owner[0][1] == 0 && (score[0] == -2 || score[4] == -2)) {
            doMove(0, 1);
            return true;
        }
        else if (owner[0][2] == 0 && (score[0] == -2 || score[5] == -2 || score[7] == -2)){
            doMove(0, 2);
            return true;
        }
        else if (owner[1][0] == 0 && (score[1] == -2 || score[3] == -2)){
            doMove(1, 0);
            return true;
        }
        else if (owner[1][1] == 0 && (score[1] == -2 || score[4] == -2 || score[6] == -2 || score[7] == -2)){
            doMove(1, 1);
            return true;
        }
        else if (owner[1][2] == 0 && (score[1] == -2 || score[5] == -2)){
            doMove(1, 2);
            return true;
        }
        else if (owner[2][0] == 0 &&  (score[2] == -2 || score[3] == -2 || score[7] == -2)){
            doMove(2, 0);
            return true;
        }
        else if (owner[2][1] == 0 && (score[2] == -2 || score[4] == -2)){
            doMove(2, 1);
            return true;
        }
        else if (owner[2][2] == 0 && (score[2] == -2 || score[5] == -2 || score[6] == -2)){
            doMove(2, 2);
            return true;
        }
        else
            return false;
    }

    public boolean checkForDanger(){

        if (owner[0][0] == 0 && //Jos kyseinen ruutu on tyhjä (0), katsoo AI onko vieressä pelaajan rukseja
                (score[0] == 2 || score[3] == 2 || score[6] == 2 )) {
            doMove(0, 0);
            return true;
        }
        else if (owner[0][1] == 0 && (score[0] == 2 || score[4] == 2)) {
            doMove(0, 1);
            return true;
        }
        else if (owner[0][2] == 0 && (score[0] == 2 || score[5] == 2 || score[7] == 2)){
            doMove(0, 2);
            return true;
        }
        else if (owner[1][0] == 0 && (score[1] == 2 || score[3] == 2)){
            doMove(1, 0);
            return true;
        }
        else if (owner[1][1] == 0 && (score[1] == 2 || score[4] == 2 || score[6] == 2 || score[7] == 2)){
            doMove(1, 1);
            return true;
        }
        else if (owner[1][2] == 0 && (score[1] == 2 || score[5] == 2)){
            doMove(1, 2);
            return true;
        }
        else if (owner[2][0] == 0 &&  (score[2] == 2 || score[3] == 2 || score[7] == 2)){
            doMove(2, 0);
            return true;
        }
        else if (owner[2][1] == 0 && (score[2] == 2 || score[4] == 2)){
            doMove(2, 1);
            return true;
        }
        else if (owner[2][2] == 0 && (score[2] == 2 || score[5] == 2 || score[6] == 2)){
            doMove(2, 2);
            return true;
        }
        else
            return false;

    }

    public void doMove(int x,int y){

        button[x][y].setEnabled(false);
        button[x][y].setText("X");
        owner[x][y] = -1;
        checkGameOver();
    }

    public void updateRows(){
        score[0] = owner[0][0]+owner[0][1]+owner[0][2]; //Vaakarivit
        score[1] = owner[1][0]+owner[1][1]+owner[1][2];
        score[2] = owner[2][0]+owner[2][1]+owner[2][2];

        score[3] = owner[0][0]+owner[1][0]+owner[2][0];  //Pystyrivit
        score[4] = owner[0][1]+owner[1][1]+owner[2][1];
        score[5] = owner[0][2]+owner[1][2]+owner[2][2];

        score[6] = owner[0][0]+owner[1][1]+owner[2][2];  //Cross rivit
        score[7] = owner[2][0]+owner[1][1]+owner[0][2];
    }
    public boolean checkGameOver(){

        updateRows();

        gameOver = false;
        boolean playerWin = false, computerWin = false;

        for(int i = 0; i<8;i++){
            if(score[i] == 3)  //Pelaaja voittaa
                playerWin = true;
            if(score[i] == -3)  //Tietokone voittaa
                computerWin = true;
        }

        if(playerWin){
            listener.addResult("win", vaikeusTaso);
            Toast.makeText(getActivity(),"You win! ", Toast.LENGTH_LONG).show();
            gameOver = true;
        }
        else if(computerWin){
            listener.addResult("loss", vaikeusTaso);
            Toast.makeText(getActivity(), "Computer wins! ", Toast.LENGTH_LONG).show();
            gameOver = true;
        }
        else {
            gameOver = true;
            for(int i=0; i<3;i++){  //Katsoo jos kaikki ruudut pelattu
                for (int j=0;j<3;j++){
                    if(owner[i][j] == 0 )
                        gameOver = false;
                }
            }
            if(gameOver == true) {
                listener.addResult("draw", vaikeusTaso);
                Toast.makeText(getActivity(), "It's a DRAW!", Toast.LENGTH_LONG).show();
                firstMove = false;
                secondMove = false;
                turn = 0;
            }
        }
        if(gameOver == true){
            for(int i=0; i<3;i++){  //Laittaa napit pois käytöstä, jotta niitä ei voi painaa pelin päätyttyä
                for (int j=0;j<3;j++){
                    button[i][j].setEnabled(false);

                }
            }
        }


        return gameOver;
    }
}