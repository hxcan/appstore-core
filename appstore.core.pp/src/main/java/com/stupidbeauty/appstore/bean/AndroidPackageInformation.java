package com.stupidbeauty.appstore.bean;

import java.util.ArrayList;
import java.util.List;

public class AndroidPackageInformation {

    private String packageName;
    private String appName;
    private String infoUrl;
    private String installUrl;
    private String packageType;
    private String versionCode;
    private String iconUrl;
    private long lastModified;
    private List<String> extraPackageNames;

    public AndroidPackageInformation() {
        this.extraPackageNames = new ArrayList<>();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public String getInstallUrl() {
        return installUrl;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public List<String> getExtraPackageNames() {
        return extraPackageNames;
    }

    public void addExtraPackageName(String packageName) {
        this.extraPackageNames.add(packageName);
    }

    public void removeExtraPackageName(String packageName) {
        this.extraPackageNames.remove(packageName);
    }
}
