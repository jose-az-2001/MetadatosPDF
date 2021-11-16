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
             RandomAccessFile archive=new RandomAccessFile("archive.txt","rw");
            Data wr=new Data();
            archive.seek(archive.length());
            Node Nw=new Node();
           
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
    
    public void posiciones(){
        try { 
            RandomAccessFile archive=new RandomAccessFile("archive.txt","rw");
            archive.seek(archive.length());
            Node aux=Root;
            while(aux.getNext()!=null){
                System.out.println(""+aux.getPos());
                archive.writeChars("\n"+aux.getPos());
                aux=aux.getNext();
            }
          archive.writeChars("\n");
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public void nombre(long pos){
        /*Esta funcion lee los nombres de los pdf*/
        try { 
             RandomAccessFile archive=new RandomAccessFile("archive.txt","rw");
             archive.seek(pos);
             System.out.println(""+archive.readLine());
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public void addDesc(String Desc){
        try { 
             RandomAccessFile archive=new RandomAccessFile("archive.txt","rw");
             Data wr=new Data();
             archive.seek(archive.length());
            archive.writeChars(Desc);
           archive.writeChars("\n \n");
            archive.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
