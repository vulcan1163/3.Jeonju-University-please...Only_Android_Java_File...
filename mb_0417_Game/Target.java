package com.example.mb_0417_Game;

import android.graphics.Bitmap;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.GraphicObject;


public class Target extends GraphicObject {

	public int speedX = 1, speedY = 1;

	public Target(Bitmap bitmap) {
		super(bitmap);

		AppManager.getInstance().setTarget(this);

	}

	public void Move() {
		m_x += (int) AppManager.getInstance().m_myactivity.ACCX * speedX * 2;
		m_y += (int) AppManager.getInstance().m_myactivity.ACCY * speedY * 2;

		if (m_y < 0) {
			m_y = 0;
		} else if (m_y > GameView.height - 100) {
			m_y = GameView.height - 100;
		} else if (m_x > GameView.width - 100) {
			m_x = GameView.width - 100;
		} else if (m_x < 0) {
			m_x = 0;
		}

	}

}
