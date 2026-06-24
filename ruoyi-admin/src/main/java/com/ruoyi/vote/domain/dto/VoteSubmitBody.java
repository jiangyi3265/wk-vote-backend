package com.ruoyi.vote.domain.dto;

import java.util.List;

/**
 * 投票提交请求体
 *
 * @author ruoyi
 */
public class VoteSubmitBody
{
    /** 活动ID */
    private Long activityId;

    /** 投票人姓名 */
    private String voterName;

    /** 客户端标识（前端生成，用于防重复投票） */
    private String clientId;

    /** 选票列表 */
    private List<VoteBallot> ballots;

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

    public List<VoteBallot> getBallots()
    {
        return ballots;
    }

    public void setBallots(List<VoteBallot> ballots)
    {
        this.ballots = ballots;
    }
}
