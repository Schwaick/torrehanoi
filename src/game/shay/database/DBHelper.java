package game.shay.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "game";
	public static final String TABLE_NAME = "level";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_LVL = "lvl";
	public static final String COLUMN_LOCKED = "locked";
	public static final String COLUMN_MOVES = "moves";
	public static final int DB_VERSION = 1;

	private static final String CREATE_SQL = 
			"CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + 
					COLUMN_LVL+" INTEGER NOT NULL, "+COLUMN_LOCKED+" INTEGER NOT NULL, " +
					COLUMN_MOVES+" INTEGER NOT NULL)";

	private static final String DELETE_SQL = 
			"DROP TABLE " + TABLE_NAME;
		
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_SQL);
		
		ContentValues cv = new ContentValues();
               
        for(int i=1;i<=8;i++) {
	        cv = new ContentValues();
			cv.put(COLUMN_ID, i);
	        cv.put(COLUMN_LVL, i);
	        cv.put(COLUMN_LOCKED, i==1?0:1);
	        cv.put(COLUMN_MOVES, 9999);
	        db.insert(TABLE_NAME, null, cv);
        }
                
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DELETE_SQL);
		db.execSQL(CREATE_SQL);
	}
	
	public boolean isLevelLocked(int id) {
		SQLiteDatabase myDB = this.getReadableDatabase();
        
        Cursor myCursor = myDB.query(TABLE_NAME, null, COLUMN_ID+"=?", new String[]{String.valueOf(id)}, null, null, null);
        if(myCursor.moveToFirst()) {
        	int index = myCursor.getColumnIndex(COLUMN_LOCKED);
        	if(myCursor.getInt(index) == 1) {
        		myDB.close();
        		return true;
        	} else {
        		myDB.close();
        		return false;
        	}
        }
        myDB.close();
        return false;
	}
	
	public void unlockLevel(int level) {
		SQLiteDatabase myDB = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_LOCKED, 0);
		myDB.update(TABLE_NAME, cv, COLUMN_LVL+"=?", new String[]{String.valueOf(level)});
		
		myDB.close();
	}
	
	public int getRecord(int id) {
		SQLiteDatabase myDB = this.getReadableDatabase();
		
		Cursor myCursor = myDB.query(TABLE_NAME, null, COLUMN_ID+"=?", new String[]{String.valueOf(id)}, null, null, null);
		if(myCursor.moveToFirst()) {
			int index = myCursor.getColumnIndex(COLUMN_MOVES);
			int result = myCursor.getInt(index);
			myDB.close();
        	return result;
		}
		myDB.close();
        return 0;
	}
	
	public void setRecord(int level, int contador) {
		SQLiteDatabase myDB = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_MOVES, contador);
		myDB.update(TABLE_NAME, cv, COLUMN_LVL+"=?", new String[]{String.valueOf(level)});
		
		myDB.close();
	}
}
