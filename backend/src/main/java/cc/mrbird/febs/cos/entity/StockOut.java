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
 * 出库管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockOut implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 单号
     */
    private String num;

    /**
     * 学生ID
     */
    private Integer userId;

    /**
     * 总价
     */
    private BigDecimal price;

    /**
     * 保管员
     */
    private String custodian;

    /**
     * 经手人
     */
    private String handler;

    /**
     * 出库时间
     */
    private String createDate;

    /**
     * 申请单ID
     */
    private Integer applyId;

    @TableField(exist = false)
    private String goods;
    @TableField(exist = false)
    private String userName;
}
