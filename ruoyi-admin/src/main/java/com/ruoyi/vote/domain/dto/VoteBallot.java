package com.ruoyi.vote.domain.dto;

/**
 * 单张选票（候选人 + 维度）
 *
 * @author ruoyi
 */
public class VoteBallot
{
    /** 候选人ID */
    private Long candidateId;

    /** 维度ID */
    private Long optionId;

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
}
