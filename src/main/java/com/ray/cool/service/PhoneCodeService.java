package com.ray.cool.service;

import com.ray.cool.base.DaoSupport;
import com.ray.cool.entity.PhoneCode;

import java.util.List;

public interface PhoneCodeService extends DaoSupport<PhoneCode> {

	public List<PhoneCode> findAll();

	public void delete(Long id);

	public void save(PhoneCode leve);

	public PhoneCode getById(Long id);

	public void update(PhoneCode leve);
	
}
