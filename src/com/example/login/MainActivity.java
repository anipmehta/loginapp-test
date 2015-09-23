package com.example.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.GINGERBREAD) public class MainActivity extends Activity {
	String response;
	protected static final String TAG = null;
	private Button login;
	 public static final String USER_NAME = "USERNAME";
    private Button signup;
    public EditText uname;
    ProgressDialog progressDialog;
    public EditText pass;
//    String user;
//    String password;
    
    
    @TargetApi(Build.VERSION_CODES.GINGERBREAD) @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);
        uname=(EditText)findViewById(R.id.editText1);
        pass=(EditText)findViewById(R.id.editText2);
//    	Log.i(TAG,uname.getText().toString());
//    	Log.i(TAG,pass.getText().toString());
//    	user=uname.getText().toString();
//		password=pass.getText().toString();
//		System.out.println(user+password);
		
    	
        login.setOnClickListener(new OnClickListener() {
        	
			
			//login(user,pass);
			@SuppressLint("NewApi") @Override
			public void onClick(View arg0) {
				
				Log.i(TAG,"entered onclick()");
				
				new  LoginTask().execute("");
				/*if(uname.getText().toString().equals("admin@gmail.com") && pass.getText().toString().equals("admin")){
				 
				 
					 Toast.makeText(MainActivity.this, "Congrats: Login Successfully", Toast.LENGTH_LONG).show();
				}
				else
					 Toast.makeText(MainActivity.this, "Incorrect Password or username", Toast.LENGTH_LONG).show();
			*/
			}
			
		});
        
//		
       
        signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i(TAG,"Entered onclick()");
				Intent intent=new Intent(getApplicationContext(),Signup.class);
				startActivity(intent);
				
			}
		});
        }
    private class LoginTask extends AsyncTask<String, Integer, String>{
    	
    	
    	protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(0);
            progressDialog.show();
        }


		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			
			Log.i(TAG,uname.getText().toString()+"ksjdvnslkdvxnwadlk");
//			Log.i(TAG,pass.getText().toString()+"fsdxcjvnskjdn");
			try{
		Log.i(TAG,"entered try()");
				
			//Toast.makeText(getApplicationContext(), "Please wait,connecting to server",Toast.LENGTH_LONG).show();
			Log.i(TAG,"entered toast()");
//			String email = URLEncoder.encode(uname.getText().toString(), "UTF-8");
//			String passss= URLEncoder.encode(pass.getText().toString(),"UTF-8");
			HttpClient Client=new DefaultHttpClient();
			Log.i(TAG,"created client");
			String URL="http://188.166.249.229/login.php?email="+uname.getText().toString()+"&password="+pass.getText().toString();
//			try{
//				String Response="";
				HttpGet httpget=new HttpGet(URL);
				Log.i(TAG,"hhtp get");
				ResponseHandler<String> responseHandler=new BasicResponseHandler();
				Log.i(TAG,"in response handler");
				//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

				//StrictMode.setThreadPolicy(policy); 
				Log.i(TAG,"in strict mode");
				String Response=Client.execute(httpget,responseHandler);
				Log.i(TAG,"request executed");
				response=Response;
				//Toast.makeText(MainActivity.this, "Congrats: Login Successfully", Toast.LENGTH_LONG).show();
				System.out.println(Response);
				//content.setText(SetServerString);
//			}
//			catch(Exception ex){
//				Toast.makeText(MainActivity.this, "Incorrect Password or username", Toast.LENGTH_LONG).show();
//			}
			}
			catch(UnsupportedEncodingException ex){
				Toast.makeText(MainActivity.this, "Incorrect Password or username", Toast.LENGTH_LONG).show();
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
	        Log.i(TAG,response);
	        //if(response.contentEquals("Valid user"))
			Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
	        //else
	        	//Toast.makeText(MainActivity.this, "invalid credentials", Toast.LENGTH_LONG).show();

		}
		
	}
    	
    }



