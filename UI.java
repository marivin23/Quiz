/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codequiz;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mariu
 */
public class UI extends JFrame implements ActionListener {
    
    private JLabel question;
    private JTextField answer;
    private JButton newQuestion, submit, addNew;
    private JPanel text, buttons;
    private final DBConnect db;
    
    public UI(DBConnect connection) {
        
        db = connection;
        question = new JLabel("<html>Press new question!<br></html>", SwingConstants.CENTER);
        
        answer = new JTextField();
        //answer.setColumns(35);
        answer.setSize(150, 40);
        
        newQuestion = new JButton("New");
        submit = new JButton("Submit answer");
        addNew = new JButton("Add new question");
        
        text = new JPanel(new GridLayout(2, 1));
        text.add(question);
        text.add(answer);
        
        buttons = new JPanel(new GridLayout(0, 3));
        buttons.add(submit);
        buttons.add(newQuestion);
        buttons.add(addNew);
        
        add(text, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
        
        newQuestion.addActionListener(this);
        submit.addActionListener(this);
        addNew.addActionListener(this);
        
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(525, 100);
        setTitle("CodeQuiz");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    db.endConnection();
                    System.exit(0);
                } catch (SQLException e) { }
            }
        });
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        
        question.setForeground(Color.black);
        
        try {
            if (e.getSource() == newQuestion) {

                NewQuestionOutput q = new NewQuestionOutput(db);
                question.setText(q.data());

            } else if (e.getSource() == submit) {

                String a = answer.getText().toUpperCase();
                NewQuestionOutput q = new NewQuestionOutput(db);

                String dbAnswer = q.data(q.index);

                if (a.equals(dbAnswer.toUpperCase())) {
                    question.setText("Correct!  Answer: " + dbAnswer);
                } else {
                    question.setForeground(Color.red);
                    question.setText("Wrong! Try again!");
                }

            } else if (e.getSource() == addNew) {

                AddNewQuestion newQuestion = new AddNewQuestion(db);

            }
        } catch (SQLException er) { }
        
    }
    
    
}
