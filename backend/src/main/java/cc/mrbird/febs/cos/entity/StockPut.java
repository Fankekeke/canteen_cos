package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 入库管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockPut implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 单号
     */
    private String num;

    /**
     * 总价
     */
    private BigDecimal price;

    /**
     * 保管人
     */
    private String custodian;

    /**
     * 入库人
     */
    private String putUser;

    /**
     * 备注
     */
    private String content;

    /**
     * 入库时间
     */
    private String createDate;


}
