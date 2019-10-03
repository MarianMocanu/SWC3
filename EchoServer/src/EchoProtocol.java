public class EchoProtocol {
    public String processClientInput(String input) {
        String output;

        if (input == null) {
            output = "Say something!";
        } else {
            output = input;
        }
        return output;
    }
}
