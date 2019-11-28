package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * 根据类别分页查询
 */
public interface RouteService {
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    Route findOne(String rid);
}
