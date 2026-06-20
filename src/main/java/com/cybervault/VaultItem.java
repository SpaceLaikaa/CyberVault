package com.cybervault;

public abstract class VaultItem {
    private int id;
    private String appName;
    private String url;

    public VaultItem(int id,String appName,String url){
        this.id=id;
        this.appName=appName;
        this.url=url;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getAppName() {return appName;}
    public void setAppName(String appName) {this.appName = appName;}

    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}

    public abstract void displaySummary();
}
