/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.url.metadatospdf;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USUARIO
 */
public class Reader2 {
     public Reader2(){
        
    }
    private long xRefN(String path){
        long val=0;
        try {
           RandomAccessFile archive=new RandomAccessFile(path,"r");
           String sr="";
           int c=0;
           while(c<1){
               sr=archive.readLine();
              if(sr.equals("startxref")){
                c++;
              }
           }
            sr=archive.readLine();
            val=Integer.valueOf(sr);
            archive.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Reader2.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Reader2.class.getName()).log(Level.SEVERE, null, ex);
       }
        return val;
    }
    public String Read(String path){
        String r=" ";
        try {
           RandomAccessFile archive=new RandomAccessFile(path,"r");
           long xRef=xRefN(path);
           archive.seek(xRef);
           archive.readLine();
           String n=archive.readLine();
           int v=num(n);
           int inf=Info(path);
           for (int i=0;i<v;i++){
                n=archive.readLine();
                if(n.charAt(n.length()-1)!='f'){
                    if(i==inf)
                  r=Vls(printObj(TB(n), path));    
               }   
            }
           
           archive.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Reader2.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Reader2.class.getName()).log(Level.SEVERE, null, ex);
       }
        return r;
    }
    private String Vls(String l){
      System.out.println(""+l);
        String wd="";
        char le=' ';
        int i=9;
        wd="\n Autor ";
            while((int)le!=41){
                i=i+1;
                le=l.charAt(i);
                wd+=le;
            }
           // System.out.println(""+wd);
           i=i+10;
           le=l.charAt(i);
           
            
            wd+="\n Programa ";
            while((int)le!=41){
                i=i+1;
                le=l.charAt(i);
                wd+=le;
            }
            i=i+16;
            le=l.charAt(i);
            wd+="\n Fecha de creacion";
            while((int)le!=41 ){
               i=i+1;
                le=l.charAt(i);
                wd+=le;
                
            }
            i=i+11;
            le=l.charAt(i);
            wd+="\n Fecha de Modificacion";
            while((int)le!=41){
               i=i+1;
                le=l.charAt(i);
                wd+=le;
                
            }
        return wd;
    }
    private int num(String f){

        String  r="";
        int i;
       // System.out.println("Largoooooooooooo"+f.length());
        for(i=f.length()-1;i>=0;i--){
            if(f.charAt(i)==' '){
                break;
            }
        }
        i++;
        for(int j=i;j<f.length();j++){
            r+=f.charAt(j);
        }
        return Integer.valueOf(r);
    }
    private long TB(String l){
        String r="";
        for(int i=0;i<10;i++){
            r+=l.charAt(i);
        }
        return Long.valueOf(r);
    }
    private String printObj(long pos, String path){
    String r="";
        try {
           RandomAccessFile archive=new RandomAccessFile(path,"r");
           archive.seek(pos);
           archive.readLine();
           r=archive.readLine();
          archive.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Reader2.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Reader2.class.getName()).log(Level.SEVERE, null, ex);
       }
        return r;
    }
    private int Info(String path){
        int n=0;
        try {
           RandomAccessFile archive=new RandomAccessFile(path,"r");
           String l=" ";
           while(!l.equals("trailer")){
               l=archive.readLine();
           }
           do{
                l=archive.readLine();
                if(l.length()>6){
                    n=word(l);
                }                
           }while(l.length()<12);
           archive.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Reader2.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Reader2.class.getName()).log(Level.SEVERE, null, ex);
       }
        return n;
    }
    private int word(String w){
        String r="0";
        int j=0;
        String wd="";
        for(int i=0;i<w.length();i++){
           wd=" ";
           wd+=w.charAt(i);
            if(i+1<w.length()){
                wd+=w.charAt(i+1);
            }
            if(i+2<w.length()){
                wd+=w.charAt(i+2);
            }
            if(i+3<w.length()){
                wd+=w.charAt(i+3);
            }           
            j=i;
            if(wd.equals(" Info")){
                break;
            }
        }
        if(wd.equals(" Info")){
            j=j+5;
            char le;
                do{
                    le=w.charAt(j);
                    if(le!=' ') r+=le;
                    j++;
                }while(le!=' ');                
        }
        return Integer.valueOf(r);
    }
}
