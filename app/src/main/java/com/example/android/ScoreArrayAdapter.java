package com.example.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ScoreArrayAdapter extends ArrayAdapter<Score> {


    public ScoreArrayAdapter(@NonNull Context context, @NonNull List<Score> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.team_one_item, parent, false);
        }
        final TextView teamOneScore = listView.findViewById(R.id.teamOneText);
        final TextView teamTwoScore = listView.findViewById(R.id.teamTwoText);

        Score score = getItem(position);
        if (score.getTeamOneScore() > 0 || score.getTeamTwoScore() > 0) {
            teamOneScore.setText(score.getTeamOneScore() + "");
            teamTwoScore.setText(score.getTeamTwoScore() + "");
        }


        return listView;
    }


}
