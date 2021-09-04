package com.example.prhack.controllers;


import com.example.prhack.model.CommandContentModel;
import com.example.prhack.model.CommandModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class CommandController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public CommandModel commandContentModel(CommandModel commandModel) throws Exception {
        Thread.sleep(1000); // simulated delay

        boolean succeed = false;

        if(HtmlUtils.htmlEscape(commandModel.getLink()).equals("start_verification")){

            // Start verifiation call


//            if(isVer){
//                succeed = true;
//            }
//            if (!isVer){
//                succeed = false;
//            }

        }

        commandModel = new CommandModel(String.valueOf(succeed));

        return commandModel;
    }

}

