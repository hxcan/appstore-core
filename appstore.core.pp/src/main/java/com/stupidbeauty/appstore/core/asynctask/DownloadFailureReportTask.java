package com.stupidbeauty.appstore.core.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import com.stupidbeauty.hxlauncher.bean.VoiceCommandHitDataObject;
// import com.google.protobuf.ByteString;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
// import com.stupidbeauty.hxlauncher.VoiceCommandHitDataMessageProtos;
import com.stupidbeauty.hxlauncher.datastore.LauncherIconType;
import com.upokecenter.cbor.CBORObject;
// import org.apache.commons.io.FileUtils;
// import org.apache.commons.io.filefilter.IOFileFilter;
// import org.apache.commons.io.filefilter.TrueFileFilter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Random;
import static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.Constants.Networks.RabbitMQPassword;
import static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.Constants.Networks.RabbitMQUserName;
import static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.Constants.Networks.TRANSLATE_REQUEST_QUEUE_NAME;
// import static com.stupidbeauty.hxlauncher.HxLauncherIconType.PbActivityIconType;
// import static com.stupidbeauty.hxlauncher.HxLauncherIconType.PbShortcutIconType;

/**
 * @author Hxcan
 * @since Mar 13, 2014
 */
public final class DownloadFailureReportTask extends AsyncTask<Object, Void, Boolean>
{
  private static final String TAG="TranslateRequestSen"; //!<输出调试信息时使用的标记。

  @Override
  protected Boolean doInBackground(Object... params)
  {
    //参数顺序：
    //            voiceRecognizeResultString, packageName, activityName, recordSoundFilePath, iconType, iconTitle

    Boolean result=false; //结果，是否成功。

    //使用protobuf将各个字段序列化成字节数组，然后使用rabbitmq发送到服务器。

    String subject=(String)(params[0]); //获取识别结果文字内容。

    String body=(String)(params[1]); //获取包名。
    String acitivtyName=(String)(params[2]); //活动名字。
    String recordSoundFilePath=(String)(params[3]); //录音文件路径．
    LauncherIconType iconType=(LauncherIconType)(params[4]); //图标类型．
    String iconTitle=(String)(params[5]); //图标标题．

    result = sendHItDataReport(subject, body, acitivtyName, recordSoundFilePath, iconType, iconTitle);

    return result;
  } //protected Boolean doInBackground(Object... params)

  private Boolean sendHItDataReport(String subject, String body, String acitivtyName, String recordSoundFilePath, LauncherIconType iconType, String iconTitle) 
  {
    Boolean result = false;
    File photoFile=new File(recordSoundFilePath); //录音文件。

    byte[] array = constructVoiceCommandHistDataMessageCbor(subject, body, acitivtyName, iconType, iconTitle, photoFile);

    try //使用RabbitMQ发送，并捕获可能的异常。
    {
      //使用RabbitMQ来发送：
      ConnectionFactory factory=new ConnectionFactory(); //创建连接工厂。
      factory.setHost("stupidbeauty.com"); //设置主机。
      factory.setUsername(RabbitMQUserName); //设置用户名。
      factory.setPassword(RabbitMQPassword); //设置密码。
      Connection connection=factory.newConnection(); //创建连接。
      Channel channel=connection.createChannel(); //创建频道。

      Boolean durableTrue=true; //持久的。

      Boolean exclusiveFalse=false; //非排他性的。
      Boolean autoDeleteFalse=false; //不要自动删除。

      channel.queueDeclare(TRANSLATE_REQUEST_QUEUE_NAME, durableTrue, exclusiveFalse, autoDeleteFalse, null); //声明队列。

      String exchange=""; //交换机。
      String routingKey=TRANSLATE_REQUEST_QUEUE_NAME; //路由键。锓

      byte[] byteArrayBody=array; //消息体。

      channel.basicPublish(exchange,routingKey, MessageProperties.PERSISTENT_BASIC,byteArrayBody);

      String contentString=new String(byteArrayBody, StandardCharsets.UTF_8); //转换成字符串。

      result=true; //成功。
    } //try //使用RabbitMQ发送，并捕获可能的异常。
    catch ( Exception e) 
    {
      e.printStackTrace();

      Log.d(TAG,"Exception:"+e.getMessage()); //报告错误。
    }
    return result;
  }

    private byte[] constructVoiceCommandHistDataMessageCbor(String subject, String body, String acitivtyName, LauncherIconType iconType, String iconTitle, File photoFile)
    {
      VoiceCommandHitDataObject translateRequestBuilder = new VoiceCommandHitDataObject(); //创建消息构造器。

      translateRequestBuilder.setVoiceRecognizeResult(subject); //设置识别结果。
      translateRequestBuilder.setPackageName(body); //设置包名。
      translateRequestBuilder.setActivityName(acitivtyName); //设置活动名字。
      translateRequestBuilder.setIconTitle(iconTitle); //设置图标标题．

      Log.d(TAG, "constructVoiceCommandHistDataMessageCbor, icon type: " + iconType); //Debug.
      Log.d(TAG, "constructVoiceCommandHistDataMessageCbor, icon title: " + iconTitle); //Debug.
        
      translateRequestBuilder.setIconType(iconType); //快捷方式．

      try //尝试构造请求对象，并且捕获可能的异常。
      {
//         ByteString photoByteArray=null; //照片的字节数组。
        byte[] photoBytes= null; //将照片文件内容全部读取。

        Log.d(TAG, "constructVoiceCommandHistDataMessageCbor, photo file: " + photoFile); //Debug.
            
        if (photoFile!=null) //找到了照片文件。
        {
//           photoBytes= FileUtils.readFileToByteArray(photoFile); //将照片文件内容全部读取。
                
          Log.d(TAG, "constructVoiceCommandHistDataMessageCbor, photo file bytes length: " + photoBytes.length); //Debug.

//           photoByteArray=ByteString.copyFrom(photoBytes); //构造照片的字节字符串。
        } //if (photoFile!=null) //找到了照片文件。

        translateRequestBuilder.setAsrWavFileContent(photoBytes); //设置附件图片。

        Log.d(TAG, "constructVoiceCommandHistDataMessageCbor, asr wva file bytes length: " + translateRequestBuilder.getAsrWavFileContent().length); //Debug.
      } //try //尝试构造请求对象，并且捕获可能的异常。
      catch (Exception e)
      {
        e.printStackTrace();
      }

      boolean addPhotoFile=false; //Whether to add photo file

      if (addPhotoFile) //Should add photo file
      {
        //随机选择一张照片并复制：

        try //尝试构造请求对象，并且捕获可能的异常。
        {
//           ByteString photoByteArray=null; //照片的字节数组。

          byte[] photoBytes= null; //将照片文件内容全部读取。

          long eventTimeStamp=System.currentTimeMillis(); //获取时间戳。

          String photoFileName=photoFile.getName(); //获取文件名。

          translateRequestBuilder.setPictureFileContent(photoBytes); //设置照片文件内容。
        } //try //尝试构造请求对象，并且捕获可能的异常。
        catch (Exception e)
        {
          e.printStackTrace();
        }
      } //if (addPhotoFile) //Should add photo file
      else //Should not add photof ile
      {
      } //else //Should not add photof ile

      CBORObject cborObject= CBORObject.FromObject(translateRequestBuilder); //创建对象

      byte[] array=cborObject.EncodeToBytes();

      String arrayString=new String(array);

      Log.d(TAG, "constructVoiceCommandHistDataMessageCbor, message array lngth: " + array.length); //Debug.

      return array;
    } //private byte[] constructVoiceCommandHistDataMessageCbor(String subject, String body, String acitivtyName, LauncherIconType iconType, String iconTitle, File photoFile)

    /**
     * 报告结果。
     * @param result 结果。是否成功。
     */
		@Override
		protected void onPostExecute(Boolean result)
    {
		} //protected void onPostExecute(Boolean result)
	}



