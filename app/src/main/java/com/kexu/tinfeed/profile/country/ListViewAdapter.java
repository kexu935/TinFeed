package com.kexu.tinfeed.profile.country;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kexu.tinfeed.MainActivity;
import com.kexu.tinfeed.R;
import com.kexu.tinfeed.common.TinFragmentManager;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<Country> countries;
    private Context context;
    private CountryListener countryListener;
    private TinFragmentManager tinFragmentManager;

    ListViewAdapter(List<Country> countries, Context context, CountryListener countryListener, TinFragmentManager tinFragmentManager) {
        this.countries = countries;
        this.context = context;
        this.countryListener = countryListener;
        this.tinFragmentManager = tinFragmentManager;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.country_item_list, parent, false);
        }

        Country country = countries.get(position);
        TextView textView = convertView.findViewById(R.id.row_text);
        textView.setText(country.getString());
        ImageView flag = convertView.findViewById(R.id.flag);
        flag.setImageResource(country.getDrawableRes());

        ImageView checkMark = convertView.findViewById(R.id.checkmark);
        checkMark.setImageResource(country.isSelected() ? R.drawable.ic_check_circle_black_24dp :
                R.drawable.ic_check_circle_grey_24dp);

        convertView.setOnClickListener(v -> {
            unset();
            country.setSelected(true);
            countryListener.onCountryClicked(country);
            notifyDataSetChanged();
            tinFragmentManager.showSnackBar("Country changed!");
        });
        return convertView;

    }

    private void unset() {
        for (Country country : countries) {
            country.setSelected(false);
        }
    }

    public interface CountryListener {
        void onCountryClicked(Country country);
    }
}