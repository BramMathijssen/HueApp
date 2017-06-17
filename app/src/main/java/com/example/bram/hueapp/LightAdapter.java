package com.example.bram.hueapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bram on 17-6-2017.
 */

public class LightAdapter extends BaseAdapter {

    private Context context;

    private LayoutInflater inflater;

    /**
     * Collectie van Light-instanties.
     */
    private ArrayList list;

    /**
     * Constructor.
     * @param context
     * @param inflater
     * @param list
     */
    public LightAdapter(Context context, LayoutInflater inflater, ArrayList<Light> list) {
        this.context = context;
        this.inflater = inflater;
        this.list = list;
    }

    /**
     * Retourneer aantal Lights in de collectie.
     * @return aantal Light-instanties
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * Retourneer een specifieke Light-instantie.
     * @param position positie
     * @return Light-instantie op de gevraagde positie
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     * Dummy methode. Retourneert gewoon het positienummer.
     * @param position positie
     * @return positie
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Retourneert een ListView-rij voor een bepaalde positie.
     * @param position positie
     * @param convertView doel-ListView
     * @param parent
     * @return ListView-rij
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row, null);
            v = new ViewHolder();
            v.id = (TextView) convertView.findViewById(R.id.id);
            v.description = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(v);
        } else
            v = (ViewHolder) convertView.getTag();

        Light l = (Light) list.get(position);
        v.id.setText(l.getId());
        v.description.setText(l.getDescription());
        return convertView;
    }

    /**
     * Private class voor de ListView-rij.
     */
    private static class ViewHolder {
        public TextView id;
        public TextView description;
    }

}
