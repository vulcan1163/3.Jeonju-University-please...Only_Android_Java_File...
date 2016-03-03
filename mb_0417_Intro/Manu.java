package com.example.mb_0417_Intro;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mb_0417_Game.GameState;
import com.example.mb_0417_test.R;

public class Manu extends Activity {

	public static Activity ac;
	ImageView image1, image2, image3;
	Animation animation1, animation2;
	private int count = 0;
	private int i = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 인트로화면이므로 타이틀바를 없앤다
		setContentView(R.layout.manu);

		GameState.Score = 0;

		animation1 = AnimationUtils.loadAnimation(this, R.anim.frame_animation);
 
		animation1.setDuration(1000);
		animation1.setFillBefore(true);
		animation1.setFillAfter(true);

		animation2 = AnimationUtils.loadAnimation(this,
				R.anim.frame_animation_2);

		animation2.setDuration(1000);
		animation2.setFillBefore(true);
		animation2.setFillAfter(true);

		findViewById(R.id.ImageView01).setOnClickListener(ButtonClick);
		findViewById(R.id.ImageView02).setOnClickListener(ButtonClick);
		findViewById(R.id.ImageView03).setOnClickListener(ButtonClick);
		findViewById(R.id.ImageView04).setOnClickListener(ButtonClick);
		startService(new Intent("com.Example.service.test_2"));
		ac = Manu.this;
		h.sendEmptyMessage(0);

	}

	Handler h = new Handler() {

		public void handleMessage(Message msg) {

			if (i == 2) {
				i = 0;
				h.sendEmptyMessage(0);
			}

			else if (i == 0) {

				image1 = (ImageView) findViewById(R.id.image01);
				image1.setImageResource(R.drawable.back_final_3);
				image1.startAnimation(animation1); 

				h.sendEmptyMessageDelayed(0, 1000); // ms단위 딜레이 함수

				i++; 
 
			} else if (i == 1) {

				image2 = (ImageView) findViewById(R.id.image01);
				image2.setImageResource(R.drawable.back_final_3);
				image2.startAnimation(animation2);

				h.sendEmptyMessageDelayed(0, 1000); // ms단위 딜레이 함수

				i++;

			}

		}
	};

	Button.OnClickListener ButtonClick = new Button.OnClickListener() {

		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.ImageView01:
				startActivity(new Intent(Manu.this, Stage_1.class));
				exit();
				finish();
				overridePendingTransition(android.R.anim.fade_in,
						android.R.anim.fade_out);
				break;

			case R.id.ImageView02:
				final int check = 0;
				final int check2 = 1;

				final String[] version = new String[] { "배경음악 ON", "배경음악 OFF" };
				AlertDialog.Builder dlg = new AlertDialog.Builder(Manu.this);
				dlg.setTitle("Option(게임설정)");
				dlg.setSingleChoiceItems(version, check,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								if (which == 0) {
									startService(new Intent(
											"com.Example.service.test_2"));

								} else if (which == 1) {
									exit();
								}

							}

						});
				dlg.setPositiveButton("확인", null);
				dlg.show();
				break;

			case R.id.ImageView03:

				AlertDialog.Builder dlg2 = new AlertDialog.Builder(Manu.this);
				dlg2.setTitle("HELP(게임방법)");
				dlg2.setMessage("이 게임은 외계에서 전주대학교를 침공한 녀석들을 물리치는 게임 입니다. Accelerometer 센서를 이용하여 적을 조준 한다음 버튼을 눌러 적을 소탕하세요. 콤보게이지를 잘 이용한다면 전투를 승리로 이끌수 있습니다.");
				dlg2.setPositiveButton("확인", null);
				dlg2.show();
				break;

			case R.id.ImageView04:
				exit();
				finish();
				break;

			}
		}
	};

	protected void onDestroy() {
		super.onDestroy();
		exit();

	}

	public void exit() {

		stopService(new Intent("com.Example.service.test_2")); // 종료되면 배경음악이
																// 꺼진다.

	}

}
