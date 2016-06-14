package com.udacity.firebase.shoppinglistplusplus.ui.activeLists;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.ui.MainActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;
import com.udacity.firebase.shoppinglistplusplus.utils.Utils;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass that shows a list of all shopping lists a user can see.
 * Use the {@link ShoppingListsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListsFragment extends Fragment {
    private ListView mListView;
    private TextView mTextViewListName,mTextViewOwner,mTextViewEditTime;
    private static final String LOG_TAG = ShoppingListsFragment.class.getSimpleName();

    public ShoppingListsFragment() {
        /* Required empty public constructor */
    }

    /**
     * Create fragment and pass bundle with data as it's arguments
     * Right now there are not arguments...but eventually there will be.
     */
    public static ShoppingListsFragment newInstance() {
        ShoppingListsFragment fragment = new ShoppingListsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * Initalize UI elements
         */
        View rootView = inflater.inflate(R.layout.fragment_shopping_lists, container, false);
        initializeScreen(rootView);

      //  Firebase listNameRef = new Firebase(Constants.FIREBASE_URL).child("listName");
        final Firebase listNameRef = new Firebase(Constants.FIREBASE_URL).child("activeList");
        listNameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(LOG_TAG,"The data changed");
              //  String listName = (String) dataSnapshot.getValue();
                ShoppingList shoppingList = dataSnapshot.getValue(ShoppingList.class);
                mTextViewListName.setText(shoppingList.getListName());
                mTextViewOwner.setText(shoppingList.getOwner());
                if (shoppingList.getDateLastChanged() != null) {
                    mTextViewEditTime.setText(
                            Utils.SIMPLE_DATE_FORMAT.format(
                                    new Date(shoppingList.getDateLastChangedLong())));
                } else {
                    mTextViewEditTime.setText("");
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        /**
         * Set interactive bits, such as click events and adapters
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * Link layout elements from XML
     */
    private void initializeScreen(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.list_view_active_lists);
        mTextViewListName = (TextView) rootView.findViewById(R.id.text_view_list_name);
        mTextViewOwner = (TextView) rootView.findViewById(R.id.text_view_created_by_user);
        mTextViewEditTime = (TextView) rootView.findViewById(R.id.text_view_edit_time);


    }
}