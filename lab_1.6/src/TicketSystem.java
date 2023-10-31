import java.util.*;

class Station {
    private String name;
    private String arrivalTime;
    private String departureTime;
    private int availableSeats;

    public Station(String name, String arrivalTime, String departureTime, int availableSeats) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
    }

    public String getName() {
        return name;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}

class Route {
    private List<Station> stations;
    private int totalSeats;
    private String daysOfWeek;
    private int flightNumber;

    public Route(int flightNumber, String daysOfWeek, int totalSeats) {
        this.flightNumber = flightNumber;
        this.daysOfWeek = daysOfWeek;
        this.totalSeats = totalSeats;
        this.stations = new ArrayList<>();
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public void removeStation(Station station) {
        stations.remove(station);
    }

    public void printRoute() {
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Days of the week: " + daysOfWeek);
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Stations:");
        for (Station station : stations) {
            System.out.println(" - " + station.getName());
        }
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public List<Station> getStations() {
        return stations;
    }

    public static Comparator<Route> sortByTotalSeats = (r1, r2) -> r1.getTotalSeats() - r2.getTotalSeats();
}

public class TicketSystem {
    private List<Route> routes = new ArrayList<>();

    public void addRoute(int flightNumber, String daysOfWeek, int totalSeats) {
        Scanner scanner = new Scanner(System.in);
        Route route = new Route(flightNumber, daysOfWeek, totalSeats);
        while (true) {
            System.out.println("Додайте станцію до маршруту (або натисніть Enter, щоб завершити): ");
            System.out.print("Назва станції: ");
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                break;
            }
            System.out.print("Час прибуття: ");
            String arrivalTime = scanner.nextLine();
            System.out.print("Час відправлення: ");
            String departureTime = scanner.nextLine();
            System.out.print("Кількість вільних місць: ");
            int availableSeats = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            Station station = new Station(name, arrivalTime, departureTime, availableSeats);
            route.addStation(station);
        }

        routes.add(route);
        System.out.println("Маршрут з номером рейсу " + flightNumber + " був доданий.");
    }

    public void removeRouteByFlightNumber(int flightNumber) {
        Iterator<Route> iterator = routes.iterator();
        while (iterator.hasNext()) {
            Route route = iterator.next();
            if (route.getFlightNumber() == flightNumber) {
                iterator.remove();
                System.out.println("Маршрут з номером рейсу " + flightNumber + " був видалений.");
                return;
            }
        }
        System.out.println("Маршрут з номером рейсу " + flightNumber + " не знайдено.");
    }

    public void listRoutes() {
        for (Route route : routes) {
            route.printRoute();
            System.out.println();
        }
    }

    public void sortRoutesByTotalSeats() {
        Collections.sort(routes, Route.sortByTotalSeats);
    }

    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Додати маршрут");
            System.out.println("2. Видалити маршрут");
            System.out.println("3. Переглянути список маршрутів");
            System.out.println("4. Відсортувати маршрути за кількістю місць");
            System.out.println("5. Вийти");
            System.out.print("Оберіть опцію: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введіть номер рейсу: ");
                    int flightNumber = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера
                    System.out.print("Введіть дні тижня: ");
                    String daysOfWeek = scanner.nextLine();
                    System.out.print("Введіть загальну кількість місць: ");
                    int totalSeats = scanner.nextInt();
                    ticketSystem.addRoute(flightNumber, daysOfWeek, totalSeats);
                    break;
                case 2:
                    System.out.print("Введіть номер рейсу для видалення: ");
                    int flightNumberToDelete = scanner.nextInt();
                    ticketSystem.removeRouteByFlightNumber(flightNumberToDelete);
                    break;
                case 3:
                    ticketSystem.listRoutes();
                    break;
                case 4:
                    ticketSystem.sortRoutesByTotalSeats();
                    System.out.println("Маршрути відсортовані за кількістю місць.");
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
