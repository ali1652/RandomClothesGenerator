package multi.screen.workoutplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDbAdapter {
    myDbHelper myhelper;

    public myDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name, String platform) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.MyPlatform, platform);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;

    }

    public String getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] coloumns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.MyPlatform};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, coloumns,
                null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndexOrThrow(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.NAME));
            String platform = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.MyPlatform));
            buffer.append(cid + "   " + name + "   " + platform + "  \n");
        }
        return buffer.toString();
    }




    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";
        private static final String TABLE_NAME = "UserDetails";
        private static final int DATABASE_Version = 1;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String MyPlatform = "Platform";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) , " + MyPlatform + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

    }
}
