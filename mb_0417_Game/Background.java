package com.example.mb_0417_Game;

import android.graphics.Canvas;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GraphicObject;
import com.example.mb_0417_test.R;

public class Background extends GraphicObject {

	int width, height;

	public Background() {
		super(AppManager.getInstance().getBitmap(R.drawable.jc_07));
		
	} 

	public void Draw(Canvas canvas) {
		canvas.drawBitmap(m_bitmap, m_x, m_y, null);
		// m_bitmap = Bitmap.createScaledBitmap(m_bitmap, GameView.width,
		// GameView.height, false);

	} 

	public int GetX(int x) {

		return width = x;

	}

	public int GetY(int y) {

		return height = y;

	}

}
