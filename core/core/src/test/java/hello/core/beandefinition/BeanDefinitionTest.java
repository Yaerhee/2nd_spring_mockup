package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    //AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    //cf) ApplicationContext로 설정할 경우 .getBeanDefinition()을 활용할 수 없음!
    
    //이번엔 xml로 설정했던 bean의 설정을 읽어봄 (xml로 읽을 시 정확하게 데이터가 입력된 것 같은 느낌을 받을 수 있음)
    //단, factory method 등과 같은 내용은 null임
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        " beanDefinition = " + beanDefinition);
                //factory bean, method 등을 확인할 수 있음
                //Init, Destroy method name 
                //조회한 해당 메타 정보를 가지고 실제 인스턴스를 생성할 수 있음
            }
        }
    }
}
