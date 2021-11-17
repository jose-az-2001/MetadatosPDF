/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.url.metadatospdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class Writer {
    private Node n=null;
    private Node Root=new Node();
    public Writer(){
    
    }
    public void addPdf(File file){
        try { 
             RandomAccessFile archive=new RandomAccessFile("archive.bin","rw");
            Data wr=new Data();
            archive.seek(archive.length());
            Node Nw=new Node();
            archive.writeByte(1);
           
            if(n==null){
                n=Root;
            }else{
                n.setNext(Nw);
                n=Nw;
            }
            n.setPos(archive.getFilePointer());
            archive.writeChars(file.getName());
            archive.writeChars("\n");
            archive.close();
            
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public void addDesc(String Desc){
        try { 
             RandomAccessFile archive=new RandomAccessFile("archive.bin","rw");
             Data wr=new Data();
             archive.seek(archive.length());
            archive.writeChars(Desc);
           archive.writeChars("\n");
            archive.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public void posiciones(){
        try { 
            RandomAccessFile archive=new RandomAccessFile("archive.bin","rw");
            archive.seek(archive.length());
            Node aux=Root;
            archive.writeChars("///////");
            archive.writeChars("\n");
            while(aux.getNext()!=null){
               if(aux!=null){
                    archive.writeChars("\n"+aux.getPos());
               }
               aux=aux.getNext();
            }
          archive.writeChars("\n");
          archive.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
   
    
}
