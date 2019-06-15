package ru.skillbox.socialnetwork.model;

import javax.persistence.*;

/**
 * файлы, прикреплённые к постам
 */
@Entity
public class PostFile {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * пост
     */
    @Column(name = "post_id")
    private int postId;

    /**
     * Имя файла
     */
    @Column(name = "name")
    private String name;

    /**
     * путь к файлу в хранилище
     */
    @Column(name = "path")
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}