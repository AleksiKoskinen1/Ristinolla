package projects.aleksi.ristinolla;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class RistiNollaMain extends AppCompatActivity implements OnItemSelectedListener{

    FragmentTransaction transaction;
    MainFragment newFragment;
    SettingsFragment setFragment;
    ScoresFragment scoresFragment;
    DataBase db;
    private String vaikeustaso = "Normaali";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risti_nolla_main);

        db = new DataBase(getApplicationContext());

        newFragment = MainFragment.newInstance();
        transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.my_container, newFragment);
        transaction.commit();

    }

    @Override
    public void changeFragment(String s, String taso){

        if(!(taso.equalsIgnoreCase("")))
            vaikeustaso = taso;

        if(s.equalsIgnoreCase("settings")) {

            if(setFragment == null)
                setFragment = SettingsFragment.newInstance();
            transaction = getSupportFragmentManager().beginTransaction();

            transaction.addToBackStack(null);
            transaction.replace(R.id.my_container, setFragment);
            transaction.commit();
        }
        else if(s.equalsIgnoreCase("scores")){


            scoresFragment = ScoresFragment.newInstance(db.getEasyScoreFromTable(),db.getNormalScoreFromTable(), db.getHardScoreFromTable());

            transaction = getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.my_container, scoresFragment);
            transaction.commit();

        }
        else if(s.equalsIgnoreCase("main")){

            transaction = getSupportFragmentManager().beginTransaction();
            newFragment.setVaikeusTaso(vaikeustaso);
            transaction.addToBackStack(null);
            transaction.replace(R.id.my_container, newFragment);
            transaction.commit();
        }
    }

    public void resetMainFragment(){

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.detach(newFragment);
        transaction.attach(newFragment);
        transaction.commit();
     }
    public void endActivity(){

        this.finish();  //Päätetään aktiviteetti
    }
    public void addResult(String result, String vaikeustaso){

        db.insertScore(result, vaikeustaso);

    }
    public void resetDB(){
        db.deleteAll();
    }

}
