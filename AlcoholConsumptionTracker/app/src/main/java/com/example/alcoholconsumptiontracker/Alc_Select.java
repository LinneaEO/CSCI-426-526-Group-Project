package com.example.alcoholconsumptiontracker;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alcoholconsumptiontracker.system.DrinkTemplate;
import com.example.alcoholconsumptiontracker.system.DrinkTemplateManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Alc_Select#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Alc_Select extends Fragment {

    ///
    ///  Locals
    ///
    // List to be populated with drinks and button that adds more drinks
    private ListView alcSelectListView;


    public Alc_Select() {
        super(R.layout.fragment_alc__select);

        }


    public static Alc_Select newInstance() {
        Alc_Select fragment = new Alc_Select();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_alc__select, container, false);

        // Initialize alcSelect list
        this.alcSelectListView = root.findViewById(R.id.alc_select_list);
        this.alcSelectListView.setAdapter(new alcSelectListAdapter(getContext()));

        return root;

    }
    public class alcSelectListAdapter extends BaseAdapter {

        Context listContext;
        LayoutInflater inflater;
        DrinkTemplate[] templateList;

        public alcSelectListAdapter(Context context) {
            this.templateList = MainActivity.GetDrinkTemplateManager().GetTemplateList().values().toArray(new DrinkTemplate[0]);
            this.listContext = context;
        }

        public alcSelectListAdapter(Context context, DrinkTemplate[] templateList) {
            this.templateList = templateList;
            this.listContext = context;
        }

        public int getCount() {
            return this.templateList.length;
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            inflater = (LayoutInflater) this.listContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row;
            row = inflater.inflate(R.layout.alc_select_list_item, parent, false);
            TextView drinkName;
            TextView drinkType;
            TextView drinkServings;
            TextView drinkCalories;
            TextView drinkPrice;
            ImageView drinkImage;

            /*
            drinkName = (TextView) row.findViewById(R.id.alcoholSelectDrinkNameList);
            drinkName.setText(this.templateList[position].GetName());
            drinkType = (TextView) row.findViewById(R.id.alcoholSelectDrinkTypeList);
            drinkType.setText(this.templateList[position].GetType().Get());
            drinkServings = (TextView) row.findViewById(R.id.alcoholSelectDrinkServingsList);
            drinkServings.setText(this.templateList[position].GetServings());
            drinkCalories = (TextView) row.findViewById(R.id.alcoholSelectDrinkCaloriesList);
            drinkCalories.setText(Float.toString(this.templateList[position].GetCalories()));
            drinkPrice = (TextView) row.findViewById(R.id.alcoholSelectDrinkPriceList);
            drinkPrice.setText(Float.toString(this.templateList[position].GetCalories()));
            drinkImage = (ImageView) row.findViewById(R.id.alcoholSelectImageList);
            */

            return (row);
        }
    }
}