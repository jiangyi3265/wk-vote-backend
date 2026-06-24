package com.ruoyi.vote.mapper;

import java.util.List;
import com.ruoyi.vote.domain.VoteOption;

/**
 * 投票评选维度 数据层
 *
 * @author ruoyi
 */
public interface VoteOptionMapper
{
    public VoteOption selectVoteOptionById(Long optionId);

    public List<VoteOption> selectVoteOptionList(VoteOption voteOption);

    public int insertVoteOption(VoteOption voteOption);

    public int updateVoteOption(VoteOption voteOption);

    public int deleteVoteOptionById(Long optionId);

    public int deleteVoteOptionByIds(Long[] optionIds);

    public int deleteVoteOptionByActivityId(Long activityId);
}
