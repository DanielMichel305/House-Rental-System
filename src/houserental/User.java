package houserental;
        
import java.util.ArrayList;

abstract public class User{

   
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String email;
    protected String phone;
    protected String userName;
    protected String password;
    protected UserType type;
    protected String userID;
    protected UserType userType;
    static protected int userCounter;
    static protected ArrayList<User> Users;
    static protected ArrayList<Receptionist> Receptionists;   ///Moved From Receptionist Class and changed to static 
    static protected ArrayList<Admin> Admins;
    static protected ArrayList<Renter> Renters;
    
    User(){}
    
    User(String firstName, String lastName, String email, String phone, int age, String userName, String password, UserType type){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.userName = userName;
        this.password = password;
        this.type = type;
    }
    
    public static String generateUserId(String FirstName, String LastName, int age, String phone, String email, Enum type) {
        // Extract initials from the first and last name
        System.out.println("houserental.User.generateUserId()");
        char firstInitial = FirstName.isEmpty() ? '?' : FirstName.charAt(0);
        char lastInitial = LastName.isEmpty() ? '?' : LastName.charAt(0);

        // Use the age as part of the ID
        String ageString = String.valueOf(age);

        // Generate a unique counter for each user
        String counterString = String.valueOf(userCounter++);

        // Combine the elements
        String userId = type.toString() + String.valueOf(firstInitial) + String.valueOf(lastInitial) + ageString + counterString;

        return userId;
    } 
    
    
//    public User getUserByID(String UserID){ ///Error Handling
//        
//        for (User e : Users){
//            if(e.userID.equals(UserID)){
//                int index = Users.indexOf(e);
//                return Users.get(index);
//            }
//        }
//        return null;
//    }
    public ArrayList<User> getUsersByType(UserType userType){
        
        ArrayList<User> Users = new ArrayList();
        for(User e: Users)
        {
            if(e.userType == userType){
                Users.add(e);
            }
        }
        return Users;
        
    }
    
     
    
    
    
    abstract protected void signUp(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword, String userID);
    abstract protected void login(String username, String password);
    abstract protected void writeBin();
    abstract protected void readBin();
    // all users have unique ArrayLists making it impossible to generalize in this class so it will be overridden in each subclass
    
    
     public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
}