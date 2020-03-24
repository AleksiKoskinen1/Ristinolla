package projects.aleksi.ristinolla;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ScoresFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    private int [] easyScore, normalScore, hardScore;

    private Button backB, resetB;
    private TextView dataTexts [];

    private OnItemSelectedListener listener;

    public ScoresFragment() {
        // Required empty public constructor
    }

    public static ScoresFragment newInstance(int [] easy, int [] normal, int [] hard) {
        ScoresFragment fragment = new ScoresFragment();
        Bundle args = new Bundle();
        args.putIntArray(ARG_PARAM1, easy);
        args.putIntArray(ARG_PARAM2, normal);
        args.putIntArray(ARG_PARAM3, hard);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            easyScore = getArguments().getIntArray(ARG_PARAM1);
            normalScore = getArguments().getIntArray(ARG_PARAM2);
            hardScore = getArguments().getIntArray(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scores, container, false);

        backB = (Button) view.findViewById(R.id.button14);
        resetB = (Button) view.findViewById(R.id.button16);
        dataTexts = new TextView[9];
        backB.setOnClickListener(this);
        resetB.setOnClickListener(this);
        dataTexts[0] = (TextView) view.findViewById(R.id.totalEasyWins);
        dataTexts[1] = (TextView) view.findViewById(R.id.totalEasyLosses);
        dataTexts[2] = (TextView) view.findViewById(R.id.easyDraw);

        dataTexts[3] = (TextView) view.findViewById(R.id.totalNormalWins);
        dataTexts[4] = (TextView) view.findViewById(R.id.totalNormalLosses);
        dataTexts[5] = (TextView) view.findViewById(R.id.normalDraw);

        dataTexts[6] = (TextView) view.findViewById(R.id.totalHardWins);
        dataTexts[7] = (TextView) view.findViewById(R.id.totalHardLosses);
        dataTexts[8] = (TextView) view.findViewById(R.id.hardDraw);

        dataTexts[0].setText(""+easyScore[0]);
        dataTexts[1].setText(""+easyScore[1]);
        dataTexts[2].setText(""+easyScore[2]);

        dataTexts[3].setText(""+normalScore[0]);
        dataTexts[4].setText(""+normalScore[1]);
        dataTexts[5].setText(""+normalScore[2]);

        dataTexts[6].setText(""+hardScore[0]);
        dataTexts[7].setText(""+hardScore[1]);
        dataTexts[8].setText(""+hardScore[2]);

        return view;
    }


    public void onClick(View view) {

        if (view.getId() == R.id.button14) { //Jos "Takaisin" nappia painettu
            listener.changeFragment("main", ""); //Vaihetaan takaisin mainfragmenttii
        }
        else if (view.getId() == R.id.button16) { //Jos "reset scores" nappia painettu
            listener.resetDB();
            for(int i=0;i<9;i++)
                dataTexts[i].setText("0");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
