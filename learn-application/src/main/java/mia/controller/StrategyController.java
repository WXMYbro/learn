package mia.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import mia.common.Result;
import mia.strategy.model.req.DrawReq;
import mia.strategy.service.draw.IDrawExec;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/Mia")
public class StrategyController  {

    @Resource
    IDrawExec iDrawExec;


    @GetMapping(value = "/start/lottery")
    public Result page(@RequestBody DrawReq req) {
        return Result.success(iDrawExec.doDrawExec(req));
    }
}
