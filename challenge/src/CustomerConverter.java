public class CustomerConverter {

    public Customer[] Customers;
    int counter = 0;

    public CustomerConverter(String[] CustomerFile) {
        int length = CustomerFile.length;
        Customers = new Customer[length];

        while ( counter < length ){
            String Line = CustomerFile[counter];
            Customer Customer = new Customer(Line);
            Customers[counter] = Customer;
            counter ++;
        }
    }
}
