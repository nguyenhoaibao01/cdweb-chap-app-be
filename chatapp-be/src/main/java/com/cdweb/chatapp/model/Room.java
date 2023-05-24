package com.cdweb.chatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "rooms")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private boolean isGroup;
    @ManyToOne
    private User admin;
    //    @ManyToOne
//    private User updateBy;
    @Column
    private LocalDateTime createAt ;
    @Column
    private LocalDateTime updateAt;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pin_message_id")
    private Message pinMessage;
    @ManyToMany(mappedBy = "rooms")
    @JsonIgnore
    private Set<User> members= new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Message> messages= new HashSet<>();
    public void addMessage(Message message){
        this.messages.add(message);
    }

    public void addMember(User u){
        this.members.add(u);
    }

    public void addAllMember(Set<User> users){
        this.members.addAll(users);
    }
}

