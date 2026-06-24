package com.ruoyi.vote.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import javax.validation.constraints.NotBlank;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 投票候选人 vote_candidate
 *
 * @author ruoyi
 */
public class VoteCandidate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 候选人ID */
    @Excel(name = "候选人ID")
    private Long candidateId;

    /** 所属活动ID */
    private Long activityId;

    /** 候选人姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 编号 */
    @Excel(name = "编号")
    private String no;

    /** 头像 */
    private String avatar;

    /** 简介/宣言 */
    @Excel(name = "简介")
    private String description;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 状态(0正常 1停用) */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 非持久化：累计得票数（统计用） */
    private Long totalVotes;

    public Long getCandidateId()
    {
        return candidateId;
    }

    public void setCandidateId(Long candidateId)
    {
        this.candidateId = candidateId;
    }

    public Long getActivityId()
    {
        return activityId;
    }

    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }

    @NotBlank(message = "候选人姓名不能为空")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNo()
    {
        return no;
    }

    public void setNo(String no)
    {
        this.no = no;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Long getTotalVotes()
    {
        return totalVotes;
    }

    public void setTotalVotes(Long totalVotes)
    {
        this.totalVotes = totalVotes;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("candidateId", getCandidateId())
            .append("activityId", getActivityId())
            .append("name", getName())
            .append("no", getNo())
            .append("avatar", getAvatar())
            .append("description", getDescription())
            .append("sort", getSort())
            .append("status", getStatus())
            .toString();
    }
}
