package com.heima.trade.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.trade.IOrderLogisticsService;
import com.heima.trade.dataobject.OrderLogistics;
import com.heima.trade.mapper.OrderLogisticsMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
public class OrderLogisticsServiceImpl extends ServiceImpl<OrderLogisticsMapper, OrderLogistics> implements IOrderLogisticsService {

}
