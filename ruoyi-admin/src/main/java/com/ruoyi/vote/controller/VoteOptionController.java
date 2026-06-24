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
import com.ruoyi.vote.domain.VoteOption;
import com.ruoyi.vote.service.IVoteOptionService;

/**
 * 投票评选维度 控制器
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/vote/option")
public class VoteOptionController extends BaseController
{
    @Autowired
    private IVoteOptionService voteOptionService;

    @PreAuthorize("@ss.hasPermi('vote:option:list')")
    @GetMapping("/list")
    public TableDataInfo list(VoteOption voteOption)
    {
        startPage();
        List<VoteOption> list = voteOptionService.selectVoteOptionList(voteOption);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('vote:option:query')")
    @GetMapping(value = "/{optionId}")
    public AjaxResult getInfo(@PathVariable("optionId") Long optionId)
    {
        return success(voteOptionService.selectVoteOptionById(optionId));
    }

    @PreAuthorize("@ss.hasPermi('vote:option:add')")
    @Log(title = "评选维度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody VoteOption voteOption)
    {
        voteOption.setCreateBy(getUsername());
        return toAjax(voteOptionService.insertVoteOption(voteOption));
    }

    @PreAuthorize("@ss.hasPermi('vote:option:edit')")
    @Log(title = "评选维度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody VoteOption voteOption)
    {
        voteOption.setUpdateBy(getUsername());
        return toAjax(voteOptionService.updateVoteOption(voteOption));
    }

    @PreAuthorize("@ss.hasPermi('vote:option:remove')")
    @Log(title = "评选维度", businessType = BusinessType.DELETE)
    @DeleteMapping("/{optionIds}")
    public AjaxResult remove(@PathVariable Long[] optionIds)
    {
        return toAjax(voteOptionService.deleteVoteOptionByIds(optionIds));
    }
}
