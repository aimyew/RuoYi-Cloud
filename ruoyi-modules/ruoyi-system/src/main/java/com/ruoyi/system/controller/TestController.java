package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.bean.BeanValidators;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.mapper.SysMenuMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Validator;

/**
 * TestController
 *
 * @date 2022/9/29 11:12
 */
@RestController
@RequestMapping(value = "/test")
public class TestController extends BaseController {

    @Resource
    private ISysUserService userService;
    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysMenuMapper menuMapper;
    @Resource
    protected Validator validator;

    @GetMapping(value = "/validator")
    public R<?> validator() {
        SysMenu sysMenu = new SysMenu();
        BeanValidators.validateWithException(validator, sysMenu);
        return R.ok();
    }
}
