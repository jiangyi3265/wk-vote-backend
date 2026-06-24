package com.ruoyi.vote.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import javax.validation.constraints.NotBlank;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 投票评选维度 vote_option
 *
 * @author ruoyi
 */
public class VoteOption extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维度ID */
    private Long optionId;

    /** 所属活动ID */
    private Long activityId;

    /** 维度名称(帅/美/幽默...) */
    private String name;

    /** 图标(emoji) */
    private String icon;

    /** 主题色 */
    private String color;

    /** 排序 */
    private Integer sort;

    /** 状态(0正常 1停用) */
    private String status;

    public Long getOptionId()
    {
        return optionId;
    }

    public void setOptionId(Long optionId)
    {
        this.optionId = optionId;
    }

    public Long getActivityId()
    {
        return activityId;
    }

    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }

    @NotBlank(message = "维度名称不能为空")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
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

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("optionId", getOptionId())
            .append("activityId", getActivityId())
            .append("name", getName())
            .append("icon", getIcon())
            .append("color", getColor())
            .append("sort", getSort())
            .append("status", getStatus())
            .toString();
    }
}
