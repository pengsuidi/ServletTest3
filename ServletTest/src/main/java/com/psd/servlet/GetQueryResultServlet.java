package com.psd.servlet;

import com.alibaba.fastjson.JSONObject;
import com.psd.dao.UserDao;
import com.psd.entity.Result;
import com.psd.entity.Shop_Info;
import com.psd.util.Config;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * 登陆接口定义类
 */
@WebServlet("/GetQueryResultServlet")
public class GetQueryResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Result result = new Result();

            System.out.println("在此处处理 LoginServlet 的 GET/POST 请求");

            //获取参数
            String queryString = req.getParameter(Config.REQUEST_QUERY_STRING);
            String queryString123 = queryString;


            System.out.println(queryString);
            //检测数据是否异常
            if (queryString == null || queryString.trim().length() == 0) {
                System.err.println("JSON -> " + JSONObject.toJSONString(result));
                return;
            }
            List<Shop_Info> ShopInfoList = new ArrayList<>();
            ShopInfoList.addAll(new UserDao().GetShopListByName_right(queryString));
            ShopInfoList.addAll(new UserDao().GetShopListByName(queryString));

            //循环查询
//            while(queryString.length()>0)
//            {
//                //ShopInfoList.addAll(new UserDao().GetShopListByName(queryString));
//
//                ShopInfoList.addAll(new UserDao().GetShopListByName_right(queryString));
//               // ShopInfoList.addAll(new UserDao().GetShopListByName_right_left(queryString));
//                queryString= queryString.substring(0,queryString.length()-1);
//            }
            String shop_name="店名";
            String sql="select * from shop where shop_name like "+"'"+shop_name+"%"+"'";

            if (ShopInfoList.size()==0) {
                result.setCode(Config.STATUS_FAILURE);
                result.setMessage("失败1:"+sql);

                System.err.println("JSON -> " + JSONObject.toJSONString(result));
            } else {
                //TODO 返回响应：登陆成功；保存用户信息
                result.setCode(Config.STATUS_SUCCESS);
                result.setMessage("成功!!:"+queryString123.length());
                for(int i=0;i<ShopInfoList.size();i++)
                {
                    //ShopInfoList.get(i).setShop_image(image2byte(ShopInfoList.get(i).getShop_img_addr()));
                }

                result.setData(ShopInfoList);
                System.err.println("JSON -> " + JSONObject.toJSONString(result));
            }

            resp.getWriter().write(JSONObject.toJSONString(result));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
    public static byte[] image2byte(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    public static List<String> duplicateListyByIte(List<String> ShopIdlist) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = ShopIdlist.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        return newList;
    }
}
