package com.example.mb_0417_Intro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.mb_0417_test.MyActivity;
import com.example.mb_0417_test.R;

public class Stage_1 extends Activity {

	Handler h;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // ��Ʈ��ȭ���̹Ƿ� Ÿ��Ʋ�ٸ� ���ش�
		setContentView(R.layout.stage_1);

		Manu aa = (Manu) Manu.ac;
		aa.finish();

		h = new Handler(); // �����̸� �ֱ� ���� �ڵ鷯 ����
		h.postDelayed(mrun, 3000); // ������ ( ����� ��ü�� mrun, �ð� 2��)

	}

	Runnable mrun = new Runnable() {
		@Override
		public void run() {
			Intent i = new Intent(Stage_1.this, MyActivity.class);
			startActivity(i);
			finish();
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			// overridePendingTransition �̶� �Լ��� �̿��Ͽ� fade in,out ȿ������. ������ �߿�
		}
	};

	// ��Ʈ�� �߿� �ڷΰ��⸦ ���� ��� �ڵ鷯�� ������� �ƹ��� ���� ����� �κ�
	// �� ������ ��Ʈ�� �� �ڷΰ��⸦ ������ ��Ʈ�� �Ŀ� Ȩȭ���� ����.
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		h.removeCallbacks(mrun);
	}

}
