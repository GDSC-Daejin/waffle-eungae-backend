package com.example.waffleeungaebackend.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 유저 아이디
    private Long memberId;

    @Column
    // 유저 이름
    private String name;

    @Column
    // 유저 이메일
    private String email;

    private Long level;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;



    /*
    @Builder
    public Member(String name, String email, Role role) {

        this.name = name;
        this.email = email;
        this.role = role;
    }


 */
    public Member update(String name ) {
        this.name = name;
        return this;
    }


    public String getRoleKey() {
        return this.role.getKey();
    }

    public void setId(Long memberId) {
        this.memberId = memberId;
    }
}
