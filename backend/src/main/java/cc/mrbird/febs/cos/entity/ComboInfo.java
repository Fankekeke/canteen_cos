package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 实验套餐管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ComboInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 套餐编号
     */
    private String code;

    /**
     * 实验套餐名称
     */
    private String name;

    /**
     * 备注
     */
    private String content;

    /**
     * 创建时间
     */
    private String createDate;
    @TableField(exist = false)
    private BigDecimal price;
    @TableField(exist = false)
    private String goods;

}
