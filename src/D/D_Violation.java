package D;

public class D_Violation {
    public static void main(String[] args){

    }
    public class StripePaymentGateway{
        public void charge(int amount){

        }
    }
    public class Order {
        public int getAmount(){
            return 0 ;
        }
    }
    public class CheckoutProcessor {
        private StripePaymentGateway stripeGateway;

        public CheckoutProcessor() {
            this.stripeGateway = new StripePaymentGateway();
        }

        public void checkout(Order order) {
            stripeGateway.charge(order.getAmount());
        }
    }

}
