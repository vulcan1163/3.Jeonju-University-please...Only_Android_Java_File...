package com.example.mb_0417_test;

import java.util.Random;

import com.example.mb_0417_Game.Background;
import com.example.mb_0417_Game.Effect_EX;
import com.example.mb_0417_Game.Effect_EX_2;
import com.example.mb_0417_Game.Effect_EX_3;
import com.example.mb_0417_Intro.EndGame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MyActivity extends Activity implements OnInitListener{

	private SensorManager sm;
	private Display mDisplay;
	private WindowManager mWin;
	private SensorEventListener accL;
	private Sensor accSensor;
	public float ACCX, ACCY;

	public static MediaPlayer player;
	
	private TextToSpeech myTTS;
	public static Vibrator vibrator;
	
	GameView GV;
	Animation animation1, animation2;
	Random rnd = new Random();
	Thread t;
	Button btn1;

	public ImageView img_1, img2, img_2, img1;

	public MyActivity() {

		AppManager.getInstance().setMyActivity(this);

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GV = new GameView(this, null);
		setContentView(R.layout.main);

		mWin = (WindowManager) getSystemService(WINDOW_SERVICE);
		mDisplay = mWin.getDefaultDisplay();

		startService(new Intent("com.Example.service.test"));
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		accL = new Listener();

		findViewById(R.id.ImageView05).setOnClickListener(ButtonClick);
		findViewById(R.id.weapon).setOnClickListener(ButtonClick);

		animation1 = AnimationUtils.loadAnimation(this, R.anim.mainframeanim);
		animation1.setDuration(500);
		animation1.setFillBefore(true);
		animation1.setFillAfter(true);
		animation1.setAnimationListener(test);

		animation2 = AnimationUtils.loadAnimation(this, R.anim.mainframeanim_2);
		animation2.setDuration(500);
		animation2.setFillBefore(true);
		animation2.setFillAfter(true);
		animation2.setAnimationListener(test);
		
		 myTTS = new TextToSpeech(this, this);
		 vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		 
		SoundManager.getInstance().addSound(3, R.raw.doublekill);
		SoundManager.getInstance().addSound(4, R.raw.tkill);
		SoundManager.getInstance().addSound(5, R.raw.qkill);
		SoundManager.getInstance().addSound(6, R.raw.pkill);
		SoundManager.getInstance().addSound(7, R.raw.rkill);
		SoundManager.getInstance().addSound(8, R.raw.hkill);
		SoundManager.getInstance().addSound(11, R.raw.bex);
		SoundManager.getInstance().addSound(12, R.raw.reload);
		SoundManager.getInstance().addSound(13, R.raw.gun_sc);

		img_1 = (ImageView) findViewById(R.id.counter01);

		img_2 = (ImageView) findViewById(R.id.bul);

		img2 = (ImageView) findViewById(R.id.weapon);

		img1 = (ImageView) findViewById(R.id.weapon_2);

		player = MediaPlayer.create(getBaseContext(), R.raw.stage_3);

		player.setVolume(0.4f, 0.4f);
		player.setLooping(true);
		player.start();

	}

	Button.OnClickListener ButtonClick = new Button.OnClickListener() {

		public void onClick(View view) {
			switch (view.getId()) {

			case R.id.ImageView05:

				if (AppManager.getInstance().m_state.bullet > 0) {

					AppManager.getInstance().m_state.checkEnemy(AppManager.getInstance().m_target.m_x,
							AppManager.getInstance().m_target.m_y);

					AppManager.getInstance().m_state.checkItem(AppManager.getInstance().m_target.m_x,
							AppManager.getInstance().m_target.m_y);

					if ((AppManager.getInstance().m_target.m_x < GameView.width / 3 * 2)
							&& (AppManager.getInstance().m_target.m_y) > GameView.height / 2 - 50) {
						AppManager.getInstance().m_state.ex.add(new Effect_EX(
								AppManager.getInstance().m_target.m_x + 10, AppManager.getInstance().m_target.m_y));// +15

						SoundManager.getInstance().play(1);
					}

					else if (AppManager.getInstance().m_target.m_x > GameView.width / 3 * 2 - 20) {
						AppManager.getInstance().m_state.ex3.add(new Effect_EX_3(AppManager.getInstance().m_target.m_x,
								AppManager.getInstance().m_target.m_y));

						SoundManager.getInstance().play(13);
					}

					else if ((AppManager.getInstance().m_target.m_x < GameView.width / 3 * 2 - 20)
							&& (AppManager.getInstance().m_target.m_y) < GameView.height / 2 - 50) {

						AppManager.getInstance().m_state.ex2.add(new Effect_EX_2(AppManager.getInstance().m_target.m_x,
								AppManager.getInstance().m_target.m_y));

						SoundManager.getInstance().play(1);
					}

					AppManager.getInstance().m_state.bullet--;
					test();

					if (AppManager.getInstance().m_state.bullet == 30)
						img_2.setImageResource(R.drawable.tan30);
					if (AppManager.getInstance().m_state.bullet == 29)
						img_2.setImageResource(R.drawable.tan29);
					if (AppManager.getInstance().m_state.bullet == 28)
						img_2.setImageResource(R.drawable.tan28);
					if (AppManager.getInstance().m_state.bullet == 27)
						img_2.setImageResource(R.drawable.tan27);
					if (AppManager.getInstance().m_state.bullet == 26)
						img_2.setImageResource(R.drawable.tan26);
					if (AppManager.getInstance().m_state.bullet == 25)
						img_2.setImageResource(R.drawable.tan25);
					if (AppManager.getInstance().m_state.bullet == 24)
						img_2.setImageResource(R.drawable.tan24);
					if (AppManager.getInstance().m_state.bullet == 23)
						img_2.setImageResource(R.drawable.tan23);
					if (AppManager.getInstance().m_state.bullet == 22)
						img_2.setImageResource(R.drawable.tan22);
					if (AppManager.getInstance().m_state.bullet == 21)
						img_2.setImageResource(R.drawable.tan21);
					if (AppManager.getInstance().m_state.bullet == 20)
						img_2.setImageResource(R.drawable.tan20);
					if (AppManager.getInstance().m_state.bullet == 19)
						img_2.setImageResource(R.drawable.tan19);
					if (AppManager.getInstance().m_state.bullet == 18)
						img_2.setImageResource(R.drawable.tan18);
					if (AppManager.getInstance().m_state.bullet == 17)
						img_2.setImageResource(R.drawable.tan17);
					if (AppManager.getInstance().m_state.bullet == 16)
						img_2.setImageResource(R.drawable.tan16);
					if (AppManager.getInstance().m_state.bullet == 15)
						img_2.setImageResource(R.drawable.tan15);
					if (AppManager.getInstance().m_state.bullet == 14)
						img_2.setImageResource(R.drawable.tan14);
					if (AppManager.getInstance().m_state.bullet == 13)
						img_2.setImageResource(R.drawable.tan13);
					if (AppManager.getInstance().m_state.bullet == 12)
						img_2.setImageResource(R.drawable.tan12);
					if (AppManager.getInstance().m_state.bullet == 11)
						img_2.setImageResource(R.drawable.tan11);
					if (AppManager.getInstance().m_state.bullet == 10)
						img_2.setImageResource(R.drawable.tan10);
					if (AppManager.getInstance().m_state.bullet == 9)
						img_2.setImageResource(R.drawable.tan9);
					if (AppManager.getInstance().m_state.bullet == 8)
						img_2.setImageResource(R.drawable.tan8);
					if (AppManager.getInstance().m_state.bullet == 7)
						img_2.setImageResource(R.drawable.tan7);
					if (AppManager.getInstance().m_state.bullet == 6)
						img_2.setImageResource(R.drawable.tan6);
					if (AppManager.getInstance().m_state.bullet == 5)
						img_2.setImageResource(R.drawable.tan5);
					if (AppManager.getInstance().m_state.bullet == 4)
						img_2.setImageResource(R.drawable.tan4);
					if (AppManager.getInstance().m_state.bullet == 3)
						img_2.setImageResource(R.drawable.tan3);
					if (AppManager.getInstance().m_state.bullet == 2)
						img_2.setImageResource(R.drawable.tan2);
					if (AppManager.getInstance().m_state.bullet == 1)
						img_2.setImageResource(R.drawable.tan1);
					if (AppManager.getInstance().m_state.bullet == 0)
						img_2.setImageResource(R.drawable.tan0);

				} else {
					SoundManager.getInstance().play(12);
					AppManager.getInstance().m_state.checkItem(AppManager.getInstance().m_target.m_x + 100,
							AppManager.getInstance().m_target.m_y + 100);
				}
				break;

			case R.id.weapon:

				AppManager.getInstance().m_state.combo = 0;
				AppManager.getInstance().m_state.makeSuppot();
				SoundManager.getInstance().play(11);
				img2.setVisibility(View.GONE);
				img1.setVisibility(View.GONE);

				break;

			}
		}
	};

	public void onInit(int status) {
		String myText1 = "게임 시작합니다!.";

		myTTS.speak(myText1, TextToSpeech.QUEUE_FLUSH, null);

	}


	public MyActivity(Background b) {

	}

	protected void onResume() {
		super.onResume();

		sm.registerListener(accL, accSensor, SensorManager.SENSOR_DELAY_FASTEST); // 占쏙옙占쌈듸옙

	}

	public void EndGame() {
		Intent in = new Intent(MyActivity.this, EndGame.class);

		startActivity(in);
		exit();

		AppManager.getInstance().m_gameview.m_Thread.setRunning(false);

		AppManager.getInstance().m_gameview.destroyDrawingCache();

		// SoundManager.getInstance().m_SoundPool.release();

		finish();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	}

	public void stage1_end() {
		Intent in = new Intent(MyActivity.this, EndGame.class);

		startActivity(in);
		exit();

		AppManager.getInstance().m_gameview.m_Thread.setRunning(false);

		AppManager.getInstance().m_gameview.destroyDrawingCache();

		finish();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (sm != null) {
			sm.unregisterListener(accL);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		exit();

		if (sm != null) {
			sm.unregisterListener(accL);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 2, 0, "게임옵션");
		menu.add(0, 6, 0, "게임종료");

		return true;
	} // end createoption item

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case 1:
			GV.StopGame();
			exit();
			finish();
			break;
		case 2:
			final int check = 0;
			final int check2 = 1;
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View viewInDialog = inflater.inflate(R.layout.seekbar, null);

			final String[] version = new String[] { "배경음악 ON", "배경음악 OFF" };
			AlertDialog.Builder dlg = new AlertDialog.Builder(MyActivity.this);
			dlg.setTitle("Option(게임설정)");
			dlg.setView(viewInDialog);
			dlg.setSingleChoiceItems(version, check, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

					if (which == 0) {
						startService(new Intent("com.Example.service.test_2"));

					} else if (which == 1) {
						exit();
					}

				}

			});

			dlg.setPositiveButton("확인", null);
			dlg.show();

			final SeekBar mSeekBar = (SeekBar) viewInDialog.findViewById(R.id.seekBar1);

			mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {

				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {

				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

					if (mSeekBar.getProgress() > 5) {
						AppManager.getInstance().m_target.speedX += progress;
						AppManager.getInstance().m_target.speedY += progress;
						AppManager.getInstance().m_state.Score += progress;
					}

					if (mSeekBar.getProgress() < 5) {
						AppManager.getInstance().m_target.speedX += progress - 4;
						AppManager.getInstance().m_target.speedY += progress - 4;
						AppManager.getInstance().m_state.Score -= progress * 5;
					}

				}
			});

			break;

		case 6: // Background
			AppManager.getInstance().m_myactivity.EndGame();

			break;
		}

		return true;
	} // end option item

	private class Listener implements SensorEventListener {

		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}

		public void onSensorChanged(SensorEvent event) {

			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

				ACCX = event.values[1];
				ACCY = event.values[0] - 6f;

			}

		}

	}

	public void exit() {

		stopService(new Intent("com.Example.service.test"));

	}

	public void test() {

		if (AppManager.getInstance().m_state.comboS_1) {

			SoundManager.getInstance().play(3);
			AppManager.getInstance().m_state.comboS_1 = false;

		} else if (AppManager.getInstance().m_state.comboS_2) {

			SoundManager.getInstance().play(4);
			AppManager.getInstance().m_state.comboS_2 = false;

		} else if (AppManager.getInstance().m_state.comboS_3) {

			SoundManager.getInstance().play(5);
			AppManager.getInstance().m_state.comboS_3 = false;

		} else if (AppManager.getInstance().m_state.comboS_4) {

			SoundManager.getInstance().play(6);
			AppManager.getInstance().m_state.comboS_4 = false;

		} else if (AppManager.getInstance().m_state.comboS_5) {

			SoundManager.getInstance().play(7);
			AppManager.getInstance().m_state.comboS_5 = false;

		} else if (AppManager.getInstance().m_state.comboS_6) {

			SoundManager.getInstance().play(8);
			AppManager.getInstance().m_state.comboS_6 = false;

		} else if (AppManager.getInstance().m_state.finalCombo) {

			img2.setImageResource(R.drawable.btn_boom);
			img2.setVisibility(View.VISIBLE);

			AppManager.getInstance().m_state.finalCombo = false;

		}

	}

	Animation.AnimationListener test = new Animation.AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationEnd(Animation animation) {

			img_1.setVisibility(View.GONE);

			img_1.clearAnimation();

		}
	};

}
