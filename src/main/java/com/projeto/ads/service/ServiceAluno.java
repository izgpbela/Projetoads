package com.projeto.ads.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.ads.model.Aluno;
import com.projeto.ads.repository.AlunoRepository;

@Service
public class ServiceAluno {

    @Autowired
    AlunoRepository alunoRepository;

    public String gerarMatricula(int id) {
        Date data = new Date();
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        int ano = calendario.get(Calendar.YEAR);
        return "SENAC" + ano + (id + 1);
    }

    public String cadastrarAluno(Aluno aluno) {
        Aluno alunoExistente = alunoRepository.findByCpf(aluno.getCpf());
        Aluno emailExiste = alunoRepository.findByEmail(aluno.getEmail());
        if(emailExiste!=null) {
            return "Já existe um aluno com o mesmo email!";
        }
        if(alunoExistente == null) {
            Aluno aux = alunoRepository.findLastInsertedAluno();
            if(aux != null) {

                aluno.setMatricula(this.gerarMatricula((int)aux.getId()));
            } else {
                aluno.setMatricula(this.gerarMatricula(0));
            }
            alunoRepository.save(aluno);
            return "Aluno cadastrado com sucesso!";
        } else {
            return "Já existe um aluno com o mesmo CPF!";
        }
    }

    public String atualizarAluno(Aluno aluno) {
        Aluno alunoExistente = alunoRepository.findByCpf(aluno.getCpf());
        Aluno emailExiste = alunoRepository.findByEmail(aluno.getEmail());

        if(alunoExistente!=null) {
            if(!alunoExistente.getMatricula().equals(aluno.getMatricula()))
                return "ja existe um aluno com mesmo cpf";
        }
        if(emailExiste==null) {
            if(!emailExiste.getMatricula().equals(aluno.getMatricula()))
                return "ja existe um aluno com mesmo email";

        }
        alunoRepository.save(aluno);
        return null;


    }
}
