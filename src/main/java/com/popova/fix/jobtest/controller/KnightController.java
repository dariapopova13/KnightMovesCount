package com.popova.fix.jobtest.controller;

import com.popova.fix.jobtest.service.KnightMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KnightController {

    private KnightMovementService knightMovementService;

    @Autowired
    public void setKnightMovementService(KnightMovementService knightMovementService) {
        this.knightMovementService = knightMovementService;
    }

    @GetMapping(value = {"/", "/main", ""})
    public String getMain() {
        return "main";
    }

    @RequestMapping(value = "/hourse/rest/count")
    public ModelAndView getKnightMoveCount(@RequestParam(name = "width") int width,
                                           @RequestParam(name = "height") int height,
                                           @RequestParam(name = "start") String start,
                                           @RequestParam(name = "end") String end) {
        String result = knightMovementService.countMovementsWithPathRepresentation(width, height, start, end);
        ModelAndView modelAndView = new ModelAndView("main", "result", result);
        return modelAndView;
    }
}
