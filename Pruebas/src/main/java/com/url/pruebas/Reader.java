/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.url.pruebas;


import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USUARIO
 */
public class Reader {
    public Reader(){
        
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
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
       }
        return val;
    }
    public void Read(String path){
        try {
            RandomAccessFile archive=new RandomAccessFile(path,"r");
            long xRef=xRefN(path);
            archive.seek(xRef);
            archive.readLine();
            String n=archive.readLine();
            int v=num(n);
            System.out.println(""+v);
            for (int i=0;i<v;i++){
                String Line = archive.readLine();
                if(Line.charAt(Line.length() - 1) != 'f'){
                    System.out.println(""+ Line);
                    int j = 0;
                    String k = "";
                    while(Line.charAt(j) == 0){
                       j++;
                    }
                    for(j = j; j < 10; j++){
                        k = k + Line.charAt(j);
                    }
                    int number = Integer.parseInt(k);
                    this.readByte(number, path);
                }
                
               
           }
            
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void readByte(int x, String path){
        try {
            RandomAccessFile archive=new RandomAccessFile(path,"r");
            archive.seek(x);
            String n=archive.readLine();
            System.out.println(""+n);
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
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
}
