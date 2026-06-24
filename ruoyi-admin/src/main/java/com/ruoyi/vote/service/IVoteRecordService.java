package com.ruoyi.vote.service;

import java.util.List;
import com.ruoyi.vote.domain.VoteRecord;

/**
 * 投票记录 服务层
 *
 * @author ruoyi
 */
public interface IVoteRecordService
{
    public List<VoteRecord> selectVoteRecordList(VoteRecord voteRecord);

    public int deleteVoteRecordByIds(Long[] recordIds);

    /** 清空某活动的全部投票（记录 + 投票人） */
    public int clearByActivityId(Long activityId);
}
