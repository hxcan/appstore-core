package com.stupidbeauty.qtdocchinese;

import android.content.Intent;


/**
 * @author root Hxcan <caihuosheng@gmail.com>
 *
 */
public class ArticleInfo
{
  public boolean isAutoRun() {
    return autoRun;
  }

  public void setAutoRun(boolean autoRun) {
    this.autoRun = autoRun;
  }

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }

  private boolean autoRun=false; //!<是否自动启动。

  private String packageName; //!<应用程序包名。
  private String activityName; //!<活动名字。

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  private Intent launchIntent; //!<启动意图。

  public Intent getLaunchIntent() {
    return launchIntent;
  }

  public void setLaunchIntent(Intent launchIntent) {
    this.launchIntent = launchIntent;
  }

  private CharSequence applicationLabel; //!<应用程序名字标签。

  public CharSequence getApplicationLabel() {
    return applicationLabel;
  }

  public void setApplicationLabel(CharSequence applicationLabel) {
    this.applicationLabel = applicationLabel;
  }

}
