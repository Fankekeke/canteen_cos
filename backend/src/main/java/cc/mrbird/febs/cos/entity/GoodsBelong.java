package cc.mrbird.febs.cos.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 物品所属
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsBelong implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 物品名称
     */
    private String name;

    /**
     * 型号
     */
    private String type;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 耗材类型
     */
    private Integer typeId;

    /**
     * 申请/入库单号
     */
    private String num;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 日期
     */
    private String createDate;


}
