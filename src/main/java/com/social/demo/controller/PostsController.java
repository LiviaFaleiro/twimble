package com.social.demo.controller;

import com.social.demo.model.Posts;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")

public class PostsController {
    
    private Posts posts;

    @PostMapping("posts/criar")
    public void criar(@RequestParam("texto") String texto, @RequestParam("id_usuario") int id_usuario){
        posts = new Posts();
        posts.setTexto(texto);
        posts.setId_usuario(id_usuario);
        posts.salvar();
}
    @GetMapping("posts/listar")
    public ArrayList<Posts> listar(){
        return Posts.getAll();
    }
    @GetMapping("posts/listar/{id}")
    public Posts listar(@PathVariable("id") int id){
        posts = new Posts();
        posts.setId(id);
        posts.load();
        return this.posts;
}
}//fim da classe PostsController
