package net.xdclass.service;

import net.xdclass.vo.CouponRecordVO;

import java.util.Map;

public interface CouponRecordService {

    CouponRecordVO findById(long recordId);

    Map<String, Object> page(int page, int size);

}
