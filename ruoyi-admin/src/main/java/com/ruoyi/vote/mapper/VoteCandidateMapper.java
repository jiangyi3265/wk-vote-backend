package com.ruoyi.vote.mapper;

import java.util.List;
import com.ruoyi.vote.domain.VoteCandidate;

/**
 * 投票候选人 数据层
 *
 * @author ruoyi
 */
public interface VoteCandidateMapper
{
    public VoteCandidate selectVoteCandidateById(Long candidateId);

    public List<VoteCandidate> selectVoteCandidateList(VoteCandidate voteCandidate);

    public int insertVoteCandidate(VoteCandidate voteCandidate);

    public int updateVoteCandidate(VoteCandidate voteCandidate);

    public int deleteVoteCandidateById(Long candidateId);

    public int deleteVoteCandidateByIds(Long[] candidateIds);

    public int deleteVoteCandidateByActivityId(Long activityId);
}
