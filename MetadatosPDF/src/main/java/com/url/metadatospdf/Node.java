package com.url.metadatospdf;

import java.io.File;

public class Node {
   File file;
   Node next;
    public Node(){
        this.file=null;
        this.next=null;
   } 
   public void setFile(File file){
       this.file=file;
   }
   public File getFile(){
       return this.file;
   }
   public void setNext(Node next){
       this.next=next;
   }
   public Node getNext(){
       return this.next;
   }
}
