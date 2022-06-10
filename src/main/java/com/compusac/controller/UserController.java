package com.compusac.controller;

import com.compusac.models.entity.Order;
import com.compusac.models.entity.Person;
import com.compusac.models.entity.Usuario;
import com.compusac.models.service.IOrderService;
import com.compusac.models.service.IPersonService;
import com.compusac.models.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping ("")
public class UserController {

    private final IUserService userService;

    private final IPersonService personService;

    private final IOrderService orderService;

    public UserController(IUserService userService, IPersonService personService, IOrderService orderService) {
        this.userService = userService;
        this.personService = personService;
        this.orderService = orderService;
    }

    // BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();

    @GetMapping("/usuario/registro")
    public String create() {
        return "register";
    }

    @PostMapping("/usuario/save")
    public String save(Person person, Usuario user) {
        Long idPersona = personService.guardar(person).getId();

        user.setPerson(idPersona);

        user.setUserName(person.getEmail());
        // user.setUserPass(passEncode.encode(user.getUserPass()));
        userService.guardar(user);

        return "redirect:/index";
    }

    @GetMapping("/usuario/login")
    public String login() {
        return "login";
    }

    @GetMapping("/usuario/acceder")
    public String acceder(Usuario usuario, HttpSession session) {
        Optional<Usuario> user = userService.findByUserName(usuario.getUserName());
        if (user.isPresent()) {
            session.setAttribute("idusuario", user.get().getId());
            Person p = personService.findById(user.get().getPerson());
            session.setAttribute("userName", p.getName());
            session.setAttribute("lastName", p.getLastName());
            session.setAttribute("email", p.getEmail());
            session.setAttribute("telephone", p.getTelephone());
        } else {
            session.removeAttribute("idusuario");
            session.removeAttribute("userName");
        }

        return "redirect:/index";
    }

    @GetMapping("/usuario/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("idusuario");
        session.removeAttribute("userName");
        session.removeAttribute("cart_products");

        return "login";
    }

    @GetMapping("/usuario/pedidos/{userId}")
    public String orders(Model model, @PathVariable("userId") String userId) {
        try {
            List<Order> orders = new ArrayList<>();
            if (!userId.equals("null")) {
                Usuario usuario = userService.findById(Long.parseLong(userId));
                orders.addAll(orderService.findByUsuario(usuario));
            }
            model.addAttribute("ordenes", orders);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "pedidos";
    }
}