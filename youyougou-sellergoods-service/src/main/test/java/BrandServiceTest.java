import com.youyougou.sellergoods.service.BrandService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BrandServiceTest {
    @Test
    public void findAllTest(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath*:spring/*.xml");
        BrandService brandService=(BrandService) applicationContext.getBean(BrandService.class);
        brandService.findAll();


    }
}
