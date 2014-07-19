package lm1.main;

import lm1.utils.CONS;
import lm1.utils.Methods;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class PrefActv extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Set content
		 * 
		 * 3. Set preferences
		----------------------------*/
		super.onCreate(savedInstanceState);

		//
		setContentView(R.layout.main_pref);

		this.setTitle(this.getClass().getName());
		
		/*----------------------------
		 * 3. Set preferences
			----------------------------*/
		getPreferenceManager()
				.setSharedPreferencesName(CONS.Pref.pname_MainActv);
//		this.getString(R.string.prefs_shared_prefs_name));
		
		addPreferencesFromResource(R.xml.preferences);
		
	}//public void onCreate(Bundle savedInstanceState)


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		EditTextPreference prefEditText = 
				(EditTextPreference) findPreference(
						this.getString(R.string.prefs_key_distbuff));
//		this.getString(R.string.prefs_history_size_key));
		
		prefEditText.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
		
//		this._test();
		
//		prefEditText.getEditText().setBackgroundColor(Color.BLUE);	// N/C
		
//		prefEditText.setSummary("BGM");	// N/C
		
		super.onStart();
		
	}//protected void onStart()

	@Override
	protected void onStop() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDestroy();
	}

	private void _test() {
		
		SharedPreferences prefs = 
				this.getSharedPreferences(
						CONS.Pref.pname_MainActv, 
						Context.MODE_PRIVATE);
		
//		int distBuf = prefs.getInt(
//				this.getString(R.string.prefs_key_distbuff), -1);
		
//		String distBuf = prefs.getString(
//							CONS.Pref.pkey_MainActv_CurrentBase, null);
		
//		long distBuf = prefs.getLong(
//				CONS.Pref.pkey_MainActv_CurrentBase, CONS.Pref.dflt_LongExtra_value);

		String distBuf = prefs.getString(
				this.getString(R.string.prefs_key_distbuff), null);

		// Log
		String msg_Log = "buf => " + distBuf;
		Log.d("PrefActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
		// TODO Auto-generated method stub
//		int distBuffer = Methods.get_Pref_Int(
//				this, 
//				CONS.Pref.pname_MainActv, 
//				this.getString(R.string.prefs_key_distbuff), 
//				CONS.Pref.dflt_IntExtra_value);
//		
//		// Log
//		String msg_Log = "distBuffer => " + distBuffer;
//		Log.d("PrefActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);

	}

}//public class PrefActv extends ListActivity
