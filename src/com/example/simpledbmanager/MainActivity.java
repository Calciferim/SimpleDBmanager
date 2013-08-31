package com.example.simpledbmanager;

import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private EditText mRequest;
	private TableLayout mRes;
	private DbHelper mHelper;
	private SQLiteDatabase mDb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mRequest = (EditText) findViewById(R.id.editText1);
		mRes = (TableLayout) findViewById(R.id.tableLayout1);
		findViewById(R.id.button1).setOnClickListener(this);
		mHelper = new DbHelper(this);
		mDb = mHelper.getWritableDatabase();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		String sql = mRequest.getText().toString();
		Cursor cursor = null;
		try {
			cursor = mDb.rawQuery(sql, null);
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		} finally{
			if(cursor!=null){cursor.close();}
		}

	}
	

	@Override
	protected void onDestroy() {
		mHelper.close();
		super.onDestroy();
	}
}
