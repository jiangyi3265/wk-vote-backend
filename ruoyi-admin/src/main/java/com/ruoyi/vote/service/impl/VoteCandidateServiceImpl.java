package com.ruoyi.vote.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.vote.domain.VoteCandidate;
import com.ruoyi.vote.mapper.VoteCandidateMapper;
import com.ruoyi.vote.service.IVoteCandidateService;

/**
 * 投票候选人 服务层实现
 *
 * @author ruoyi
 */
@Service
public class VoteCandidateServiceImpl implements IVoteCandidateService
{
    @Autowired
    private VoteCandidateMapper voteCandidateMapper;

    @Override
    public VoteCandidate selectVoteCandidateById(Long candidateId)
    {
        return voteCandidateMapper.selectVoteCandidateById(candidateId);
    }

    @Override
    public List<VoteCandidate> selectVoteCandidateList(VoteCandidate voteCandidate)
    {
        return voteCandidateMapper.selectVoteCandidateList(voteCandidate);
    }

    @Override
    public int insertVoteCandidate(VoteCandidate voteCandidate)
    {
        if (voteCandidate.getActivityId() == null)
        {
            throw new ServiceException("所属活动不能为空");
        }
        if (voteCandidate.getStatus() == null || voteCandidate.getStatus().isEmpty())
        {
            voteCandidate.setStatus("0");
        }
        return voteCandidateMapper.insertVoteCandidate(voteCandidate);
    }

    @Override
    public int updateVoteCandidate(VoteCandidate voteCandidate)
    {
        return voteCandidateMapper.updateVoteCandidate(voteCandidate);
    }

    @Override
    public int deleteVoteCandidateByIds(Long[] candidateIds)
    {
        return voteCandidateMapper.deleteVoteCandidateByIds(candidateIds);
    }

    @Override
    public int deleteVoteCandidateById(Long candidateId)
    {
        return voteCandidateMapper.deleteVoteCandidateById(candidateId);
    }
}
