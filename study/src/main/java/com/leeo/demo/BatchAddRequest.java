package com.leeo.demo;

import java.util.Collections;
import java.util.List;

/**
 * Created by lijun on 2016/6/25.
 */
public class BatchAddRequest {
    private List<AddRecordRequest> records = Collections.EMPTY_LIST;

    public BatchAddRequest() {
    }

    public BatchAddRequest(List<AddRecordRequest> records) {
        this.records = records;
    }

    public List<AddRecordRequest> getRecords() {
        return records;
    }

    public void setRecords(List<AddRecordRequest> records) {
        this.records = records;
    }
}
