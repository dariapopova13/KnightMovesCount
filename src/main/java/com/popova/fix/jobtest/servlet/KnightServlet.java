package com.popova.fix.jobtest.servlet;

import com.popova.fix.jobtest.service.KnightMovementService;
import com.popova.fix.jobtest.service.impl.KnightMovementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class KnightServlet extends HttpServlet {

    private KnightMovementService knightMovementService;

    @Autowired
    public void setKnightMovementService(KnightMovementService knightMovementService) {
        this.knightMovementService = knightMovementService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = Integer.valueOf(req.getParameter("width"));
        int height = Integer.valueOf(req.getParameter("height"));
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        String result = knightMovementService.countMovementsWithPathRepresentation(
                width, height, start, end
        );
        req.setAttribute("result", result);
        req.getRequestDispatcher("/").forward(req, resp);
    }
}
