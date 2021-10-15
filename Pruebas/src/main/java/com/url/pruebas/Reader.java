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
            archive.close();
       } catch (FileNotFoundException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
       }
        return val;
    }
    public String Read(String path){
        String r = " ";
        try {
            RandomAccessFile archive=new RandomAccessFile(path,"r");
            long xRef=xRefN(path);
            archive.seek(xRef);
            archive.readLine();
            String n=archive.readLine();
            do{
                int v=num(n);
                System.out.println(""+v);
                String l="";
                for (int i=0;i<v;i++){
                    l=archive.readLine();
                    if(l.charAt(l.length()-1)!='f'){
                        //printObj(TB(l), path);
                        r+=this.searchMetadata(TB(l), path);
                    }
                }
                n=archive.readLine();
            }while(!n.equals("trailer"));
            archive.close();
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
           Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(r);
        return r;
    }
    private int num(String f){
        String  r="";
        int i;
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
    private void printObj(long pos, String path){
        try {
            RandomAccessFile archive=new RandomAccessFile(path,"r");
            archive.seek(pos);
            String l=" ";
            while(!l.equals("endobj")){
               
               l=archive.readLine();
               System.out.println(""+l);
            }
          
        } catch (FileNotFoundException ex) {
           Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
           Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String searchMetadata(long pos, String path){
        String r = " ";
        try {
            RandomAccessFile archive=new RandomAccessFile(path,"r");
            archive.seek(pos);
            archive.readLine();
            String l=" ";
                l=archive.readLine();
                for(int i = 0; i < l.length(); i++){
                    if(l.charAt(i) == '/'){
                        if(!this.CountSearchFunction(l).equals("")){
                            r+=(this.CountSearchFunction(l));
                        }
                        if(!this.TitleSearchFunction(l).equals("")){
                            r+=(this.TitleSearchFunction(l));
                        }
                        if(!this.KeyWordsSearchFunction(l).equals("")){
                            r+=(this.KeyWordsSearchFunction(l));
                        }
                        if(!this.ImageSearchFunction(l).equals("")){
                            r+=(this.ImageSearchFunction(l));
                        }
                        if(!this.FontSearchFunction(l).equals("")){
                            r+=(this.FontSearchFunction(l));
                        }
                        break;
                    }
                }
                //System.out.println(""+l);
        } catch (FileNotFoundException ex) {
           Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
           Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(r);
        return r;
    }
    
    
    private String CountSearchFunction(String Line){
        String r = "";
        for(int i = 0; i < Line.length()-6; i++){
            if(Line.charAt(i) == '/' &&Line.charAt(i+1) == 'C' && Line.charAt(i+2) == 'o' && Line.charAt(i+3) == 'u' && Line.charAt(i+4) == 'n' && Line.charAt(i+5) == 't'){
                
                do{
                    r = r + Line.charAt(i);
                    i++;
                }while(Line.charAt(i) != '/' || i == Line.length()-1);
                break;
            }
        }
        return r;
    }
    
    private String TitleSearchFunction(String Line){
        String r = "";
        for(int i = 0; i < Line.length()-6; i++){
            if(Line.charAt(i) == '/' &&Line.charAt(i+1) == 'T' && Line.charAt(i+2) == 'i' && Line.charAt(i+3) == 't' && Line.charAt(i+4) == 'l' && Line.charAt(i+5) == 'e'){
                do{
                    r = r + Line.charAt(i);
                    i++;
                }while(Line.charAt(i) != '/' || i == Line.length()-1);
                break;
            }
        }
        return r;
    }
    
    private String KeyWordsSearchFunction(String Line){
        String r = "";
        for(int i = 0; i < Line.length()-6; i++){
            if(Line.charAt(i) == '/' &&Line.charAt(i+1) == 'K' && Line.charAt(i+2) == 'e' && Line.charAt(i+3) == 'y' && Line.charAt(i+4) == 'w' && Line.charAt(i+5) == 'o' && Line.charAt(i+5) == 'r' && Line.charAt(i+5) == 'd' && Line.charAt(i+5) == 's' ){
                do{
                    r = r + Line.charAt(i);
                    i++;
                }while(Line.charAt(i) != '/' || i == Line.length()-1);
                break;
            }
        }
        return r;
    }
    
    private String ImageSearchFunction(String Line){
        String r = "";
        for(int i = 0; i < Line.length()-6; i++){
            if(Line.charAt(i) == '/' &&Line.charAt(i+1) == 'I' && Line.charAt(i+2) == 'm' && Line.charAt(i+3) == 'a' && Line.charAt(i+4) == 'g' && Line.charAt(i+5) == 'e'){
                for(int j = i; j < i+7; j++){
                    r = r + Line.charAt(j);
                }
                break;
            }
        }
        return r;
    }
    
    private String FontSearchFunction(String Line){
        String r = "";
        for(int i = 0; i < Line.length()-6; i++){
            if(Line.charAt(i) == '/' &&Line.charAt(i+1) == 'F' && Line.charAt(i+2) == 'o' && Line.charAt(i+3) == 'n' && Line.charAt(i+4) == 't' && Line.charAt(i+5) == 'N' && Line.charAt(i+5) == 'a' && Line.charAt(i+5) == 'm' && Line.charAt(i+5) == 'e'){
                do{
                    r = r + Line.charAt(i);
                    i++;
                }while(Line.charAt(i) != '/' || i == Line.length()-1);
                break;
            }
        }
        return r;
    }
}
