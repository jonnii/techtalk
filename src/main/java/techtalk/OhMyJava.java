package techtalk;

import java.math.BigDecimal;

public class OhMyJava {
    public static void Hello(){

        // use classes
        Person tom = new Person("Tom", "Jones");
        tom.getFirstName();
        tom.getLastName();

        // call functions
        String notImmutableHere = AppKt.getImmutableString();

        // use data classes
        MagicBean bean = new MagicBean(BeanType.Paula, new BigDecimal(500));
        bean.getKind();

        MagicBean extraFancyBean = bean.copy(bean.getKind(), new BigDecimal(600));
    }

    public class AmazingJavaClass {
        public String getName(){
            return "Ok";
        }
    }
}
