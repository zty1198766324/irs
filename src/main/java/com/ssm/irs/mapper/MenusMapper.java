package com.ssm.irs.mapper;

import com.ssm.irs.pojo.Menus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenusMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(Menus record);

    int insertSelective(Menus record);

    Menus selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(Menus record);

    int updateByPrimaryKey(Menus record);

    List<Menus> queryMenus();

    List<Menus> queryChildMenus(@Param("parentId") Long parentId);


}
