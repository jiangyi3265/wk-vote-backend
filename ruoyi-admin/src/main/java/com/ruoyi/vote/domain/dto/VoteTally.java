package com.ruoyi.vote.domain.dto;

/**
 * 票数统计行（候选人 + 维度 + 票数）
 *
 * @author ruoyi
 */
public class VoteTally
{
    private Long candidateId;

    private Long optionId;

    private Long votes;

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

    public Long getVotes()
    {
        return votes;
    }

    public void setVotes(Long votes)
    {
        this.votes = votes;
    }
}
