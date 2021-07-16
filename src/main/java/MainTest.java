import ua.lviv.trainapplogos.domain.Bucket;
import ua.lviv.trainapplogos.domain.Product;
import ua.lviv.trainapplogos.domain.UserAccount;
import ua.lviv.trainapplogos.service.BucketService;
import ua.lviv.trainapplogos.service.ProductService;
import ua.lviv.trainapplogos.service.UserAccountService;
import ua.lviv.trainapplogos.service.impl.BucketServiceImpl;
import ua.lviv.trainapplogos.service.impl.ProductServiceImpl;
import ua.lviv.trainapplogos.service.impl.UserAccountServiceImpl;

public class MainTest {
	public static void main(String[] args) {
		//user record creation
		UserAccountService userService = UserAccountServiceImpl.getUserService(); //new UserServiceImpl();
		userService.create(new UserAccount("admin@a.a", "Adminitstrator", "Freelancer", "password", "ADMINISTRATOR"));
		
		UserAccount user = userService.read(1);
		System.out.println("> 1st User: " + user);
		
		user.setEmail("changed_test2@test.test");
		userService.update(user);
		user = userService.read(1);
		System.out.println("> 1st User after updating: " + user);
		
		//userService.delete(1);
		user = userService.read(1);
		System.out.println("> Retrieve 1st User after deleting: " + user);
		
		//Product record creation
		ProductService product = ProductServiceImpl.getProductService();
		product.create(new Product("Bike", "Commanchero", 1300.0));
		
		//Bucket record creation
		BucketService b = BucketServiceImpl.getBucketService();
		b.create(new Bucket(1, 1, new java.util.Date()));
		b.read(18);
		
	}
}
