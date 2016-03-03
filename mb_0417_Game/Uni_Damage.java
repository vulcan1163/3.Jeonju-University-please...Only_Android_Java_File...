package com.example.mb_0417_Game;

import android.graphics.Bitmap;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.R;
import com.example.mb_0417_test.SpriteAnimation;

public class Uni_Damage extends SpriteAnimation {
	
	public Uni_Damage(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.uni_6));
//		m_bitmap = Bitmap.createScaledBitmap(m_bitmap, 1000, 720, false);
		this.InitSpriteDate(720, 500, 20, 2);
 
		m_x = x;
		m_y = y;
  
		mbReply = false;
  
	} 
 
	public void Update(long GameTime) {
		super.Update(GameTime);
	}
 
}
