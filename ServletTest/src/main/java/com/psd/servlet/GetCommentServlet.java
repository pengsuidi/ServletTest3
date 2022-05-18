package com.psd.servlet;

import com.alibaba.fastjson.JSONObject;
import com.psd.dao.UserDao;
import com.psd.entity.User;
import com.psd.entity.UserComment;
import com.psd.entity.Result;
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
import java.util.List;

@WebServlet("/GetCommentServlet")
public class GetCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Result result = new Result();

        String shopid = req.getParameter(Config.REQUEST_PARAMETER_SHOPID);
        System.out.println("---------:"+shopid);
        try {
            UserDao Dao = new UserDao();
            List<UserComment> comments = Dao.selectCommentList(shopid);

            result.setCode(Config.STATUS_SUCCESS);
            result.setMessage("网络请求成功:"+comments.size());
            for (int i=0;i<comments.size();i++)
            {
                UserComment comment=comments.get(i);
                if (comment.getComment_img_addr()!=null)
                {
                    comment.setComment_img(image2byte(comments.get(i).getComment_img_addr()));
                    //获取用户信息
                    User user=Dao.GetUserImg(comment.getUser_id().toString());
                    comment.setUser_pic(image2byte(user.getUser_img_addr()));
                }

            }
            result.setData(comments);
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

}
