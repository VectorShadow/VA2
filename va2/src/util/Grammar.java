package util;

public class Grammar {
    public static String configure(
            boolean self,
            String selfIntro,
            String otherIntro,
            String variable,
            String selfOutro,
            String otherOutro
    ) {
        String configuredMessage = (self ? selfIntro : otherIntro) + " ";
        char[] unconfiguredMessage = variable.toCharArray();
        for (char c : unconfiguredMessage) {
            if (c == '$') {
                if (self) continue;
                else configuredMessage += 's';
            } else if (c == '&') {
                if (self) continue;
                else configuredMessage += "es";
            } else
                configuredMessage += c;
        }
        configuredMessage += " " + (self ? selfOutro : otherOutro);
        return configuredMessage;
    }
}
