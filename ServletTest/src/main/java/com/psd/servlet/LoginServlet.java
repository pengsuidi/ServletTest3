package com.psd.servlet;

import com.alibaba.fastjson.JSONObject;
import com.psd.dao.UserDao;
import com.psd.entity.Result;
import com.psd.entity.User;
import com.psd.util.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 登陆接口定义类
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {

            Result result = new Result();
            System.out.println("在此处处理 LoginServlet 的 GET/POST 请求");
            //获取参数
//            String uid = req.getParameter(Config.REQUEST_PARAMETER_UID);
            String uname = req.getParameter(Config.REQUEST_PARAMETER_USERNAME);
            String upassword = req.getParameter(Config.REQUEST_PARAMETER_PASSWORD);
            System.out.println("uname:"+uname+"upassword:"+upassword);
            //检测数据是否异常
            if (uname == null || uname.trim().length() == 0 || upassword == null || upassword.trim().length() == 0)
            {
                result.setCode(Config.STATUS_ERROR);
                result.setMessage("用户名或密码为空");

                //TODO 将响应规范对象 转换为 JSON 格式字符串回传；在客户端解析 JSON 格式字符串，并构建实体对象
                System.err.println("JSON -> " + JSONObject.toJSONString(result));
                resp.getWriter().write(JSONObject.toJSONString(result));
                return;
            }
            User user = new UserDao().login(uname, upassword);

            if (user == null) {
                result.setCode(Config.STATUS_FAILURE);
                //TODO 返回响应：登陆失败
                System.err.println("JSON -> " + JSONObject.toJSONString(result));
            }
            else {
                //TODO 返回响应：登陆成功；保存用户信息
                req.getSession().setAttribute("user", user);
                result.setCode(Config.STATUS_SUCCESS);
                result.setMessage(user.getUser_id().toString());//返回用户ID
                result.setData(user.getUname());
                System.out.println("JSON -> " + JSONObject.toJSONString(result));
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
}
