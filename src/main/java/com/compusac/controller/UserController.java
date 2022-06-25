package com.compusac.controller;

import com.compusac.models.entity.Order;
import com.compusac.models.entity.OrderDetail;
import com.compusac.models.entity.Person;
import com.compusac.models.entity.Usuario;
import com.compusac.models.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UserController {

    private final IUserService userService;

    private final IPersonService personService;

    private final IOrderService orderService;

    private final IOrderDetailService orderDetailService;

    private final OrderReportService reportService;

    public UserController(IUserService userService, IPersonService personService, IOrderService orderService,
            IOrderDetailService orderDetailService, OrderReportService reportService) {
        this.userService = userService;
        this.personService = personService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.reportService = reportService;
    }

    @GetMapping("/registro")
    public String create() {
        return "register";
    }

    @PostMapping("/save")
    public String save(Person person, Usuario user) {
        Long idPersona = personService.guardar(person).getId();
        user.setPerson(idPersona);
        user.setUserName(person.getEmail());
        // user.setUserPass(passEncode.encode(user.getUserPass()));

        if (user.getRol().equals("null")) {
            user.setRol(0);
        }

        userService.guardar(user);

        return "redirect:/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session) {
        Optional<Usuario> user = userService.findByUserName(usuario.getUserName());
        if (user.isPresent()) {
            session.setAttribute("idusuario", user.get().getId());
            Person p = personService.findById(user.get().getPerson());
            session.setAttribute("userName", p.getName());
            session.setAttribute("lastName", p.getLastName());
            session.setAttribute("email", p.getEmail());
            session.setAttribute("telephone", p.getTelephone());
            if (user.get().getRol() != 0) {
                session.setAttribute("rol", user.get().getRol());
            }

        } else {
            session.removeAttribute("idusuario");
            session.removeAttribute("userName");
        }

        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("idusuario");
        session.removeAttribute("userName");
        session.removeAttribute("cart_products");
        session.removeAttribute("rol");

        return "login";
    }

    @GetMapping("/pedidos/{userId}")
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

    @GetMapping("/productos")
    public String productos(Model model) {
        //model.addAttribute("productosAdmin", productoService.findAll());
        return "productos-admin";
    }

    @GetMapping(value = "/detallePedidos/{order_id}")
    public String getListPedidoById(Model model, @PathVariable("order_id") String order_id) {
        model.addAttribute("status", false);
        try {
            Order order = orderService.findById(Long.parseLong(order_id));

            model.addAttribute("ordenesDetalle", orderDetailService.findProductDetailsByOrder(order));
            model.addAttribute("status", true);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "pedido-detalle";
    }

    @PostMapping("/export/orders")
    public String exportOrders(@RequestParam String userId, @RequestParam String reportFormat) {
        List<Order> orders = new ArrayList<>();
        try {
            if (!userId.equals("null")) {
                Usuario usuario = userService.findById(Long.parseLong(userId));
                orders.addAll(orderService.findByUsuario(usuario));
            }
            reportService.generateOrderReport(orders, reportFormat);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/index";
    }

    @PostMapping("/export/order-details")
    public String exportOrderDetails(Model model, @RequestParam String orderId, @RequestParam String reportFormat) {
        model.addAttribute("status", false);
        try {
            Order order = orderService.findById(Long.valueOf(orderId));
            List<OrderDetail> orderDetail = orderDetailService.findProductDetailsByOrder(order);
            reportService.generateOrderDetailReport(orderDetail, reportFormat);

            model.addAttribute("ordenesDetalle", orderDetail);
            model.addAttribute("status", true);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "pedido-detalle";
    }

    @GetMapping("/form-producto")
    public String formProducto(Model model) {
        return "form-producto";
    }
}