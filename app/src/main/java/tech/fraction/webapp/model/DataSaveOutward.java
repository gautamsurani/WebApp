package tech.fraction.webapp.model;

import java.io.Serializable;

public class DataSaveOutward implements Serializable {
    private int RecordId;

    public int getRecordId() {
        return RecordId;
    }

    public void setRecordId(int recordId) {
        RecordId = recordId;
    }
}
