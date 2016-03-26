package com.qtong.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.net.URI;
import java.util.Date;

/**
 * 附件类
 */

@Entity
@Table(name = "t_attachment")
public class Attachment {

    private String attchId;//附件ID

    @JsonIgnore
    private URI serverPath;//文件储存路径

    private long fileLength;//文件大小

    private String extension;//拓展名

    private Date createTime;//创建时间

    private String groupflag;//附件分组信息

    private String fileName;//文件名称

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "attch_id")
    public String getAttchId() {
        return attchId;
    }

    public void setAttchId(String attchId) {
        this.attchId = attchId;
    }

    @Column(name = "server_path")
    public URI getServerPath() {
        return serverPath;
    }

    public void setServerPath(URI serverPath) {
        this.serverPath = serverPath;
    }

    @Column(name = "file_length")
    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "group_flag")
    public String getGroupflag() {
        return groupflag;
    }

    public void setGroupflag(String groupflag) {
        this.groupflag = groupflag;
    }

    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
