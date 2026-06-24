package com.ruoyi.vote.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 投票记录 vote_record
 *
 * @author ruoyi
 */
public class VoteRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 活动ID */
    private Long activityId;

    /** 投票人ID */
    private Long voterId;

    /** 候选人ID */
    private Long candidateId;

    /** 维度ID */
    private Long optionId;

    /** 非持久化：投票人姓名 */
    private String voterName;

    /** 非持久化：候选人姓名 */
    private String candidateName;

    /** 非持久化：维度名称 */
    private String optionName;

    public Long getRecordId()
    {
        return recordId;
    }

    public void setRecordId(Long recordId)
    {
        this.recordId = recordId;
    }

    public Long getActivityId()
    {
        return activityId;
    }

    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }

    public Long getVoterId()
    {
        return voterId;
    }

    public void setVoterId(Long voterId)
    {
        this.voterId = voterId;
    }

    public Long getCandidateId()
    {
        return candidateId;
    }

    public void setCandidateId(Long candidateId)
    {
        this.candidateId = candidateId;
    }

    public Long getOptionId()
    {
        return optionId;
    }

    public void setOptionId(Long optionId)
    {
        this.optionId = optionId;
    }

    public String getVoterName()
    {
        return voterName;
    }

    public void setVoterName(String voterName)
    {
        this.voterName = voterName;
    }

    public String getCandidateName()
    {
        return candidateName;
    }

    public void setCandidateName(String candidateName)
    {
        this.candidateName = candidateName;
    }

    public String getOptionName()
    {
        return optionName;
    }

    public void setOptionName(String optionName)
    {
        this.optionName = optionName;
    }
}
