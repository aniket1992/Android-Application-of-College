package database;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.example.iet.R;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class IetDatabase extends SQLiteOpenHelper{
	public static final String DATABASE_NAME = "iet_database.db";
	public static final int SCHEMA_VERSION=2;
	
	protected Context context;
	public IetDatabase(Context context) {
		super(context,DATABASE_NAME,null,SCHEMA_VERSION);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String s;
		try {
			//Toast.makeText(context, "1", 2000).show();
			InputStream in = context.getResources().openRawResource(R.raw.sql);
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(in, null);
			NodeList statements = doc.getElementsByTagName("statement");
			for (int i=0; i<statements.getLength(); i++) {
				s = statements.item(i).getChildNodes().item(0).getNodeValue();
				db.execSQL(s);
			}
		} catch (Throwable t) {
			Toast.makeText(context, t.toString(), 50000).show();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS employees");
		onCreate(db);
		
	}

	
}
