package com.ruoyi.vote.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.vote.domain.VoteVoter;

/**
 * 投票人 数据层
 *
 * @author ruoyi
 */
public interface VoteVoterMapper
{
    public VoteVoter selectVoteVoterById(Long voterId);

    public List<VoteVoter> selectVoteVoterList(VoteVoter voteVoter);

    public int insertVoteVoter(VoteVoter voteVoter);

    /** 统计某活动某客户端是否已投票 */
    public int countByClient(@Param("activityId") Long activityId, @Param("clientId") String clientId);

    /** 统计某活动的投票人数 */
    public int countByActivityId(Long activityId);

    public int deleteVoteVoterByActivityId(Long activityId);
}
