package hu.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

@Table(name = "user")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Boolean active;

    @Column
    private String address;

    @Column
    private Date created;

    @Column
    private Date created_at;

    @Column
    private Boolean deleted;

    @Column
    private Date deleted_at;

    @Column
    private String deleted_flag;

    @Column
    private String email;

    @Column
    private String email_token;

    @Column
    private Date last_login;

    @Column
    private String name;

    @Column
    private Boolean next_login_change_password;

    @Column
    private String password;

    @Column
    private Boolean password_expired;

    @Column
    private Integer phone;

    @Column
    private Integer settlement_id;

    @Column
    private String temp_password;

    @Column
    private Boolean temp_password_expired;

    @Column
    private Boolean updated;

    @Column
    private Date updated_at;

    @Column
    private String usertype;

    @Column
    private String username;

    @Column
    private int settlements_by_settlement_id;

    @Column
    private Integer user_by_created_id;

    @Column
    private Integer user_by_deleted_id;

    @Column
    private Integer user_by_updated_id;

    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private UserRole userrole;
    
}