package com.ruoyi.vote.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.vote.domain.VoteActivity;
import com.ruoyi.vote.domain.dto.VoteSubmitBody;

/**
 * 公开投票 服务层（无需登录）
 *
 * @author ruoyi
 */
public interface IVotePublicService
{
    /** 可参与/可查看的活动列表（进行中、已结束） */
    public List<VoteActivity> listPublicActivities();

    /** 活动详情：含候选人与评选维度 */
    public Map<String, Object> getActivityDetail(Long activityId);

    /** 提交投票，返回本次投票概要 */
    public Map<String, Object> submitVote(VoteSubmitBody body, String ip);

    /** 投票结果统计 */
    public Map<String, Object> buildResult(Long activityId);
}
