package com.guidelk.tourism.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MasterDataStatus {
    DELETED(0, "Deleted"),
    OPEN(1, "Open"),
    APPROVED(2, "Approved"),
    CLOSED(3, "Closed");

    private final Integer statusSeq;
    private final String status;

    MasterDataStatus(Integer statusSeq, String status) {
        this.statusSeq = statusSeq;
        this.status = status;
    }

    public Integer getStatusSeq() {
        return statusSeq;
    }

    public String getStatus() {
        return status;
    }

    public static MasterDataStatus findOne(Integer statusSeq) {
        return Arrays.stream(MasterDataStatus.values())
                .filter(x -> x.statusSeq.equals(statusSeq))
                .findFirst()
                .orElse(null);
    }
}
