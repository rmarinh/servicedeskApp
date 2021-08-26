package eapli.base.utils.emailsender;

import java.util.ArrayList;
import java.util.List;

public class Email {
    private String to;
    private String subject;
    private String body;



    public Email(String to, String subject, String body) {

        this.to = to;
        this.subject = subject;
        this.body = body;
    }


    public void replaceBodyVariable (String variable, String newValue ){
        this.body.replaceAll(variable, newValue);
    }

    public String to(){
        return this.to;
    }

    public String subject(){
        return this.subject;
    }

    public String body(){
        return this.body;
    }


    @Override
    public String toString() {
        return "Email{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
