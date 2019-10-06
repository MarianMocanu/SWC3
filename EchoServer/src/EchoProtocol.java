public class EchoProtocol {
    public String processClientInput(String input) {
        String output = "";

        if (input == null) {
            output = "Say something!";
        } else {
            for (int i = 0; i < input.length(); i++) {
                String x = input.substring(i);
                output += x + " ";
            }

        }
        return output;
    }
}
