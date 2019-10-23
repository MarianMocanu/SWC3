public class Protocol {
    private static final int greeting = 0;
    private static final int waiting = 1;
    private static final int repeating = 2;
    private static final int closing = 3;

    private int state = greeting;

    public String processInput(String input) {
        String output = null;

        if (state == greeting) {
            output = "Hello! I'm not a server!";
            state = waiting;

        } else if (state == waiting) {
            if (input == null) {
                output = "Don't sent me empty lines! Say something!";
            } else if (input.equalsIgnoreCase("Hello!")) {
                output = "I feel you. Go on please!";
                state = repeating;
            } else if (input.equalsIgnoreCase("Bye!")) {
                output = "Are you giving up on me?";
                state = closing;
            } else {
                output = "You're supposed to say \"Hello!\" to me!!!";
            }

        } else if (state == repeating) {
            output = "Why did you sent me this message? \"" + input + "\" I told you I am not a server!!!";
            if (input.equalsIgnoreCase("Bye!")) {
                output = "Are you giving up on me?";
                state = closing;
            }

        } else if (state == closing) {
            output = "Find somebody else to talk to! Nobody listens to you like I do!";
        }
        return output;
    }
}
