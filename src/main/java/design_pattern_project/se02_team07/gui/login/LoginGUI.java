package design_pattern_project.se02_team07.gui.login;

import design_pattern_project.se02_team07.common_function.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * 로그인과 유저 정보를 불러오는 클래스
 * 
 * @author  20153270 이승윤
 * @version 1.0.0
 * @since   2019.05.10 클래스 최초 생성, Gui구성
 *           2019.05.12 파일 입출력을 통한 로그인기능 추가
 *           2019.05.17 UserData클래스를 통한 사용자 정보 저장기능 추가
 *           2019.05.29 로그인 여부 확인을 UserData클래스에서 수행하도록 옮김
 *           2019.05.31 소나큐브 오류 수정
 *           2019.06.01 JFrame extends 수정
 *           2019.06.02 버튼 클릭시 포커스 해제하였음.
 */
public class LoginGUI{
    
    public LoginGUI(){
        JFrame frame = new JFrame("어서오세요!");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel label = new JLabel("아이디 : ");
        JLabel pswrd = new JLabel("비밀번호 : ");
        JTextField txtID = new JTextField(10);
        JPasswordField txtpass = new JPasswordField(10);
        JButton logBtn = new JButton("로그인");
        JButton signup = new JButton("회원가입");
        JButton guest = new JButton("게스트 로그인");
        
        logBtn.setFocusPainted(false);
        signup.setFocusPainted(false);
        guest.setFocusPainted(false);
        
        panel.add(label);
        panel.add(txtID);
        panel.add(pswrd);
        panel.add(txtpass);
        panel.add(logBtn);
        panel.add(signup);
        panel.add(guest);
        
        label.setBounds(63,50,90,30);
        txtID.setBounds(115,52,120,30);
        pswrd.setBounds(50,100,90,30);
        txtpass.setBounds(115,102,120,30);
        logBtn.setBounds(250,52,100,80);
        signup.setBounds(50,150,140,30);
        guest.setBounds(210,150,140,30);
        
        guest.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null, "Guest 계정으로 로그인됩니다.");
            new UserSelectGUI();
            frame.dispose();
        });
        
        signup.addActionListener((ActionEvent e) -> 
            new SignupGUI()
        );
        
        logBtn.addActionListener((ActionEvent e) -> {
            UserData.loginData(txtID.getText(), new String(txtpass.getPassword()));
            if(UserData.getUserState()) {
                JOptionPane.showMessageDialog(null, "Login Success");
                new UserSelectGUI();
                frame.dispose();
            } else{
                JOptionPane.showMessageDialog(null, "Check your ID or PassWord");
            }
        });
        
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}