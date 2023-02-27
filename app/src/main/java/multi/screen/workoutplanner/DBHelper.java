package multi.screen.workoutplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context,"userDetails.db",null,1);
    }


    public static final String DBNAME = "userDetails.db";

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key,email TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = MyDB.insert("users",null,contentValues);
        if (result==-1) return false;
        else
            return true;
    }

    public Boolean checkUserName(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUserNameEmailPassword(String username, String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and email = ? and password = ?", new String[]{username,email,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUserPassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
