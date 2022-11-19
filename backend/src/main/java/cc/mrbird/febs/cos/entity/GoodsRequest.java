package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 耗材申请
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 申请单号
     */
    private String num;

    /**
     * 申请人
     */
    private Integer userId;

    /**
     * 申请说明
     */
    private String content;

    /**
     * 申请流程 0是正在审核 1是审核通过 2驳回
     */
    private Integer process;

    /**
     * 当前流程
     */
    private Integer step;

    /**
     * 创建时间
     */
    private String createDate;
    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private String team;
    @TableField(exist = false)
    private String goods;
}
