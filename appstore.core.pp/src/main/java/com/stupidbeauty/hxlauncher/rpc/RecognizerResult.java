package com.stupidbeauty.hxlauncher.rpc;

import java.util.ArrayList;
import java.util.List;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

/**
 * 识别结果信息对象。
 */
public class RecognizerResult
{
    private Uri uri=null;
    private String a = "";
    private byte[] downloadContent=null;
    private List<String> entryList=new ArrayList<>(); //!<条目列表。
    
    public byte[] getDownloadContent()
    {
        return downloadContent;
    }

    /**
     * 获取识别结果字符串。
     * @return 识别结果的JSON字符串。
     */
    public String getResultString() {
        return this.a;
    }

    public RecognizerResult(String var1) {
        if (null != var1) {
            this.a = var1;
        }

    }
    
    public Uri getUri()
    {
        return uri;
    }
    
    public void setUri(Uri uri)
    {
        this.uri=uri;
    }
    
    public RecognizerResult(byte[] content)
    {
        downloadContent=content;
    }


}
