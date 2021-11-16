package com.url.metadatospdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class Data {
    private DefaultListModel modelo = new DefaultListModel();
    private String path;
    private Node s;
    private Writer w=new Writer();
    private Reader read = new Reader();
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
                String fileN=file.getName();
                if("pdf".equals(l3(fileN))){
                    
                    modelo.addElement(fileN);
                    w.addPdf(file);
                    try {
                        w.addDesc(values(fileN));
                        
                        //System.out.println("txt"+file);
                    } catch (IOException ex) {
                        Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }            
            }
            else Files1(file);
        }
        w.posiciones();
        File binario=new File("archive.txt");
        if(binario.exists()){
            rb();
        }
        return modelo;
    }
    public void rp(){
       rb();
       
    }
    public DefaultListModel rb(){
    DefaultListModel r=new DefaultListModel();
       try { 
             RandomAccessFile archives=new RandomAccessFile("archive.txt","rw");
           archives.seek(0);
           String L="";
           while(archives.getFilePointer()!=archives.length()){
               L=archives.readLine();
               System.out.println(""+pd3(L));
               if(pd3(L).equals("pdf")){
                   break;
               }
           }
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
       }
     return r;
   }
    
  /* public void nombre(long pos){
        //Esta funcion lee los nombres de los pdf
        try { 
             RandomAccessFile archive=new RandomAccessFile("archive.txt","rw");
             archive.seek(pos);
             System.out.println(""+archive.readLine());
             archive.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
       }
    }*/
    private String pd3(String L){
        String r="";
        for(int i=1;i<4;){
            r+=L.charAt(L.length()-i);
        }
        return r;
    }
    private String l3(String fileN){
        String r="";
        for(int i=fileN.length()-1;i>=fileN.length()-3;i--){
            r=fileN.charAt(i)+r;
        }
       
        return r;
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
       String Pathvar = f.toPath().toString();
        
       String a = read.Read(Pathvar);

       r="Name: "+f.getName()+
               "\nPath: "+f.toPath()
                +"\nSize: "+at.size()+
               "bytes \n" + a;
               
       return r;
    }
}
