package com.psd.servlet;

import com.alibaba.fastjson.JSONObject;
import com.psd.dao.UserDao;
import com.psd.entity.*;
import com.psd.util.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetPayPasswordServlet")
public class getPayPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Result result = new Result();

        String userid = req.getParameter(Config.REQUEST_PARAMETER_USER_ID);
        System.out.println("---------userid:"+userid);
        try {
            UserDao dao = new UserDao();
            User buyer = dao.getPaypassword(userid);
            if(buyer!=null)
            {
                result.setCode(Config.STATUS_SUCCESS);
                result.setMessage("获得密码成功");
                result.setData(buyer);
            }
            else {
                result.setCode(Config.STATUS_FAILURE);
                result.setMessage("获得失败");
                result.setData(buyer);
            }
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
