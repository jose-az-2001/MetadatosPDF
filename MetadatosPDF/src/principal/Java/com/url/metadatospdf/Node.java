package com.url.metadatospdf;

import java.io.File;

public class Node {
   File file;
   Node next;
   long pos;
    public Node(){
        this.file=null;
        this.next=null;
        this.pos=(long)0;
   } 
   public void setFile(File file){
       this.file=file;
   }
   public File getFile(){
       return this.file;
   }
    public long getPos() {
        return this.pos;
    }
    public void setPos(long pos){
        this.pos=pos;
    }
   public void setNext(Node next){
       this.next=next;
   }
   public Node getNext(){
       return this.next;
   }
}
