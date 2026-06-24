package com.ruoyi.vote.service;

import java.util.List;
import com.ruoyi.vote.domain.VoteActivity;

/**
 * 投票活动 服务层
 *
 * @author ruoyi
 */
public interface IVoteActivityService
{
    public VoteActivity selectVoteActivityById(Long activityId);

    public List<VoteActivity> selectVoteActivityList(VoteActivity voteActivity);

    public int insertVoteActivity(VoteActivity voteActivity);

    public int updateVoteActivity(VoteActivity voteActivity);

    public int deleteVoteActivityByIds(Long[] activityIds);

    public int deleteVoteActivityById(Long activityId);
}
