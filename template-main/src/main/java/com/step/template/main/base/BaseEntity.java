package com.step.template.main.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.step.template.main.exception.MyException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * 实体父类
 */
@Getter
@Setter
public class BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * model和vo互转
     */
    public <D> D toBean(Class<D> clazz) {
        try {
            D d = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(this, d);
            return d;
        } catch (Exception e) {
            throw new MyException(e);
        }
    }
}
