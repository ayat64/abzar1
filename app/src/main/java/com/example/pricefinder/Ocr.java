package com.example.pricefinder;
import android.graphics.Bitmap; import com.googlecode.tesseract.android.TessBaseAPI; import java.io.File;
public class Ocr {
 private final TessBaseAPI api=new TessBaseAPI();
 public boolean init(File dir){return api.init(dir.getAbsolutePath(),"fas+eng");}
 public String read(Bitmap b){api.setImage(b);String s=api.getUTF8Text();api.clear();return s==null?"":s;}
 public void close(){api.end();}
}
