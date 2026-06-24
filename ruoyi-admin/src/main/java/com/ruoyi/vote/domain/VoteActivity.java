package com.ruoyi.vote.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 投票活动 vote_activity
 *
 * @author ruoyi
 */
public class VoteActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动ID */
    private Long activityId;

    /** 活动标题 */
    private String title;

    /** 活动说明 */
    private String description;

    /** 封面图 */
    private String cover;

    /** 每人可投票数 */
    private Integer votesPerPerson;

    /** 同一候选人同一维度可重复投(0否 1是) */
    private String multiPerPair;

    /** 投票是否需填写姓名(0否 1是) */
    private String requireName;

    /** 状态(0未开始 1进行中 2已结束) */
    private String status;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 删除标志(0存在 2删除) */
    private String delFlag;

    public Long getActivityId()
    {
        return activityId;
    }

    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }

    @NotBlank(message = "活动标题不能为空")
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCover()
    {
        return cover;
    }

    public void setCover(String cover)
    {
        this.cover = cover;
    }

    public Integer getVotesPerPerson()
    {
        return votesPerPerson;
    }

    public void setVotesPerPerson(Integer votesPerPerson)
    {
        this.votesPerPerson = votesPerPerson;
    }

    public String getMultiPerPair()
    {
        return multiPerPair;
    }

    public void setMultiPerPair(String multiPerPair)
    {
        this.multiPerPair = multiPerPair;
    }

    public String getRequireName()
    {
        return requireName;
    }

    public void setRequireName(String requireName)
    {
        this.requireName = requireName;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("activityId", getActivityId())
            .append("title", getTitle())
            .append("description", getDescription())
            .append("votesPerPerson", getVotesPerPerson())
            .append("multiPerPair", getMultiPerPair())
            .append("requireName", getRequireName())
            .append("status", getStatus())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
