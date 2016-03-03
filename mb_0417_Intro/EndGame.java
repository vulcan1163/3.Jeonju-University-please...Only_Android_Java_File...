package com.example.mb_0417_Intro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.mb_0417_Game.GameState;
import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.DBManager;
import com.example.mb_0417_test.MyActivity;
import com.example.mb_0417_test.Person;
import com.example.mb_0417_test.R;

public class EndGame extends Activity implements OnInitListener {

	public static int score = 0;
	private DBManager mgr;
	public static ListView listView;

	public TextToSpeech myTTS;
	Handler h;

	Button btn1, btn2;
	TextView tts[];
	TextView yourScoreText = null;
	LinearLayout LLname, LLgameover, LLrank;
	EditText EdtName;
	String name;
	int i = 0;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 인트로화면이므로 타이틀바를 없앤다
		setContentView(R.layout.end);

		AppManager.getInstance().m_gameview.m_Thread.setRunning(true);
		MyActivity.player.stop();

		h = new Handler();
		h.postDelayed(mrun, 2000);

		listView = (ListView) findViewById(R.id.listView);

		LLname = (LinearLayout) findViewById(R.id.LLname);
		LLgameover = (LinearLayout) findViewById(R.id.LLgameover);
		LLrank = (LinearLayout) findViewById(R.id.LLrank);

		EdtName = (EditText) findViewById(R.id.EdtName);

		findViewById(R.id.BtnOK).setOnClickListener(ButtonClick);
		findViewById(R.id.button1).setOnClickListener(ButtonClick);
		findViewById(R.id.button2).setOnClickListener(ButtonClick);
		findViewById(R.id.BtnRank).setOnClickListener(ButtonClick);

		yourScoreText = (TextView) findViewById(R.id.yourscoretext);
		listView = (ListView) findViewById(R.id.listView);

		score = GameState.Score;

		yourScoreText.setText(score + "");

		mgr = new DBManager(this);

	} // end onCreate

	Runnable mrun = new Runnable(){
	        @Override
	        public void run(){
	        	myTTS = new TextToSpeech(getBaseContext(),  new TextToSpeech.OnInitListener(){
	        	
	        	public void onInit(int status) {
	        		String myText1 = "게임 종료되었습니다.";
	        		String myText2 = "이름을 입력해주세요.";
	        		
	        		myTTS.speak(myText1, TextToSpeech.QUEUE_FLUSH, null);
	        		myTTS.speak(myText2, TextToSpeech.QUEUE_ADD, null);
	        	}
	        	});
	        }
	 };

	protected void onDestroy() {
		super.onDestroy();
		// 应用的最后一个Activity关闭时应释放DB
		mgr.closeDB();
	}

	public void add() {
		ArrayList<Person> persons = new ArrayList<Person>();

		Person person1 = new Person(name, score);

		persons.add(person1);

		mgr.add(persons);

	}

	public void query() {
		List<Person> persons = mgr.query();
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (Person person : persons) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", person.name);
			map.put("score", " Score : " + person.score);
			list.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2,
				new String[] { "name", "score" }, new int[] { android.R.id.text1, android.R.id.text2 });
		listView.setAdapter(adapter);
	}

	Button.OnClickListener ButtonClick=new Button.OnClickListener(){

	public void onClick(View view){switch(view.getId()){case R.id.BtnOK:name=EdtName.getText().toString();LLname.setVisibility(View.GONE);LLgameover.setVisibility(View.VISIBLE);add();query();break;

	case R.id.button1:System.exit(0);break;

	case R.id.button2:Intent in=new Intent(EndGame.this,Manu.class); // 인텐트생성(현액티비티,새로실행할액티비티)
	startActivity(in);finish();overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);break;case R.id.BtnRank:i++;if(i%2==0)LLrank.setVisibility(View.GONE);else LLrank.setVisibility(View.VISIBLE);break;}}};

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
	}
}
