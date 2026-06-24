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
import javax.servlet.http.HttpServletResponse;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.vote.domain.VoteCandidate;
import com.ruoyi.vote.service.IVoteCandidateService;

/**
 * 投票候选人 控制器
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/vote/candidate")
public class VoteCandidateController extends BaseController
{
    @Autowired
    private IVoteCandidateService voteCandidateService;

    @PreAuthorize("@ss.hasPermi('vote:candidate:list')")
    @GetMapping("/list")
    public TableDataInfo list(VoteCandidate voteCandidate)
    {
        startPage();
        List<VoteCandidate> list = voteCandidateService.selectVoteCandidateList(voteCandidate);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('vote:candidate:list')")
    @Log(title = "候选人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VoteCandidate voteCandidate)
    {
        List<VoteCandidate> list = voteCandidateService.selectVoteCandidateList(voteCandidate);
        ExcelUtil<VoteCandidate> util = new ExcelUtil<VoteCandidate>(VoteCandidate.class);
        util.exportExcel(response, list, "候选人数据");
    }

    @PreAuthorize("@ss.hasPermi('vote:candidate:query')")
    @GetMapping(value = "/{candidateId}")
    public AjaxResult getInfo(@PathVariable("candidateId") Long candidateId)
    {
        return success(voteCandidateService.selectVoteCandidateById(candidateId));
    }

    @PreAuthorize("@ss.hasPermi('vote:candidate:add')")
    @Log(title = "候选人", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody VoteCandidate voteCandidate)
    {
        voteCandidate.setCreateBy(getUsername());
        return toAjax(voteCandidateService.insertVoteCandidate(voteCandidate));
    }

    @PreAuthorize("@ss.hasPermi('vote:candidate:edit')")
    @Log(title = "候选人", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody VoteCandidate voteCandidate)
    {
        voteCandidate.setUpdateBy(getUsername());
        return toAjax(voteCandidateService.updateVoteCandidate(voteCandidate));
    }

    @PreAuthorize("@ss.hasPermi('vote:candidate:remove')")
    @Log(title = "候选人", businessType = BusinessType.DELETE)
    @DeleteMapping("/{candidateIds}")
    public AjaxResult remove(@PathVariable Long[] candidateIds)
    {
        return toAjax(voteCandidateService.deleteVoteCandidateByIds(candidateIds));
    }
}
