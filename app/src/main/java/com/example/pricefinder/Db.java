package com.example.pricefinder;
import android.content.*; import android.database.*; import android.database.sqlite.*; import java.util.*;
public class Db extends SQLiteOpenHelper {
 public Db(Context c){super(c,"prices.db",null,1);}
 public void onCreate(SQLiteDatabase d){d.execSQL("CREATE TABLE items(id INTEGER PRIMARY KEY AUTOINCREMENT,code TEXT,name TEXT,price TEXT,source TEXT,page INTEGER,ocr TEXT)");d.execSQL("CREATE INDEX idx_code ON items(code)");d.execSQL("CREATE INDEX idx_name ON items(name)");}
 public void onUpgrade(SQLiteDatabase d,int a,int b){}
 public void clearSource(String s){getWritableDatabase().delete("items","source=?",new String[]{s});}
 public void add(String c,String n,String p,String s,int pg,String o){ContentValues v=new ContentValues();v.put("code",c);v.put("name",n);v.put("price",p);v.put("source",s);v.put("page",pg);v.put("ocr",o);getWritableDatabase().insert("items",null,v);}
 public List<String[]> search(String q){ArrayList<String[]> r=new ArrayList<>();Cursor c=getReadableDatabase().rawQuery("SELECT code,name,price,source,page,ocr FROM items WHERE code LIKE ? OR name LIKE ? OR ocr LIKE ? LIMIT 200",new String[]{"%"+q+"%","%"+q+"%","%"+q+"%"});while(c.moveToNext())r.add(new String[]{c.getString(0),c.getString(1),c.getString(2),c.getString(3),String.valueOf(c.getInt(4)),c.getString(5)});c.close();return r;}
}
