public class InputCustomerAdapter {

    private Customer[] lt_customer;

    InputCustomerAdapter(InputData io_input) {

        int lv_length = io_input.Customers.length;

        lt_customer = new Customer[lv_length];

        int lv_index = 0;

        while (lv_index < lv_length) {
            String Line = io_input.Customers[lv_index];
            lt_customer[lv_index++] = new Customer(Line);
        }

    }


    public Customer[] get_customer() {
        return lt_customer;
    }

}
