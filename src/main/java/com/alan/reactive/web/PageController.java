package com.alan.reactive.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
    @GetMapping("/")
    public String getModel(Model model) {
        model.addAttribute("data", "这是一个键");
        return "index.html";
    }

    /**
     * 跳转到websocketDemo.html页面，携带自定义的cid信息。
     * http://localhost:8300/demo/toWebSocketDemo/user
     *
     * @param cid
     * @param model
     * @return
     */
    @GetMapping("/chat/{cid}")
    public String toWebSocketDemo(@PathVariable String cid, Model model) {
        model.addAttribute("cid", cid);
        return "chat.html";
    }
}
