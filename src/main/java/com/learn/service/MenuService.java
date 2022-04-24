package com.learn.service;


import com.learn.mapper.MenuMapper;
import com.learn.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取菜单数据
     *
     * @return
     */
    public List<Menu> getMenu() {
        List<Menu> list = menuMapper.menu();

        //获取为父节点的Menu，并且赋值其子节点
        for (Menu rg : list) {
            if (rg.getPid() == 1)
                rg.setChildren(getChild(rg, list));
            else
                rg.setChildren(null);
        }

        //只返回ParentId为0，代表其为审查指南
        List<Menu> returnList = new ArrayList<>();
        for (Menu rg : list)
            if (rg.getPid() == 1)
                returnList.add(rg);

        return returnList;
    }

    public List<Menu> getChild(Menu root, List<Menu> all) {

        // 创建一个集合，存储父节点的子节点
        List<Menu> childs = new ArrayList<>();

        //将 集合中父id == 当前父节点id，即代表该rg是当前父节点的子节点之一
        for (Menu rg : all) {
            if (rg.getPid() == root.getId())
                childs.add(rg);
        }

        return childs;
    }

}
