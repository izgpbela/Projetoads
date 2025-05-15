package com.projeto.ads.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.ads.model.Aluno;
import com.projeto.ads.repository.AlunoRepository;
import com.projeto.ads.service.ServiceAluno;
import com.projeto.ads.Enum.Curso;
import com.projeto.ads.Enum.Status;

@Controller
public class AlunoController {

    @Autowired
    private ServiceAluno serviceAluno;

    @Autowired
    private AlunoRepository alunoRepository;

    // MÉTODOS DE INSERÇÃO
    @GetMapping("/aluno/inserir")
    public ModelAndView getInserirAluno() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("aluno", new Aluno());
        mv.addObject("cursos", Curso.values());
        mv.addObject("statusList", Status.values());
        mv.setViewName("Aluno/inserirAluno");
        return mv;
    }

    @PostMapping("/aluno/inserir")
    public ModelAndView postInserirAluno(@ModelAttribute Aluno aluno) {
        ModelAndView mv = new ModelAndView();
        String saida = serviceAluno.cadastrarAluno(aluno);

        if(saida != null && saida.equals("Aluno cadastrado com sucesso!")) {
            mv.setViewName("redirect:/aluno/listar");
        } else {
            mv.addObject("mensagem", saida);
            mv.addObject("aluno", aluno);
            mv.addObject("cursos", Curso.values());
            mv.addObject("statusList", Status.values());
            mv.setViewName("Aluno/inserirAluno");
        }
        return mv;
    }

    // MÉTODOS DE LISTAGEM
    @GetMapping("/aluno/listar")
    public ModelAndView getListarAlunos() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("alunos", alunoRepository.findAllOrderedById());
        mv.setViewName("Aluno/listarAlunos");
        return mv;
    }

    // MÉTODOS DE EDIÇÃO/ATUALIZAÇÃO
    @GetMapping({"/aluno/editar/{id}", "/aluno/alterar/{id}"})
    public ModelAndView editarAluno(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + id));
        mv.addObject("aluno", aluno);
        mv.addObject("cursos", Curso.values());
        mv.addObject("statusList", Status.values());
        mv.setViewName("Aluno/alterarAluno");
        return mv;
    }

    @PostMapping("/aluno/alterar")
    public ModelAndView alterarAlunoPost(@ModelAttribute Aluno aluno) {
        ModelAndView mv = new ModelAndView();
        try {
            String out = serviceAluno.atualizarAluno(aluno);

            if(out != null) {
                mv.addObject("mensagem", out);
                mv.addObject("aluno", aluno);
                mv.addObject("cursos", Curso.values());
                mv.addObject("statusList", Status.values());
                mv.setViewName("Aluno/alterarAluno");
            } else {
                mv.setViewName("redirect:/aluno/listar");
            }
        } catch (Exception e) {
            mv.addObject("mensagem", "Ocorreu um erro ao atualizar o aluno");
            mv.addObject("aluno", aluno);
            mv.addObject("cursos", Curso.values());
            mv.addObject("statusList", Status.values());
            mv.setViewName("Aluno/alterarAluno");
        }
        return mv;
    }

    // MÉTODO DE EXCLUSÃO (unificado)
    @GetMapping("/aluno/excluir/{id}")
    public String excluirAluno(@PathVariable("id") Long id) {
        alunoRepository.deleteById(id);
        return "redirect:/aluno/listar";
    }
    @GetMapping("/aluno/deletar/{id}")
    public String deletarAluno(@PathVariable("id") Long id) {
        alunoRepository.deleteById(id);
        return "redirect:/aluno/listar";
    }
}
