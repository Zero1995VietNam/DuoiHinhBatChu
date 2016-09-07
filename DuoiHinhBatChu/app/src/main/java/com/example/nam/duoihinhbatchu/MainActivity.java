package com.example.nam.duoihinhbatchu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;
import static android.R.attr.tag;

public class MainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

    private Gamemanager gamemanager;
    private int is = 0;
    private TextView mTxtThongBao;
    private int mScore;
    private int mHeart;
    private TextView mTxtHeart;

    private StringBuilder da;

    private TextView mTxtSore;

    private Button mBtnCauTiep;
    private int buttonResult[] = {R.id.btn_result_1, R.id.btn_result_2, R.id.btn_result_3, R.id.btn_result_4, R.id.btn_result_5, R.id.btn_result_6, R.id.btn_result_7, R.id.btn_result_8,
            R.id.btn_result_9, R.id.btn_result_10, R.id.btn_result_11, R.id.btn_result_12, R.id.btn_result_13, R.id.btn_result_14, R.id.btn_result_15, R.id.btn_result_16};
    private int buttonSelect[] = {R.id.btn_select_1, R.id.btn_select_2, R.id.btn_select_3, R.id.btn_select_4, R.id.btn_select_5, R.id.btn_select_6, R.id.btn_select_7, R.id.btn_select_8, R.id.btn_select_9, R.id.btn_select_10,
            R.id.btn_select_11, R.id.btn_select_12, R.id.btn_select_13, R.id.btn_select_14, R.id.btn_select_15, R.id.btn_select_16};
    private ImageView mImageRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_activity);

        initview();
        initGame();
    }
    private void initview() {
        mImageRandom = (ImageView) findViewById(R.id.image_Random);

        mTxtHeart = (TextView) findViewById(R.id.txt_heart);
        mTxtThongBao = (TextView) findViewById(R.id.txtThongBao);
        mTxtSore = (TextView) findViewById(R.id.txt_score);

        mBtnCauTiep = (Button) findViewById(R.id.btnCauTiep);

        mHeart = Integer.parseInt(mTxtHeart.getText().toString());

        for (int id : buttonResult) {
            View v = (View) findViewById(id);
            v.setOnClickListener(this);
        }
        for (int id : buttonSelect) {
            View v = (View) findViewById(id);
            v.setOnClickListener(this);
        }
        mBtnCauTiep.setOnClickListener(this);
    }
    private void initGame() {
        gamemanager = new Gamemanager();
        gamemanager.randomImage();
        mImageRandom.setImageDrawable(getResources().getDrawable(getResourceID(gamemanager.getImage(), "drawable", getApplicationContext())));
        Log.i("MyTag", gamemanager.getCauTlDung().length() + "");
        for (int i = 0; i < gamemanager.getCauTlDung().length(); i++) {
            Button v = (Button) findViewById(buttonResult[i]);
            v.setText("");
            v.setVisibility(View.VISIBLE);
        }
        gamemanager.displaySelect();
        for (int i = 0; i < gamemanager.getLuaChon().length(); i++) {
            Button v = (Button) findViewById(buttonSelect[i]);
            v.setVisibility(View.VISIBLE);
            v.setText(gamemanager.getLuaChon().charAt(i) + "");
        }
    }
    @Override
    public void onClick(View v) {
        if (v.getTag().equals("2") == true) {
            for (int i = 0; i < buttonResult.length; i++) {
                Button b = (Button) findViewById(buttonResult[i]);
                if (((Button) b).getText().toString().equals("")) {
                    b.setText(((Button) v).getText().toString());
                    ((Button) v).setVisibility(View.INVISIBLE);
                    is++;
                    break;
                }
            }
            if (is == gamemanager.getCauTlDung().length()) {
                enableFalse();
                da = new StringBuilder("");
                for (int i = 0; i < buttonResult.length; i++) {
                    Button b = (Button) findViewById(buttonResult[i]);
                    da.append(b.getText().toString());
                }
                if ((gamemanager.getCauTlDung().toString()).equals(da.toString())==true) {
                    mTxtThongBao.setText("Ban da chien thang");
                    backgroundWin();
                    mScore += 100;
                    mTxtSore.setText(mScore + "");
                    mBtnCauTiep.setVisibility(View.VISIBLE);
                } else {
                    mTxtThongBao.setText("Ban da thua");
                    backgroundLose();
                    mHeart--;
                    mTxtHeart.setText(mHeart + "");
                }
            }
            if (mHeart == 0) {
                Toast.makeText(MainActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                enableFalse();
                return;
            }
        }
        if (v.getTag().equals("0") == true){
            resetGame();
            initGame();

        }
        if (v.getTag().equals("1") == true) {
            backgroundDefault();
            mTxtThongBao.setText("");
            enableTrue();
            for (int i : buttonSelect) {
                View b = (View) findViewById(i);
                if (((Button) v).getText().toString().equals(((Button) b).getText().toString()) && ((Button) b).getVisibility() == View.INVISIBLE) {
                    is--;
                    ((Button) v).setText("");
                    ((Button) b).setVisibility(View.VISIBLE);
                    break;
                }
            }
        }

    }

    private void resetGame() {
        gamemanager.setCauTlDung(null);
        enableTrue();
        is = 0;
        for (int i=0;i<buttonResult.length;i++) {
            Button b = (Button)findViewById(buttonResult[i]);
            b.setText("");
            b.setVisibility(View.GONE);
            b.setBackground(getResources().getDrawable(R.drawable.circle_grey));
        }
        da = new StringBuilder("");
        mBtnCauTiep.setVisibility(View.INVISIBLE);
        mTxtThongBao.setText("");
    }

    protected final static int getResourceID
            (final String resName, final String resType, final Context ctx) {
        final int ResourceID =
                ctx.getResources().getIdentifier(resName, resType,
                        ctx.getApplicationInfo().packageName);
        if (ResourceID == 0) {
            throw new IllegalArgumentException
                    (
                            "No resource string found with name " + resName
                    );
        } else {
            return ResourceID;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    private void enableTrue() {
        for (int i = 0; i < buttonSelect.length; i++) {
            Button b = (Button) findViewById(buttonSelect[i]);
            b.setEnabled(true);
        }
    }

    private void enableFalse() {
        for (int i = 0; i < buttonSelect.length; i++) {
            Button b = (Button) findViewById(buttonSelect[i]);
            b.setEnabled(false);
        }
    }

    private void backgroundWin(){
        for (int i : buttonResult) {
            View b1 = (View) findViewById(i);
            b1.setBackground(getResources().getDrawable(R.drawable.bg_win));
        }
    }
    private void backgroundLose(){
        for (int i : buttonResult) {
            View b1 = (View) findViewById(i);
            b1.setBackground(getResources().getDrawable(R.drawable.bg_lose));
        }
    }
    private void backgroundDefault(){
        for (int i : buttonResult) {
            View b1 = (View) findViewById(i);
            b1.setBackground(getResources().getDrawable(R.drawable.circle_grey));
        }
    }
}
