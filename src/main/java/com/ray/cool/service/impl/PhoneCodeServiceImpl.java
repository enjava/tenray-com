package com.ray.cool.service.impl;


import com.ray.cool.base.DaoSupportImpl;
import com.ray.cool.entity.PhoneCode;
import com.ray.cool.service.PhoneCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("phoneCodeServiceImpl")
@Transactional
public class PhoneCodeServiceImpl extends DaoSupportImpl<PhoneCode> implements PhoneCodeService {
	
}
