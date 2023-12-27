package houserental;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/////kakakakakakak
public class Receptionist extends User implements Serializable{
    
    private static int bookingCounter = 0;
    private String ReceptionistID;
    private ArrayList<Booking> bookingList = new ArrayList<Booking>();
    private ArrayList<House> houseList = new ArrayList<House>();
   
     
    public Receptionist(){}
    
    public Receptionist(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword){
        //super = constructor to the user calss
        super(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword);; //1 here is the enum corresponding to renter in enum type   
        //Same Note as in Renter class regarding the use of super class constructor
     }
     
    public void setReceptionistID(String ReceptionistID){
        this.ReceptionistID = ReceptionistID;
    }
    
    public String getReceptionistID(){
        return this.ReceptionistID;
    }
    
      
        public String generateBookingID(House house, String renterID, Date startDate, Date endDate) { //House ID Creation creiteria 
                                                                                                           ////Why is this function static?     //Fixed
        char renterInitial = renterID.isEmpty() ? '?' : renterID.charAt(0);
        
        String numberOfRoomsString = String.valueOf(house.getNumberOfRooms());
        String houseIDString = String.valueOf(house.getHouseID());
        
        String counterString = String.valueOf(bookingCounter++);
        char categoryInitial = house.getCategory().toString().charAt(0);
        char viewInitial = house.getView().toString().charAt(0);
        String datePart = String.valueOf(startDate.getTime() % 100000) + String.valueOf(endDate.getTime() % 100000);
        
        String bookingID = String.valueOf(renterInitial) + houseIDString + numberOfRoomsString + counterString +categoryInitial + viewInitial + datePart;
        return bookingID;
    }
     public void createBooking(Renter renter, String HouseID ,int numberOfNights, Enum category, Enum view, Date dateOfRental, Date endOfRental, int numberOfRooms) {
         
        //i wanna check if a certain house is empty of not. //Fixed 
        
          int index = -1;
        for (int i = 0; i < houseList.size(); i++) {
            if (houseList.get(i).getNumberOfRooms() == numberOfRooms &&
                houseList.get(i).getCategory() == category &&
                houseList.get(i).getView() == view &&
                !houseList.get(i).isRented()) {
                index = i;
                break;
            } else {
                System.out.println("House Category isn't found.");
            }
        }

        if (index != -1) {
            String bookingID = generateBookingID(houseList.get(index), renter.getRenterID(), dateOfRental, endOfRental);
            
            //Booking newBooking = new Booking(bookingID,this ,numberOfRooms, category, view, dateOfRental, endOfRental); // no constractor that matchs this attributes
            Booking tempBook = null;
            double cost = tempBook.calculateCost(numberOfNights);
            Booking newBooking = new Booking(bookingID, this, renter,houseList.get(houseList.indexOf(HouseID)),dateOfRental, endOfRental, numberOfNights, cost); 
            bookingList.add(newBooking);
            // saveBookingToFile(newBooking);
        }
    }
    
    public void specifyRentalDetails(String BookingID){
        
          int index = -1 ;
       for(int i = 0 ; i <bookingList.size(); i++){
           if(bookingList.get(i).getBookingID().equals(BookingID)){
               index = i;
               break;
           }
       }
       if (index != -1) {
            System.out.println("Booking ID: " + bookingList.get(index).getBookingID());
        System.out.println("Renter: " + bookingList.get(index).getRenter().getUserName()); 
        System.out.println("House: " + bookingList.get(index).getRentedHouse().getHouseID());
       }
       else{
             System.out.println("Booking not found.");
       }
    }
    
    public void selecteHouseCategoty(Enum Category){
        House newHouse = new House(); //no defualt constructor //Added Default Constructor
        newHouse.setCategory(Category); //changed line to use setters 
    }
    
   public void cancelBooking(String bookingID) {
       
        bookingList.removeIf(booking -> booking.getBookingID().equals(bookingID));
       // removeBookingFromFile(bookingID);
    }

    public double calculatePayment(String BookingID,Date dateOfRental,Date endOfRental){
        
        int index = -1 ;
       for(int i = 0 ; i <bookingList.size(); i++){
           if(bookingList.get(i).getBookingID().equals(BookingID)){
               index = i;
               break;
           }
       }
       if (index != -1) {
       long diffInMillies = Math.abs(endOfRental.getTime() - dateOfRental.getTime());
       long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
       return bookingList.get(index).calculateCost((int) diffInDays); //Fixed in Booking class changed data tyepe from int to long
       }
       else {
            System.out.println("Booking not found.");
            return 0.0; 
        }
    }

      public Receptionist getUserByID(String UserID){ ///Error Handling
        
        for (Receptionist e : Receptionists){
            if(e.userID.equals(UserID)){
                int index = Receptionists.indexOf(e);
                return Receptionists.get(index);
            }
        }
        return null;
    }
      
      
      public ArrayList<Booking> getBookingList()
      {
          return this.bookingList;
      }
    
    /**
     *
     */

    @Override
    public boolean login(String username, String Password){
        if(Receptionists!=null){
                  for(Receptionist e: Receptionists){
            if(e.userName.equals(username) && e.password.equals(Password)){
                return true;
            }
        }
            return false;
        }
        return false;
    }

    @Override
    public void signUp(){
        //readBin();
        for(Receptionist r :Receptionists){
            if(r.getReceptionistID().equals(r.getReceptionistID()))
            {
                System.out.println("User Already exists!");
            }
            
        }
        Receptionists.add(this);
        //writeBin();
    }
}
