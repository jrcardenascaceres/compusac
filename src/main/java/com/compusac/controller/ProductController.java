package com.compusac.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.compusac.models.entity.Order;
import com.compusac.models.entity.OrderDetail;
import com.compusac.models.entity.Person;
import com.compusac.models.entity.Product;
import com.compusac.models.entity.Usuario;
import com.compusac.models.service.ICategorysService;
import com.compusac.models.service.IOrderDetailService;
import com.compusac.models.service.IOrderService;
import com.compusac.models.service.IPersonService;
import com.compusac.models.service.IProductService;
import com.compusac.models.service.IUserService;
import com.compusac.models.service.SendMailService;

@Controller
@RequestMapping("/shop")
public class ProductController {

	@Autowired
	private IProductService productoService;

	@Autowired
	private ICategorysService categoryService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IOrderDetailService detailService;

	@Autowired
	private IUserService userService;

	@Autowired
	private SendMailService sendMailService;

	@Autowired
	private IPersonService personService;

	List<OrderDetail> details = new ArrayList<>();
	Order order = new Order();

	@GetMapping
	public String productos(Model model, HttpSession session) {
		model.addAttribute("categoria", categoryService.findAll());
		model.addAttribute("productos", productoService.findAll());

		return "shop";
	}

	@GetMapping(value = "/categoria/{id}")
	public String getById(@PathVariable("id") int id, Model model, HttpSession session) {
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
		if (!details.stream().anyMatch(p -> p.getProduct().getId() == product.getId())) {
			OrderDetail detail = new OrderDetail();
			detail.setNombre(product.getName());
			detail.setPrecio(product.getPrice());
			detail.setCantidad(cantidad);
			detail.setTotal(product.getPrice() * cantidad);
			detail.setProduct(product);
			details.add(detail);
		}
		Double total = details.stream().mapToDouble(dt -> dt.getTotal()).sum();
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
		List<OrderDetail> ordenesNueva = new ArrayList<OrderDetail>();

		for (OrderDetail detalleOrden : details) {
			if (detalleOrden.getProduct().getId() != id) {
				ordenesNueva.add(detalleOrden);
			}
		}
		details = ordenesNueva;

		double sumaTotal = details.stream().mapToDouble(dt -> dt.getTotal()).sum();

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
}
