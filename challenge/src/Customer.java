import static java.lang.Integer.parseInt;
    public class Customer {

        public int xAxis = 0;
        public int yAxis = 0;
        public int reward = 0;

        public Customer(String zeile) {
            String[] Line = zeile.split(" ");
            xAxis = parseInt(Line[0]);
            yAxis = parseInt(Line[1]);
            reward = parseInt(Line[2]);
        }
    }
