package projects.aleksi.ristinolla;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    private Button backButton;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private OnItemSelectedListener listener;

    public SettingsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        backButton = (Button) view.findViewById(R.id.button13);
        radioGroup = (RadioGroup)view.findViewById(R.id.vaikeusgroup);
        backButton.setOnClickListener(this);

        return view;
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    public void onClick(View view) {

        if (view.getId() == R.id.button13) { //Jos "Takaisin" nappia painettu
            int selectedTaso = radioGroup.getCheckedRadioButtonId(); //Haetaan valittu vaikeustaso
            radioButton = (RadioButton) radioGroup.findViewById(selectedTaso); //Liitetään valittu taso radioButtonii
            listener.changeFragment("main", radioButton.getText().toString()); //Vaihetaan takaisin mainFragmenttii
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
}