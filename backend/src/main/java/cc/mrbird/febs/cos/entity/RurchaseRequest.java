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
 * 采购申请
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RurchaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 采购申请单号
     */
    private String num;

    /**
     * 采购说明
     */
    private String content;

    /**
     * 预计金额
     */
    private BigDecimal price;

    /**
     * 采购流程 0是正在采购 1是入库完成
     */
    private Integer process;

    /**
     * 当前流程
     */
    private Integer step;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 创建时间
     */
    private String createDate;

    @TableField(exist = false)
    private String goods;
    @TableField(exist = false)
    private String custodian;
    @TableField(exist = false)
    private String putUser;
    @TableField(exist = false)
    private String rurchaseContent;

}
