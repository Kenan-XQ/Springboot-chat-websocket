package com.xq.websocketchatdemo.controller;

import com.xq.websocketchatdemo.po.Message;
import com.xq.websocketchatdemo.po.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class WebsocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/welcome")
    //FIXME 1.第一种方式通过注解SendTo发送消息
    //@SendTo("/topic/getResponse")//服务器端有消息时,会订阅@SendTo 中的路径的浏览器发送消息。
    public Response say(Message message) {
        Response response = new Response("广播：" + message.getName());
        //TODO 2.第二种方式通过SimpMessagingTemplate 发送
        simpMessagingTemplate.convertAndSend("/topic/getResponse", response);
        return response;
    }

    @MessageMapping("/chat")
    public Response handlerChat(Message message) {
        //System.out.println("message = " + message);
        Response response = new Response(message.getName(), message.getMyself());
        //String username = (String) session.getAttribute(player);
        //System.out.println("message = " + message.getName() + ",player = " + message.getPlayer());
        simpMessagingTemplate.convertAndSendToUser(message.getPlayer(),
                "/queue/notifications", response);
        return response;
    }

    @RequestMapping("/index")
    public String index(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("player", username);
        return "/websocket";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/loginSubmit")
    @ResponseBody
    public boolean loginSubmit(String username, String password, HttpSession session) {
        if ((username.equals("root") && password.equals("root"))
            || (username.equals("admin") && password.equals("admin"))
                || (username.equals("xq") && password.equals("xq"))) {
            session.setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }

}
