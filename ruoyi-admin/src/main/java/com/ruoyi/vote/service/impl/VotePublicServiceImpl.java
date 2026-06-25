package com.ruoyi.vote.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.vote.domain.VoteActivity;
import com.ruoyi.vote.domain.VoteCandidate;
import com.ruoyi.vote.domain.VoteOption;
import com.ruoyi.vote.domain.VoteRecord;
import com.ruoyi.vote.domain.VoteVoter;
import com.ruoyi.vote.domain.dto.VoteBallot;
import com.ruoyi.vote.domain.dto.VoteSubmitBody;
import com.ruoyi.vote.domain.dto.VoteTally;
import com.ruoyi.vote.mapper.VoteActivityMapper;
import com.ruoyi.vote.mapper.VoteCandidateMapper;
import com.ruoyi.vote.mapper.VoteOptionMapper;
import com.ruoyi.vote.mapper.VoteRecordMapper;
import com.ruoyi.vote.mapper.VoteVoterMapper;
import com.ruoyi.vote.service.IVotePublicService;

/**
 * 公开投票 服务层实现
 *
 * @author ruoyi
 */
@Service
public class VotePublicServiceImpl implements IVotePublicService
{
    @Autowired
    private VoteActivityMapper voteActivityMapper;

    @Autowired
    private VoteCandidateMapper voteCandidateMapper;

    @Autowired
    private VoteOptionMapper voteOptionMapper;

    @Autowired
    private VoteVoterMapper voteVoterMapper;

    @Autowired
    private VoteRecordMapper voteRecordMapper;

    @Override
    public List<VoteActivity> listPublicActivities()
    {
        List<VoteActivity> all = voteActivityMapper.selectVoteActivityList(new VoteActivity());
        // 仅展示进行中(1) / 已结束(2)
        return all.stream()
                .filter(a -> "1".equals(a.getStatus()) || "2".equals(a.getStatus()))
                .filter(a -> !activeCandidates(a.getActivityId()).isEmpty() && !activeOptions(a.getActivityId()).isEmpty())
                .collect(Collectors.toList());
    }

    private List<VoteCandidate> activeCandidates(Long activityId)
    {
        VoteCandidate q = new VoteCandidate();
        q.setActivityId(activityId);
        q.setStatus("0");
        return voteCandidateMapper.selectVoteCandidateList(q);
    }

    private List<VoteOption> activeOptions(Long activityId)
    {
        VoteOption q = new VoteOption();
        q.setActivityId(activityId);
        q.setStatus("0");
        return voteOptionMapper.selectVoteOptionList(q);
    }

    @Override
    public Map<String, Object> getActivityDetail(Long activityId)
    {
        VoteActivity activity = voteActivityMapper.selectVoteActivityById(activityId);
        if (activity == null)
        {
            throw new ServiceException("活动不存在或已被删除");
        }
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("activity", activity);
        data.put("candidates", activeCandidates(activityId));
        data.put("options", activeOptions(activityId));
        data.put("voterCount", voteVoterMapper.countByActivityId(activityId));
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> submitVote(VoteSubmitBody body, String ip)
    {
        if (body == null || body.getActivityId() == null)
        {
            throw new ServiceException("参数错误");
        }
        VoteActivity activity = voteActivityMapper.selectVoteActivityById(body.getActivityId());
        if (activity == null)
        {
            throw new ServiceException("活动不存在或已被删除");
        }
        if (!"1".equals(activity.getStatus()))
        {
            throw new ServiceException("当前活动未开始或已结束，无法投票");
        }
        Date now = new Date();
        if (activity.getStartTime() != null && now.before(activity.getStartTime()))
        {
            throw new ServiceException("投票尚未开始");
        }
        if (activity.getEndTime() != null && now.after(activity.getEndTime()))
        {
            throw new ServiceException("投票已结束");
        }

        List<VoteBallot> ballots = body.getBallots();
        if (ballots == null || ballots.isEmpty())
        {
            throw new ServiceException("请至少投出一票");
        }
        int max = activity.getVotesPerPerson() == null ? 8 : activity.getVotesPerPerson();
        if (ballots.size() > max)
        {
            throw new ServiceException("最多只能投 " + max + " 票");
        }
        if ("1".equals(activity.getRequireName()) && StringUtils.isBlank(body.getVoterName()))
        {
            throw new ServiceException("请填写您的姓名后再投票");
        }
        // 防重复投票
        if (StringUtils.isNotBlank(body.getClientId())
                && voteVoterMapper.countByClient(activity.getActivityId(), body.getClientId()) > 0)
        {
            throw new ServiceException("您已经参与过本次投票啦~");
        }

        // 校验候选人/维度合法性
        Set<Long> validCandidates = activeCandidates(activity.getActivityId()).stream()
                .map(VoteCandidate::getCandidateId).collect(Collectors.toSet());
        Set<Long> validOptions = activeOptions(activity.getActivityId()).stream()
                .map(VoteOption::getOptionId).collect(Collectors.toSet());
        Set<String> seenPairs = new HashSet<>();
        Set<Long> seenOptions = new HashSet<>();
        for (VoteBallot b : ballots)
        {
            if (b.getCandidateId() == null || b.getOptionId() == null
                    || !validCandidates.contains(b.getCandidateId())
                    || !validOptions.contains(b.getOptionId()))
            {
                throw new ServiceException("选票数据有误，请刷新页面后重试");
            }
            String key = b.getCandidateId() + "_" + b.getOptionId();
            if (!seenPairs.add(key))
            {
                throw new ServiceException("同一候选人的同一项目不能重复投票");
            }
            if (!seenOptions.add(b.getOptionId()))
            {
                throw new ServiceException("同一个项目只能选择一位候选人");
            }
        }

        // 写入投票人
        VoteVoter voter = new VoteVoter();
        voter.setActivityId(activity.getActivityId());
        voter.setVoterName(StringUtils.isBlank(body.getVoterName()) ? "匿名" : body.getVoterName().trim());
        voter.setClientId(StringUtils.isBlank(body.getClientId()) ? "" : body.getClientId().trim());
        voter.setIp(ip);
        voter.setVotesUsed(ballots.size());
        voteVoterMapper.insertVoteVoter(voter);

        // 写入选票
        List<VoteRecord> records = new ArrayList<>();
        for (VoteBallot b : ballots)
        {
            VoteRecord r = new VoteRecord();
            r.setActivityId(activity.getActivityId());
            r.setVoterId(voter.getVoterId());
            r.setCandidateId(b.getCandidateId());
            r.setOptionId(b.getOptionId());
            records.add(r);
        }
        voteRecordMapper.batchInsertVoteRecord(records);

        Map<String, Object> result = new HashMap<>();
        result.put("activityId", activity.getActivityId());
        result.put("voterName", voter.getVoterName());
        result.put("votes", ballots.size());
        return result;
    }

    @Override
    public Map<String, Object> buildResult(Long activityId)
    {
        VoteActivity activity = voteActivityMapper.selectVoteActivityById(activityId);
        if (activity == null)
        {
            throw new ServiceException("活动不存在或已被删除");
        }
        List<VoteCandidate> candidates = activeCandidates(activityId);
        List<VoteOption> options = activeOptions(activityId);
        List<VoteTally> tally = voteRecordMapper.selectTally(activityId);

        // (candidateId,optionId) -> votes
        Map<String, Long> pairVotes = new HashMap<>();
        // candidateId -> total votes
        Map<Long, Long> totalByCand = new HashMap<>();
        for (VoteTally t : tally)
        {
            pairVotes.put(t.getCandidateId() + "_" + t.getOptionId(), t.getVotes());
            totalByCand.merge(t.getCandidateId(), t.getVotes(), Long::sum);
        }

        // 每个维度的排行 + 冠军
        List<Map<String, Object>> optionResults = new ArrayList<>();
        for (VoteOption o : options)
        {
            List<Map<String, Object>> ranking = new ArrayList<>();
            for (VoteCandidate c : candidates)
            {
                long v = pairVotes.getOrDefault(c.getCandidateId() + "_" + o.getOptionId(), 0L);
                ranking.add(candNode(c, v));
            }
            ranking.sort(Comparator.comparingLong(m -> -((Number) m.get("votes")).longValue()));
            Map<String, Object> om = new LinkedHashMap<>();
            om.put("optionId", o.getOptionId());
            om.put("name", o.getName());
            om.put("icon", o.getIcon());
            om.put("color", o.getColor());
            Map<String, Object> top = ranking.isEmpty() ? null : ranking.get(0);
            om.put("winner", (top != null && ((Number) top.get("votes")).longValue() > 0) ? top : null);
            om.put("ranking", ranking);
            optionResults.add(om);
        }

        // 总榜
        List<Map<String, Object>> overall = new ArrayList<>();
        for (VoteCandidate c : candidates)
        {
            overall.add(candNode(c, totalByCand.getOrDefault(c.getCandidateId(), 0L)));
        }
        overall.sort(Comparator.comparingLong(m -> -((Number) m.get("votes")).longValue()));

        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("voterCount", voteVoterMapper.countByActivityId(activityId));
        summary.put("totalBallots", voteRecordMapper.countByActivityId(activityId));
        summary.put("candidateCount", candidates.size());
        summary.put("optionCount", options.size());

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("activity", activity);
        data.put("summary", summary);
        data.put("options", optionResults);
        data.put("overall", overall);
        return data;
    }

    private Map<String, Object> candNode(VoteCandidate c, long votes)
    {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("candidateId", c.getCandidateId());
        m.put("name", c.getName());
        m.put("no", c.getNo());
        m.put("avatar", c.getAvatar());
        m.put("description", c.getDescription());
        m.put("votes", votes);
        return m;
    }
}
