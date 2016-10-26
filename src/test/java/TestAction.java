import com.ray.cool.mail.SendEmail;
import com.ray.cool.mail.SendMailCode;
import com.ray.cool.util.ThUtil;
import org.junit.Test;

public class TestAction {

	@Test
	public void testBuy() {
		String str = "1234567890";
		//
		// System.out.println(ThUtil.isEmail(str));

		System.out.println(str.substring(str.length() - 2, str.length()));
	}

	@Test
	public void name() throws Exception {
		SendMailCode mail = new SendMailCode();
		String code = ThUtil.nextInt() + "";
		mail.setCode(code);
		mail.setToAddr("enjava@qq.com");

		// 发送验证码邮件
		SendEmail.doSendNormalMail(mail);
	}
	@Test
	public void phone()throws Exception{
	    String str ="1";
        boolean b=ThUtil.isPhoneNum(str);
        System.out.println(b);
    }

}
