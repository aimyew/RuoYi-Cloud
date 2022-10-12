package com.ruoyi.system.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.server.MyServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor")
public class ServerController {

    @RequiresPermissions("monitor:server:list")
    @GetMapping("/server")
    public AjaxResult getInfo() throws Exception {
        MyServer myServer = new MyServer();
        myServer.copyTo();
        return AjaxResult.success(myServer);
    }
}
