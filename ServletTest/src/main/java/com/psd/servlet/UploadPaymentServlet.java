package com.psd.servlet;

import com.alibaba.fastjson.JSONObject;
import com.psd.dao.UserDao;
import com.psd.entity.Result;
import com.psd.entity.TotalOid;
import com.psd.util.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/UploadPaymentServlet")
public class UploadPaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
        try{

            Result result = new Result();

            System.out.println("在此处处理 UpdateServlet 的 GET/POST 请求");

            String total_price=req.getParameter(Config.REQUEST_PARAMETER_TOTAL_PRICE);
            String user_id=req.getParameter(Config.REQUEST_PARAMETER_USER_ID);
            String food_id_string=req.getParameter(Config.REQUEST_PARAMETER_FOOD_ID_STRING);
            int LastOid;

            boolean isUpdate = new UserDao().updatePayment(total_price,user_id);
            if (isUpdate) {
                result.setCode(Config.STATUS_SUCCESS);
                List<TotalOid> list=new UserDao().GetOIDLIST();
                result.setMessage(food_id_string);
                result.setData(list.get(list.size()-1).getOid());
                LastOid=list.get(list.size()-1).getOid();
                //更新FK_payment_food表
                String[] res=update_FK_payment_food(food_id_string,LastOid);

                result.setData(res[res.length-1]);
            } else {
                // System.err.println("修改失败");
                result.setCode(Config.STATUS_FAILURE);
                result.setMessage("更新payment失败");
                result.setData(total_price+user_id);
                //TODO 返回响应：注册失败
                System.err.println("JSON -> " + JSONObject.toJSONString(result));
                // resp.getWriter().write("修改失败");
            }

            resp.getWriter().write(JSONObject.toJSONString(result));
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    private String[] update_FK_payment_food(String food_id_string,int LastOid) {
        //解析food_id_string
        String food_id[]= food_id_string.split(",");
        for(int i=0;i<food_id.length;i++)
        {
            try {
                boolean res=new UserDao().update_FK_payment_food(food_id[i],LastOid);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
            return food_id;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
