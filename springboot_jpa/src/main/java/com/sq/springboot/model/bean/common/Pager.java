package com.sq.springboot.model.bean.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 分页对象
 * @author: kiKi
 */
@Data
@ApiModel("Pager")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pager {

  @ApiModelProperty(value = "页码", example = "0")
  @JsonProperty(value = "page")
  private Integer page;
  @ApiModelProperty(value = "单页记录条数", example = "10")
  @JsonProperty(value = "page_size")
  private Integer pageSize;
  @ApiModelProperty(value = "记录总数", example = "10")
  @JsonProperty(value = "total")
  private Integer total;
}
