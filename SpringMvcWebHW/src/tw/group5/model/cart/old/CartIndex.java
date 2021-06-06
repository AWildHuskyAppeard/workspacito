//package tw.group5.model.cart.old;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//
//import tw.group5.model.product.Product;
//
//public class CartIndex {
//	
//	public static ArrayList<Product> cart = new ArrayList<Product>();
//	private final String format1 = "%10s | %20s | %20s | %20s | %20s | %20s ";
//	private final String format2 = "%10s | %20s | %20s | %20d | %20s | %20s ";
//	private static Session session;
//	private Scanner scanner = new Scanner(System.in);
//	
//	
//	public static void main(String[] args) {
//		action();
//	}
//	
//	public CartIndex(Session session) {
//		CartIndex.session = session;
//	}
//	
//	public void initialize() {
//			Product fakeProductBean1 = new Product("p000003", "EN_Speaking", "EN", 500, "nice", "fbk001", "pic01", "vid01");
//			Product fakeProductBean2 = new Product("p000015", "RU_Reading", "RU", 650, "awesome", "krn563", "pic02", "vid02");
//			Product fakeProductBean3 = new Product("p000009", "JP_Translation", "JP", 500, "subarashii", "duck486", "pic03", "vid03");
//	//		<jsp:useBean id="addedProduct" scope="session" class="cart.ProductBean" type="cart.ProductBean" />
//	
//				// 測試用。cart如果是空的，會自動補3件下列商品作為測試
//				if(cart.size() == 0) {
//					System.out.println("購物車沒有任何東西，因此管理員塞了三個課程進來✌💀✌");
//					cart = new ArrayList<ProductBean>();
//					cart.add(fakeProductBean1);
//					cart.add(fakeProductBean2);
//					cart.add(fakeProductBean3);
//				}
//				showCart();
//				return;
//		}
//
//	public static void action() {
//		CartIndex cartIndex = new CartIndex(CartIndex.session);
//		cartIndex.initialize();
//		cartIndex.remoteControl();
//		return;
//	}
//	
//	private void remoteControl() {
//		while(true) {
//		System.out.println("輸入1以移除商品，2以確定結帳(把資料存進資料庫)，或其他回到主選單");
//		String cmd = scanner.nextLine();
//			if (cmd == "2" && cart.size() != 0) {
//				pay();
//				try {wait(1500);} catch(Exception e){}
//				backToMainPage();
//				return;
//			}
//			if (cmd == "2" && cart.size() == 0) {
//				System.out.println("Nothing in your cart :( ");
//				continue;
//			}
//			else if (cmd == "1") {
//				removeProductFromCart();
//				continue;
//			}
//			else {
//				backToMainPage();
//				return;
//			}
//		}
//	}
//	
//	private void removeProductFromCart() {
//		while(true) {			
//			System.out.println("Enter the No of product you'd like to remove OR other key to leave.");
//			String n = scanner.nextLine();
//			try {
//				int no = Integer.parseInt(n);
//				cart.remove(no);
//				showCart();
//			} catch (Exception e) {
//				return;
//			}
//		}
//	}
//
//	private void backToMainPage() {
//		return;
//	}
//
//	private void pay() {
//		OrderDao crudor = new OrderDao(this.session);
//		// ＊生成OrderBean
//		
//		// (1) 取得O_ID：查出最新的O_ID
//			// O_ID可能是
//		Query<Order> query = this.session.createQuery("FROM OrderBean ob ORDER BY ob.O_ID DESC", Order.class).setMaxResults(1);
//		Order uniqueResult = query.uniqueResult();
//		String O_IDString = uniqueResult.getO_ID();
//		// 剝掉非O_ID中非數字的部分取出轉成Integer
//		String pureNum = HibernateUtil.stripNonDigits(O_IDString);
//		Integer latestO_ID = Integer.parseInt(pureNum);
//		// 找出當前Table裡O_ID最大數字，並+1
//		// 97~122 = a~z; 65~90 = A~Z (ASCII表)
//		// 單筆訂單內容上限 = 26筆
//		ArrayList<String> newO_IDs = new ArrayList<String>();
//		if (cart.size() > 1) {
//			for(int i = 0; i < cart.size(); i++) {			
//				String newO_ID = String.format("order%06d-%s", (latestO_ID + 1), (char)(65 + i));
//				newO_IDs.add(newO_ID);
//			}			
//		} else {	
//			String newO_ID = String.format("order%06d", (latestO_ID + 1));
//			newO_IDs.add(newO_ID);
//		}		
//		
//		// (2) 取得U_ID，U_FirstName，U_LastName，U_Email
//		// 以下為測試用，要換掉
//		ArrayList<UserBean> fakeUserBeans = new ArrayList<UserBean>();
//		UserBean fakeUserBean00 = new UserBean("user01", "psww", "1098-12-31", "TKYM", "TMT", "L@U.L", "0987654321", "F", "this.Galaxy");
//		fakeUserBeans.add(fakeUserBean00);
//		
//		// (3) 取得O_Date (使用SimpleDateFormat)
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(System.currentTimeMillis());
//		String now = sdf.format(calendar.getTime());
//		
//		// (4) 取得O_Amt
//		 Integer O_Amt = 0;
//		 for (int i = 0; i < this.cart.size(); i++) {
//			O_Amt += this.cart.get(i).getP_Price();
//		}
//		
//		// 把OrderBean的資料寫進去Database
//		// 之後把下面fakeUserBeans.get(0)改成get(i)
//		for(int i = 0; i < cart.size(); i++) {
//			Order orderBean = new Order(newO_IDs.get(i), cart.get(i).getP_ID(), cart.get(i).getP_Name(), 
//				cart.get(i).getP_Price(), fakeUserBeans.get(0).getU_ID(), fakeUserBeans.get(0).getU_FirstName(), 
//				fakeUserBeans.get(0).getU_LastName(), fakeUserBeans.get(0).getU_Email(), "done", now, O_Amt);
//			crudor.insertOrder(orderBean);
//		}
//
//		this.cart = new ArrayList<ProductBean>();
//		System.out.println("購買成功，感謝您！");
//		return;
//	}
//
//	public void showCart() {
//		System.out.println("**********************************************************************************************************************");
//		System.out.println(String.format(format1, "No.", "課程名稱(P_Name)", "課程編號(P_ID)", 
//				"課程價格(P_Price)", "課程介紹(P_DESC)", "課程老師(U_ID)"));
//		Integer totalPrice = 0;
//		for(int i = 0; i < cart.size(); i++) {
//			if(cart != null) {
//				System.out.println(String.format(format2, "(" + (i + 1) + ")", cart.get(i).getP_Name(), cart.get(i).getP_ID(),
//						cart.get(i).getP_Price(), cart.get(i).getP_DESC(), cart.get(i).getU_ID()));
//			}
//			totalPrice += cart.get(i).getP_Price();
//		}
//		
//		System.out.println(String.format("%20s%10d", "總計 = NT$", totalPrice));
////				session.setAttribute("O_Amt", totalPrice);
//		System.out.println("**********************************************************************************************************************");
//		return;		
//	}
//}
