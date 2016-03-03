

package com.example.mb_0417_Game;

import android.graphics.Rect;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.R;
import com.example.mb_0417_test.SpriteAnimation;

public class Item_bul extends SpriteAnimation {

	public static final int STATE_NORMAL = 0;
	public static final int STATE_OUT = 1;
	public int state = STATE_NORMAL;

	Rect m_BoundBox = new Rect();

	public Item_bul(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.bul_2));
		super.InitSpriteDate(50, 75, 40, 6);

		m_x = x;
		m_y = y;
	}

	public void Update(long GameTime) {
		super.Update(GameTime);

		m_BoundBox.set(m_x, m_y, m_x + 130, m_y + 130);
	}

	public void move() {

		m_y += 3;

		if (m_y > GameView.height) {
			state = STATE_OUT;
			m_bitmap.recycle();
			m_bitmap = null;
		}
	}

}
