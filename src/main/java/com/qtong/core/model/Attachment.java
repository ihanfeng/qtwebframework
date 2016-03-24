package com.qtong.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.net.URI;
import java.util.Date;

/**
 * Created by ZML on 2015/8/7.
 */

@Entity
@Table(name = "t_attachment")
public class Attachment {

    private String attchId;//附件ID

    @JsonIgnore
    private URI serverPath;//文件储存路径

    private long length;

    private String extension;

    private Date createTime;

    private String groupflag;

    private String fileName;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "attch_id")
    public String getAttchId() {
        return attchId;
    }

    public void setAttchId(String attchId) {
        this.attchId = attchId;
    }

    public URI getServerPath() {
        return serverPath;
    }

    public void setServerPath(URI serverPath) {
        this.serverPath = serverPath;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGroupflag() {
        return groupflag;
    }

    public void setGroupflag(String groupflag) {
        this.groupflag = groupflag;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
