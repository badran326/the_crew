package com.badran.thecrew;

import static com.badran.thecrew.MainActivity.names;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badran.thecrew.databinding.FragmentFinalBinding;

import java.util.ArrayList;

public class FinalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentFinalBinding binding;
    private int nescafeCount, nCanderelCount, nSugarCount, nNestleCount, nBlackCount, nCoffeeMateCount;
    private int coffeeCount, cCanderelCount, cSugarCount, cNestleCount, cBlackCount, cCoffeeMateCount;

    public FinalFragment() {
        // Required empty public constructor
    }

    public static FinalFragment newInstance (ArrayList<String> theCrewName, ArrayList<Integer> theCrewNumber) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("theCrewName", theCrewName);
        bundle.putIntegerArrayList("theCrewNumber", theCrewNumber);
        FinalFragment finalFragment = new FinalFragment();
        finalFragment.setArguments(bundle);
        return finalFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFinalBinding.inflate(getLayoutInflater(), container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}