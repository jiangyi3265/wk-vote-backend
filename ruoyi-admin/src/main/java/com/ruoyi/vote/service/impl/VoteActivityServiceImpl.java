package com.ruoyi.vote.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.vote.domain.VoteActivity;
import com.ruoyi.vote.mapper.VoteActivityMapper;
import com.ruoyi.vote.service.IVoteActivityService;

/**
 * 投票活动 服务层实现
 *
 * @author ruoyi
 */
@Service
public class VoteActivityServiceImpl implements IVoteActivityService
{
    @Autowired
    private VoteActivityMapper voteActivityMapper;

    @Override
    public VoteActivity selectVoteActivityById(Long activityId)
    {
        return voteActivityMapper.selectVoteActivityById(activityId);
    }

    @Override
    public List<VoteActivity> selectVoteActivityList(VoteActivity voteActivity)
    {
        return voteActivityMapper.selectVoteActivityList(voteActivity);
    }

    @Override
    public int insertVoteActivity(VoteActivity voteActivity)
    {
        if (voteActivity.getVotesPerPerson() == null || voteActivity.getVotesPerPerson() < 1)
        {
            voteActivity.setVotesPerPerson(8);
        }
        return voteActivityMapper.insertVoteActivity(voteActivity);
    }

    @Override
    public int updateVoteActivity(VoteActivity voteActivity)
    {
        return voteActivityMapper.updateVoteActivity(voteActivity);
    }

    @Override
    public int deleteVoteActivityByIds(Long[] activityIds)
    {
        return voteActivityMapper.deleteVoteActivityByIds(activityIds);
    }

    @Override
    public int deleteVoteActivityById(Long activityId)
    {
        return voteActivityMapper.deleteVoteActivityById(activityId);
    }
}
