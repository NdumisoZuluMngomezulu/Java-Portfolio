import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents a Flight
class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private int availableSeats;
    private double price;

    public Flight(String flightNumber, String origin, String destination, String departureTime, int availableSeats, double price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    // Getters
    public String getFlightNumber() { return flightNumber; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getDepartureTime() { return departureTime; }
    public int getAvailableSeats() { return availableSeats; }
    public double getPrice() { return price; }

    // Method to decrease available seats after booking
    public void decreaseAvailableSeats(int numSeats) {
        if (this.availableSeats >= numSeats) {
            this.availableSeats -= numSeats;
        } else {
            System.out.println("Not enough seats available for this flight.");
        }
    }

    @Override
    public String toString() {
        return "Flight " + flightNumber + " from " + origin + " to " + destination +
               " at " + departureTime + " (Available Seats: " + availableSeats + ", Price: $" + price + ")";
    }
}

// Represents a Booking
class Booking {
    private String bookingId;
    private Flight bookedFlight;
    private String passengerName;
    private int numberOfTickets;
    private double totalPrice;

    public Booking(Flight bookedFlight, String passengerName, int numberOfTickets, double totalPrice) {
        this.bookingId = generateBookingId();
        this.bookedFlight = bookedFlight;
        this.passengerName = passengerName;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
    }

    private String generateBookingId() {
        Random random = new Random();
        return "BK" + String.format("%06d", random.nextInt(999999));
    }

    // Getters
    public String getBookingId() { return bookingId; }
    public Flight getBookedFlight() { return bookedFlight; }
    public String getPassengerName() { return passengerName; }
    public int getNumberOfTickets() { return numberOfTickets; }
    public double getTotalPrice() { return totalPrice; }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + "\n" +
               "Passenger: " + passengerName + "\n" +
               "Flight: " + bookedFlight.getFlightNumber() + " (" + bookedFlight.getOrigin() + " to " + bookedFlight.getDestination() + ")\n" +
               "Tickets: " + numberOfTickets + "\n" +
               "Total Price: $" + totalPrice;
    }
}

// Main system class
public class AirlineTicketSystem {
    private List<Flight> flights;
    private List<Booking> bookings;

    public AirlineTicketSystem() {
        this.flights = new ArrayList<>();
        this.bookings = new ArrayList<>();
        // Add some sample flights
        flights.add(new Flight("AA101", "NYC", "LAX", "10:00 AM", 150, 250.00));
        flights.add(new Flight("DL202", "LAX", "CHI", "02:30 PM", 100, 180.00));
    }

    public void displayAvailableFlights() {
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    public Booking bookFlight(String flightNumber, String passengerName, int numberOfTickets) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                if (flight.getAvailableSeats() >= numberOfTickets) {
                    double totalPrice = flight.getPrice() * numberOfTickets;
                    flight.decreaseAvailableSeats(numberOfTickets);
                    Booking newBooking = new Booking(flight, passengerName, numberOfTickets, totalPrice);
                    bookings.add(newBooking);
                    System.out.println("Flight booked successfully!");
                    return newBooking;
                } else {
                    System.out.println("Booking failed: Not enough seats available on Flight " + flightNumber);
                    return null;
                }
            }
        }
        System.out.println("Booking failed: Flight " + flightNumber + " not found.");
        return null;
    }

    public static void main(String[] args) {
        AirlineTicketSystem system = new AirlineTicketSystem();
        system.displayAvailableFlights();

        System.out.println("\n--- Attempting to book a flight ---");
        Booking booking1 = system.bookFlight("AA101", "John Doe", 2);
        if (booking1 != null) {
            System.out.println(booking1);
        }

        System.out.println("\n--- Displaying flights after booking ---");
        system.displayAvailableFlights();

        System.out.println("\n--- Attempting to book another flight with insufficient seats ---");
        Booking booking2 = system.bookFlight("DL202", "Jane Smith", 120); // More than available seats
        if (booking2 != null) {
            System.out.println(booking2);
        }
    }
}