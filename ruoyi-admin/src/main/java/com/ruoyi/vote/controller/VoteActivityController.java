package com.ruoyi.vote.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.vote.domain.VoteActivity;
import com.ruoyi.vote.service.IVoteActivityService;

/**
 * 投票活动 控制器
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/vote/activity")
public class VoteActivityController extends BaseController
{
    @Autowired
    private IVoteActivityService voteActivityService;

    @PreAuthorize("@ss.hasPermi('vote:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(VoteActivity voteActivity)
    {
        startPage();
        List<VoteActivity> list = voteActivityService.selectVoteActivityList(voteActivity);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('vote:activity:query')")
    @GetMapping(value = "/{activityId}")
    public AjaxResult getInfo(@PathVariable("activityId") Long activityId)
    {
        return success(voteActivityService.selectVoteActivityById(activityId));
    }

    @PreAuthorize("@ss.hasPermi('vote:activity:add')")
    @Log(title = "投票活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody VoteActivity voteActivity)
    {
        voteActivity.setCreateBy(getUsername());
        return toAjax(voteActivityService.insertVoteActivity(voteActivity));
    }

    @PreAuthorize("@ss.hasPermi('vote:activity:edit')")
    @Log(title = "投票活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody VoteActivity voteActivity)
    {
        voteActivity.setUpdateBy(getUsername());
        return toAjax(voteActivityService.updateVoteActivity(voteActivity));
    }

    @PreAuthorize("@ss.hasPermi('vote:activity:edit')")
    @Log(title = "投票活动状态", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody VoteActivity voteActivity)
    {
        VoteActivity update = new VoteActivity();
        update.setActivityId(voteActivity.getActivityId());
        update.setStatus(voteActivity.getStatus());
        update.setUpdateBy(getUsername());
        return toAjax(voteActivityService.updateVoteActivity(update));
    }

    @PreAuthorize("@ss.hasPermi('vote:activity:remove')")
    @Log(title = "投票活动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    public AjaxResult remove(@PathVariable Long[] activityIds)
    {
        return toAjax(voteActivityService.deleteVoteActivityByIds(activityIds));
    }
}
