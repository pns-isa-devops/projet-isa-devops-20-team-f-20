package arquillian;

import core.BillerBean;
import core.InvoiceModifier;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractBillerTest {

    @Deployment
    public static WebArchive createDeployment(){
        WebArchive res = AbstractLivrairTest.createDeployment();
        return res.addPackage(InvoiceModifier.class.getPackage())
                .addPackage(BillerBean.class.getPackage());
    }
}
