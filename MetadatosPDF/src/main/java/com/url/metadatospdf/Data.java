package com.url.metadatospdf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import javax.swing.DefaultListModel;

public class Data {
    private DefaultListModel modelo = new DefaultListModel();
    private String path;
    private Node s;
    public Data(){
        s=null;
    }
    public DefaultListModel Files1(File Folder){
        modelo = new DefaultListModel();
        return Files2(Folder);
    }
    private DefaultListModel Files2(File Folder){
        for(File file:Folder.listFiles()){
            
            if(!file.isDirectory()){
                Node n=new Node();
                
                n.setFile(file);
                if(s==null){
                    n.setNext(null);
                    s=n;
                }else{
                   Node prev=prev(s,null);
                   prev.setNext(n);
                   n.setNext(null);
                }
                
                modelo.addElement(file.getName());            
            }
            else Files1(file);
        }
        return modelo;
    }
    private Node prev(Node aux,Node value){
        while(aux.getNext()!=value && aux!=null){
           aux=aux.getNext();
        }
        return aux;
    }
    public String values(String name) throws IOException{
       String r="";
       Node aux=s;
       while(!aux.getFile().getName().equals(name) && aux!=null){
           aux=aux.getNext();
       }
       File f=aux.getFile();
       BasicFileAttributes at= Files.readAttributes(f.toPath(), BasicFileAttributes.class);
       r="Name: "+f.getName()+"\nPath: "+f.toPath()
                +"\nSize: "+at.size()
                +"B \nCreationTime: "+at.creationTime()
                +"\nLastModified:  "+at.lastModifiedTime();
       return r;
    }
}
