package com.un.blog.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 问题标签中间表
 * </p>
 *
 * @author un
 * @since 2023-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_question_label")
@ApiModel(value="QuestionLabel对象", description="问题标签中间表")
public class QuestionLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "文章 id")
    private String questionId;

    @ApiModelProperty(value = "标签id")
    private String labelId;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;


}
