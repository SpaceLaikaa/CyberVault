package com.cybervault.model;

public class SecureNote extends VaultItem{
    private String noteContent;

    public SecureNote(int id, String appName, String url, String noteContent) {
        super(id, appName, url);
        this.noteContent = noteContent;
    }

    public String getNoteContent() {return noteContent;}
    public void setNoteContent(String noteContent) {this.noteContent = noteContent;}

    @Override
    public void displaySummary(){
        System.out.println("Secure Note --> "+getAppName()+" - secret...");
    }
}
