package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.GraphicObject;
import com.example.mb_0417_test.MyActivity;
import com.example.mb_0417_test.R;
import com.example.mb_0417_test.SoundManager;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Vibrator;

public class Uni extends GraphicObject {

	public static int hp;
	
	Rect m_BoundBox = new Rect();

	public Uni() {
		super(AppManager.getInstance().getBitmap(R.drawable.uni_3));
		// TODO Auto-generated constructor stub
		super.SetPosition(GameView.width - 300, 0);
		m_BoundBox.set(m_x, m_y, m_x + 200, m_y + 800);
		hp = 20;

	}

	public void Draw(Canvas canvas) {
		canvas.drawBitmap(m_bitmap, m_x, m_y, null);
	}

	public void destroyEnemy() {

		AppManager.getInstance().m_state.m_uni.add(new Uni_Damage(AppManager.getInstance().m_state.uni.m_x - 70,
				AppManager.getInstance().m_state.uni.m_y));

		SoundManager.getInstance().play(10);
		
		MyActivity.vibrator.vibrate(300);
		
		hp--;

		if (hp <= 0) {
			AppManager.getInstance().m_myactivity.EndGame();
		}
	}

	public int getLife() {
		return hp;
	}

}
