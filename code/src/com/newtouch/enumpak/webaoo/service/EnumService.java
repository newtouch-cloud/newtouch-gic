package com.newtouch.enumpak.webaoo.service;

import java.util.List;

import com.newtouch.enumpak.vo.EnumEntity;

public interface EnumService {
  public List<EnumEntity> findEnum(String parentId);
  
  public List<EnumEntity> findEnumPro(EnumEntity entity);
}
