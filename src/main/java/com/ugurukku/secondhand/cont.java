package com.ugurukku.secondhand;

import com.ugurukku.secondhand.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a")
public class cont {

    @GetMapping
    public User print(){
        return new User(null,"sa","A","as","as");
    }

}
