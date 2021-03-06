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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 登陆接口定义类
 */
@WebServlet("/GetRandomShopServlet")
public class GetRandomShopServlet extends HttpServlet {
    private int count = 3;//刷新时候一次性刷新的店铺个数,即每次从数据库读取的店铺个数

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Result result = new Result();

            System.out.println("在此处处理 LoginServlet 的 GET/POST 请求");

            //获取参数
            Random random = new Random();

            List<Shop_Info> shopinfolist = new ArrayList<>();
            for(int i=0;i<count;i++)
            {
                String shopid = String.valueOf(random.nextInt(3) + 1);
                shopinfolist.add(new UserDao().GetShopInfoByShopid(shopid));
            }


            if (shopinfolist.size()==0) {

                System.err.println("JSON -> " + JSONObject.toJSONString(result));
            } else {
                //TODO 返回响应：登陆成功；保存用户信息


                result.setCode(Config.STATUS_SUCCESS);
                result.setMessage("成功!!");
                for (int i = 0; i < shopinfolist.size(); i++) {
                    shopinfolist.get(i).setShop_image(image2byte(shopinfolist.get(i).getShop_img_addr()));

                }
                //Shop_Info tmp_shop=new UserDao().GetShopInfoByShopid("1");
                //result.setData(tmp_shop);
                result.setData(shopinfolist);
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

    public static byte[] image2byte(String path) {
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
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }
}
