package com.karol.forum.dao.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long ID;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "user")
    private Set<Reply> replies;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    public User() {
    }

    public User(Long ID, String login, String password, String email, String type, Set<Reply> replies, Set<Post> posts) {
        this.ID = ID;
        this.login = login;
        this.password = password;
        this.email = email;
        this.type = type;
        this.replies = replies;
        this.posts = posts;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
