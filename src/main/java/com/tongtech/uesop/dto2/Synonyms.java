package com.tongtech.uesop.dto2;

import java.util.Date;
import lombok.Data;

@Data
public class Synonyms {
    private Integer id;

    private String questionName;

    private Integer hot;

    private Date updateTime;

    private String status;

    private String semanticStatus;

    private String source;

    private String administrativeDivision;

    private String maintenanceAgency;

    private String terminalType;

    private String shelvesStatus;

    private String sourceType;

    private String linEvent;
}