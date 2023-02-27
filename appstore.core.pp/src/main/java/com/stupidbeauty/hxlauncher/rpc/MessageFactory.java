package com.stupidbeauty.hxlauncher.rpc;

import java.util.HashSet;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import com.upokecenter.cbor.CBORObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import android.annotation.SuppressLint;
import android.app.Application;

public class MessageFactory
{
  /**
  * 序列化成字节数组。
  */
  public byte[] constructApplicationLockMessage(HashSet<String> applicationLockSet) 
  {
  //       CBORObject cborObject= CBORObject.NewMap(); //创建对象
//         
//       for(String currentVoiceRecognizeResult: applicationLockSet) //一个个地保存。
//       {
//         String currentPackageName=(currentVoiceRecognizeResult); //获取包名。
// 
//         cborObject.Add(currentVoiceRecognizeResult, currentPackageName); // 加入映射中。
//       } //for(String currentVoiceRecognizeResult: voicePackageNameMap.keySet()) //一个个地保存。

//       ApplicationLockInformation translateRequestBuilder=new ApplicationLockInformation(); // 创建对象。
//       
//       translateRequestBuilder.setApplicationLockSet(applicationLockSet); // 设置锁数据集合。
// 
//       CBORObject cborObject= CBORObject.FromObject(translateRequestBuilder, options); //创建对象
//       
//       byte[] array=cborObject.EncodeToBytes();
// 
//       byte[] serializedContent=array; //序列化成字节数组。


        CBORObject cborObject= CBORObject.NewArray(); // 创建列表

              for(String currentVoiceRecognizeResult: applicationLockSet) //一个个地保存。
      {
//         String currentPackageName=(currentVoiceRecognizeResult); //获取包名。

        cborObject.Add(currentVoiceRecognizeResult); // 加入列表中。
      } //for(String currentVoiceRecognizeResult: voicePackageNameMap.keySet()) //一个个地保存。

//         cborObject.Add(currentVoiceRecognizeResult, currentPackageName); // 加入映射中。

      byte[] array=cborObject.EncodeToBytes();


      return array;

  } // public byte[] constructApplicationLockMessage(HashSet<String> applicationLockSet)
}
