package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

//import com.cpe.backend.entity.ClubMember;;;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name="Register")
public class Register {

    @Id
    @SequenceGenerator(name="Register_seq",sequenceName="Register_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Register_seq")
    @Column(name = "Register_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String user_id;
	private @NonNull String password;
	private @NonNull String FristName;
    private @NonNull String LastName;
    private @NonNull LocalDate RegisterDate;
	

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Age.class)
    @JoinColumn(name = "Age_ID", insertable = true)
    private Age age;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "Activity_ID", insertable = true)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Title.class)
    @JoinColumn(name = "Title_ID", insertable = true)
    private Title title;

 
}