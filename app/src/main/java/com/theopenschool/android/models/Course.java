package com.theopenschool.android.models;

import com.google.firebase.firestore.DocumentId;

public class Course {

    @DocumentId
    private String documentId;

    private String title;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
