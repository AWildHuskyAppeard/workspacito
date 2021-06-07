package tw.group5.controller.cart;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import tw.group5.model.cart.Order;
import tw.group5.model.cart.OrderDao;
import tw.group5.model.cart.OrderService;
import tw.group5.model.cart.old.UserBean;
import tw.group5.model.product.ProductInfo;

@SessionAttributes(names = "cart")
@Controller
@RequestMapping(path = "/cart.controller")
public class CartController {
	@Autowired
	private OrderService orderService;
	
	public CartController() {
		   System.out.println("=====>	IoC 容器正在建立本類別 (CartController) 的物件	<=====");
	}
	
	
	@PostMapping(value = {"/cartIndex"})
	@GetMapping(value = {"/cartIndex"})
	public String redirectToCartIndex() {
		return "cart/cartIndex";
	}
	
	@PostMapping(value = {"/cartCheckout"})
	public String redirectToCartCheckout() {
		return "cart/cartIndex";
	}
	
	
	public static ArrayList<ProductInfo> cart = new ArrayList<ProductInfo>();
	
	
	@GetMapping(value="/showCart", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ProductInfo> showCart(HttpSession session) {
		cart = (ArrayList<ProductInfo>) session.getAttribute("cart");
		return cart;
	}
	
	@GetMapping(value = "/remove", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ProductInfo> removeProductFromCart(HttpSession session, @RequestParam("ckbox") String[] ckbox ) {
		cart = (ArrayList<ProductInfo>) session.getAttribute("cart");
		for (int i = 0; i < ckbox.length; i++) {
			int ckIndex = Integer.parseInt(ckbox[i]);
			cart.remove(ckIndex - i);
		}
		session.setAttribute("cart", cart);
		return cart;
	}

	private void backToMainPage() {
		return;
	}
	
	@PostMapping("/pay")
	private String pay() {
		Order orderBean = new Order();
		
		// (1) 取得O_ID：查出最新的O_ID ❌

		
		// (2) 取得U_ID，U_FirstName，U_LastName，U_Email
		// 以下為測試用，要換掉
		ArrayList<UserBean> fakeUserBeans = new ArrayList<UserBean>();
		UserBean fakeUserBean00 = new UserBean("user01", "psww", "1999-12-31", "TKYM", "TMT", "OWOsama@gmail.com", "0987654321", "F", "this.Galaxy");
		fakeUserBeans.add(fakeUserBean00);
		
		// (3) 取得O_Date (使用SimpleDateFormat)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		String now = sdf.format(calendar.getTime());
		
		// (4) 取得O_Amt
		 Integer O_Amt = 0;
		 for (int i = 0; i < this.cart.size(); i++) {
			O_Amt += this.cart.get(i).getP_Price();
		}
		
		// 把OrderBean的資料寫進去Database
		// 之後把下面fakeUserBeans.get(0)改成get(i)
		for(int i = 0; i < cart.size(); i++) {
			Order orderBean = new Order(newO_IDs.get(i), cart.get(i).getP_ID(), cart.get(i).getP_Name(), 
				cart.get(i).getP_Price(), fakeUserBeans.get(0).getU_ID(), fakeUserBeans.get(0).getU_FirstName(), 
				fakeUserBeans.get(0).getU_LastName(), fakeUserBeans.get(0).getU_Email(), "done", now, O_Amt);
			crudor.insertOrder(orderBean);
		}

		this.cart = new ArrayList<ProductBean>();
		System.out.println("購買成功，感謝您！");
		return;
	}

	// 純粹測試用；最後用不到
	@GetMapping("/refill")
	@ResponseBody
	public String refill() {
			
				// 測試用。cart如果是空的，會自動補3件下列商品作為測試
				if(cart.size() == 0) {
					ProductInfo fakeProductBean1 = new ProductInfo();
					fakeProductBean1.setP_ID(3);
					fakeProductBean1.setP_Name("EN_Speaking");
					fakeProductBean1.setP_Class("EN");
					fakeProductBean1.setP_Price(500);
					fakeProductBean1.setP_DESC("nice!!!");
					fakeProductBean1.setU_ID("fbk001");
					fakeProductBean1.setP_Img("pic001");
					fakeProductBean1.setP_Video("vid001");
					fakeProductBean1.setCreateDate(new Date());
					
					System.out.println("購物車沒有任何東西，因此管理員塞了一個課程進來✌💀✌");
					cart = new ArrayList<ProductInfo>();
					cart.add(fakeProductBean1);
				}
				return "購物車沒有任何東西，因此管理員塞了一個課程進來✌💀✌";
		}
}