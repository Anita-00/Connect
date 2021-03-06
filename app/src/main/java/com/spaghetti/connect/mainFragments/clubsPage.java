package com.spaghetti.connect.mainFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;
import com.spaghetti.connect.data.ObservableArrayList;
import com.spaghetti.connect.R;
import com.spaghetti.connect.data.Club;
import com.spaghetti.connect.firestoreAdapters.FirebaseProfileAdapter;
import com.spaghetti.connect.ui.recyclerViewAdapter.ClubsRCA;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link clubsPage#newInstance} factory method to
 * create an instance of this fragment.
 */

public class clubsPage extends Fragment {
    private View view;
    private Context c;
    FirebaseFirestore db;

    RecyclerView clubsRcView;
    RecyclerView.Adapter clubsAdapter;
    RecyclerView.LayoutManager clubsLayoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public clubsPage() {
        // Required empty public constructor0
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment page3.
     */
    // TODO: Rename and change types and number of parameters
    public static clubsPage newInstance(String param1, String param2) {
        clubsPage fragment = new clubsPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        FirebaseProfileAdapter adapter = new FirebaseProfileAdapter();
        ObservableArrayList<Club> observableClubList = new ObservableArrayList();
        Observer OnCompleteListener = new Observer() {
            @Override
            public void update(Observable observable, Object o) {
                ArrayList<Club> clubList = observableClubList.getList();
                clubsAdapter = new ClubsRCA(clubList);
                clubsRcView.setAdapter(clubsAdapter);
            }
        };

        observableClubList.addObserver(OnCompleteListener);
        adapter.RetrieveAllClubs(observableClubList);

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_clubspage, container, false);
        clubsRcView = view.findViewById(R.id.clubRecyclerView);
        clubsLayoutManager = new LinearLayoutManager(c);
        clubsRcView.setLayoutManager(clubsLayoutManager);

        return view;
    }
}