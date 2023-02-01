package org.example.client;

import org.example.data.CourseInfo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws ClassNotFoundException{
        ObjectInputStream fromServer;
        ObjectOutputStream toServer;

        try {
            Socket socket = new Socket("localhost", 8000);
            System.out.println("Client connected");
            try {
                Scanner sc = new Scanner(System.in);
                toServer = new ObjectOutputStream(socket.getOutputStream());
                fromServer = new ObjectInputStream(socket.getInputStream());
                System.out.println("Please enter your Username and Password");
                boolean validation = false;

                String userName = sc.nextLine();


                String password = sc.nextLine();

                toServer.writeObject(userName);
                toServer.writeObject(password);

                validation = (Boolean) fromServer.readObject();

                while (!validation){

                    System.out.println("Please a valid Username and Password");
                    userName = sc.nextLine();
                    password = sc.nextLine();
                    toServer.writeObject(userName);
                    toServer.writeObject(password);

                    validation = (Boolean) fromServer.readObject();
                    System.out.println(validation);
                }

                System.out.println("User validated! ");
                System.out.println("Your courses are: ");
                List<CourseInfo> courses = (List<CourseInfo>) fromServer.readObject();
                int counter = 1;
                for (CourseInfo c: courses) {

                    System.out.println(counter + "- Course Name: " + c.getCourseName() + ", Course Grade: " + c.getCourseGrade());
                    counter++;
                }
                System.out.println("Please select a course: ");
                int selection = sc.nextInt();
                selection--;
                while( selection < 0 || selection > courses.size()){
                    System.out.println("Please enter a valid course: ");
                    selection = sc.nextInt();
                    selection--;
                }
                CourseInfo courseInfo = courses.get(selection);
                toServer.writeObject(courseInfo);
                System.out.println("Selected Course: " + courseInfo.getCourseName());


                int max = (int) fromServer.readObject();
                int min = (int) fromServer.readObject();
                double average = (Double) fromServer.readObject();
                double median = (Double) fromServer.readObject();


                System.out.println("Course Max Grade: " + max);
                System.out.println("Course Min Grade: " + min);
                System.out.println("Course Grades Average: " + average);
                System.out.println("Course Grades Median: " + median);
            }catch (Exception e){
                e.printStackTrace();
            }

        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
}