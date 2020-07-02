package com.shenrs.eblog.entity;

import com.shenrs.eblog.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author shenrs
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCollection extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long postId;

    private Long postUserId;


}
