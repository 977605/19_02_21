package tests;

public class Data {
    private final String firstName;
    private final String lastName;
    private final String userEmail;
    private final String userNumber;
    private final String subjectsInput;
    private final String currentAddress;
    private final String state;
    private final String city;

    public Data(String firstName, String lastName,
                String userEmail, String userNumber,
                String subjectsInput, String currentAddress,
                String state, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userNumber = userNumber;
        this.subjectsInput = subjectsInput;
        this.currentAddress = currentAddress;
        this.state = state;
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getSubjectsInput() {
        return subjectsInput;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }
}
