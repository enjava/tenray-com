package com.ray.cool.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.ray.cool.biz.UserBiz;
import com.ray.cool.entity.Books;
import com.ray.cool.entity.User;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by en on 2016/10/23.
 */
@Controller("testAction")
public class TestAction extends ActionSupport {

    @Resource
    private   UserBiz userBiz;
    private  List<User>  users;
    private List<Books> bookses;

    private String te;

    private String result;


    public String execute(){
        Map<String,String> map = new HashMap<String,String>();
        users= userBiz.list();
        for (User user:users){
            System.out.println(user.getUsername());
            //user= userBiz.load(user.getId());
            Set<Books> abc =user.getBookses();
            for (Books b:abc) {
                map.put("bookId", b.getId()+"");
                map.put("bookName",b.getName());
                map.put("bookPrice", b.getPrice());
                System.out.println(b.getId()+b.getName()+b.getPrice());
                JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
                 result = json.toString();//给result赋值，传递给页面
            }
            System.out.println(user.getBookses());
        }
        return Action.SUCCESS;

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getTe() {
        return te;
    }

    public void setTe(String te) {
        this.te = te;
    }
}
