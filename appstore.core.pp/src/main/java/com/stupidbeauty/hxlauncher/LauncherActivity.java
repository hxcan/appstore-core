package com.stupidbeauty.hxlauncher;

import com.stupidbeauty.appstore.core.asynctask.DownloadFailureReportTask;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Handler;
import java.util.HashSet;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import android.net.Uri;
import android.os.Debug;
import java.util.Timer;
import java.util.TimerTask;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.LocaleList;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Pair;
import com.andexert.library.RippleView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.stupidbeauty.hxlauncher.bean.VoiceCommandHitDataObject;
import com.google.gson.Gson;
import com.huiti.msclearnfootball.AnswerAvailableEvent;
import com.huiti.msclearnfootball.VoiceRecognizeResult;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.stupidbeauty.hxlauncher.bean.ApplicationNameInternationalizationData;
import com.stupidbeauty.hxlauncher.bean.ApplicationNamePair;
import com.stupidbeauty.hxlauncher.bean.HxShortcutInfo;
import com.stupidbeauty.hxlauncher.callback.LauncherAppsCallback;
import com.stupidbeauty.hxlauncher.datastore.LauncherIconType;
import com.stupidbeauty.hxlauncher.datastore.RuntimeInformationStore;
import com.stupidbeauty.hxlauncher.datastore.VoiceCommandSourceType;
// import com.stupidbeauty.hxlauncher.external.ShutDownAt2100Manager;
import com.stupidbeauty.qtdocchinese.ArticleInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import com.stupidbeauty.hxlauncher.interfaces.LocalServerListLoadListener;
import com.stupidbeauty.hxlauncher.bean.ApplicationListData;
import com.iflytek.cloud.SpeechRecognizer;
import static android.content.Intent.ACTION_PACKAGE_CHANGED;
import static android.content.Intent.ACTION_PACKAGE_REPLACED;
import static android.content.Intent.EXTRA_COMPONENT_NAME;
import static android.content.Intent.EXTRA_PACKAGE_NAME;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_DYNAMIC;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_MANIFEST;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_PINNED;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_PINNED_BY_ANY_LAUNCHER;
import static com.stupidbeauty.hxlauncher.Constants.Actions.LegacyInstallShortcut;
import static com.stupidbeauty.hxlauncher.Constants.LanImeAction.InputtingForPackage;
import static com.stupidbeauty.hxlauncher.Constants.LanImeAction.PackageNameOfInputting;
import static com.stupidbeauty.hxlauncher.Constants.Numbers.IgnoreVoiceResultLength;
import static com.stupidbeauty.hxlauncher.Constants.Operation.ToggleBuiltinShortcuts;
import static com.stupidbeauty.hxlauncher.Constants.Operation.ToggleHiveLayout;
import static com.stupidbeauty.hxlauncher.Constants.Operation.UnlinkVoiceCommand;
// import static com.stupidbeauty.hxlauncher.HxLauncherIconType.PbShortcutIconType;
import static com.stupidbeauty.hxlauncher.datastore.LauncherIconType.ActivityIconType;
import static com.stupidbeauty.hxlauncher.datastore.LauncherIconType.ShortcutIconType;
import static com.stupidbeauty.hxlauncher.datastore.VoiceCommandSourceType.LocalVoiceCommandMap;
import static com.stupidbeauty.hxlauncher.datastore.VoiceCommandSourceType.ServerVoiceCommandResponse;
import android.os.Process;

public class LauncherActivity extends Activity implements  LocalServerListLoadListener
{
  private static final String PACKAGE_INSTALLED_ACTION = "com.example.android.apis.content.SESSION_API_PACKAGE_INSTALLED";

  private AnimationDrawable rocketAnimation; //!<录音按钮变暗

  private Stack<VoiceCommandHitDataObject> voiceCommandHitDataStack=new Stack<>(); //!<语音命中数据记录栈

  private boolean mscIsInitialized=false; //!<讯飞语音识别是否已经初始化。
  private HashMap<String, Long> packageItemLastLaunchTimestampMap=new HashMap<>(); //!<包名加类名的字符串与最后一次启动时间戳之间的映射。

  private HashMap<String, String> serverVoiceCommandResponseIgnoreMap=null; //!<服务器的回复中，要忽略掉的关系映射

  private HashMap<String, ShortcutInfo> shortcutTitleInfoMap; //!<快捷方式的标题与快捷方式对象本身的映射。
  private HashMap<String, ShortcutInfo> shortcutIdInfoMap; //!<快捷方式的编号与快捷方式对象本身的映射
  private HashMap<String, Integer> packageNameItemNamePositionMap=new HashMap<>(); //!<包名加类名的字符串与图标位置之间的映射。
  private HashMap<String, Integer> packageNamePositionMap=new HashMap<>(); //!<包名字符串与图标位置之间的映射。

    PowerManager.WakeLock wakeLock=null; //!<游戏辅助唤醒锁。
    private boolean activityHasBeenResumed=false; //!<活动是否处于被继续的状态，即正常的运行状态。
    private boolean sentVoiceAssociationData=false; //!<是否已经成功发送语音指令关联应用程序数据。

    private List<ShortcutInfo> shortcutInfos=null; //!< 快捷方式列表。
    
    private ArrayList<ArticleInfo> articleInfoArrayList = null; //!< 应用程序信息列表。
    
    private boolean builtinShortcutsVisible= true; //!< 内置 快捷方式是否可见。
    
    /**
    * 包名加类名的字符串与图标位置之间的映射。
    */
    public void setPackageNameItemNamePositionMap (HashMap<String, Integer> packageNameItemNamePositionMap)
    {
        this.packageNameItemNamePositionMap=packageNameItemNamePositionMap;
    } //public void setPackageNameItemNamePositionMap (HashMap<String, Integer> packageNameItemNamePositionMap)

    /**
    * 包名字符串与图标位置之间的映射。
    */
    public void setPackageNamePositionMap (HashMap<String, Integer> packageNamePositionMap)
    {
        this.packageNamePositionMap=packageNamePositionMap;
    }
    
        public void setServerVoiceCommandResponseIgnoreMap (HashMap<String, String> serverVoiceCommandResponseIgnoreMap) //!<服务器的回复中，要忽略掉的关系映射
        {
            this.serverVoiceCommandResponseIgnoreMap=serverVoiceCommandResponseIgnoreMap;
        }


    public void setSentVoiceShortcutAssociationData(boolean sentVoiceShortcutAssociationData) {
        this.sentVoiceShortcutAssociationData = sentVoiceShortcutAssociationData;
    }

    private boolean sentVoiceShortcutAssociationData=false; //!<是否已经成功发送语音指令关联快捷方式的数据。
    private static final int PERMISSIONS_REQUEST = 1; //!<权限请求标识

    private static final String PERMISSION_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO; //!<录音权限。
    private static final String PERMISSION_FINE_LOCATIN = Manifest.permission.ACCESS_FINE_LOCATION; //!<位置权限
    private static final String PERMISSION_INSTALL_PACKAGE = Manifest.permission.REQUEST_INSTALL_PACKAGES; //!< 安装应用程序权限

    private boolean foundActivity=false; //!<是否命中了活动。

    private HashMap<String, HxShortcutInfo> voiceShortcutIdMap=null; //!<语音识别结果与快捷方式编号之间的映射关系．
    private HashMap<String, HxShortcutInfo> voiceShortcutIdMapBuiltin=null; //!<语音识别结果与快捷方式编号之间的映射关系．
    
    private HashMap<String, String> internationalizationDataPackageNameMap=new HashMap<>(); //映射。应用程序的国际化名字与包名之间的映射。

    private String voiceRecognizeResultString; //!<语音识别结果。

    int ret = 0;

    private SpeechRecognizer mIat; //!<语言识别器。

    private String recordSoundFilePath; //!<录音文件路径．

    private int recognizeCounter=0; //!<识别计数器．

    private Vibrator vibrator;

    private boolean voiceEndDetected=false; //!<是否已经探测到用户声音结束。

    private int mPageNumber = 1;//{1, 1, 1};

    private final int MSG_REFRESH   = 1;
    private final int MSG_LOAD_MORE = 2;
    private boolean mIsLastPage = true;

    private int mCurrMsg = -1;

    private ArrayList<ArticleInfo> builtinShortcuts =null; //!< 内置快捷方式列表。
    
    public void setVoiceShortcutIdMap (HashMap<String, HxShortcutInfo> voiceShortcutIdMap) //!<语音识别结果与快捷方式编号之间的映射关系．
    {
      this.voiceShortcutIdMap=voiceShortcutIdMap;
        
      if (voiceShortcutIdMapBuiltin!=null)
      {
        this.voiceShortcutIdMap.putAll(voiceShortcutIdMapBuiltin); // 合并。
      }
    }
    
    /**
    * 语音识别结果与快捷方式编号之间的映射关系．
    */
    public void setVoiceShortcutIdMapBuiltin (HashMap<String, HxShortcutInfo> voiceShortcutIdMapBuiltin) 
    {
      this.voiceShortcutIdMapBuiltin=voiceShortcutIdMapBuiltin;
            
      this.voiceShortcutIdMap.putAll(voiceShortcutIdMapBuiltin); // 合并。
    } //public void setVoiceShortcutIdMapBuiltin (HashMap<String, HxShortcutInfo> voiceShortcutIdMapBuiltin)
    
    public void setInternationalizationDataPackageNameMap(HashMap<String, String>  internationalizationDataPackageNameMap)
    {
      this.internationalizationDataPackageNameMap=internationalizationDataPackageNameMap;
    
      Log.d(TAG, "setInternationalizationDataPackageNameMap, map: " + this.internationalizationDataPackageNameMap); // Debug.
    }
    
    /**
    * 选择随机端口。
    */
    private int chooseRandomPort() 
    {
      int randomIndex=1239; //随机选择一个文件。

      return randomIndex;
    } //private int chooseRandomPort()

    /**
     * 设置结果，发送语音关联数据的结果。
     * @param result 发送结果。
     */
    public void setSendVoiceAssociationDataResult(Boolean result)
    {
      sentVoiceAssociationData=result; //记录。
    } //public void setSendVoiceAssociationDataResult(Boolean result)

    /**
     * 处理事件，软件包列表载入完毕。
     */
    @Override
    public void onLoadPackageInfoList()
    {
        solveLauncherIntents(); //刷新已安装的应用程序。
    } //public void onLoadPackageInfoList()

    private static final String TAG="LauncherActivity"; //!< 输出调试信息时使用的标记。
    private final String categoryName="default"; //!<要显示的分类的名字。
    final Map<String,PackageCountObject> packageNameCountObjectMap=new HashMap<>(); //软件包名字与计数对象之间的映射。
    final List<PackageCountObject> packageCountObjectList=new ArrayList<>(); //!<计数对象列表。
    
    /**
     * 要求显示系统的墙纸在本活动后面。
     */
    private void askShowSystemWallpaper()
    {
      WindowManager.LayoutParams p=getWindow().getAttributes();
      p.flags |= WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER;
    } //private void askShowSystemWallpaper()
    
    /**
     * 检查自己是不是默认启动器。
     * @return 自己是不是默认启动器。
     */
    private boolean isMyAppLauncherDefault()
    {
        boolean result=false; //结果。

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        String currentHomePackage = resolveInfo.activityInfo.packageName;

        String myPackageName=getPackageName(); //获取包名。

        if (currentHomePackage.equals(myPackageName)) //是的。
        {
            result=true;
        } //if (currentHomePackage.equals(myPackageName)) //是的。

        return result;
    } //private boolean isMyAppLauncherDefault()

    /**
     * 构造映射，快捷方式的标题与快捷方式对象之间的映射。
     * @param shortcutInfos 快捷方式列表。
     */
    public void buildShortcutTitleInfoMap(List<ShortcutInfo> shortcutInfos)
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) //26之后才有钉住的快捷方式。
        {
            shortcutTitleInfoMap=new HashMap<>(); //创建映射。
            shortcutIdInfoMap=new HashMap<>(); //创建映射。

            for(ShortcutInfo shortcutInfo: shortcutInfos) //一个个地显示。
            {
                String title = shortcutInfo.getShortLabel().toString(); //获取标题。
                String shortcutId= shortcutInfo.getId(); //获取编号

                shortcutTitleInfoMap.put(title, shortcutInfo); //加入映射。
                shortcutIdInfoMap.put(shortcutId, shortcutInfo); //加入映射。
            } //for(ShortcutInfo shortcutInfo: shortcutInfos) //一个个地显示。
        } //if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) //26之后才有钉住的快捷方式。
    } //private void buildShortcutTitleInfoMap(List<ShortcutInfo> shortcutInfos)

    /**
     * 检查权限。
     */
    private void checkPermission()
    {
      if (hasPermission()) 
      {
      }
      else 
      {
        requestPermission();
      }
    } //private void checkPermission()

    /**
     * 请求获取权限
     */
    private void requestPermission()
    {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //动态权限
      {
        if ( shouldShowRequestPermissionRationale(PERMISSION_STORAGE)  || shouldShowRequestPermissionRationale(PERMISSION_RECORD_AUDIO) || shouldShowRequestPermissionRationale(PERMISSION_FINE_LOCATIN)  || shouldShowRequestPermissionRationale(PERMISSION_INSTALL_PACKAGE)) //应当告知原因。
        {
          Toast.makeText(this, "Camera AND storage permission are required for this demo", Toast.LENGTH_LONG).show();
        } //if ( shouldShowRequestPermissionRationale(PERMISSION_STORAGE)  || shouldShowRequestPermissionRationale(PERMISSION_RECORD_AUDIO)) //应当告知原因。
        requestPermissions(new String[] {PERMISSION_STORAGE, PERMISSION_RECORD_AUDIO, PERMISSION_FINE_LOCATIN, PERMISSION_INSTALL_PACKAGE}, PERMISSIONS_REQUEST);
      } //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //动态权限
    } //private void requestPermission()

    private boolean hasPermission()
    {
      boolean result=false; //结果。

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //安卓6.
      {
        ArrayList<String> articleInfoArrayList = new ArrayList<>(); // 权限列表。
        
        articleInfoArrayList.add(PERMISSION_STORAGE);
        articleInfoArrayList.add(PERMISSION_RECORD_AUDIO);
        articleInfoArrayList.add(PERMISSION_FINE_LOCATIN);
        articleInfoArrayList.add(PERMISSION_INSTALL_PACKAGE); // 安装应用程序的权限。
        
        for(String permissionString: articleInfoArrayList) // 一个个检查
        {
          result=(checkSelfPermission(permissionString) == PackageManager.PERMISSION_GRANTED); //录音权限。
          
          if (!result) // 没有权限
          {
            break; // 没有权限。
          } // if (!result) // 没有权限
        } // for(String permissionString: articleInfoArrayList) // 一个个检查
      
//         result= checkSelfPermission(PERMISSION_STORAGE) == PackageManager.PERMISSION_GRANTED; //存储权限。
// 
//         if (result) //存储权限已有。
//         {
//           result=(checkSelfPermission(PERMISSION_RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED); //录音权限。
//         } //if (result) //存储权限已有。
      } //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //安卓6.
      else //旧版本。
      {
        result=true; //有权限。
      } //else //旧版本。

      return result;
    } //private boolean hasPermission()

    /**
     * 寻找数据文件。要忽略的服务器回复映射
     * @return 寻找到的数据文件
     */
    private File findServerVoiceCommandReponseIgnoreMapFile()
    {
        File result=null;

        File filesDir=getFilesDir();

        if (filesDir==null) //该目录不存在。
        {

        } //if (filesDir==null) //该目录不存在。
        else //该目录存在。
        {
            result=new File(filesDir.getAbsolutePath()+"/serverVoiceCommandIgnoreMap.cx"); //指定文件名。

            if (result.exists()) //文件存在。
            {

            } //if (result.exists()) //文件存在。
            else //文件不存在。
            {
                try
                {
                    boolean createResult=result.createNewFile(); //创建文件。

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            } //else //文件不存在。
        } //else //该目录存在。

        return result;

    } //private File findServerVoiceCommandReponseIgnoreMapFile()

    /**
     * 寻找数据记录文件。
     * @return 数据记录文件．语音识别结果与快捷方式之间的映射文件．
     */
    private File findVoiceShortcutIdMapFile()
    {
        File result=null;

        File filesDir=getFilesDir();

        if (filesDir==null) //该目录不存在。
        {

        } //if (filesDir==null) //该目录不存在。
        else //该目录存在。
        {
            result=new File(filesDir.getAbsolutePath()+"/voiceShortcutIdMap.etn"); //指定文件名。

            if (result.exists()) //文件存在。
            {

            } //if (result.exists()) //文件存在。
            else //文件不存在。
            {
                try
                {
                    boolean createResult=result.createNewFile(); //创建文件。

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            } //else //文件不存在。
        } //else //该目录存在。

        return result;
    } //private File findVoiceShortcutIdMapFile()

    /**
     * 寻找语音识别与软件包映射文件。
     * @return 语音识别与软件包映射文件。
     */
    private  File findVoicePackageMapFile()
    {
      File result=null;

      File filesDir=getFilesDir();

      Log.d(TAG, "1459, findRandomPhotoFile, files dir: "+ filesDir); //Debug.

      if (filesDir==null) //该目录不存在。
      {
      } //if (filesDir==null) //该目录不存在。
      else //该目录存在。
      {
        result=new File(filesDir.getAbsolutePath()+"/voicePackageNameMap.proto"); //指定文件名。

        Log.d(TAG, "1469, findRandomPhotoFile, files exists: "+ result.exists() + ", size: " + result.length()); //Debug.

        if (result.exists()) //文件存在。
        {
        } //if (result.exists()) //文件存在。
        else //文件不存在。
        {
          try
          {
            boolean createResult=result.createNewFile(); //创建文件。

                    Log.d(TAG, "findRandomPhotoFile, create file result: " + createResult); //Debug.

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            } //else //文件不存在。
        } //else //该目录存在。

        return result;
    } //private  File findRandomPhotoFile()

    /**
     * 考虑要不要初始化讯飞语音识别。
     */
    private void assessInitializeMsc()
    {
      long startTimestamp=System.currentTimeMillis(); // 记录开始时间戳。
      Log.w(TAG, "assessInitializeMsc, 1630, enter assessInitializeMsc, timestamp: " + System.currentTimeMillis()); //Debug.
      if (mscIsInitialized) //已经初始化。
      {
      } //if (mscIsInitialized) //已经初始化。
      else  //尚未初始化。
      {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected) //网络已经连接。
        {
          Log.w(TAG, "assessInitializeMsc, 1643, assessInitializeMsc, timestamp: " + System.currentTimeMillis()); //Debug.

          initializeMsc(); //初始化讯飞语音识别。

          Log.w(TAG, "assessInitializeMsc, 1647, assessInitializeMsc, timestamp: " + System.currentTimeMillis()); //Debug.

          mscIsInitialized=true; //已经初始化。
        } //if (isConnected) //网络已经连接。
      } //else  //尚未初始化。
      long endTimestamp=System.currentTimeMillis(); // 记录开始时间戳。
      Log.w(TAG, "assessInitializeMsc, 1649, leave assessInitializeMsc, timestamp: " + System.currentTimeMillis()); //Debug.
      Log.d(TAG, "assessInitializeMsc, 1650, time in assessInitializeMsc: " + (endTimestamp-startTimestamp)); // 报告，onCreate 所花的时间。
    } //private void assessInitializeMsc()

    /**
     * 初始化MSC。
     */
    private void initializeMsc()
    {
      ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

      NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
      boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

      Log.w(TAG, "initializeMsc, 729, timestamp: " + System.currentTimeMillis() + ", active network: " + activeNetwork + ", is connected: " + isConnected); //Debug.

      Log.w(TAG, "initializeMsc, 720, timestamp: " + System.currentTimeMillis()); //Debug.

      SpeechUtility.createUtility(this, SpeechConstant.APPID + "=56e142d3"); //创建工具。
      Log.w(TAG, "initializeMsc, 723, timestamp: " + System.currentTimeMillis()); //Debug.

      mIat= SpeechRecognizer.createRecognizer(this, null);
      Log.w(TAG, "initializeMsc, 725, timestamp: " + System.currentTimeMillis()); //Debug.
    } //private void initializeMsc()

    /**
     * 注册启动器应用的回调对象。
     */
    private void registerLauncherAppsCallback()
    {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) //21之后才有启动器应用对象。
      {
        LauncherApps launcherApps=(LauncherApps) (getSystemService(Context.LAUNCHER_APPS_SERVICE)); //获取启动器应用对象。

        LauncherApps.Callback launcherAppsCallback=new LauncherAppsCallback(); //创建回调对象。

        launcherApps.registerCallback(launcherAppsCallback); //注册回调对象。
      }
    } //private void registerLauncherAppsCallback()

    @Override
    protected void onNewIntent(Intent intent)
    {
      Log.d(TAG, "onNewIntent, intent: " + intent); //Debug.
      super.onNewIntent(intent);

      String action=intent.getAction(); //获取动作。

      if (PACKAGE_INSTALLED_ACTION.equals(intent.getAction())) 
      {
        Bundle extras = intent.getExtras();

        int status = extras.getInt(PackageInstaller.EXTRA_STATUS);
        String message = extras.getString(PackageInstaller.EXTRA_STATUS_MESSAGE);
            
        Log.d(TAG, "onNewIntent, status: " + status + ", message: " + message); // Debug.

        switch (status) 
        {
          case PackageInstaller.STATUS_PENDING_USER_ACTION:
            // This test app isn't privileged, so the user has to confirm the install.
            Intent confirmIntent = (Intent) extras.get(Intent.EXTRA_INTENT);
            startActivity(confirmIntent);
            break;

          case PackageInstaller.STATUS_SUCCESS:
            Toast.makeText(this, "Install succeeded!", Toast.LENGTH_SHORT).show();
            break;

          case PackageInstaller.STATUS_FAILURE:
          case PackageInstaller.STATUS_FAILURE_ABORTED:
          case PackageInstaller.STATUS_FAILURE_BLOCKED:
          case PackageInstaller.STATUS_FAILURE_CONFLICT:
          case PackageInstaller.STATUS_FAILURE_INCOMPATIBLE:
          case PackageInstaller.STATUS_FAILURE_INVALID:
          case PackageInstaller.STATUS_FAILURE_STORAGE:
            Toast.makeText(this, "Install failed! " + status + ", " + message, Toast.LENGTH_SHORT).show();
            break;
          default:
            Toast.makeText(this, "Unrecognized status received from installer: " + status, Toast.LENGTH_SHORT).show();
        }
      }
    }

    /**
     * 广播接收器。
     */
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()
    {
      private final String TAG="BroadcastReceiver"; //!<输出调试信息时使用的标记。

      @SuppressWarnings("ConstantConditions")
      @Override
      /**
      * 接收到广播。
      */
      public void onReceive(Context context, Intent intent)
      {
        String action = intent.getAction(); //获取广播中带的动作字符串。

        Log.d(TAG,"1587, onReceive,got broadcast:"+action + ", equals package_added?: " + (Intent.ACTION_PACKAGE_ADDED.equals(action))); //Debug.

        if (Constants.NativeMessage.APPLICATION_LAUNCHED.equals(action)) //虚拟卡启动结果。
        {
          Bundle extras=intent.getExtras(); //获取参数包。
          String packageName=extras.getString(Constants.NativeMessage.APPLICATION_LAUNCHED_PACKAGE_KEY); //get the progress value.

          countApplicationLaunch(packageName); //检查是否要显示错误码。
        } //if (Constants.NativeMessage.APPLICATION_LAUNCHED.equals(action)) //虚拟卡启动结果。
        else if (UnlinkVoiceCommand.equals(action)) //断开语音指令关联。
        {
          Bundle extras=intent.getExtras(); //获取参数包。
          boolean uid=extras.getBoolean("useHiveLayout"); //获取状态符，是否要可见。

          unlinkVoiceCommand(); //断开语音指令关联。
        } //else if (Intent.ACTION_PACKAGE_ADDED.equals(action)) //应用被安装。
      } //public void onReceive(Context context, Intent intent)
    }; //private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()

    /**
     * 断开语音指令关联。
     */
    private void unlinkVoiceCommand()
    {
        Log.d(TAG, ",unlinkVoiceCommand, stack length: " + voiceCommandHitDataStack.size()); //Debug.
        VoiceCommandHitDataObject commandToUnlink=null; //最终要断开关联的对象

        //寻找要断开关联的命令对象：
        if (voiceCommandHitDataStack.empty()) //栈为空，不行动
        {

        } //if (voiceCommandHitDataStack.empty()) //栈为空，不行动
        else  //栈不为空，做事
        {
            VoiceCommandHitDataObject voiceCommandHitDataObject=voiceCommandHitDataStack.pop(); //获取最后一个

            String packageName=voiceCommandHitDataObject.getPackageName(); //获取包名。

            Log.d(TAG, ",unlinkVoiceCommand, stack length: " + voiceCommandHitDataStack.size()+ ", packange name: " + packageName); //Debug.
        } //else  //栈不为空，做事

        Log.d(TAG, ",unlinkVoiceCommand, stack length: " + voiceCommandHitDataStack.size()+ ", command unlink: " + commandToUnlink); //Debug.
        Log.d(TAG, ",unlinkVoiceCommand, stack length: " + voiceCommandHitDataStack.size()+ ", command unlink: " + commandToUnlink.getPackageName()); //Debug.
        Log.d(TAG, ",unlinkVoiceCommand, stack length: " + voiceCommandHitDataStack.size()+ ", command unlink: " + commandToUnlink.getActivityName()); //Debug.
        Log.d(TAG, ",unlinkVoiceCommand, stack length: " + voiceCommandHitDataStack.size()+ ", command unlink: " + commandToUnlink.getIconType()); //Debug.


        //断开关联：
        if (commandToUnlink!=null) //有内容
        {
            VoiceCommandSourceType voiceCommandSourceType=commandToUnlink.getVoiceCommandSourceType(); //获取语音命中方式。
            LauncherIconType launcherIconType=commandToUnlink.getIconType(); //获取图标类型
            String voiceCommandString=commandToUnlink.getVoiceRecognizeResult(); //获取语音识别结果
            String voiceCommandPackage=commandToUnlink.getPackageName(); //获取包名
            String voiceCommandActivityName=commandToUnlink.getActivityName(); //获取活动名

            if (voiceCommandSourceType==LocalVoiceCommandMap) //本地映射
            {
                if (launcherIconType==ActivityIconType) //是应用图标
                {
                } //if (launcherIconType==ActivityIconType) //是应用图标
                else  //是快捷方式图标
                {
                    HxShortcutInfo hxShortcutInfo=voiceShortcutIdMap.get(voiceCommandString); //获取快捷方式对象
                    String packageName=hxShortcutInfo.packageName; //获取包名。
                    String activityName=hxShortcutInfo.shortcutId; //获取快捷方式编号。

                    if ((packageName.equals(voiceCommandPackage)) && (activityName.equals(voiceCommandActivityName))) //一致
                    {
                        voiceShortcutIdMap.remove(voiceCommandString, hxShortcutInfo); //删除映射
                    } //if ((packageName.equals(voiceCommandPackage)) && (activityName.equals(voiceCommandActivityName))) //一致
                } //else  //是快捷方式图标
            } //if (voiceCommandSourceType==LocalVoiceCommandMap) //本地映射
        } //if (commandToUnlink!=null) //有内容
    } //private void unlinkVoiceCommand()

    /**
    * 检查该应用是不是已经安装了。
    */
    private boolean checkInstalled(String packageName) 
    {
      PackageManager packageManager=getPackageManager(); //获取软件包管理器。
      
      boolean result=false; // 结果。
      
      try
      {
        PackageInfo packageInfo=packageManager.getPackageInfo(packageName,0); //获取对应的软件包信息。

        result=true;
      }
      catch (PackageManager.NameNotFoundException e) //未找到该软件包。
      {
        e.printStackTrace(); //报告错误。
      } //catch (PackageManager.NameNotFoundException e) //未找到该软件包。

      return result;
    } // private bool checkInstalled(String internationalizationName)

    /**
     * 检查是否要显示错误信息。
     * @param packageName 错误码。
     */
    private void countApplicationLaunch(String packageName)
    {
        PackageCountObject packageCountObject=packageNameCountObjectMap.get(packageName); //获取对应的计数对象。

        if (packageCountObject!=null) //计数对象存在。
        {
            packageCountObject.incrementLikelyCount(); //受欢迎度计数。

            PackageCountObject firstObjectInList=packageCountObjectList.get(0);  //获取第一个计数对象。

            if (!(firstObjectInList.getPackageName().equals(packageName))) //点击的不是第一个软件包。
            {
                firstObjectInList.decrementLikelyCount(); //受欢迎度降低。
            } //else //点击的不是第一个软件包。
        } //if (packageCountObject!=null) //计数对象存在。
    } //private void checkErrorCode(int errorCode)

    /**
     * 启动友军“21点关机”的服务。
     */
    protected void startFriendShutDownAt2100Service()
    {
      Intent intent = new Intent();
      intent.setComponent(new ComponentName("com.stupidbeauty.shutdownat2100androidnative", "com.stupidbeauty.shutdownat2100androidnative.TimeCheckService")); //设置组件。

      try //启动服务
      {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) //前台服务
        {
          startForegroundService(intent); //启动前台服务
        } //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) //前台服务
        else //没有前台服务
        {
          startService(intent); //启动服务。
        } //else //没有前台服务
      } //try //启动服务
      catch (IllegalStateException e) //不允许启动服务。
      {
        e.printStackTrace(); //报告错误。
      } //catch (IllegalStateException e) //不允许启动服务。
    } //protected void startFriendShutDownAt2100Service()

    /**
     * 活动被暂停。
     */
    @Override
    protected void onPause()
    {
      super.onPause();

      activityHasBeenResumed=false; //不是处于正常运行状态。
    } //protected void onPause()

    /**
     * 考虑，是否要发送语音指令关联应用数据。
     */
    private void assesSendVoiceAssociationData()
    {
      if (sentVoiceAssociationData) //已经成功发送。
      {
      } //if (sentVoiceAssociationData) //已经成功发送。
    } //private void assesSendVoiceAssociationData()

    /**
     * 考虑是否要重新启动语音识别过程。
     */
    private void assesRestartSpeechRecognize()
    {
    } //private void assesRestartSpeechRecognize()

    /**
     * 振动。
     */
    private void vibrate()
    {
        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate( 100);
    } //private void vibrate()

    /**
     * 在线命令词识别。
     */
    public void commandRecognizebutton2()
    {
      recognizeCounter=recognizeCounter+1; //计数．

      voiceEndDetected=false; //重置状态，未探测到用户的声音结束。

      foundActivity=false; //重置，未找到对应的活动。

      if (mIat==null) //识别器未创建。
      {
        mIat= SpeechRecognizer.createRecognizer(this,null); //创建识别器。
      } //if (mIat==null) //识别器未创建。

      if (mIat==null) //仍然创建失败。
      {
      } //if (mIat==null) //仍然创建失败。
      else //创建成功。
      {
        Log.i(TAG, "commandRecognizebutton2, starting voice recognize. Is listening: "+ mIat.isListening()); //Debug.

        if (!setParam()) //参数设置失败。
        {
          return;
        } //if (!setParam()) //参数设置失败。

        String appNamePrompt=getString(R.string.appNamePrompt); // 获取提示的应用名字。

        String myString = "Messages";
        int myInt = 12;
        String formatted = getString(R.string.sayToMeAppName, appNamePrompt);
      } //else //创建成功。
    } //public void commandRecognizebutton2()

    /**
     * 参数设置
     *
     * @return 是否设置成功。
     */
    public boolean setParam()
    {
      boolean result = false;

      if (mIat!=null) //识别器存在。
      {
        // 设置识别引擎
        String mEngineType = SpeechConstant.TYPE_CLOUD;

        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);

        // 设置返回结果为json格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");

        // 设置云端识别使用的语法id
        mIat.setParameter(SpeechConstant.DOMAIN,"iat");

        setLanguageAndAccentParameters(); //设置语言及区域参数字符串。

        String vadEos=mIat.getParameter(SpeechConstant.VAD_EOS); //获取默认的后端点时间。

        Log.d(TAG,"setParam, default vad eos: "+vadEos); //Debug.

        mIat.setParameter(SpeechConstant.VAD_EOS, "100"); //后端点时间长度。

        mIat.setParameter(SpeechConstant.ASR_PTT, "0"); //不要标点符号。https://www.xfyun.cn/doc/asr/voicedictation/Android-SDK.html#_2%E3%80%81sdk%E9%9B%86%E6%88%90%E6%8C%87%E5%8D%97

        result = true;

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");

        recordSoundFilePath=Environment.getExternalStorageDirectory() + "/msc/asr."+ recognizeCounter +".wav"; //构造录音文件路径．

        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, recordSoundFilePath); //设置录音存储路径。

        mIat.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "false"); //不获取焦点。
      } //if (mIat!=null) //识别器存在。

      return result;
    }

    /**
     * 设置语言及区域参数字符串。
     */
    private void setLanguageAndAccentParameters()
    {
      boolean foundDirectLanguage=false; //是否已经直接找到匹配的语言

      //获取系统当前的语言。
      Locale locale=Locale.getDefault(); //获取默认语系。

      String androidLocaleName=locale.toString(); //获取语系名字。

      Locale zhCnLocale=Locale.SIMPLIFIED_CHINESE;

      if (androidLocaleName.startsWith("zh_CN")) //简体中文。
      {
        mIat.setParameter(SpeechConstant.LANGUAGE,"zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");

        foundDirectLanguage=true;
      } //简体中文。
      else if (androidLocaleName.startsWith("en")) //英语
      {
        mIat.setParameter(SpeechConstant.LANGUAGE,"en_us");

        foundDirectLanguage=true;
      } //else if (androidLocaleName.startsWith("en")) //英语
      else //其它语言。后面还有机会选择
      {
        mIat.setParameter(SpeechConstant.LANGUAGE,"en_us");

        foundDirectLanguage=false; //未直接找到匹配的语言
      } //else //英语。

      Locale[] locales = Locale.getAvailableLocales();
      ArrayList<String> localcountries=new ArrayList<String>();
      for(Locale l:locales)
      {
        localcountries.add(l.getDisplayLanguage().toString());
      }
      
      String[] languages=(String[]) localcountries.toArray(new String[localcountries.size()]);

      if (foundDirectLanguage) //直接找到了语言
      {
      } //if (foundDirectLanguage) //直接找到了语言
      else //未直接找到语言
      {
        LocaleList localeList;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) //选择多种语言
        {
          localeList= LocaleList.getDefault(); //获取默认语言列表

          int localeSize=localeList.size();

          int localeCounter=0;

          for(localeCounter=0; localeCounter< localeSize; localeCounter++)
          {
            Locale locale1=localeList.get(localeCounter);

            androidLocaleName=locale1.toString(); //获取语系名字。

            Log.d(TAG, "setLanguageAndAccentParameters, candidate language: " + androidLocaleName+ ", locale counter: " + localeCounter); //Debug.

            if (androidLocaleName.startsWith("zh_CN")) //简体中文。
            {
              mIat.setParameter(SpeechConstant.LANGUAGE,"zh_cn");
              mIat.setParameter(SpeechConstant.ACCENT, "mandarin");

              foundDirectLanguage=true;
            } //简体中文。
            else if (androidLocaleName.startsWith("en")) //英语
            {
              mIat.setParameter(SpeechConstant.LANGUAGE,"en_us");

              foundDirectLanguage=true;
            } //else if (androidLocaleName.startsWith("en")) //英语
            else //其它语言。后面还有机会选择
            {
              mIat.setParameter(SpeechConstant.LANGUAGE,"en_us");

              foundDirectLanguage=false; //未直接找到匹配的语言
            } //else //英语。

            if (foundDirectLanguage) //找到了。
            {
              break; //不用再找了
            } //if (foundDirectLanguage) //找到了。
          } //for(localeCounter=0; localeCounter< localeSize; localeCounter++)
        } //if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) //选择多种语言
      } //else //未直接找到语言
    } //private void setLanguageAndAccentParameters()

    @Override
    /**
     * 活动被停止。被另一个活动完全覆盖。
     */
    protected void onStop()
    {
        super.onStop(); //超类停止。

        saveApplicationLaunchCount(); //保存应用的启动计数数据。
    } //protected void onStop()

    /**
     * 保存应用的启动计数数据。
     */
    private void saveApplicationLaunchCount()
    {
    } //private void saveApplicationLaunchCount()

    /**
     * 获取活动条目的位置。
     * @param packageItemInfopackageName 包名。
     * @param packageItemInfoname 活动类名。
     * @return 图标的位置编号。
     */
    public int getItemPosition(String packageItemInfopackageName, String packageItemInfoname)
    {
        Log.d(TAG, "getItemPosition, packageNameItemNamePositionMap: " + packageNameItemNamePositionMap); //Debug.
        int result=0; //结果。

        if (packageItemInfoname!=null) //有类名。
        {
            Integer resultInteger=packageNameItemNamePositionMap.get(packageItemInfopackageName+"/"+packageItemInfoname); //从映射中查找对应的数字。

            if (resultInteger!=null) //不是空指针。
            {
                result=resultInteger; //从映射中查找。
            } //if (resultInteger!=null) //不是空指针。
        } //if (packageItemInfoname!=null) //有类名。
        else //没有类名。
        {
            Log.d(TAG, "getItemPosition, packageNamePositionMap: " + packageNamePositionMap + ", package name: " + packageItemInfopackageName); //Debug.

            Integer resultInteger1=packageNamePositionMap.get(packageItemInfopackageName); //从映射中查找。

            if (resultInteger1!=null) //不是空指针。
            {
                result=resultInteger1; //解包。
            } //if (resultInteger1!=null) //不是空指针。
        } //else //没有类名。

        return result;
    } //public int getItemPosition(String packageItemInfopackageName, String packageItemInfoname)

    /**
     * 解析启动意图。
     */
    private void solveLauncherIntents()
    {
      ArrayList<ArticleInfo> articleInfoArrayList = new ArrayList<>(); // 创建应用列表。

      Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
      mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

      PackageManager packageManager=getPackageManager(); //获取软件包管理器。

      List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent, 0);

      int position=0; //条目的位置。

      //按照活动的标题来匹配：
      for (ResolveInfo temp : appList) //遍历解析结果，并一个个加入到列表中。
      {
        Intent activitylaunchIntent=new Intent(mainIntent);
        activitylaunchIntent.setClassName(temp.activityInfo.packageName, temp.activityInfo.name); //设置类名。

        CharSequence activityapplicationLabel=temp.activityInfo.loadLabel(packageManager); //获取文字标记。

        ArticleInfo currentApplication=new ArticleInfo(); //创建应用程序信息对象。

        currentApplication.setApplicationLabel(activityapplicationLabel); //设置应用程序文字。
        currentApplication.setLaunchIntent(activitylaunchIntent); //设置启动意图。
        currentApplication.setPackageName(temp.activityInfo.packageName); //设置包名。
        currentApplication.setActivityName(temp.activityInfo.name); //设置活动名字。

        articleInfoArrayList.add(currentApplication); //添加应用。

        packageNameItemNamePositionMap.put(temp.activityInfo.packageName+"/"+temp.activityInfo.name, position); //记录映射。
        packageNamePositionMap.put(temp.activityInfo.packageName, position); //记录映射。

        position++; //计数。
      } //for (ResolveInfo temp : appList) //遍历解析结果，并一个个加入到列表中。
    } //private void solveLauncherIntents()

    /**
    * 显示信息页面。
    */
    private void showUrlPage(String internationalizationName) 
    {
      try
      {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(internationalizationName)));
      }
      catch (ActivityNotFoundException e)
      {
        e.printStackTrace();
      }
      catch (NullPointerException e) // 捕获空指针异常。
      {
        e.printStackTrace();
      }
    } // private void  showInformationPage(String internationalizationName)

  /**
  * 从映射中寻找目标快捷方式的包名。从用户自己积累的映射中寻找。
  * @param voiceShortcutIdMap 映射对象
  * @return 找到的包名
  */
  private String findVoiceTargetMapShortcutPackageName(HashMap<String, HxShortcutInfo> voiceShortcutIdMap)
  {
    String result="";
      
    if (voiceShortcutIdMap!=null) // The map exists
    {
      if (voiceShortcutIdMap.containsKey(voiceRecognizeResultString)) //有对应的映射关系。用户自己积累的语音指令与包条目映射。
      {
        String packageName=voiceShortcutIdMap.get(voiceRecognizeResultString).packageName; //获取包名。

        result=packageName; //命中了。
      } //if (voicePackageNameMap.contains(voiceRecognizeResultString)) //有对应的映射关系。
    } // if (voiceShortcutIdMap!=null) // The map exists

    return result;
  } //private String findVoiceTargetMapShortcutPackageName(HashMap<String, HxShortcutInfo> voiceShortcutIdMap)

  /**
  * 从映射中寻找目标快捷方式，并启动。
  * @param voiceShortcutIdMap 要进行寻找的映射．
  * @return　是否寻找到了．
  */
  private boolean findVoiceTargetMapShortcutAndLaunch(HashMap<String, HxShortcutInfo> voiceShortcutIdMap)
    {
      boolean result=false; //结果，是否命中了活动。
      
      if (voiceShortcutIdMap!=null) // The map exists. Loaded.
      {
        if (voiceShortcutIdMap.containsKey(voiceRecognizeResultString)) //有对应的映射关系。用户自己积累的语音指令与包条目映射。
        {
          String packageName=voiceShortcutIdMap.get(voiceRecognizeResultString).packageName; //获取包名。
          String activityName=voiceShortcutIdMap.get(voiceRecognizeResultString).shortcutId; //获取快捷方式编号。

          result=true; //命中了。

          rememberVoiceCommandHitData(voiceRecognizeResultString, packageName, activityName, ShortcutIconType, LocalVoiceCommandMap); //记录语音识别命中应用的数据

          result=launchShortcut(packageName, activityName); //启动快捷方式．

          List<String> idList= Arrays.asList(activityName); //构造编号列表．

          if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N_MR1) //25之后才有快捷方式编号。
          {
            LauncherApps.ShortcutQuery query=new LauncherApps.ShortcutQuery(); //创建查询器．

            int queryFlag= FLAG_MATCH_PINNED | FLAG_MATCH_MANIFEST | FLAG_MATCH_DYNAMIC; //查询标志位

            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.P) //25之后才有快捷方式编号。
            {
              queryFlag = queryFlag | FLAG_MATCH_PINNED_BY_ANY_LAUNCHER;
            } //if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N_MR1) //25之后才有快捷方式编号。

            query.setQueryFlags(queryFlag); //设置标志位．

            query.setShortcutIds(idList); //设置编号列表．
            query.setPackage(packageName); //设置包名．

            LauncherApps launcherApps=(LauncherApps) (getSystemService(Context.LAUNCHER_APPS_SERVICE)); //获取启动器应用对象。

            List<ShortcutInfo> shortcutInfos=launcherApps.getShortcuts(query, Process.myUserHandle()); //获取快捷方式列表．

            if (shortcutInfos.size()>0) //有快捷方式。
            {
              ShortcutInfo shortcutInfo=shortcutInfos.get(0); //获取第一个快捷方式信息．

              String shortcutTitle=shortcutInfo.getShortLabel().toString(); //获取标题．

              reportVoiceCommandHitData(voiceRecognizeResultString, packageName, activityName, recordSoundFilePath, ShortcutIconType, shortcutTitle); //报告语音识别命中应用的数据。命中快捷方式．
            } //if (shortcutInfos.size()) //有快捷方式。
          } //if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N_MR1) //25之后才有快捷方式编号。
        } //if (voicePackageNameMap.contains(voiceRecognizeResultString)) //有对应的映射关系。
      } // if (voiceShortcutIdMap!=null) // The map exists. Loaded.

      return result;
    } //private boolean findVoiceTargetMapShortcutAndLaunch(HashMap<String, HxShortcutInfo> voiceShortcutIdMap)

    /**
     * 记录语音识别命中应用的数据
     * @param voiceRecognizeResultString 语音识别结果
     * @param packageName 包名
     * @param activityName 活动名
     * @param activityIconType 目标类型。活动还是快捷方式
     */
    private void rememberVoiceCommandHitData(String voiceRecognizeResultString, String packageName, String activityName, LauncherIconType activityIconType, VoiceCommandSourceType voiceCommandSourceType)
    {
        VoiceCommandHitDataObject voiceCommandHitDataObject=new VoiceCommandHitDataObject(); //创建实例

        voiceCommandHitDataObject.setVoiceRecognizeResult(voiceRecognizeResultString);
        voiceCommandHitDataObject.setPackageName(packageName);
        voiceCommandHitDataObject.setActivityName(activityName);
        voiceCommandHitDataObject.setIconType(activityIconType);
        voiceCommandHitDataObject.setVoiceCommandSourceType(voiceCommandSourceType);

        voiceCommandHitDataStack.push(voiceCommandHitDataObject); //加入栈中

        Log.d(TAG, "rememberVoiceCommandHitData, stack size: " + voiceCommandHitDataStack.size()); //Debug.
    } //private boolean rememberVoiceCommandHitData(String voiceRecognizeResultString, String packageName, String activityName, LauncherIconType activityIconType)

    /**
     * 报告语音识别命中应用的数据。
     * @param voiceRecognizeResultString 语音识别结果字符串。
     * @param packageName 命中的包名。
     */
    private void reportVoiceCommandHitData(String voiceRecognizeResultString, String packageName, String activityName, String recordSoundFilePath, LauncherIconType iconType, String iconTitle)
    {
        Log.d(TAG, "reportVoiceCommandHitData, result: " + voiceRecognizeResultString + ", title: " + iconTitle); //Debug.

        DownloadFailureReportTask translateRequestSendTask =new DownloadFailureReportTask(); // 创建异步任务。

        translateRequestSendTask.execute(voiceRecognizeResultString, packageName, activityName, recordSoundFilePath, iconType, iconTitle); //执行任务。
    } //private void reportVoiceCommandHitData(String voiceRecognizeResultString, String packageName)

    /**
     * 启动快捷方式。
     * @param shortcutInfo 要启动的快捷方式信息对象。
     */
    public void launchShortcut(ShortcutInfo shortcutInfo)
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N_MR1) //25之后才有快捷方式编号。
        {
            String shortcutId=shortcutInfo.getId(); //Get the shortcut id.

            String packageName=shortcutInfo.getPackage(); //获取包名。

            launchShortcut(packageName, shortcutId); //启动快捷方式。
        } //if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N_MR1) //25之后才有快捷方式编号。
    } //public void launchShortcut(ShortcutInfo shortcutInfo)

    /**
     * 启动快捷方式。
     * @param shortcutInfo 要启动的快捷方式信息对象。
     */
    public boolean launchShortcut(String packageName, String shortcutId)
    {
        boolean launchSuccess=false; //是否成功启动。

        try //尝试启动快捷方式
        {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N_MR1) //25之后才有快捷方式编号。
            {
                LauncherApps launcherApps=(LauncherApps) (getSystemService(Context.LAUNCHER_APPS_SERVICE)); //获取启动器应用对象。

                launcherApps.startShortcut(packageName, shortcutId, null, null, Process.myUserHandle()); //启动快捷方式。

                launchSuccess=true; //成功启动。

            } //if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N_MR1) //25之后才有快捷方式编号。
        } //try //尝试启动快捷方式
        catch (ActivityNotFoundException e)
        {
          e.printStackTrace();
        } //catch (ActivityNotFoundException e)

        return launchSuccess;
    } //public void launchShortcut(ShortcutInfo shortcutInfo)

    /**
     * 检查该个活动是否被导出了。
     * @param launchIntent 要检查的意图。
     * @return 活动是否被导出了。
     */
    private boolean checkExported(Intent launchIntent)
    {
      boolean result=true; //结果。

      if (launchIntent!=null) //启动意图存在。
      {
        String packageName=launchIntent.getComponent().getPackageName(); //获取包名。
        String activityName=launchIntent.getComponent().getClassName(); //获取活动的类名。

        String activity=activityName; //活动名字。

        ComponentName cm = new ComponentName(packageName, activity); //构造组件名字对象。

        PackageManager packageManager=getPackageManager(); //获取包管理器。

        try 
        {
          ActivityInfo activityInfo=packageManager.getActivityInfo(cm, 0); //获取活动信息。

          result=activityInfo.exported; //是否导出。
        }
        catch (PackageManager.NameNotFoundException e)
        {
          e.printStackTrace();
        } //catch (PackageManager.NameNotFoundException e)
      } //if (launchIntent!=null) //启动意图存在。
      else //启动意图不存在。
      {
        result=false; //未导出。
      } //else //启动意图不存在。

      return result;
    } //private boolean checkExported(Intent launchIntent)

    /**
     * 主动崩溃。
     */
    private void crashIntended()
    {
       startActivity(null);
    } //private void crashIntended()

    /**
     * 检查启动的冷却时间。
     * @param launchIntent 启动意图。
     * @return 根据冷却时间，是否允许启动。
     */
    private boolean checkLaunchCoolDownTime(Intent launchIntent)
    {
        boolean result=false; //结果，是否允许启动。

        String packageName=""; //包名。
        String activityName=""; //活动类名。

        if (launchIntent!=null) //意图存在。
        {
             packageName=launchIntent.getComponent().getPackageName(); //获取包名。

             activityName=launchIntent.getComponent().getClassName(); //获取活动的类名。
        } //if (launchIntent!=null) //意图存在。

        if (activityName.startsWith(".")) //相对名字。
        {
            activityName=packageName+activityName; //构造完整名字。
        } //if (activityName.startsWith(".")) //相对名字。

        return result;
    } //private boolean checkLaunchCoolDownTime(Intent launchIntent)

    /**
     * 显示软件包列表。
     * @param packageInfoList 软件包列表。
     */
    private void showInstalledPackages(List<PackageInfo>  packageInfoList)
    {
      solveLauncherIntents(); //解析启动意图，并填充数据。
    } //void showInstalledPackages(List<PackageInfo>  packageInfoList)

    /**
     * 检查并更正启动意图中的类名。
     * @param launchIntent 要检查的启动意图。
     */
    private void checkAndCorrectClassNameInLauncherIntent(Intent launchIntent)
    {
        String className=launchIntent.getComponent().getClassName(); //获取类名。

        int indexOfDollar=className.indexOf('$'); //寻找美元符号。

        if (indexOfDollar>0) //存在美元符号。
        {
            int indexBeforeDollar=indexOfDollar-1; //复制到前一个字符为止。
            String correctedClassName=className.substring(0, indexBeforeDollar); //切出开头的部分。

            String packageName=launchIntent.getComponent().getPackageName(); //获取包名。

            launchIntent.setClassName(packageName, correctedClassName); //设置类名。
        } //if (indexBeforeDollar>0) //存在美元符号。
    } //private void checkAndCorrectClassNameInLauncherIntent(Intent launchIntent)
}
