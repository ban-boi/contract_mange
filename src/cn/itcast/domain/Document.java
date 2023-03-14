package cn.itcast.domain;

import jakarta.servlet.http.Part;

public class Document {
    private byte[] document;
    private  String doc_name;

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }
}
