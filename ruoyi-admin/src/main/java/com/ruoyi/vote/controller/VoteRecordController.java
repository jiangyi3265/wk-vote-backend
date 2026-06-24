package com.ruoyi.vote.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.vote.domain.VoteRecord;
import com.ruoyi.vote.service.IVotePublicService;
import com.ruoyi.vote.service.IVoteRecordService;

/**
 * 投票记录 & 统计 控制器
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/vote/record")
public class VoteRecordController extends BaseController
{
    @Autowired
    private IVoteRecordService voteRecordService;

    @Autowired
    private IVotePublicService votePublicService;

    @PreAuthorize("@ss.hasPermi('vote:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(VoteRecord voteRecord)
    {
        startPage();
        List<VoteRecord> list = voteRecordService.selectVoteRecordList(voteRecord);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('vote:record:remove')")
    @Log(title = "投票记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(voteRecordService.deleteVoteRecordByIds(recordIds));
    }

    @PreAuthorize("@ss.hasPermi('vote:record:clear')")
    @Log(title = "清空投票", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clear/{activityId}")
    public AjaxResult clear(@PathVariable Long activityId)
    {
        return toAjax(voteRecordService.clearByActivityId(activityId));
    }

    /** 后台统计看板数据 */
    @PreAuthorize("@ss.hasPermi('vote:stat:list')")
    @GetMapping("/statistics/{activityId}")
    public AjaxResult statistics(@PathVariable Long activityId)
    {
        return success(votePublicService.buildResult(activityId));
    }
}
