package com.ray.cool.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ray.cool.entity.User;
import com.ray.cool.service.UserService;
import com.ray.cool.util.ThUtil;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by en on 2016/10/24.
 */
@Controller("fileUploadAction")
@Scope("prototype")
public class FileUploadAction  extends ActionSupport {

    private File idBackImg;//身份证背面
    private String idBackImgContentType;
    private String idBackImgFileName;

    private File idFaceImg;//正面头像
    private String idFaceImgContentType;//正面头像
    private String idFaceImgFileName;//正面头像

    private String message = "你已成功上传文件";
    @Resource
    // 获取订单业务类
    private UserService userService;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @SuppressWarnings("deprecation")
    public String idFaceImgUpload() throws Exception {
        User user = ThUtil.sysUser();
      if (user!=null&&idFaceImg!=null) {

          try {
              File f = this.getIdFaceImg();
              if(this.getIdFaceImgFileName().endsWith(".exe")){
                  message="对不起,你上传的文件格式不允许!!!";
                  return ERROR;
              }

              String filePathFace = ServletActionContext.getServletContext().getRealPath("upload/idpic");
              String fileNameFace = UUID.randomUUID().toString().replaceAll("-", "") + idFaceImgFileName.substring(idFaceImgFileName.lastIndexOf("."));
              if (user.getIdFace()!=null) {
                  File file=new File(ServletActionContext.getServletContext().getRealPath("upload/")+user.getIdFace());
                  try {
                      FileUtils.forceDelete(file);
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
              //复制文件
              FileUtils.copyFile(idFaceImg, new File(filePathFace, fileNameFace));
              user.setIdFace("idpic/" + fileNameFace);
          } catch (Exception e) {
              e.printStackTrace();
              message = "对不起,文件上传失败了!!!!";
          }
          //user.setIdFace();
      }

        return SUCCESS;

    }

    public String idBackImgUpload() throws Exception {
        User user = ThUtil.sysUser();
        if (user!=null&&idBackImg!=null) {
            try {
                File f = this.getIdBackImg();
                if (this.getIdBackImgFileName().endsWith(".exe")) {
                    message = "对不起,你上传的文件格式不允许!!!";
                    return ERROR;
                }
                String filePathBack = ServletActionContext.getServletContext().getRealPath("upload/idpic");
                String fileNameBack = UUID.randomUUID().toString().replaceAll("-", "") + idBackImgFileName.substring(idBackImgFileName.lastIndexOf("."));

                if (user.getIdBack()!=null) {
                    File file=new File(ServletActionContext.getServletContext().getRealPath("upload/")+user.getIdBack());
                    try {
                        FileUtils.forceDelete(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //复制文件
                FileUtils.copyFile(idBackImg, new File(filePathBack, fileNameBack));
                user.setIdBack("idpic/" + fileNameBack);

                if(user.getIdFace()!=null&&user.getIdCard()!=null&&user.getIdName()!=null) {
                    user=userService.getById(user.getId());
                    user.setIdState("0");
                    userService.update(user);
                    ThUtil.put("user",user);
                }
                else
                    message = "对不起,文件上传失败了!!!!";

            } catch (Exception e) {
                e.printStackTrace();
                message = "对不起,文件上传失败了!!!!";
            }
        }
        return SUCCESS;
    }


    public File getIdBackImg() {
        return idBackImg;
    }

    public void setIdBackImg(File idBackImg) {
        this.idBackImg = idBackImg;
    }

    public String getIdBackImgContentType() {
        return idBackImgContentType;
    }

    public void setIdBackImgContentType(String idBackImgContentType) {
        this.idBackImgContentType = idBackImgContentType;
    }

    public String getIdBackImgFileName() {
        return idBackImgFileName;
    }

    public void setIdBackImgFileName(String idBackImgFileName) {
        this.idBackImgFileName = idBackImgFileName;
    }

    public File getIdFaceImg() {
        return idFaceImg;
    }

    public void setIdFaceImg(File idFaceImg) {
        this.idFaceImg = idFaceImg;
    }

    public String getIdFaceImgContentType() {
        return idFaceImgContentType;
    }

    public void setIdFaceImgContentType(String idFaceImgContentType) {
        this.idFaceImgContentType = idFaceImgContentType;
    }

    public String getIdFaceImgFileName() {
        return idFaceImgFileName;
    }

    public void setIdFaceImgFileName(String idFaceImgFileName) {
        this.idFaceImgFileName = idFaceImgFileName;
    }
}