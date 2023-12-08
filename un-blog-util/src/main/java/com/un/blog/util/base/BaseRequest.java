package com.un.blog.util.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 请求参数基础类，带分页参数
 * */
@Data
public class BaseRequest<T> implements Serializable {

    @ApiModelProperty(value="页码",required = true)
    private long current;

    @ApiModelProperty(value = "每页有多少数据",required = true)
    private long size;

    /**
     * 封装分页对象
     * */
    @ApiModelProperty(hidden = true)
    public IPage<T> getPage(){
        return new Page<T>().setCurrent(this.current).setSize(this.size);
    }
}
