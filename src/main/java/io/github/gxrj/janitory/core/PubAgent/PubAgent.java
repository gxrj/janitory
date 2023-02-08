package io.github.gxrj.janitory.core.PubAgent;

import java.util.UUID;

import io.github.gxrj.janitory.core.Dept.Dept;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity( name = "Funcionario" )
public class PubAgent {
    
    @Id
    @Column( columnDefinition = "uuid not null" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @ManyToOne
    @JoinColumn( name = "secretaria" )
    private Dept dept;

    @Column( name = "nome", nullable = false, length = 60 )
    private String name;

    @Column( unique = true, nullable = false, length = 20 )
    private String login;

    @Column( name = "senha", nullable = false )
    private String password;
}
