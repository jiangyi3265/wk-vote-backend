package com.ruoyi.vote.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.vote.domain.dto.VoteSubmitBody;
import com.ruoyi.vote.service.IVotePublicService;

/**
 * 公开投票 控制器（无需登录，供 uni-app 前端调用）
 *
 * @author ruoyi
 */
@Anonymous
@RestController
@RequestMapping("/vote/public")
public class VotePublicController extends BaseController
{
    @Autowired
    private IVotePublicService votePublicService;

    /** 进行中/已结束的活动列表 */
    @GetMapping("/activities")
    public AjaxResult activities()
    {
        return success(votePublicService.listPublicActivities());
    }

    /** 活动详情（候选人 + 评选维度） */
    @GetMapping("/activity/{activityId}")
    public AjaxResult activity(@PathVariable Long activityId)
    {
        return success(votePublicService.getActivityDetail(activityId));
    }

    /** 提交投票 */
    @PostMapping("/submit")
    public AjaxResult submit(@RequestBody VoteSubmitBody body, HttpServletRequest request)
    {
        Map<String, Object> data = votePublicService.submitVote(body, IpUtils.getIpAddr(request));
        return AjaxResult.success("投票成功，感谢您的参与！", data);
    }

    /** 投票结果统计 */
    @GetMapping("/result/{activityId}")
    public AjaxResult result(@PathVariable Long activityId)
    {
        return success(votePublicService.buildResult(activityId));
    }
}
