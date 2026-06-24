package com.ruoyi.vote.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 投票人 vote_voter
 *
 * @author ruoyi
 */
public class VoteVoter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 投票人ID */
    private Long voterId;

    /** 活动ID */
    private Long activityId;

    /** 投票人姓名 */
    private String voterName;

    /** 客户端标识(防重复) */
    private String clientId;

    /** IP */
    private String ip;

    /** 已使用票数 */
    private Integer votesUsed;

    public Long getVoterId()
    {
        return voterId;
    }

    public void setVoterId(Long voterId)
    {
        this.voterId = voterId;
    }

    public Long getActivityId()
    {
        return activityId;
    }

    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }

    public String getVoterName()
    {
        return voterName;
    }

    public void setVoterName(String voterName)
    {
        this.voterName = voterName;
    }

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public Integer getVotesUsed()
    {
        return votesUsed;
    }

    public void setVotesUsed(Integer votesUsed)
    {
        this.votesUsed = votesUsed;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("voterId", getVoterId())
            .append("activityId", getActivityId())
            .append("voterName", getVoterName())
            .append("clientId", getClientId())
            .append("ip", getIp())
            .append("votesUsed", getVotesUsed())
            .append("createTime", getCreateTime())
            .toString();
    }
}
