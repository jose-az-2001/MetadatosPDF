/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.url.metadatospdf;

/**
 *
 * @author USUARIO
 */
public class PDFNode {
    PDFNode next;
    String name;
    String Descripcion;

    public PDFNode getNext() {
        return next;
    }

    public void setNext(PDFNode next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
}
