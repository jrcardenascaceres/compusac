package com.compusac.controller;

import com.compusac.models.entity.*;
import com.compusac.models.service.*;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/shop")
public class ProductController {

	private final IProductService productoService;

	private final ICategorysService categoryService;

	private final IOrderService orderService;

	private final IOrderDetailService detailService;

	private final IUserService userService;

	private final SendMailService sendMailService;

	private final IPersonService personService;

	List<OrderDetail> details = new ArrayList<>();
	Order order = new Order();

	public ProductController(IProductService productoService, ICategorysService categoryService, IOrderService orderService, IOrderDetailService detailService, IUserService userService, SendMailService sendMailService, IPersonService personService) {
		this.productoService = productoService;
		this.categoryService = categoryService;
		this.orderService = orderService;
		this.detailService = detailService;
		this.userService = userService;
		this.sendMailService = sendMailService;
		this.personService = personService;
	}

	@GetMapping
	public String productos(Model model) {
		model.addAttribute("categoria", categoryService.findAll());
		model.addAttribute("productos", productoService.findAll());

		return "shop";
	}

	@GetMapping(value = "/categoria/{id}")
	public String getById(@PathVariable("id") int id, Model model) {
		model.addAttribute("status", false);
		try {
			model.addAttribute("categoria", categoryService.findAll());
			model.addAttribute("productos", productoService.findByIdCategory(id));
			model.addAttribute("status", true);
		} catch (NotFoundException nfe) {
			model.addAttribute("message", "No existe el producto en mención");
		}
		return "shop";
	}

	@PostMapping("/add-product-cart")
	public String registrarProduct(@RequestParam Long idProduct, @RequestParam int cantidad, Model model,
			HttpSession session) throws NotFoundException {
		Product product = productoService.findById(idProduct);
		if (details.stream().noneMatch(p -> Objects.equals(p.getProduct().getId(), product.getId()))) {
			OrderDetail detail = new OrderDetail();
			detail.setNombre(product.getName());
			detail.setPrecio(product.getPrice());
			detail.setCantidad(cantidad);
			detail.setTotal(product.getPrice() * cantidad);
			detail.setProduct(product);
			details.add(detail);
		}
		double total = details.stream().mapToDouble(OrderDetail::getTotal).sum();
		order.setTotal(total);

		model.addAttribute("cart", details);
		model.addAttribute("order", order);
		session.setAttribute("cart_products", details.size());
		model.addAttribute("categoria", categoryService.findAll());
		model.addAttribute("productos", productoService.findAll());

		return "shop";
	}

	@GetMapping("/shopping-cart")
	public String sales(Model model) {
		model.addAttribute("cart", details);
		model.addAttribute("order", order);
		return "shopping-cart";
	}

	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Long id, Model model, HttpSession session) {
		List<OrderDetail> ordenesNueva = new ArrayList<>();

		for (OrderDetail detalleOrden : details) {
			if (!Objects.equals(detalleOrden.getProduct().getId(), id)) {
				ordenesNueva.add(detalleOrden);
			}
		}
		details = ordenesNueva;

		double sumaTotal = details.stream().mapToDouble(OrderDetail::getTotal).sum();

		order.setTotal(sumaTotal);
		model.addAttribute("cart", details);
		model.addAttribute("order", order);
		session.setAttribute("cart_products", details.size());

		return "shopping-cart";
	}

	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("cart", details);
		model.addAttribute("order", order);
		return "checkout";
	}

	@PostMapping("/buy")
	public String comprar(HttpSession session) {
		Date fechaCreacion = new Date();
		order.setFechaCreacion(fechaCreacion);
		order.setNumero(orderService.generarNumeroOrden());

		Usuario usuario = userService.findById(Long.parseLong(session.getAttribute("idusuario").toString()));

		order.setUsuario(usuario);
		orderService.create(order);

		// guardar detalles
		for (OrderDetail dt : details) {
			dt.setOrder(order);
			detailService.create(dt);
		}
		Person p = personService.findById(usuario.getPerson());

		sendMailService.sendMail("compusac.peru@gmail.com", p.getEmail(), "Compra éxitosa!",
				"Hola " + p.getName() + ": Gracias por su compra, vuelva pronto");

		// limpiar lista y orden
		order = new Order();
		details.clear();
		session.removeAttribute("cart_products");
		return "redirect:/index";

	}
	
	@GetMapping("/eliminar")
	public String eliminarproducto () {
		
		return "eliminarproducto";
	}
}
