package com.ruoyi.vote.service;

import java.util.List;
import com.ruoyi.vote.domain.VoteOption;

/**
 * 投票评选维度 服务层
 *
 * @author ruoyi
 */
public interface IVoteOptionService
{
    public VoteOption selectVoteOptionById(Long optionId);

    public List<VoteOption> selectVoteOptionList(VoteOption voteOption);

    public int insertVoteOption(VoteOption voteOption);

    public int updateVoteOption(VoteOption voteOption);

    public int deleteVoteOptionByIds(Long[] optionIds);

    public int deleteVoteOptionById(Long optionId);
}
