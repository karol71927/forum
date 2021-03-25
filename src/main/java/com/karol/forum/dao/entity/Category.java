package com.karol.forum.dao.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "id_category")
    private Long ID;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Post> posts;

    public Category() {
    }

    public Category(Long ID, String name, Set<Post> posts) {
        this.ID = ID;
        this.name = name;
        this.posts = posts;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
