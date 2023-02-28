package com.stupidbeauty.appstore.core.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import com.stupidbeauty.appstore.bean.VoiceCommandHitDataObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.stupidbeauty.hxlauncher.datastore.LauncherIconType;
import com.upokecenter.cbor.CBORObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Random;

/**
 * @author Hxcan
 * @since Mar 13, 2014
 */
public final class DownloadFailureReportTask extends AsyncTask<Object, Void, Boolean>
{
  private static final String TAG="DownloadFailureReport"; //!< 输出调试信息时使用的标记。

  @Override
  protected Boolean doInBackground(Object... params)
  {
    //参数顺序：
    // packageName

    Boolean result=false; //结果，是否成功。

    //使用protobuf将各个字段序列化成字节数组，然后使用rabbitmq发送到服务器。

//     String subject=(String)(params[0]); //获取识别结果文字内容。

    String body=(String)(params[0]); // 获取包名。
    String RabbitMQUserName=(String)(params[1]); // user name
    String RabbitMQPassword=(String)(params[2]); // password 
    String TRANSLATE_REQUEST_QUEUE_NAME=(String)(params[3]); // message queue name.

    result = sendHItDataReport(body, RabbitMQUserName, RabbitMQPassword, TRANSLATE_REQUEST_QUEUE_NAME);

    return result;
  } //protected Boolean doInBackground(Object... params)

  private Boolean sendHItDataReport(String body, String RabbitMQUserName, String RabbitMQPassword, String TRANSLATE_REQUEST_QUEUE_NAME)
  {
    Boolean result = false;

    byte[] array = constructVoiceCommandHistDataMessageCbor(body);

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

    private byte[] constructVoiceCommandHistDataMessageCbor(String body)
    {
      VoiceCommandHitDataObject translateRequestBuilder = new VoiceCommandHitDataObject(); //创建消息构造器。

      translateRequestBuilder.setPackageName(body); //设置包名。

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



