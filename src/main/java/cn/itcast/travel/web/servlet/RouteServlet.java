package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        //接收rname线路名称
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        //处理参数
        int cid = 0;
        int currentPage = 0;
        int pageSize = 0;

        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }

        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }

        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 5;
        }

        //调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);

        //将PageBean序列化为Json返回
        writeValue(pb,response);
    }
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收id
        String rid = request.getParameter("rid");
        //2.调用service查询route对象
        Route route = routeService.findOne(rid);
        //3.转为json写会客户端
        writeValue(route,response);
    }

}
