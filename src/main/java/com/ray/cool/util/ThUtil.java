package com.ray.cool.util;

import com.opensymphony.xwork2.ActionContext;
import com.ray.cool.entity.PhoneCode;
import com.ray.cool.entity.User;
import com.ray.cool.service.PhoneCodeService;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThUtil {

    @Resource
    private static PhoneCodeService phoneCodeService;

	public static int nextInt() {
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		return tmp % (999999 - 100000 + 1) + 100000;
	}

	/**
	 * @return 6位正整数的字符串,开头为1
	 */
	public static String nextStr() {
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		return (tmp % (999999 - 100000 + 1) + 100000) + "";
	}

	public static void response(String strResult) throws IOException {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(strResult.getBytes());
		outputStream.close();
	}

	public static User sysUser() {
		Map<String, Object> attibutes = ActionContext.getContext().getSession();
		if (attibutes.containsKey("user"))
			return (User) attibutes.get("user");
		return null;
	}

	public static void put(String key, Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}

	/**
	 * 获得指定key
	 * 
	 * @param key
	 * @return
	 */
	public static Object getSessionValue(String key) {
		Map<String, Object> attibutes = ActionContext.getContext().getSession();
		if (attibutes.containsKey(key))
			return attibutes.get(key);
		return null;
	}

	public static String getSessionStr(String key) {
		Map<String, Object> attibutes = ActionContext.getContext().getSession();
		if (attibutes.containsKey(key))
			return (String) attibutes.get(key);
		return null;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str != null && str.trim().length() > 0)
			return false;
		return true;
	}

	/**
	 * 检测手机号码是否合法
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isPhoneNum(String number) {
		Pattern p = Pattern.compile("^1(3|5|7|8)\\d{9}$");
		return p.matcher(number).matches();
	}

	/**
	 * 检测邮箱地址是否合法
	 * 
	 * @param email
	 * @return true合法 false不合法
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		// "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}

    public static int getIntervalDays(Date fDate, Date oDate) {

        if (null == fDate || null == oDate) {
            return -1;
        }

        long intervalMilli = oDate.getTime() - fDate.getTime();

        return (int) (intervalMilli / (24 * 60 * 60 * 1000));

    }



    //（版本2）

    public static int daysOfTwo(Date fDate, Date oDate) {

        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(fDate);

        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(oDate);

        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;

    }
     //是否允许发送手机验证码  每天超过10次验证码,不让发送  以24点位准
    public static boolean isAllowCode(String phoneNum){
        if (!isEmpty(phoneNum)) {
            PhoneCode phoneCode = phoneCodeService.getById(Long.parseLong(phoneNum));
            if (phoneCode == null) {
                phoneCode = new PhoneCode();
                phoneCode.setId(Long.parseLong(phoneNum));
                phoneCode.setAmount(1);
                phoneCode.setTime(new Date());
                phoneCodeService.update(phoneCode);
            } else {
                if (phoneCode.getAmount() > 9)
                {
                  if (daysOfTwo(phoneCode.getTime(),new Date())<1)
                      return false;
                    else {
                      phoneCode.setTime(new Date());
                      phoneCode.setAmount(1);
                      phoneCodeService.update(phoneCode);
                      return  true;
                  }

                }
                else {
                    if (!(daysOfTwo(phoneCode.getTime(),new Date())<1))//超过一天
                    {
                        phoneCode.setTime(new Date());
                        phoneCode.setAmount(1);
                        phoneCodeService.update(phoneCode);
                        return true;
                    }
                    else {
                        phoneCode.setAmount(phoneCode.getAmount() + 1);
                        phoneCodeService.update(phoneCode);
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
