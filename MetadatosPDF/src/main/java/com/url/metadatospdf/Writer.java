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
    private Reader r=new Reader();
    public Writer(){
    
    }
    public void addPdf(File file){
        try { 
             RandomAccessFile archive=new RandomAccessFile("archive.bin","rw");
             archive.seek(archive.length());
            archive.writeChars(file.getName());
            archive.writeChars(r.Read(file.getPath()));
            while(archive.getFilePointer()!=archive.length())
             System.out.println(""+archive.readLine());
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
