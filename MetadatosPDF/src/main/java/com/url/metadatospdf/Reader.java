package com.url.metadatospdf;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public void Read(String path){
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
                        this.searchMetadata(TB(l), path);
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
    
    public void searchMetadata(long pos, String path){
        try {
            RandomAccessFile archive=new RandomAccessFile(path,"r");
            archive.seek(pos);
            archive.readLine();
            String l=" ";
                l=archive.readLine();
                for(int i = 0; i < l.length(); i++){
                    if(l.charAt(i) == '/'){
                        System.out.println("Encontre esto");
                        if(l.charAt(i+1) == 'C' && l.charAt(i+2) == 'o' && l.charAt(i+3) == 'u' && l.charAt(i+4) == 'n' && l.charAt(i+5) == 't'){
                            System.out.println("Esto si");
                            int j=i+7;
                            String Count = "";
                            
                            System.out.println("Numero de paginas: " + Count );
                        }
                    }
                }
                //System.out.println(""+l);
        } catch (FileNotFoundException ex) {
           Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
           Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
