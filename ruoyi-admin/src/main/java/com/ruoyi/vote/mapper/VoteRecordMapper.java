package com.ruoyi.vote.mapper;

import java.util.List;
import com.ruoyi.vote.domain.VoteRecord;
import com.ruoyi.vote.domain.dto.VoteTally;

/**
 * 投票记录 数据层
 *
 * @author ruoyi
 */
public interface VoteRecordMapper
{
    public List<VoteRecord> selectVoteRecordList(VoteRecord voteRecord);

    public int insertVoteRecord(VoteRecord voteRecord);

    public int batchInsertVoteRecord(List<VoteRecord> list);

    public int deleteVoteRecordByIds(Long[] recordIds);

    public int deleteVoteRecordByActivityId(Long activityId);

    /** 按候选人+维度聚合票数 */
    public List<VoteTally> selectTally(Long activityId);

    /** 某活动总票数 */
    public int countByActivityId(Long activityId);
}
