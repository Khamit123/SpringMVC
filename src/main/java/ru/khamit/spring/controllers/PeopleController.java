package ru.khamit.spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.khamit.spring.dao.PeopleDao;
import ru.khamit.spring.model.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PeopleDao peopleDao;

    @Autowired
    public PeopleController(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleDao.People());
        return "People/index";
    }

//    @GetMapping("/person/{id}")
//    public String person(@PathVariable("id") int id, Model model) {
//        model.addAttribute("person", peopleDao.person(id));
//        return "People/person";
//    }
//
//    @GetMapping("/delete")
//    public String deletePreson(@RequestParam("personToDelete") Person person) {
//        peopleDao.deletePerson(person);
//        return "People/index";
//    }
//
    @GetMapping("/new")
    public String newPeople(Model model) {
        model.addAttribute("person", new Person());
        return "People/new";
    }
////    @GetMapping("/new")
////    public String newPeople(@ModelAttribute("person") Person person){
////        return "People/new";
////    }
//
////    @PostMapping()
////    public String add(@RequestParam("name") String name, Model model) {
////        Person person = new Person();
////        person.setName(name);
////        peopleDao.newPerson(person);
////        model.addAttribute("person", person);
////        return "redirect:people";
////    }
//
//    // Ниже код которым можно заменить верхнюю функцию. ModelAtribute создаёт Person, добавляет туда имя и передаёт модели
    @PostMapping()
    public String add(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "People/new";
        peopleDao.newPerson(person);
        return "redirect:people";
    }
//    @ModelAttribute("headerMessage") //в данном случае ModelAtribute делает так чтобы
//    // в любую модель(Model) будет попадать ключ-значение(headerMessage-Welcome to my site) можно не толкьо String
//    public String headerMessage() {
//        return "Welcome to my site";
//    }
//
//    @GetMapping("/person/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("person", peopleDao.person(id));
//        return "People/edit";
//    }
//
//    @PatchMapping("/person/{id}")
//    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
//        if(bindingResult.hasErrors()) return "People/edit";
//        peopleDao.update(id, person);
//        return "redirect:/people/person/{id}";
//    }
//    @DeleteMapping("/person/{id}")
//    public String delete(@PathVariable("id")int id){
//        peopleDao.delete(id);
//        return "redirect:/people";
//    }

}
