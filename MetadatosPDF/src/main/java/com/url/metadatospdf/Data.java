package com.url.metadatospdf;

import java.io.File;

public class Data {
    public Data(){
    }
    public void Files(File Folder){
        for(File file:Folder.listFiles()){
            if(!file.isDirectory())System.out.println("" + file.getName());
            else Files(file);
        }
    }
}
