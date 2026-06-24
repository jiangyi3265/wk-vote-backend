package com.ruoyi.vote.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.vote.domain.VoteOption;
import com.ruoyi.vote.mapper.VoteOptionMapper;
import com.ruoyi.vote.service.IVoteOptionService;

/**
 * 投票评选维度 服务层实现
 *
 * @author ruoyi
 */
@Service
public class VoteOptionServiceImpl implements IVoteOptionService
{
    @Autowired
    private VoteOptionMapper voteOptionMapper;

    @Override
    public VoteOption selectVoteOptionById(Long optionId)
    {
        return voteOptionMapper.selectVoteOptionById(optionId);
    }

    @Override
    public List<VoteOption> selectVoteOptionList(VoteOption voteOption)
    {
        return voteOptionMapper.selectVoteOptionList(voteOption);
    }

    @Override
    public int insertVoteOption(VoteOption voteOption)
    {
        if (voteOption.getActivityId() == null)
        {
            throw new ServiceException("所属活动不能为空");
        }
        if (voteOption.getStatus() == null || voteOption.getStatus().isEmpty())
        {
            voteOption.setStatus("0");
        }
        return voteOptionMapper.insertVoteOption(voteOption);
    }

    @Override
    public int updateVoteOption(VoteOption voteOption)
    {
        return voteOptionMapper.updateVoteOption(voteOption);
    }

    @Override
    public int deleteVoteOptionByIds(Long[] optionIds)
    {
        return voteOptionMapper.deleteVoteOptionByIds(optionIds);
    }

    @Override
    public int deleteVoteOptionById(Long optionId)
    {
        return voteOptionMapper.deleteVoteOptionById(optionId);
    }
}
