package MovieTicketDesign;
import java.util.*;

abstract class movieDetails{
	String MovieName;
	String slot;
	int NumOfPerson;
	List<Seat>seats;
	movieDetails(String MovieName,String slot,int NumOfPerson){
		this.MovieName = MovieName;
		this.slot = slot;
		this.NumOfPerson = NumOfPerson;
		seats = new ArrayList<>();
		initializeSeat();
	}
	public String getMovieName() {
		return MovieName;
	}
	public void setMovieName(String movieName) {
		MovieName = movieName;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	public int getNumOfPerson() {
		return NumOfPerson;
	}
	public void setNumOfPerson(int numOfPerson) {
		NumOfPerson = numOfPerson;
	}
	private void initializeSeat() {
		for (char row = 'A'; row < 'F'; row++) {
            for (int seatNum = 1; seatNum <= 10; seatNum++) {
                seats.add(new Seat(row + String.valueOf(seatNum)));
            }
        }
	}
	public void avilableSeats() {
		System.out.println("Avilable Seats : ");
		for(Seat seat:seats) {
			 if (seat.isAvailable()) {
	                System.out.print(seat.getSeatNumber() + " ");
	            }
	        }
	        System.out.println();
	}
	
	abstract void userDetials();
}
class userDetails extends movieDetails{
	private String userName;
	private String userEmail;
	private String phoneNumber;
	private String catergoryTicket;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCatergoryTicket() {
		return catergoryTicket;
	}

	public void setCatergoryTicket(String catergoryTicket) {
		this.catergoryTicket = catergoryTicket;
	}

	userDetails(String MovieName, String slot, int NumOfPerson) {
		super(MovieName, slot, NumOfPerson);
		
	}

	@Override
	void userDetials() {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("Enter Name: ");
		userName = sc.nextLine();
		System.out.print("Enter Your Phone Number : ");
		phoneNumber = sc.nextLine();
		System.out.print("Enter Your Email: ");
		userEmail = sc.nextLine();
		System.out.print("Total Number Of Person: ");
		NumOfPerson = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter Your Category(bronze/silver/gold/platinum)(press 1,2,3,4) : ");
		catergoryTicket = sc.nextLine();
		
		System.out.println("---------------Welcome------------");
		System.out.println("Name: " + userName);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Email: " + userEmail);
        System.out.println("Movie: " + MovieName);
        System.out.println("Slot: " + slot);
        System.out.println("Number of Persons: " + NumOfPerson);
        avilableSeats();
        System.out.println("Category: " + catergoryTicket);
        
        int ticketprice = 0;
        if (catergoryTicket.equals("1")) {
            catergoryTicket = "Bronze";
            ticketprice = 150;
        } else if (catergoryTicket.equals("2")) {
            catergoryTicket = "Silver";
            ticketprice = 200;
        } else if (catergoryTicket.equals("3")) {
            catergoryTicket = "Gold";
            ticketprice = 250;
        } else if (catergoryTicket.equals("4")) {
            catergoryTicket = "Platinum";
            ticketprice = 300;
        } else {
            System.out.print("Invalid Category !");
            return;
        }

        System.out.println("Category: " + catergoryTicket);
        List<String> selectedSeats = new ArrayList<>();
        for (int i = 0; i < NumOfPerson; i++) {
            System.out.println("Select seat number " + (i + 1) + ": ");
            String seatChoice = sc.nextLine();

            boolean seatFound = false;
            for (Seat seat : seats) {
            	if (seat.getSeatNumber().equalsIgnoreCase(seatChoice) && seat.isAvailable()) {
                    seat.bookSeat();
                    selectedSeats.add(seatChoice);
                    seatFound = true;
                    break;
                }
            }
            if (!seatFound) {
                System.out.println("Seat " + seatChoice + " is not available, please select another seat.");
                i--;
            }
        }
        int totalPrice = NumOfPerson*ticketprice;
        System.out.println("Your Selected Seats: "+ selectedSeats);
        System.out.print("Your Total Bill is : "+ totalPrice);
        System.out.println("\nDo you want to proceed to payment? (yes/no): ");
        String paymentChoice = sc.nextLine();

        if (paymentChoice.equalsIgnoreCase("yes")) {
            System.out.print("Payment successful! Thank you, " + userName + ". Have a nice day!");
        } else {
            System.out.print("Payment cancelled. Thank you, " + userName + ". Have a nice day!");
        }
	}

	
}
class Seat {
    private String seatNumber;
    private boolean isAvailable;

    Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.isAvailable = true;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookSeat() {
        this.isAvailable = false;
    }
}
public class ticketBooking {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<String> movieTitles = Arrays.asList(
		        "Yeh Jawani Hai Dewani",
		        "Life in a Metro",
		        "Bhag Milkha Bhag",
		        "MS Dhoni: Untold Story"
		    );

		    List<String> timeSlots = Arrays.asList(
		        "11:00 AM - 1:30 PM",
		        "2:00 PM - 4:30 PM",
		        "5:15 PM - 7:45 PM",
		        "8:00 PM - 10:30 PM"
		    );

		   
		    List<userDetails> screenings = new ArrayList<>();

		    for (int i = 0; i < movieTitles.size(); i++) {
		        screenings.add(new userDetails(movieTitles.get(i), timeSlots.get(i), 0));
		    }

		    System.out.println("Available Movie Screenings:");
		    for (int i = 0; i < screenings.size(); i++) {
		        System.out.println((i + 1) + ". " + screenings.get(i).getMovieName() + " -- " + screenings.get(i).getSlot());
		    }

		    System.out.print("Select a screening to book tickets (Enter number): ");
		    int choice = sc.nextInt();
		    sc.nextLine();  

		    if (choice < 1 || choice > screenings.size()) {
		        System.out.println("Invalid choice!");
		        sc.close();
		        return;
		    }

		    userDetails selectedScreening = screenings.get(choice - 1);
		    selectedScreening.userDetials();
		    

	}

}
