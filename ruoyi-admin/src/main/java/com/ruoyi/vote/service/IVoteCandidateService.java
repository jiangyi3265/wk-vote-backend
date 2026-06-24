package com.ruoyi.vote.service;

import java.util.List;
import com.ruoyi.vote.domain.VoteCandidate;

/**
 * 投票候选人 服务层
 *
 * @author ruoyi
 */
public interface IVoteCandidateService
{
    public VoteCandidate selectVoteCandidateById(Long candidateId);

    public List<VoteCandidate> selectVoteCandidateList(VoteCandidate voteCandidate);

    public int insertVoteCandidate(VoteCandidate voteCandidate);

    public int updateVoteCandidate(VoteCandidate voteCandidate);

    public int deleteVoteCandidateByIds(Long[] candidateIds);

    public int deleteVoteCandidateById(Long candidateId);
}
