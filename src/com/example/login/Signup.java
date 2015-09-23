package com.example.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

//import com.example.login.MainActivity.LoginTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends Activity {
	private static final String TAG = null;
	private Button signup;
    private EditText name;
    private EditText pass;
    private EditText email;
    private EditText rpass;
    private EditText course;
    private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		Log.i(TAG,"Entered signup");
		email=(EditText)findViewById(R.id.email);
		Log.i(TAG,"email");
        signup=(Button)findViewById(R.id.button1);
        Log.i(TAG,"signup");
        name=(EditText)findViewById(R.id.name);
        Log.i(TAG,"name");
        pass=(EditText)findViewById(R.id.pass);
        Log.i(TAG,"pass");
        rpass=(EditText)findViewById(R.id.repass);
        course=(EditText)findViewById(R.id.course);
        
        signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Log.i(TAG,"onclick2");
	//			Toast.makeText(Signup.this, "Name:"+name.getText().toString()+"  "+"Email:"+email.getText().toString(), Toast.LENGTH_LONG).show();
				 Log.i(TAG,pass.getText().toString());
				 new  Register().execute("");
				 Intent intent=new Intent(Signup.this, MainActivity.class);
				 startActivity(intent);
			
			}
			
		});
		
	}
	  private class Register extends AsyncTask<String, Integer, String>{
	    	
	    	
	    	protected void onPreExecute() {
	            super.onPreExecute();
	            progressDialog = new ProgressDialog(Signup.this);
	            progressDialog.setCancelable(true);
	            progressDialog.setMessage("Loading...");
	            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	            progressDialog.setProgress(0);
	            progressDialog.show();
	        }


			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				
				
				//Log.i(TAG,uname.getText().toString()+"ksjdvnslkdvxnwadlk");
//				Log.i(TAG,pass.getText().toString()+"fsdxcjvnskjdn");
				try{
			Log.i(TAG,"entered try()");
					
				//Toast.makeText(getApplicationContext(), "Please wait,connecting to server",Toast.LENGTH_LONG).show();
				Log.i(TAG,"entered toast()");
//				String email = URLEncoder.encode(uname.getText().toString(), "UTF-8");
//				String passss= URLEncoder.encode(pass.getText().toString(),"UTF-8");
				HttpClient Client=new DefaultHttpClient();
				Log.i(TAG,"created client");
				String URL="http://188.166.249.229/register.php?name="+name.getText().toString()+"&email="+email.getText().toString()+"&password="+pass.getText().toString()+"&course="+course.getText().toString();
//				try{
//					String Response="";
					HttpGet httpget=new HttpGet(URL);
					Log.i(TAG,"hhtp get");
					ResponseHandler<String> responseHandler=new BasicResponseHandler();
					Log.i(TAG,"in response handler");
					//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

					//StrictMode.setThreadPolicy(policy); 
					Log.i(TAG,"in strict mode");
					String Response=Client.execute(httpget,responseHandler);
					Log.i(TAG,"request executed");
					//Toast.makeText(MainActivity.this, "Congrats: Login Successfully", Toast.LENGTH_LONG).show();
					System.out.println(Response);
					//content.setText(SetServerString);
//				}
//				catch(Exception ex){
//					Toast.makeText(MainActivity.this, "Incorrect Password or username", Toast.LENGTH_LONG).show();
//				}
				}
				catch(UnsupportedEncodingException ex){
				//	Toast.makeText(Signup.this, "Incorrect Password or username", Toast.LENGTH_LONG).show();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				return null;
			}
			protected void onProgressUpdate(Integer... values) {
		        super.onProgressUpdate(values);
		    }
			protected void onPostExecute(String result){
		        progressDialog.dismiss();
				Toast.makeText(Signup.this, "registered succesfully", Toast.LENGTH_LONG).show();
			}
			
		}
	    	


}
