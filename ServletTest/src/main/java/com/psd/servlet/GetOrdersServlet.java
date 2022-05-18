package com.psd.servlet;

import com.alibaba.fastjson.JSONObject;
import com.psd.dao.UserDao;
import com.psd.entity.*;
import com.psd.util.Config;
import com.psd.util.MyUtil;
import org.ietf.jgss.Oid;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetOrdersServlet")
public class GetOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Result result = new Result();
        String userid = req.getParameter(Config.REQUEST_PARAMETER_USER_ID);
        try {
            UserDao Dao = new UserDao();
            //从payment中获取该userid下的所有oid
            List<payment> OIDList = Dao.selectOrderesList(userid);//存放Oid,total_price
            List<payment> FoodIDList = new ArrayList<>();//存放FOODID
            List<payment> ResList=new ArrayList<>();//存放FOODID,OID,食物信息
            Shop_Info shop_info=new Shop_Info();
            for(int i=0;i<OIDList.size();i++){
                //根据每个oid获取FK_payment_food中的food_id
                List<payment> List=Dao.GetFoodID(String.valueOf(OIDList.get(i).getOid()));
                for(int j=0;j<List.size();j++)
                {
                    List.get(j).setTotal_price(OIDList.get(i).getTotal_price());
                }
                FoodIDList.addAll(List);

            }
            for(int i=0;i<FoodIDList.size();i++){
                //根据Food_id获取结果
                ResList.add(Dao.GetFoodInfo(String.valueOf(FoodIDList.get(i).getFood_id())));

                 ResList.get(i).setFood_image(new MyUtil().image2byte(ResList.get(i).getFood_img_addr()));
                shop_info=Dao.GetShopInfoByShopid(String.valueOf(ResList.get(i).getShop_id()));
                ResList.get(i).setShop_name(shop_info.getShop_name());
                ResList.get(i).setOid(FoodIDList.get(i).getOid());
                ResList.get(i).setTotal_price(FoodIDList.get(i).getTotal_price());
                ResList.get(i).setShop_img(new MyUtil().image2byte(shop_info.getShop_img_addr()));
            }


            result.setCode(Config.STATUS_SUCCESS);
            result.setMessage("网络请求成功");

            result.setData(ResList);
            System.out.println("abc:"+JSONObject.toJSONString(result));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //TODO 异常响应
            result.setCode(Config.STATUS_ERROR);
            result.setMessage(e.getMessage());
        } finally {
            resp.getWriter().write(JSONObject.toJSONString(result));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }



}
