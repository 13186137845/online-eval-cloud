package com.sjy.eval.auth.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.eval.common.base.BaseController;
import com.eval.common.base.BaseEnum;
import com.eval.common.base.BaseResult;
import com.eval.common.util.ValidateUtil;
import com.sjy.eval.auth.entity.User;
import com.sjy.eval.auth.queryVo.UserQuerVo;
import com.sjy.eval.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @program: online-eval-clould
 * @description:
 * @author: Created by youxun
 * @create: 2018-12-17 16:25
 **/
@RestController
@RequestMapping("/auth")
@Api(tags ={ "鉴权服务"},description = "鉴权服务接口-网关路径/api-auth")
@RefreshScope
public class AuthController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Value("${env}")
    private String env;

    //查询滚动动态信息
    @PutMapping("queryUserPage")
    @ApiOperation(value="分页查询测试", notes="分页查询测试")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="userQuerVo", name = "code", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="userQuerVo", name = "password", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="userQuerVo", name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    public BaseResult queryUserPage(@RequestBody UserQuerVo userQuerVo) {
        //必要的入参校验以及日志打印
        if(ValidateUtil.isNull(userQuerVo.getCode())){
            return new BaseResult(BaseEnum.BLANK.status, "学校ID不能为空", false);
        }
        Page tPage = userQuerVo.buildPage();
        //List userList = userService.queryUserList(tPage,userQuerVo);
        List userList = userService.queryListbyCode(tPage, userQuerVo.getCode());
        tPage.setRecords(userList);

        System.out.println("获取动态配置信息：");
        System.out.println(env);

        return new BaseResult(BaseEnum.SUCCESS.getStatus(), "查询成功", tPage);
    }


    @PostMapping("inserUser")
    public int inserUser(@RequestParam("userName") String userName) {

        LOGGER.debug("执行插入操作" + userName);

        User user = new User();
        user.setPassword("asdasdasd");
        user.setCreateDate(new Date());
        user.setUserId("T0006666");
        user.setSchooolCode(43532452);
        int id = userService.inserUser(user);



        return id;
    }

}
