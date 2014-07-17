package lm1.tasks;

import lm1.utils.CONS;
import lm1.utils.Methods;
import android.app.Activity;
import android.media.AudioTrack;
import android.os.AsyncTask;
import android.util.Log;

public class TaskAudioTrack extends AsyncTask<Integer, Integer, Integer> {

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		if(this.isCancelled()) {
			
			this.onCancelled();
			
		}

		super.onPreExecute();
	}

	Activity actv;
	int bgmResourceId;
	
	public TaskAudioTrack(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	public TaskAudioTrack(Activity actv, int bgmResourceId) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		this.bgmResourceId = bgmResourceId;
	}

	@Override
	protected Integer doInBackground(Integer... bgmResourceIds) {
		
//		if(this.isCancelled()) {
//			
//			this.onCancelled();
//			
//		}
		
		Methods.playSound(actv, bgmResourceIds[0]);
		
		return null;
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		if(CONS.Main.audioTrack.getPlayState() 
				== AudioTrack.PLAYSTATE_PLAYING) {
			
			CONS.Main.audioTrack.stop();
			
			// Log
			String msg_Log = "track => stopped";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			String msg_Log = "track => not playing";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
