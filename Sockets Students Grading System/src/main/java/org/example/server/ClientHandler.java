package org.example.server;

import org.example.data.CourseInfo;
import org.example.data.StudentDaoUser;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable{
    final StudentDaoUser studentDaoUser;
    final Socket socket;
     ObjectInputStream fromClient;
     ObjectOutputStream toClient;

    public ClientHandler(Socket client) {
        studentDaoUser = new StudentDaoUser();
        socket = client;
        try {
            fromClient = new ObjectInputStream(socket.getInputStream());
            toClient = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private double calculateMedian(List<CourseInfo> courses){
        if(courses.size()%2==0){

            double first = courses.get(courses.size()/2).getCourseGrade();
            double second = courses.get(courses.size()/2 -1 ).getCourseGrade();
            return  (first + second) / 2;

        }
        else{
            return courses.get(courses.size()/2).getCourseGrade();
        }

    }

    @Override
    public void run() {
        try {
            String userName = (String)fromClient.readObject();
            String password = (String)fromClient.readObject();
            boolean validation = studentDaoUser.validateCredentials(userName, password);
            toClient.writeObject((Boolean)validation);
            while (!validation){
                userName = (String)fromClient.readObject();
                password = (String)fromClient.readObject();
                validation = studentDaoUser.validateCredentials(userName, password);
                System.out.println(validation);
                toClient.writeObject(validation);
            }
            int currentStudentId = studentDaoUser.getStudentID(userName);
            List<CourseInfo> courses = new ArrayList<>();
            courses = studentDaoUser.getStudentInfo(currentStudentId);
            toClient.writeObject(courses);
            toClient.flush();
            CourseInfo courseInfo = (CourseInfo) fromClient.readObject();
            courses.clear();


            courses = studentDaoUser.getCourseInfo(courseInfo);
            int maxGrade = studentDaoUser.getMaxGrade(courseInfo);
            int minGrade = studentDaoUser.getMinGrade(courseInfo);
            double gradesAverage = studentDaoUser.getGradesAverage(courseInfo);
            double gradesMedian = calculateMedian(courses);

            toClient.writeObject(maxGrade);
            toClient.writeObject(minGrade);
            toClient.writeObject(gradesAverage);
            toClient.writeObject(gradesMedian);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
