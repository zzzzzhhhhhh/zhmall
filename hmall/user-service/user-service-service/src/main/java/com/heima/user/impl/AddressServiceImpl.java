package com.heima.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.user.IAddressService;
import com.heima.user.dataobject.Address;
import com.heima.user.mapper.AddressMapper;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
