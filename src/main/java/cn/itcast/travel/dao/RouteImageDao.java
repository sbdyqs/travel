package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImageDao {
    //根据route的线路id查询图片
    List<RouteImg> findByRid(int rid);
}
