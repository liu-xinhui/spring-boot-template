package com.step.template.main.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseSvo {
    private int pageNum = 1;
    private int pageSize = 10;

    public <T> Page<T> getPage() {
        return new Page<>(pageNum, pageSize);
    }
}
