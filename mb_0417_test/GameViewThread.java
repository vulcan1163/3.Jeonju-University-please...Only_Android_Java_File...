package com.example.mb_0417_test;

import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.SurfaceHolder;

public class GameViewThread extends Thread {

	private SurfaceHolder m_holder;
	private GameView m_gameview;
	private boolean m_run = false;
	public boolean canRun = true;
	public boolean isWait = false;
	Handler handler = new Handler();
	

	
	public GameViewThread(SurfaceHolder surfaceHolder, GameView gameview) {
		m_holder = surfaceHolder;
		m_gameview = gameview;

	}

	public void StopThread() {
		canRun = false;
		synchronized (this) {
			this.notify();
		}
	}

	public void PauseNResume(boolean wait) {
		isWait = wait;
		synchronized (this) {
			this.notify();
		}
	}

	public void setRunning(boolean run) {
		m_run = run;
	}

	public void run() {
		Canvas canvas;

		while (m_run) {

			canvas = null;

			try {

				m_gameview.Update();

				canvas = m_holder.lockCanvas(null);
				synchronized (m_holder) {

					m_gameview.DrawChractor(canvas);

				}
			} finally {
				if (canvas != null)
					m_holder.unlockCanvasAndPost(canvas);
			}
		}

	}

}
