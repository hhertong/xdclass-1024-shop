package net.xdclass.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 
 * </p>
 *

 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product")
public class ProductDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图
     */
    @JsonProperty("cover_img")
    private String coverImg;

    /**
     * 详情
     */
    private String detail;

    /**
     * 老价格
     */
    @JsonProperty("old_price")
    private BigDecimal oldPrice;

    /**
     * 新价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 创建时间
     */
    @JsonProperty("create_time")
    private Date createTime;

    /**
     * 锁定库存
     */
    @JsonProperty("lock_stock")
    private Integer lockStock;


}
