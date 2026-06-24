package com.ruoyi.vote.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.vote.domain.VoteRecord;
import com.ruoyi.vote.mapper.VoteRecordMapper;
import com.ruoyi.vote.mapper.VoteVoterMapper;
import com.ruoyi.vote.service.IVoteRecordService;

/**
 * 投票记录 服务层实现
 *
 * @author ruoyi
 */
@Service
public class VoteRecordServiceImpl implements IVoteRecordService
{
    @Autowired
    private VoteRecordMapper voteRecordMapper;

    @Autowired
    private VoteVoterMapper voteVoterMapper;

    @Override
    public List<VoteRecord> selectVoteRecordList(VoteRecord voteRecord)
    {
        return voteRecordMapper.selectVoteRecordList(voteRecord);
    }

    @Override
    public int deleteVoteRecordByIds(Long[] recordIds)
    {
        return voteRecordMapper.deleteVoteRecordByIds(recordIds);
    }

    @Override
    @Transactional
    public int clearByActivityId(Long activityId)
    {
        voteRecordMapper.deleteVoteRecordByActivityId(activityId);
        return voteVoterMapper.deleteVoteVoterByActivityId(activityId);
    }
}
