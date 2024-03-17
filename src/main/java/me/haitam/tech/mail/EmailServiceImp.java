package me.haitam.tech.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(SimpleMailMessage message){
        mailSender.send(message);
    }

    public SimpleMailMessage setEmail(String email, String name){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Your Tech B'Darija Account - Quickstart guide");
        mail.setText("Hi "+name+", \n \n" + "Welcome to Tech B'Darija! 🚀\n \n"+
        "We're thrilled to have you join our community of IT enthusiasts and professionals. Your registration has been successfully completed, and you're now part of our vibrant community .\n \n"+
        "To help you get started on Tech B'Darija, we've prepared a quickstart guide to familiarize you with our platform's key features:\n \n"+
        "1 - Posting and Solving Technical Issues:  Have a coding problem or facing a technical challenge? Head over to the \"BugOverFlow\" section where you can post your and get solutions from our knowledgeable community.\n"+
        "2 - Blogging: Share your insights, experiences, and expertise by creating blog posts in our dedicated blogging section. It's a great way to contribute to the community and showcase your skills.\n"+
        "3 - Engaging Discussions: Dive into discussions on various IT topics in our vibrant community. Whether it's sharing industry trends, seeking advice, or discussing emerging technologies, there's always something interesting happening in our discussion threads.\n"+
        "4 - Job Opportunities: Explore internship and job opportunities posted by leading companies in the IT sector. Don't miss out on exciting career prospects tailored to your interests and skills.\n"+
        "5 - Online Book Library: Access our collection of free online books covering a wide range of IT topics. Expand your knowledge and stay updated with the latest developments in the field.\n \n"+
        "Remember, Tech B'Darija is not just a platform; it's a community where you can connect, learn, and grow alongside like-minded individuals passionate about technology.\n \n"+
        "To start exploring Tech B'Darija, simply click here"+" http://localhost:3000/home "+"to access your home page.\n \n"+
        "If you have any questions, feedback, or suggestions, feel free to reach out to us at [haitammk0708@gmail.com]. Our team is here to assist you every step of the way.\n \n"+
        "Best regards,\n \n"+
        "Haitam Mabrouk\n"+
        "Tech B'Darija Team");
        return mail;
    }
}
