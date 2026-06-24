package com.ruoyi.vote.mapper;

import java.util.List;
import com.ruoyi.vote.domain.VoteActivity;

/**
 * 投票活动 数据层
 *
 * @author ruoyi
 */
public interface VoteActivityMapper
{
    public VoteActivity selectVoteActivityById(Long activityId);

    public List<VoteActivity> selectVoteActivityList(VoteActivity voteActivity);

    public int insertVoteActivity(VoteActivity voteActivity);

    public int updateVoteActivity(VoteActivity voteActivity);

    public int deleteVoteActivityById(Long activityId);

    public int deleteVoteActivityByIds(Long[] activityIds);
}
