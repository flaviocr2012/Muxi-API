package com.flaviocr.MuxiAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "TB_TERMINAL")
public class TerminalModel {

    @Id
    @JsonProperty("logic")
    @Column(updatable = false)
    private int logic;
    @JsonProperty("serial")
    private String serial;
    @JsonProperty("model")
    private String model;
    @JsonProperty("sam")
    private int sam;
    @JsonProperty("ptid")
    private String ptid;
    @JsonProperty("plat")
    private int plat;
    @JsonProperty("version")
    private String version;
    @JsonProperty("logic")
    private int mxr;
    @JsonProperty("logic")
    private int mxf;
    @JsonProperty("logic")
    private String verfm;

}
