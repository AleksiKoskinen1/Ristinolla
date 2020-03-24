package projects.aleksi.ristinolla;

/**
 * Created by Aleksi on 2.5.2017.
 */

public interface OnItemSelectedListener {
    void changeFragment(String s, String taso);
    void resetMainFragment();
    void endActivity();
    void addResult(String result, String vaikeustaso);
    void resetDB();
}

