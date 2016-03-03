package com.example.mb_0417_Intro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mb_0417_Game.GameState;
import com.example.mb_0417_test.R;

public class Rank extends Activity {
	public static ListView listView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 인트로화면이므로 타이틀바를 없앤다
		setContentView(R.layout.rank);
	
		findViewById(R.id.btnBack).setOnClickListener(ButtonClick);
		
		listView = (ListView) findViewById(R.id.listView);
		
		listView = EndGame.listView;
	}
	
	Button.OnClickListener ButtonClick = new Button.OnClickListener() {

		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.btnBack:
				Intent in = new Intent(Rank.this, EndGame.class); 
				startActivity(in);
				finish();
				overridePendingTransition(android.R.anim.fade_in,
						android.R.anim.fade_out);
				break;
			}
		}
	};
}
