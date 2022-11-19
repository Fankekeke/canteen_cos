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
 * 库房管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 入库物品名称
     */
    private String name;

    /**
     * 类别
     */
    private Integer typeId;

    /**
     * 型号
     */
    private String type;

    /**
     * 物品数量
     */
    private Integer amount;

    /**
     * 物品单位
     */
    private String unit;

    /**
     * 备注
     */
    private String content;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 出/入库时间
     */
    private String createDate;

    /**
     * 是否是库房数据 0.库房数据 1.入库数据 2.出库数据
     */
    private Integer isIn;

    /**
     * 出库到哪个学生
     */
    private Integer toUserId;

    /**
     * 入库信息对应的库房ID
     */
    private Integer parentId;

    @TableField(exist = false)
    private Integer userId;

}
