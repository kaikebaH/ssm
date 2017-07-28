package com.kaikeba.app.action;

import com.kaikeba.app.entity.Actor;
import com.kaikeba.app.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
@Controller
@Scope("prototype")
@RequestMapping("/actorAction")
public class ActorAction {
    @Autowired
    private IActorService actorService;

    @RequestMapping("/getAllActor")
    public String getAllActor(Model model){
        List<Actor> list = actorService.allActor();
        model.addAttribute("list",list);
        return "forward:/actors.jsp";
    }
}
