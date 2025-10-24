package lab2;


public class House {
    private int floors;

    public House(int floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        int n = floors % 100;
        String ending;

        if (n >= 11 && n <= 19) {
            ending = "этажами";
        } else {
            int last = n % 10;
            if (last == 1) {
                ending = "этажом";
            } else if (last >= 2 && last <= 4) {
                ending = "этажами";
            } else {
                ending = "этажами";
            }
        }

        return "Дом с " + floors + " " + ending;
    }
}
