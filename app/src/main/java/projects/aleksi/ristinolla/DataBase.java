package projects.aleksi.ristinolla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aleksi on 4.5.2017.
 */

public class DataBase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "scores";

    // Table Names
    private static final String TABLE_EASY = "easy";
    private static final String TABLE_NORMAL = "normal";
    private static final String TABLE_HARD = "hard";

    // Easy table column names
    private static final String EASY_KEY_ID = "id";
    private static final String EASY_WINS = "wins";
    private static final String EASY_LOSSES = "losses";
    private static final String EASY_DRAW = "draw";

    // Normal Table column nmaes
    private static final String NORMAL_KEY_ID = "ID";
    private static final String NORMAL_WINS = "wins";
    private static final String NORMAL_LOSSES = "losses";
    private static final String NORMAL_DRAW = "draw";

    // Hard Table column nmaes
    private static final String HARD_KEY_ID = "ID";
    private static final String HARD_WINS = "wins";
    private static final String HARD_LOSSES = "losses";
    private static final String HARD_DRAW = "draw";

  private SQLiteDatabase db;

    // Todo table create statement
    private static final String CREATE_TABLE_EASY = "CREATE TABLE "
            + TABLE_EASY + "(" + EASY_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + EASY_WINS
            + " INTEGER," + EASY_LOSSES
            + " INTEGER," + EASY_DRAW + " INTEGER" + ")";

    private static final String CREATE_TABLE_NORMAL = "CREATE TABLE "
            + TABLE_NORMAL + "(" + NORMAL_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NORMAL_WINS
            + " INTEGER," + NORMAL_LOSSES
            + " INTEGER," + NORMAL_DRAW + " INTEGER" + ")";

    private static final String CREATE_TABLE_HARD = "CREATE TABLE "
            + TABLE_HARD + "(" + HARD_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + HARD_WINS
            + " INTEGER," + HARD_LOSSES
            + " INTEGER," + HARD_DRAW + " INTEGER" + ")";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertScore(String score, String vaikeus){
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        if(vaikeus.equalsIgnoreCase("Helppo")){ //Jos vaikeustaso millä pelattiin on easy
            if(score.equalsIgnoreCase("win"))  //jos voitto
                values.put(EASY_WINS,1); //Laitetaan yksi voitto taulukkoon
            else if(score.equalsIgnoreCase("loss"))  //Jos tappio
                values.put(EASY_LOSSES,1);
            else if(score.equalsIgnoreCase("draw"))  //Jos tasapeli
                values.put(EASY_DRAW,1);

            db.insert(TABLE_EASY, null, values);  //Updeitataan taulukko
        }
        else if (vaikeus.equalsIgnoreCase("Normaali")){   //Jos normaali vaikeutaso
            if(score.equalsIgnoreCase("win"))  //jos voitto
                values.put(NORMAL_WINS,1); //Laitetaan yksi voitto taulukkoon
            else if(score.equalsIgnoreCase("loss"))  //Jos tappio
                values.put(NORMAL_LOSSES,1);
            else if(score.equalsIgnoreCase("draw"))  //Jos tasapeli
                values.put(NORMAL_DRAW,1);

            db.insert(TABLE_NORMAL, null, values);  //Updeitataan taulukko
        }
        else if (vaikeus.equalsIgnoreCase("Vaikea")){   //Jos vaikea vaikeutaso
            if(score.equalsIgnoreCase("win"))  //jos voitto
                values.put(HARD_WINS,1); //Laitetaan yksi voitto taulukkoon
            else if(score.equalsIgnoreCase("loss"))  //Jos tappio
                values.put(HARD_LOSSES,1);
            else if(score.equalsIgnoreCase("draw"))  //Jos tasapeli
                values.put(HARD_DRAW,1);

            db.insert(TABLE_HARD, null, values);  //Updeitataan taulukko
        }

        db.close(); //Suljetaan DB

    }

    public int []  getEasyScoreFromTable() {

        db = this.getReadableDatabase();
        int [] scores = new int [3];
        int win = 0, loss = 0, draw = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_EASY ;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                win += c.getInt(c.getColumnIndex(EASY_WINS));
                loss += c.getInt(c.getColumnIndex(EASY_LOSSES));
                draw += c.getInt(c.getColumnIndex(EASY_DRAW));
            } while (c.moveToNext());
        }
        scores[0] = win;
        scores[1] = loss;
        scores[2] = draw;

        c.close();  //Suljetaan kursori

        return scores;
    }
    public int [] getNormalScoreFromTable() {
        db = this.getReadableDatabase();
        int [] scores = new int [3];
        int win = 0, loss = 0, draw = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_NORMAL ;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                win += c.getInt(c.getColumnIndex(NORMAL_WINS));
                loss += c.getInt(c.getColumnIndex(NORMAL_LOSSES));
                draw += c.getInt(c.getColumnIndex(NORMAL_DRAW));
            } while (c.moveToNext());
        }
        scores[0] = win;
        scores[1] = loss;
        scores[2] = draw;

        c.close();  //Suljetaan kursori

        return scores;
    }
    public int [] getHardScoreFromTable() {
        db = this.getReadableDatabase();
        int [] scores = new int [3];
        int win = 0, loss = 0, draw = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_HARD ;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                win += c.getInt(c.getColumnIndex(HARD_WINS));
                loss += c.getInt(c.getColumnIndex(HARD_LOSSES));
                draw += c.getInt(c.getColumnIndex(HARD_DRAW));
            } while (c.moveToNext());
        }
        scores[0] = win;
        scores[1] = loss;
        scores[2] = draw;

        c.close();  //Suljetaan kursori

        return scores;
    }


    public void closeDB() {
        db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Tehdään taulukot
        db.execSQL(CREATE_TABLE_EASY);
        db.execSQL(CREATE_TABLE_NORMAL);
        db.execSQL(CREATE_TABLE_HARD);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EASY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NORMAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HARD);

        // create new tables
        onCreate(db);
    }
    public void deleteAll(){

        db.delete(TABLE_EASY,null,null);
        db.delete(TABLE_NORMAL,null,null);
        db.delete(TABLE_HARD,null,null);

    }


}
