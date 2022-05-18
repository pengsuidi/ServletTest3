package com.psd.dao;

import com.mysql.jdbc.MysqlParameterMetadata;
import com.psd.entity.*;
import com.psd.util.JDBCUtil;

import java.util.List;

public class
UserDao {

    /**
     * 使用 JDBC 连接数据库，并对数据库进行操作，得到响应数据结果
     */

    public User selectPayPassword(int user_id)throws ClassNotFoundException{
        return new JDBCUtil().selectOne(User.class, "select * from user where user_id=?",user_id);

    }
    public SellerInfo JudgeIfSeller(String user_id)throws ClassNotFoundException{
        return new JDBCUtil().selectOne(SellerInfo.class, "select * from seller_info where user_id=?",user_id);

    }
    public List<SellerInfo> GetSellerInfo(String user_id)throws ClassNotFoundException{
        return new JDBCUtil().selectList(SellerInfo.class, "select * from seller_info where user_id=?",user_id);

    }
    public boolean updatePayPassword(User buyer) throws ClassNotFoundException {
        return new JDBCUtil().execute("update user set pay_password=? where user_id=?", buyer.getPay_password(),buyer.getUser_id());

    }
    public boolean update(User user) throws ClassNotFoundException {
        return new JDBCUtil().execute("update user set unickname=? where user_id=?", user.getUnickname(),user.getUser_id());

    }
    public boolean UpdateShop(User user) throws ClassNotFoundException {
        return new JDBCUtil().execute("update user set unickname=? where user_id=?", user.getUnickname(),user.getUser_id());

    }


    public boolean updateBirthday(String user_id, String date) throws ClassNotFoundException {
        return new JDBCUtil().execute("update user set ubirthday=? where user_id=?", date,user_id);

    }

    public boolean updateNickname(String user_id,String uname) throws ClassNotFoundException {
        return new JDBCUtil().execute("update user set unickname=? where user_id=?", uname,user_id);

    }
    public boolean updateOid(String oldoid,String newoid) throws ClassNotFoundException {
        return new JDBCUtil().execute("update totaloid set oid=? where oid=?", newoid,oldoid);

    }
    public boolean updatePayment(String total_price,String user_id)
            throws ClassNotFoundException {
        return new JDBCUtil().execute("insert into payment(total_price,user_id)" +
                " values(?,?)",total_price,user_id);


    }
    public boolean Upload_socket_info(String socket_id)
            throws ClassNotFoundException {
        return new JDBCUtil().execute("insert into socket_info(socket_id)" +
                " values(?)",socket_id);
    }
    public boolean update_FK_payment_food(String food_id,int oid)
            throws ClassNotFoundException {
        return new JDBCUtil().execute("insert into FK_payment_food(food_id,oid)" +
                " values(?,?)",food_id,String.valueOf(oid));
    }

    public boolean Upload_food_menu(String food_name,String food_description,String food_price,String food_type,String food_image_addr,String shop_id)
            throws ClassNotFoundException {
        return new JDBCUtil().execute("insert into food(food_name,food_txt,food_price,food_type,food_img_addr,shop_id)" +
                " values(?,?,?,?,?,?)",food_name, food_description,food_price,food_type,food_image_addr,shop_id);


    }
    public boolean Update_shop(String shop_name,String shop_img_addr,String user_id,String shop_type)
            throws ClassNotFoundException {
        return new JDBCUtil().execute("insert into shop(shop_name,shop_img_addr,user_id,shop_type,shop_grade)" +
                " values(?,?,?,?,?)",shop_name, shop_img_addr,user_id,shop_type,0);


    }
    public boolean Update_shop_grade(String shop_id,String new_grade)
            throws ClassNotFoundException {
        return new JDBCUtil().execute("update shop set shop_grade=? where shop_id=?"
                ,new_grade, shop_id); }
    public boolean UploadUserImg(String user_id,String user_img_addr)
            throws ClassNotFoundException {
        return new JDBCUtil().execute("update user set user_img_addr=? where user_id=?"
                ,user_img_addr, user_id);


    }
    public boolean UpldateSocketRemark(String remark,String socket_id)
            throws ClassNotFoundException {
        return new JDBCUtil().execute("update socket_info set remark=? where socket_id=?"
                ,remark,socket_id);


    }
    public boolean BecomeSeller(String user_id)
            throws ClassNotFoundException {
        return new JDBCUtil().execute("insert into seller_info(user_id)" +
                " values(?)", user_id);


    }
    public boolean insertBuyer(int user_id,String paypassword)
            throws ClassNotFoundException {
        return new JDBCUtil().execute("update user set pay_password=?  where user_id=?",
                paypassword ,user_id);
    }

    public boolean updatePassword(User user) throws ClassNotFoundException {
        return new JDBCUtil().execute("update user set upassword=? where user_id=?", user.getUpassword(),user.getUser_id());

    }

    public User selectOne(String user_id)throws ClassNotFoundException{
        return new JDBCUtil().selectOne(User.class, "select * from user where user_id=?",user_id);
    }
    public TotalOid getOID()throws ClassNotFoundException{
        return new JDBCUtil().selectOne(TotalOid.class, "select * from totaloid");
    }

    public boolean addsuggestion(Suggestion suggestion) throws ClassNotFoundException {
        return new JDBCUtil().execute("insert into suggestion(content) values(?)", suggestion.getSuggestion());}


    public boolean upload_comment(String comment , String shop_id,
                                  String user_id, String grade,String comment_img_addr)
            throws ClassNotFoundException {
        return new JDBCUtil().execute
                ("insert into comment(comment,shop_id,user_id,comment_grade,comment_img_addr)" +
                        "values(?,?,?,?,?)", comment, shop_id, user_id, grade, comment_img_addr);

    }

    public User login(String name, String pass) throws ClassNotFoundException {
        return new JDBCUtil().selectOne(User.class, "select * from user where uname=? and upassword=?", name, pass);
    }
    public User test() throws ClassNotFoundException {
        return new JDBCUtil().selectOne(User.class, "select * from user ");
    }


    public boolean register(User user) throws ClassNotFoundException {
        return new JDBCUtil().execute("insert into user(uname,unickname,upassword,pay_password,user_img_addr) values(?,?,?,?,?)", user.getUname(), user.getUnickname(), user.getUpassword(),"123",user.getUser_img_addr());
    }

    public List<Shop_Info> GetShopInfoByUid(String user_id) throws ClassNotFoundException {
        return new JDBCUtil().selectList(Shop_Info.class, "select * from shop where user_id=?", user_id);
    }

    public ShopInfoBuy getCommentShopInfo(String shopid) throws ClassNotFoundException {
        return new JDBCUtil().selectOne(ShopInfoBuy.class, "select * from shop where shop_id=?", shopid);
    }
    public User GetUserInfo(String user_id) throws ClassNotFoundException {
        return new JDBCUtil().selectOne(User.class, "select * from user where user_id=?", user_id);
    }
    public User getPaypassword(String user_id) throws ClassNotFoundException {
        return new JDBCUtil().selectOne(User.class, "select * from user where user_id=?", user_id);
    }
    //    public Shop selectShopid(String shop_name) throws ClassNotFoundException {
//        return new JDBCUtil().selectOne(Shop.class, "select * from shop where shop_name=?",shop_name );
//    }
    public FavouriteShop isFavourite(String name, String user_id) throws ClassNotFoundException {
        return new JDBCUtil().selectOne(FavouriteShop.class, "select * from collection where shop_id=? and user_id =?", name, user_id);
    }

    public List<Food_Menu> selectList(String shopid) throws ClassNotFoundException {
        return new JDBCUtil()
                .selectList(Food_Menu.class,
                        "select * from food where shop_id=?", shopid);
    }

    public List<UserComment> selectCommentList(String shopid) throws ClassNotFoundException {
        return new JDBCUtil()
                .selectList(UserComment.class,
                        "select * from comment where shop_id=?", shopid);
    }

    public List<payment> selectOrderesList(String user_id) throws ClassNotFoundException {
        return new JDBCUtil()
                .selectList(payment.class,
                        "select * from payment where user_id=?", user_id);
    }
///
    public List<OrderInfo> selectOrderesFoodList(String user_id, String oid) throws ClassNotFoundException {
        return new JDBCUtil()
                .selectList(OrderInfo.class,
                        "select * from payment where user_id=? and oid=?", user_id, Integer.parseInt(oid));
    }
    public List<UserComment> GetPersonComments(String user_id) throws ClassNotFoundException {
        return new JDBCUtil()
                .selectList(UserComment.class,
                        "select * from comment where user_id=?", user_id);
    }

    public List<ShoppingcarFood> selectOrderesFoodList(String user_id) throws ClassNotFoundException {
        return new JDBCUtil()
                .selectList(ShoppingcarFood.class,
                        "select * from bought_food where user_id=?", user_id);
    }
    public List<FavouriteShop> GetFavouriteList(String user_id) throws ClassNotFoundException {
        return new JDBCUtil()
                .selectList(FavouriteShop.class,
                        "select * from collection where user_id=?", user_id);
    }


    public ShopInfoBuy GetShopInfoByName(String shop_name) throws ClassNotFoundException {
        return new JDBCUtil().selectOne(ShopInfoBuy.class, "select * from shop where shop_name=?", shop_name);

    }
    public List<Shop_Info> GetShopListByName(String shop_name) throws ClassNotFoundException {
        String sql="select * from shop where shop_name like "+"'"+"%"+shop_name+"%"+"'";

        return new JDBCUtil().selectList(Shop_Info.class, sql);
    }
    public List<Shop_Info> GetShopListByName_right(String shop_name) throws ClassNotFoundException {
        String sql="select * from shop where shop_name like "+"'"+shop_name+"%"+"'";

        return new JDBCUtil().selectList(Shop_Info.class, sql);
    }
    public Shop_Info GetShopInfoByShopid(String shopid) throws ClassNotFoundException {
        return new JDBCUtil().selectOne(Shop_Info.class, "select * from shop where shop_id=?", shopid
        );
    }
    public List<TotalOid> GetOIDLIST() throws ClassNotFoundException {
        return new JDBCUtil().selectList(TotalOid.class, "select * from payment ");
    }//获取OID
     public payment GetFoodInfo(String food_id) throws ClassNotFoundException {
        return new JDBCUtil().selectOne(payment.class, "select * from food where food_id=? ",food_id);
    }//获取食物信息

    public Integer GetOid() throws ClassNotFoundException {
        return new JDBCUtil().selectOne(Integer.class, "select oid from payment ");
    }//获取OID
    public User GetUserImg(String user_id) throws ClassNotFoundException {
        return new JDBCUtil().selectOne(User.class, "select * from user where user_id=?", user_id
        );
    }
    public List<payment> GetFoodID(String oid) throws ClassNotFoundException {
        return new JDBCUtil().selectList(payment.class, "select * from FK_payment_food where oid=?", oid
        );
    }
    public boolean uploadFavouriteShop(String shop_id, String user_id) throws ClassNotFoundException {
        return new JDBCUtil()
                .execute("insert into collection (user_id,shop_id)" +
                                "values(?,?)"
                        , user_id, shop_id);
    }

    public boolean BuyFood(String foodname, String food_price, String user_id,String shop_name) throws ClassNotFoundException {
        return new JDBCUtil()
                .execute("insert into bought_food(food_name,food_price,user_id,shop_name)" +
                                "values(?,?,?,?)"
                        , foodname, food_price, user_id,shop_name);
    }

    public boolean deleteFavouriteShop(String shop_id, String user_id) throws ClassNotFoundException {
        return new JDBCUtil()
                .execute("delete from  collection where  user_id=? and shop_id=?"
                        , user_id, shop_id);
    }

    public boolean minusFood(String food_name, String user_id) throws ClassNotFoundException {
        return new JDBCUtil()
                .execute("delete from  bought_food where  user_id=? and food_name=?"
                        , user_id, food_name);
    }
    public boolean deleteBoughtFood(String user_id) throws ClassNotFoundException {
        return new JDBCUtil()
                .execute("delete from  bought_food where  user_id=?"
                        , user_id);
    }
    public boolean deleteComment(String comment_id) throws ClassNotFoundException {
        return new JDBCUtil()
                .execute("delete from  comment where  comment_id=?"
                        , comment_id);
    }
}
