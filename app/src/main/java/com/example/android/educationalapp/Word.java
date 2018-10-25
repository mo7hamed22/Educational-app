package com.example.android.educationalapp;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by mohamed on 26/09/2018.
 */

public class Word {
//   Declaration of  Englishe string word
    private String Eword;
//  Declaration of    Arabic string word
    private String Arword;
//    add src number for imahe icone
    private int image_add=Has_No_imge;
    //varible to set ststue of the image
    private static int Has_No_imge=-1;
//    creat intger @param sounedId
   private int sounedId;
//icon player id
    private  int ic_player;

//    Constractor that take 3 @parameters image source ,English and Arabic word
    public Word(int imgeSrc ,String Enword , String ARword ,int Sid,int icc_player)
    {
        Eword=Enword;
        Arword=ARword;
        image_add=imgeSrc;
        sounedId=Sid;
        ic_player=icc_player;

    }
    // another Constractor that take 2  @parameters English and Arabic word
    public Word(String Enword , String ARword,int Sid,int icc_player)
    {
        Eword=Enword;
        Arword=ARword;
        sounedId=Sid;
        ic_player=icc_player;
    }
//    get english word
    public String GetEglishWord()
    {return Eword;}
//    get arabic words
    public String GetArabichWord()
    {return Arword;}
//    get image id
    public int getImage_iconesrc()
    {
        return image_add;
    }
    public boolean HaveImge() {
       return image_add!=Has_No_imge;
    }
//get sound id
    public int getSounedId_()
{
    return sounedId;
}
    //get sound id
    public int ic_id_()
    {
        return ic_player;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + Arword + '\'' +
                ", mMiwokTranslation='" +Eword + '\'' +
                ", mAudioResourceId=" + image_add +
                ", mImageResourceId=" + sounedId +
                '}';
    }
}
