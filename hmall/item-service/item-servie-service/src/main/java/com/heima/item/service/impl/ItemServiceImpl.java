package com.heima.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.item.api.request.OrderDetailReq;
import com.heima.item.api.response.ItemResp;
import com.heima.item.dataobject.Item;
import com.heima.item.mapper.ItemMapper;
import com.heima.item.service.IItemService;
import com.heima.common.exception.BizIllegalException;
import com.heima.common.utils.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author 虎哥
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Override
    public void deductStock(List<OrderDetailReq> items) {
        String sqlStatement = "com.heima.item.mapper.ItemMapper.updateStock";
        boolean r = false;
        try {
            r = executeBatch(items, (sqlSession, entity) -> sqlSession.update(sqlStatement, entity));
        } catch (Exception e) {
            log.error("更新库存异常", e);
            return;
        }
        if (!r) {
            throw new BizIllegalException("库存不足！");
        }
    }

    @Override
    public List<ItemResp> queryItemByIds(Collection<Long> ids) {
        List<Item> items = listByIds(ids);
        return BeanUtils.copyList(listByIds(ids), ItemResp.class);
    }
}
