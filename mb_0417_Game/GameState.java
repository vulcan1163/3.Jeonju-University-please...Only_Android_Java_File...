package com.example.mb_0417_Game;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.mb_0417_Intro.EndGame;
import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.IState;
import com.example.mb_0417_test.MyDatabase;
import com.example.mb_0417_test.R;
import com.example.mb_0417_test.SoundManager;

public class GameState implements IState {

	public static int Score = 0;

	Background m_back;
	Target tt;
	Enemy_1 tmp;
	Enemy_2 tmp2;
	Enemy_3 tmp3;
	Missile tmp4;
	Random rnd;
	Uni uni;

	public int kill = 0;

	public ArrayList<Effect_EX> ex = new ArrayList<Effect_EX>();
	public ArrayList<Effect_EX_2> ex2 = new ArrayList<Effect_EX_2>();
	public ArrayList<Effect_EX_3> ex3 = new ArrayList<Effect_EX_3>();

	public ArrayList<Enemy_1_Dead> m_enemy_dead = new ArrayList<Enemy_1_Dead>();
	ArrayList<Enemy_1_Ex> m_enemy_ex = new ArrayList<Enemy_1_Ex>();
	ArrayList<Enemy_1> m_enemy = new ArrayList<Enemy_1>();
	ArrayList<Enemy_1_Dead_2> m_enemy_ex_2 = new ArrayList<Enemy_1_Dead_2>();

	public ArrayList<Enemy_2_Dead> m_enemy2_dead = new ArrayList<Enemy_2_Dead>();
	ArrayList<Enemy_2_Ex> m_enemy2_ex = new ArrayList<Enemy_2_Ex>();
	ArrayList<Enemy_2> m_enemy2 = new ArrayList<Enemy_2>();
	ArrayList<Enemy_2_Dead_2> m_enemy2_ex_2 = new ArrayList<Enemy_2_Dead_2>();

	public ArrayList<Enemy_3_Dead> m_enemy3_dead = new ArrayList<Enemy_3_Dead>();
	ArrayList<Enemy_3_Ex> m_enemy3_ex = new ArrayList<Enemy_3_Ex>();
	ArrayList<Enemy_3> m_enemy3 = new ArrayList<Enemy_3>();

	ArrayList<Missile> m_missile = new ArrayList<Missile>();
	ArrayList<Missile_Dead_2> m_missile_ex_2 = new ArrayList<Missile_Dead_2>();

	public ArrayList<Enemy_4_Dead> m_enemy4_dead = new ArrayList<Enemy_4_Dead>();
	ArrayList<Enemy_4> m_enemy4 = new ArrayList<Enemy_4>();
	
	ArrayList<Support> m_support = new ArrayList<Support>();
	ArrayList<Item_bul> m_item = new ArrayList<Item_bul>();
	ArrayList<Uni_Damage> m_uni = new ArrayList<Uni_Damage>();

	public static Bitmap hp[] = new Bitmap[11];
	public int k = 0;
	
	int V_x, V_y;
	int x_target, y_target;
	public int combo;
	public int bullet = 30;
	public int ii = 1;
	public int jj = 1;
	int counter = 0;
	long LastRegenEnemy;
	long LastDead;

	public int d1 = 0;
	public int d2 = 0;
	public int d3 = 0;

	public boolean comboS_1 = false;
	public boolean comboS_2 = false;
	public boolean comboS_3 = false;
	public boolean comboS_4 = false;
	public boolean comboS_5 = false;
	public boolean comboS_6 = false;
	public boolean finalCombo = false;

	boolean start = false;

	public int i = 0;
	
	public GameState() {
		rnd = new Random();
		V_x = GameView.width;
		V_y = GameView.height;
		AppManager.getInstance().setGameState(this);
		LastRegenEnemy = System.currentTimeMillis();

		SoundManager.getInstance().addSound(9, R.raw.enemy_dead);
		SoundManager.getInstance().addSound(10, R.raw.uni_ex);

	}

	public void Init() {

		m_back = new Background();
		// gun = new Gun(AppManager.getInstance().getBitmap(R.drawable.gun1));
		tt = new Target(AppManager.getInstance().getBitmap(R.drawable.cross_33));
		uni = new Uni();

		for (int i = 0; i <= 10; i++)
			hp[i] = AppManager.getInstance().getBitmap(R.drawable.hp_0 + i);

	}

	public void Destroy() {
	}

	public void Update() {

		long GameTime = System.currentTimeMillis();
		tt.Move();

		for (int i = ex.size() - 1; i >= 0; i--) {

			Effect_EX exp = ex.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				ex.remove(i);
				exp.m_bitmap.recycle();
			}
		}

		for (int i = ex2.size() - 1; i >= 0; i--) {

			Effect_EX_2 exp = ex2.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				ex2.remove(i);
				exp.m_bitmap.recycle();
			}
		}

		for (int i = ex3.size() - 1; i >= 0; i--) {

			Effect_EX_3 exp = ex3.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				ex3.remove(i);
				exp.m_bitmap.recycle();
			}
		}

		for (int i = m_uni.size() - 1; i >= 0; i--) {

			Uni_Damage exp = m_uni.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				m_uni.remove(i);
				exp.m_bitmap.recycle();
			}
		}

		for (int i = m_support.size() - 1; i >= 0; i--) {

			Support exp = m_support.get(i);
			exp.Update(GameTime);
			exp.move();
			if (exp.state == Enemy.STATE_OUT) {
				m_support.remove(i);
				exp.m_bitmap.recycle();
			}
		}

		for (int i = m_item.size() - 1; i >= 0; i--) {

			Item_bul exp = m_item.get(i);

			exp.Update(GameTime);
			exp.move();
			if (exp.state == Enemy.STATE_OUT) {
				m_item.remove(i);
			}
		}

		// zombie_1
		for (int i = m_enemy_ex.size() - 1; i >= 0; i--) {

			Enemy_1_Ex exp = m_enemy_ex.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				m_enemy_ex.remove(i);
				exp.m_bitmap.recycle();
			}
		}

		for (int i = m_enemy_ex_2.size() - 1; i >= 0; i--) {

			Enemy_1_Dead_2 exp = m_enemy_ex_2.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				m_enemy_ex_2.remove(i);
				exp.m_bitmap.recycle();
			}
		}

		for (int i = m_enemy_dead.size() - 1; i >= 0; i--) {

			Enemy_1_Dead exp = m_enemy_dead.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				m_enemy_dead.remove(i);
				exp.m_bitmap.recycle();
			}
		}

		for (int i = m_enemy.size() - 1; i >= 0; i--) {
			Enemy_1 tmp = m_enemy.get(i);
			tmp.Update(GameTime);
			tmp.move();
			if (tmp.state == Enemy.STATE_OUT) {
				m_enemy.remove(i);
			}
		}

		// zombie2
		for (int i = m_enemy2_ex.size() - 1; i >= 0; i--) {

			Enemy_2_Ex exp = m_enemy2_ex.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				m_enemy2_ex.remove(i);
				exp.m_bitmap.recycle();
			}

		}

		for (int i = m_enemy2_ex_2.size() - 1; i >= 0; i--) {

			Enemy_2_Dead_2 exp = m_enemy2_ex_2.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				m_enemy2_ex_2.remove(i);
				exp.m_bitmap.recycle();
			}

		}

		for (int i = m_enemy2_dead.size() - 1; i >= 0; i--) {

			Enemy_2_Dead exp = m_enemy2_dead.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				m_enemy2_dead.remove(i);
				exp.m_bitmap.recycle();
			}
		}

		for (int i = m_enemy2.size() - 1; i >= 0; i--) {
			Enemy_2 tmp2 = m_enemy2.get(i);
			tmp2.Update(GameTime);
			tmp2.move();
			if (tmp2.state == Enemy.STATE_OUT) {
				m_enemy2.remove(i);

			}
		}

		// zombie3
		for (int i = m_enemy3_ex.size() - 1; i >= 0; i--) {

			Enemy_3_Ex exp = m_enemy3_ex.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				m_enemy3_ex.remove(i);
				exp.m_bitmap.recycle();
			}

		}

		for (int i = m_enemy3_dead.size() - 1; i >= 0; i--) {

			Enemy_3_Dead exp = m_enemy3_dead.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				m_enemy3_dead.remove(i);
				exp.m_bitmap.recycle();
			}
		}

		for (int i = m_enemy3.size() - 1; i >= 0; i--) {
			Enemy_3 tmp3 = m_enemy3.get(i);
			tmp3.Update(GameTime);
			tmp3.move();
			if (tmp3.state == Enemy.STATE_OUT) {
				m_enemy3.remove(i);

			}
		}

		// missile
		for (int i = m_missile_ex_2.size() - 1; i >= 0; i--) {

			Missile_Dead_2 exp = m_missile_ex_2.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				m_missile_ex_2.remove(i);
				exp.m_bitmap.recycle();
			}
		}

		for (int i = m_missile.size() - 1; i >= 0; i--) {
			Missile tmp = m_missile.get(i);
			tmp.Update(GameTime);
			tmp.move();
			if (tmp.state == Enemy.STATE_OUT) {
				m_missile.remove(i);
			}
		}

		//zombie4
		for (int i = m_enemy4_dead.size() - 1; i >= 0; i--) {

			Enemy_4_Dead exp = m_enemy4_dead.get(i);
			exp.Update(GameTime);

			if (exp.getAnimationEnd()) {
				m_enemy4_dead.remove(i);
				exp.m_bitmap.recycle();
			}
		}

		for (int i = m_enemy4.size() - 1; i >= 0; i--) {
			Enemy_4 tmp2 = m_enemy4.get(i);
			tmp2.Update(GameTime);
			tmp2.move();
			if (tmp2.state == Enemy.STATE_OUT) {
				m_enemy4.remove(i);

			}
		}

		

		makeEnemy();
		checkItem();

		if (combo >= 210) {
			combo = 210;
		}

		if (start) {

			if (System.currentTimeMillis() - LastDead >= 3000) {
				combo = 0;
				start = false;
			}
		}

		checkCollision();

	}

	public void Render(Canvas canvas) {

		m_back.Draw(canvas);
		uni.Draw(canvas);
		// gun.Draw(canvas);

		for (int i = ex.size() - 1; i >= 0; i--) {
			Effect_EX tmp = ex.get(i);
			tmp.Draw(canvas);
		}

		for (int i = ex2.size() - 1; i >= 0; i--) {
			Effect_EX_2 tmp = ex2.get(i);
			tmp.Draw(canvas);

		}

		for (int i = ex3.size() - 1; i >= 0; i--) {
			Effect_EX_3 tmp = ex3.get(i);
			tmp.Draw(canvas);

		}

		for (int i = m_uni.size() - 1; i >= 0; i--) {
			Uni_Damage tmp = m_uni.get(i);
			tmp.Draw(canvas);
		}

		// zombie1
		for (int i = m_enemy_dead.size() - 1; i >= 0; i--) {
			Enemy_1_Dead tmp = m_enemy_dead.get(i);
			tmp.Draw(canvas);
		}

		for (int i = m_enemy_ex.size() - 1; i >= 0; i--) {
			Enemy_1_Ex tmp = m_enemy_ex.get(i);
			tmp.Draw(canvas);
		}

		for (int i = m_enemy_ex_2.size() - 1; i >= 0; i--) {
			Enemy_1_Dead_2 tmp = m_enemy_ex_2.get(i);
			tmp.Draw(canvas);
		}

		for (int i = m_enemy.size() - 1; i >= 0; i--) {
			Enemy_1 tmp = m_enemy.get(i);
			tmp.Draw(canvas);
		}

		// zombie2
		for (int i = m_enemy2_dead.size() - 1; i >= 0; i--) {
			Enemy_2_Dead tmp2 = m_enemy2_dead.get(i);
			tmp2.Draw(canvas);
		}

		for (int i = m_enemy2_ex.size() - 1; i >= 0; i--) {
			Enemy_2_Ex tmp2 = m_enemy2_ex.get(i);
			tmp2.Draw(canvas);
		}

		for (int i = m_enemy2_ex_2.size() - 1; i >= 0; i--) {
			Enemy_2_Dead_2 tmp2 = m_enemy2_ex_2.get(i);
			tmp2.Draw(canvas);
		}

		for (int i = m_enemy2.size() - 1; i >= 0; i--) {
			Enemy_2 tmp2 = m_enemy2.get(i);
			tmp2.Draw(canvas);
		}

		// zombie3
		for (int i = m_enemy3_dead.size() - 1; i >= 0; i--) {
			Enemy_3_Dead tmp3 = m_enemy3_dead.get(i);
			tmp3.Draw(canvas);
		}

		for (int i = m_enemy3_ex.size() - 1; i >= 0; i--) {
			Enemy_3_Ex tmp3 = m_enemy3_ex.get(i);
			tmp3.Draw(canvas);
		}

		for (int i = m_enemy3.size() - 1; i >= 0; i--) {
			Enemy_3 tmp3 = m_enemy3.get(i);
			tmp3.Draw(canvas);
		}

		// missile
		for (int i = m_missile_ex_2.size() - 1; i >= 0; i--) {
			Missile_Dead_2 tmp4 = m_missile_ex_2.get(i);
			tmp4.Draw(canvas);
		}

		for (int i = m_missile.size() - 1; i >= 0; i--) {
			Missile tmp4 = m_missile.get(i);
			tmp4.Draw(canvas);
		}
		
		// zombie4
		for (int i = m_enemy4_dead.size() - 1; i >= 0; i--) {
			Enemy_4_Dead tmp2 = m_enemy4_dead.get(i);
			tmp2.Draw(canvas);
		}
		for (int i = m_enemy4.size() - 1; i >= 0; i--) {
			Enemy_4 tmp2 = m_enemy4.get(i);
			tmp2.Draw(canvas);
		}
		
		//
		
		tt.Draw(canvas);
		
		for (int i = m_support.size() - 1; i >= 0; i--) {
			Support tmp = m_support.get(i);
			tmp.Draw(canvas);

		}

		for (int i = m_item.size() - 1; i >= 0; i--) {
			Item_bul tmp = m_item.get(i);
			tmp.Draw(canvas);

		}

		Paint p = new Paint();
		p.setTextSize(45);

		p.setColor(Color.WHITE);
		canvas.drawText("SCORE : " + String.valueOf(Score), 200, 70, p);

		k = Uni.hp / 2;
		canvas.drawBitmap(hp[k], 740, 10, p);

//		canvas.drawText("HP : " + String.valueOf(Uni.hp), 800, 70, p);

		p.setColor(Color.RED);
 
		if (combo >= 60)
			p.setColor(Color.BLUE); 
		if (combo >= 100)
			p.setColor(Color.CYAN);
		if (combo >= 160)
			p.setColor(Color.GREEN); 
		if (combo >= 200)
			p.setColor(Color.WHITE);

		Rect rect = new Rect();
		p.setTextSize(50);

		rect.set(30, 100, combo + 100, 155);
		canvas.drawRect(rect, p);
	}

	public boolean onKeyDown(int keycode, KeyEvent event) {

		return false;
	}

	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			int x = (int) event.getX();
			int y = (int) event.getY();

			/*
			 * switch(counter){ case 1: gun = new
			 * Gun(AppManager.getInstance().getBitmap(R.drawable.gun2));
			 * SoundManager.getInstance().play(1); ex.add(new Effect_EX(x, y));
			 * break;
			 * 
			 * case 2: gun = new
			 * Gun(AppManager.getInstance().getBitmap(R.drawable.gun3));
			 * SoundManager.getInstance().play(1); ex.add(new Effect_EX(x, y));
			 * break;
			 * 
			 * case 3: gun = new
			 * Gun(AppManager.getInstance().getBitmap(R.drawable.gun4));
			 * SoundManager.getInstance().play(1); ex.add(new Effect_EX(x, y));
			 * break;
			 * 
			 * case 4: gun = new
			 * Gun(AppManager.getInstance().getBitmap(R.drawable.gun5));
			 * SoundManager.getInstance().play(1); ex.add(new Effect_EX(x, y));
			 * break;
			 * 
			 * case 5: gun = new
			 * Gun(AppManager.getInstance().getBitmap(R.drawable.gun6));
			 * SoundManager.getInstance().play(1); ex.add(new Effect_EX(x, y));
			 * break;
			 * 
			 * case 6: gun = new
			 * Gun(AppManager.getInstance().getBitmap(R.drawable.gun7));
			 * SoundManager.getInstance().play(1); ex.add(new Effect_EX(x, y));
			 * break;
			 * 
			 * case 7: gun = new
			 * Gun(AppManager.getInstance().getBitmap(R.drawable.gun1));
			 * SoundManager.getInstance().play(2); counter=0; break; }
			 */
		}
		return false;
	}

	public void makeEnemy() {

		Random rnd = new Random();
		i = rnd.nextInt(3) + 1;

		
		if (m_enemy.size() >= 10)
			return;

		if (System.currentTimeMillis() - LastRegenEnemy >= 1000) {
			LastRegenEnemy = System.currentTimeMillis();

			m_enemy.add(new Enemy_1(0, rnd.nextInt(V_y - 445) + 350, i));

			ii++;
			jj++;

			if (ii % 5 == 0)
				m_enemy2.add(new Enemy_2(0, rnd.nextInt(V_y - 455) + 350));

			if(ii % 8 == 0)
				m_enemy4.add(new Enemy_4(0, rnd.nextInt(V_y - 455) + 350));
				
			if (jj % 20 == 0 && m_enemy3.size() < 1)
				m_enemy3.add(new Enemy_3(0, 425));
		
			if (m_enemy3.size() >= 1 && m_missile.size() < 1)
				m_missile.add(new Missile(50, 473));
		}
	}

	public void makeSuppot() {

		boolean start = true;

		while (start) {

			if (m_support.size() > 3)
				start = false;

			Random rnd = new Random();
			int rnds = rnd.nextInt(GameView.height - 450) + 350;
			// x_target = AppManager.getInstance().m_target.m_x;

			m_support.add(new Support(rnd.nextInt(V_y), 0, rnds));

		}
	}

	public void checkEnemy(int x, int y) {
		boolean flag = false;

		// zombie_1
		for (int i = m_enemy.size() - 1; i >= 0; i--) {

			if (Math.pow(m_enemy.get(i).m_x - x, 2) + Math.pow(m_enemy.get(i).m_y - y, 2) <= Math
					.pow(m_enemy.get(i).m_bitmap.getWidth() / 9, 2)) {

				m_enemy.get(i).destroyEnemy();

				if (m_enemy.get(i).getLife() == 0) {

					m_enemy.get(i).state = m_enemy.get(i).STATE_OUT;

					m_enemy_dead.add(new Enemy_1_Dead(m_enemy.get(i).m_x, m_enemy.get(i).m_y));

					d1++;
					if (d1 % 20 == 0 && Uni.hp < 20)
						Uni.hp++;

					Score += 100;
					combo += 10;

					kill++;
					if (kill > 7)
						kill = 0;

					CheckCombo();
					SoundManager.getInstance().play(9);

					if (combo == 20)
						comboS_1 = true;

					if (combo == 30)
						comboS_2 = true;

					if (combo == 40)
						comboS_3 = true;

					if (combo == 50)
						comboS_4 = true;

					if (combo == 60)
						comboS_5 = true;

					if (combo == 70)
						comboS_6 = true;

					if (combo == 200)
						finalCombo = true;

				}
			}
		}

		// zombie_2
		for (int i = m_enemy2.size() - 1; i >= 0; i--) {

			if (Math.pow(m_enemy2.get(i).m_x - x, 2) + Math.pow(m_enemy2.get(i).m_y - y, 2) <= Math
					.pow(m_enemy2.get(i).m_bitmap.getWidth() / 8, 2)) {

				m_enemy2.get(i).destroyEnemy();

				if (m_enemy2.get(i).getLife() == 0) {

					ii = 0;

					m_enemy2.get(i).state = m_enemy2.get(i).STATE_OUT;

					m_enemy2_dead.add(new Enemy_2_Dead(m_enemy2.get(i).m_x, m_enemy2.get(i).m_y));

					bullet = 30;
					d1++;
					if (d1 % 20 == 0 && Uni.hp < 20)
						Uni.hp++;

					Score += 200;
					combo += 10;

					kill++;
					if (kill > 7)
						kill = 0;

					CheckCombo();
					SoundManager.getInstance().play(9);

					if (combo == 20)
						comboS_1 = true;

					if (combo == 30)
						comboS_2 = true;

					if (combo == 40)
						comboS_3 = true;

					if (combo == 50)
						comboS_4 = true;

					if (combo == 60)
						comboS_5 = true;

					if (combo == 70)
						comboS_6 = true;

					if (combo == 200)
						finalCombo = true;

				}
			}
		}

		// zombie_3
		for (int i = m_enemy3.size() - 1; i >= 0; i--) {

			if (Math.pow(m_enemy3.get(i).m_x - x, 2) + Math.pow(m_enemy3.get(i).m_y - y, 2) <= Math
					.pow(m_enemy3.get(i).m_bitmap.getWidth() / 4, 2)) {

				m_enemy3.get(i).destroyEnemy();

				if (m_enemy3.get(i).getLife() == 0) {

					jj = 0;

					m_enemy3.get(i).state = m_enemy3.get(i).STATE_OUT;

					m_enemy3_dead.add(new Enemy_3_Dead(m_enemy3.get(i).m_x, m_enemy3.get(i).m_y));

					if (Uni.hp < 20)
						Uni.hp++;

					Score += 1000;
					combo += 10;

					kill++;
					if (kill > 7)
						kill = 0;

					CheckCombo();
					SoundManager.getInstance().play(9);

					if (combo == 20)
						comboS_1 = true;

					if (combo == 30)
						comboS_2 = true;

					if (combo == 40)
						comboS_3 = true;

					if (combo == 50)
						comboS_4 = true;

					if (combo == 60)
						comboS_5 = true;

					if (combo == 70)
						comboS_6 = true;

					if (combo == 200)
						finalCombo = true;

				}
			}
		}

		// Missile
		for (int i = m_missile.size() - 1; i >= 0; i--) {

			if (Math.pow(m_missile.get(i).m_x - x, 2) + Math.pow(m_missile.get(i).m_y - y, 2) <= Math
					.pow(m_missile.get(i).m_bitmap.getWidth() / 16, 2)) {

				m_missile.get(i).destroyEnemy();

				if (m_missile.get(i).getLife() == 0) {

					jj = 0;

					m_missile.get(i).state = m_missile.get(i).STATE_OUT;

					m_missile_ex_2.add(new Missile_Dead_2(m_missile.get(i).m_x, m_missile.get(i).m_y));
				}
			}
		}
		
		// zombie_4
		for (int i = m_enemy4.size() - 1; i >= 0; i--) {

			if (Math.pow(m_enemy4.get(i).m_x - x, 2) + Math.pow(m_enemy4.get(i).m_y - y, 2) <= Math
					.pow(m_enemy4.get(i).m_bitmap.getWidth() / 9, 2)) {

				m_enemy4.get(i).destroyEnemy();

				if (m_enemy4.get(i).getLife() == 0) {

					ii = 0;

					m_enemy4.get(i).state = m_enemy4.get(i).STATE_OUT;

					m_enemy4_dead.add(new Enemy_4_Dead(m_enemy4.get(i).m_x, m_enemy4.get(i).m_y));

					bullet = 30;
					d1++;
					if (d1 % 20 == 0 && Uni.hp < 20)
						Uni.hp++;

					Score += 200;
					combo += 10;

					kill++;
					if (kill > 7)
						kill = 0;

					CheckCombo();
					SoundManager.getInstance().play(9);

					if (combo == 20)
						comboS_1 = true;

					if (combo == 30)
						comboS_2 = true;

					if (combo == 40)
						comboS_3 = true;

					if (combo == 50)
						comboS_4 = true;

					if (combo == 60)
						comboS_5 = true;

					if (combo == 70)
						comboS_6 = true;

					if (combo == 200)
						finalCombo = true;

				}
			}
		}
	}

	public void checkItem(int x, int y) {

		for (int i = m_item.size() - 1; i >= 0; i--) {

			if (Math.pow(m_item.get(i).m_x - x, 2) + Math.pow(m_item.get(i).m_y - y, 2) <= Math
					.pow(m_item.get(i).m_bitmap.getWidth() / 6, 2)) {

				m_item.get(i).state = m_item.get(i).STATE_OUT;

				bullet = 30;
			}

		}

	}

	public void CheckCombo() {
		LastDead = System.currentTimeMillis();
		start = true;

	}

	public void checkItem() {

		if (m_item.size() > 1)
			return;

		m_item.add(new Item_bul(rnd.nextInt(V_y), 0));

	}

	public void checkCollision() {

		// zombie1
		for (int i = m_enemy.size() - 1; i >= 0; i--) {

			if (CollisionManager.CheckBoxToBox(uni.m_BoundBox, m_enemy.get(i).m_BoundBox)) {

				m_enemy.get(i).state = m_enemy.get(i).STATE_OUT;
				m_enemy_ex_2.add(new Enemy_1_Dead_2(m_enemy.get(i).m_x, m_enemy.get(i).m_y));
				uni.destroyEnemy();

			}
		}

		for (int i = m_support.size() - 1; i >= 0; i--) {
			for (int j = m_enemy.size() - 1; j >= 0; j--) {
				if (CollisionManager.CheckBoxToBox(m_support.get(i).m_BoundBox, m_enemy.get(j).m_BoundBox)) {

					m_enemy.get(j).state = m_enemy.get(j).STATE_OUT;
					m_enemy_ex_2.add(new Enemy_1_Dead_2(m_enemy.get(j).m_x, m_enemy.get(j).m_y));

					d1++;
					if (d1 % 20 == 0 && Uni.hp < 20)
						Uni.hp++;

					Score += 100;

				}
			}
		}

		for (int i = m_enemy_ex.size() - 1; i >= 0; i--) {
			for (int j = m_enemy.size() - 1; j >= 0; j--) {

				if (CollisionManager.CheckBoxToBox(m_enemy_ex.get(i).m_BoundBox, m_enemy.get(j).m_BoundBox)) {
					m_enemy.get(j).state = m_enemy.get(j).STATE_OUT;
					m_enemy_ex_2.add(new Enemy_1_Dead_2(m_enemy.get(j).m_x, m_enemy.get(j).m_y));

					d1++;
					if (d1 % 20 == 0 && Uni.hp < 20)
						Uni.hp++;

					Score += 100;

				}
			}
		}

		// zombie2
		for (int i = m_enemy2.size() - 1; i >= 0; i--) {

			if (CollisionManager.CheckBoxToBox(uni.m_BoundBox, m_enemy2.get(i).m_BoundBox)) {

				m_enemy2.get(i).state = m_enemy2.get(i).STATE_OUT;
				m_enemy2_ex_2.add(new Enemy_2_Dead_2(m_enemy2.get(i).m_x, m_enemy2.get(i).m_y));
				uni.destroyEnemy();

			}
		}

		for (int i = m_support.size() - 1; i >= 0; i--) {
			for (int j = m_enemy2.size() - 1; j >= 0; j--) {
				if (CollisionManager.CheckBoxToBox(m_support.get(i).m_BoundBox, m_enemy2.get(j).m_BoundBox)) {
					m_enemy2.get(j).state = m_enemy2.get(j).STATE_OUT;
					m_enemy2_ex_2.add(new Enemy_2_Dead_2(m_enemy2.get(j).m_x, m_enemy2.get(j).m_y));

					bullet = 30;
					d1++;
					if (d1 % 20 == 0 && Uni.hp < 20)
						Uni.hp++;

					Score += 200;

				}
			}
		}

		for (int i = m_enemy2_ex.size() - 1; i >= 0; i--) {
			for (int j = m_enemy2.size() - 1; j >= 0; j--) {

				if (CollisionManager.CheckBoxToBox(m_enemy2_ex.get(i).m_BoundBox, m_enemy2.get(j).m_BoundBox)) {
					m_enemy2.get(j).state = m_enemy2.get(j).STATE_OUT;
					m_enemy2_ex_2.add(new Enemy_2_Dead_2(m_enemy2.get(j).m_x, m_enemy2.get(j).m_y));

					bullet = 30;
					d1++;
					if (d1 % 20 == 0 && Uni.hp < 20)
						Uni.hp++;

					Score += 200;

				}
			}
		}

		// missile
		for (int i = m_missile.size() - 1; i >= 0; i--) {

			if (CollisionManager.CheckBoxToBox(uni.m_BoundBox, m_missile.get(i).m_BoundBox)) {

				m_missile.get(i).state = m_missile.get(i).STATE_OUT;
				m_missile_ex_2.add(new Missile_Dead_2(m_missile.get(i).m_x, m_missile.get(i).m_y));
				uni.destroyEnemy();
			}
		}

		
		// zombie4
		for (int i = m_enemy4.size() - 1; i >= 0; i--) {

			if (CollisionManager.CheckBoxToBox(uni.m_BoundBox, m_enemy4.get(i).m_BoundBox)) {

				m_enemy4.get(i).state = m_enemy4.get(i).STATE_OUT;
				m_enemy2_ex_2.add(new Enemy_2_Dead_2(m_enemy4.get(i).m_x, m_enemy4.get(i).m_y));
				uni.destroyEnemy();

			}
		}

		for (int i = m_support.size() - 1; i >= 0; i--) {
			for (int j = m_enemy4.size() - 1; j >= 0; j--) {
				if (CollisionManager.CheckBoxToBox(m_support.get(i).m_BoundBox, m_enemy4.get(j).m_BoundBox)) {
					m_enemy4.get(j).state = m_enemy4.get(j).STATE_OUT;
					m_enemy2_ex_2.add(new Enemy_2_Dead_2(m_enemy4.get(j).m_x, m_enemy4.get(j).m_y));

					bullet = 30;
					d1++;
					if (d1 % 20 == 0 && Uni.hp < 20)
						Uni.hp++;

					Score += 200;

				}
			}
		}

		for (int i = m_enemy2_ex.size() - 1; i >= 0; i--) {
			for (int j = m_enemy4.size() - 1; j >= 0; j--) {

				if (CollisionManager.CheckBoxToBox(m_enemy2_ex.get(i).m_BoundBox, m_enemy4.get(j).m_BoundBox)) {
					m_enemy4.get(j).state = m_enemy4.get(j).STATE_OUT;
					m_enemy2_ex_2.add(new Enemy_2_Dead_2(m_enemy2.get(j).m_x, m_enemy4.get(j).m_y));

					bullet = 30;
					d1++;
					if (d1 % 20 == 0 && Uni.hp < 20)
						Uni.hp++;

					Score += 200;

				}
			}
		}
		
		for (int i = m_support.size() - 1; i >= 0; i--) {
			for (int j = m_missile.size() - 1; j >= 0; j--) {
				if (CollisionManager.CheckBoxToBox(m_support.get(i).m_BoundBox, m_missile.get(j).m_BoundBox)) {

					m_missile.get(j).state = m_missile.get(j).STATE_OUT;
					m_missile_ex_2.add(new Missile_Dead_2(m_missile.get(j).m_x, m_missile.get(j).m_y));
				}
			}
		}
		//
	}

	public int getScore() {
		return Score;
	}

}
