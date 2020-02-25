package com.example.android;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AlertDialog AskOption(int s1,int s2) {
        AlertDialog.Builder myQuittingDialogBox = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogLayout= inflater.inflate(R.layout.last_dialog_layout,null);
        teamOneFinalDialog= dialogLayout.findViewById(R.id.teamOneFinalScore);
        teamTwoFinalDialog = dialogLayout.findViewById(R.id.teamTwoFinalScore);
        teamOneFinalDialog.setText(s1+"");
        teamTwoFinalDialog.setText(s2+"");


        myQuittingDialogBox.setView(dialogLayout)
                .setPositiveButton("كرها", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        reset();
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("خلك هنا", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return myQuittingDialogBox.create();
    }
    private Dialog scoreDetails( int s1, int s2) {
        AlertDialog.Builder myQuittingDialogBox = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogLayout= inflater.inflate(R.layout.dialog_layout,null);
        teamOneDialog = dialogLayout.findViewById(R.id.teamOneScore);
        teamTwoDialog = dialogLayout.findViewById(R.id.teamTwoScore);
        teamOneDialog.setText(s1+"");
        teamTwoDialog.setText(s2+"");
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        myQuittingDialogBox.setView(dialogLayout)
                // Add action buttons
                .setPositiveButton("تمام", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        return myQuittingDialogBox.create();
    }

    private TextView teamOneText, teamTwoText;
    private EditText teamOneEdit, teamTwoEdit;
    private Button count;
    private ArrayList<Score> score = new ArrayList<>();
    ScoreArrayAdapter adapter;
    ListView listView;
    int teamOneScore = 0;
    int teamTwoScore = 0;
    String teamOneScoreString;
    String teamTwoScoreString;
    TextView teamOneDialog;
    TextView teamTwoDialog;
    TextView teamOneFinalDialog;
    TextView teamTwoFinalDialog;
    Button del;
    int tempOne;
    int tempTwo;
    ImageView roll;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        teamOneText = findViewById(R.id.teamOneText);
        teamTwoText = findViewById(R.id.teamTwoText);
        teamOneEdit = findViewById(R.id.teamOneEdit);
        teamTwoEdit = findViewById(R.id.teamTwoEdit);
        roll = findViewById(R.id.roll);
        roll.setImageResource(R.drawable.down);
        roll.setTag(R.drawable.down);
        teamOneScoreString = teamOneEdit.getText().toString();
        teamTwoScoreString = teamTwoEdit.getText().toString();
        count = findViewById(R.id.count);
        adapter = new ScoreArrayAdapter(this, score);
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                scoreDetails(score.get(position).getPrevTeamOneScore(),score.get(position).getPrevTeamTwoScore()).show();
            }
        });

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentId = getDrawableId(roll);
                switch (currentId) {
                    case R.drawable.up:
                        roll.setImageResource(R.drawable.left);
                        roll.setTag(R.drawable.left);
                        break;
                    case R.drawable.right:
                        roll.setImageResource(R.drawable.up);
                        roll.setTag(R.drawable.up);
                        break;
                    case R.drawable.down:
                        roll.setImageResource(R.drawable.right);
                        roll.setTag(R.drawable.right);
                        break;
                    case R.drawable.left:
                        roll.setImageResource(R.drawable.down);
                        roll.setTag(R.drawable.down);
                }
            }
        });
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty())
                    return;
                listView.setAdapter(adapter);
                if (isFinished())
                    AskOption(score.get(score.size()-1).getTeamOneScore(),score.get(score.size()-1).getTeamTwoScore()).show();
                teamOneEdit.setText("");
                teamTwoEdit.setText("");
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(count.getWindowToken(), 0);
            }
        });
    }

    private int getDrawableId(ImageView iv) {
        return (Integer) iv.getTag();
    }

    private void setRoll() {
        int currentId = getDrawableId(roll);
        switch (currentId) {
            case R.drawable.up:
                roll.setImageResource(R.drawable.left);
                roll.setTag(R.drawable.left);
                break;
            case R.drawable.right:
                roll.setImageResource(R.drawable.up);
                roll.setTag(R.drawable.up);
                break;
            case R.drawable.down:
                roll.setImageResource(R.drawable.right);
                roll.setTag(R.drawable.right);
                break;
            case R.drawable.left:
                roll.setImageResource(R.drawable.down);
                roll.setTag(R.drawable.down);
        }
    }

    private void reset() {
        adapter.clear();
        score.clear();
        teamOneEdit.setText("");
        teamTwoEdit.setText("");
        teamOneScore = 0;
        teamTwoScore = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.reset) {
            reset();
        }
        if (id == R.id.del) {
            if (!adapter.isEmpty()) {
                int position = listView.getLastVisiblePosition();
                Score s = adapter.getItem(position);
                adapter.remove(s);
                if (position == 0)
                    reset();
                if (!score.isEmpty()) {
                    teamOneScore = score.get(position - 1).getTeamOneScore();
                    teamTwoScore = score.get(position - 1).getTeamTwoScore();
                }
                listView.setAdapter(adapter);
            }
        }
        return true;
    }

    private boolean count() {
        tempOne = teamOneScore;
        tempTwo = teamTwoScore;
        teamOneScoreString = teamOneEdit.getText().toString();
        teamTwoScoreString = teamTwoEdit.getText().toString();

        if (!teamOneScoreString.equalsIgnoreCase(""))
            teamOneScore = Integer.parseInt(teamOneScoreString) + teamOneScore;


        if (!teamTwoScoreString.equalsIgnoreCase(""))
            teamTwoScore = Integer.parseInt(teamTwoScoreString) + teamTwoScore;

        if (teamOneScoreString.equalsIgnoreCase("") && teamTwoScoreString.equalsIgnoreCase(""))
            return false;

        if (teamOneScore <= 0 && teamTwoScore <= 0)
            return false;
        score.add(new Score(teamOneScore, teamTwoScore,Integer.parseInt(0+teamOneScoreString),Integer.parseInt(0+teamTwoScoreString)));
        setRoll();
        return true;
    }

    private boolean isEmpty() {
        if (count()) {
            Score currentScore = score.get(score.size() - 1);
            if (currentScore.getTotal() == 0)
                return true;
        }
        return false;
    }

    private boolean isFinished() {
        if (teamOneScore > 152 && teamTwoScore > 152) {
            if (teamOneScore > teamTwoScore || teamTwoScore > teamOneScore)
                return true;
            return false;
        }
        if (teamOneScore > 152 || teamTwoScore > 152)
            return true;
        else
            return false;
    }

}

