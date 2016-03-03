package com.example.mb_0417_test;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.mb_0417_Game.GameState;
import com.example.mb_0417_Game.Target;

public class AppManager {

	public GameView m_gameview;
	public Resources m_resources;
	public MyActivity m_myactivity;
	public GameState m_state;
	public Target m_target;

	public Bitmap getBitmap(int r) {
		return BitmapFactory.decodeResource(m_resources, r);
	}

	public void setTarget(Target tt) {
		m_target = tt;
	}

	public void setGameState(GameState gamestate) {
		m_state = gamestate;
	}

	public void setGameView(GameView gameview) {
		m_gameview = gameview;

	}

	public void setMyActivity(MyActivity tmp) {
		m_myactivity = tmp;
	}

	public void setResources(Resources resources) {
		m_resources = resources;
	}

	public GameView getGameView() {
		return m_gameview;
	}

	public Resources getResources() {
		return m_resources;
	}

	private static AppManager m_instance;

	public static AppManager getInstance() {

		if (m_instance == null) {
			m_instance = new AppManager();
		}
		return m_instance;
	}

}
