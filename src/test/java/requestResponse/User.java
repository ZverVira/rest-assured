package requestResponse;

public class User {
     private int id;
     private String email;
     private String firstName;
     private String lastName;
     private String avatar;

     public User(){}

     //Used for POST requests
     public User(String email, String firstName, String lastName, String avatar){
          setEmail(email);
          setFirstName(firstName);
          setLastName(lastName);
          setAvatar(avatar);
     }

     //Used for GET requests
     public User(int id, String email, String firstName, String lastName, String avatar){
          setId(id);
          setFirstName(firstName);
          setLastName(lastName);
          setAvatar(avatar);
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

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

     public String getAvatar() {
          return avatar;
     }

     public void setAvatar(String avatar) {
          this.avatar = avatar;
     }
}
