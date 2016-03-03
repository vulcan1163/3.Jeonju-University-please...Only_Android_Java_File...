package com.example.mb_0417_Intro;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.mb_0417_test.R;

public class SecLoading extends Activity {
	VideoView videoView;
	  Handler h;//�ڵ鷯 ����
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE); //��Ʈ��ȭ���̹Ƿ� Ÿ��Ʋ�ٸ� ���ش�
	        setContentView(R.layout.secloading);
	        
	        
	        h= new Handler(); //�����̸� �ֱ� ���� �ڵ鷯 ����
	        h.postDelayed(mrun, 11000); // ������ ( ����� ��ü�� mrun, �ð� 2��)
	        
	        
	       videoView = (VideoView)findViewById(R.id.videos);

	       Uri video = Uri.parse("android.resource://com.example.mb_0417_test/"+ R.raw.video_9);
	       videoView.setVideoURI(video);

	       
	       videoView.requestFocus();
	        
	       videoView.start();
	  
	    }

	     
	    Runnable mrun = new Runnable(){
	        @Override
	        public void run(){
	        Intent i = new Intent(SecLoading.this, Manu.class); //����Ʈ ����(�� ��Ƽ��Ƽ, ���� ������ ��Ƽ��Ƽ)
	        
	        startActivity(i);
	        finish();
	        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); 
	        //overridePendingTransition �̶� �Լ��� �̿��Ͽ� fade in,out ȿ������. ������ �߿�
	        }
	    };
	    //��Ʈ�� �߿� �ڷΰ��⸦ ���� ��� �ڵ鷯�� ������� �ƹ��� ���� ����� �κ�
	    //�� ������ ��Ʈ�� �� �ڷΰ��⸦ ������ ��Ʈ�� �Ŀ� Ȩȭ���� ����.
	    @Override
	    public void onBackPressed(){
	        super.onBackPressed();
	        h.removeCallbacks(mrun);
	    }

}
