package design_pattern_project.se02_team07.gui.login;

import design_pattern_project.se02_team07.common_function.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * 회원가입을 위한 GUI클래스
 * 
 * @author  20153270 이승윤
 * @version 1.0.0
 * @since   2019.05.12 클래스 최초 생성
 *           2019.05.29 아이디 중복검색, 관리자 아이디생성 차단, null방지 추가
 *           2019.06.01 JFrame extends 수정
 *           2019.06.02 버튼 클릭시 포커스 해제하였음
 */
public class SignupGUI {
    
    public SignupGUI() {
        JFrame frame = new JFrame("회원가입");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel label = new JLabel("아이디 : ");
        JLabel pswrd = new JLabel("비밀번호 : ");
        JLabel name = new JLabel("이  름(영어) : ");
        JTextField txtID = new JTextField(10);
        JPasswordField txtpass = new JPasswordField(10);
        JTextField txtname = new JTextField(10);
        JButton signBtn = new JButton("회원가입");
        JButton backBtn = new JButton("돌아가기");
        
        signBtn.setFocusPainted(false);
        backBtn.setFocusPainted(false);
        
        panel.add(label);
        panel.add(txtID);
        panel.add(pswrd);
        panel.add(txtpass);
        panel.add(name);
        panel.add(txtname);
        panel.add(signBtn);
        panel.add(backBtn);
        
        label.setBounds(63,50,90,30);
        txtID.setBounds(115,52,120,30);
        pswrd.setBounds(50,100,90,30);
        txtpass.setBounds(115,102,120,30);
        name.setBounds(35,150,90,30);
        txtname.setBounds(115,152,120,30);
        
        signBtn.setBounds(250,52,100,80);
        backBtn.setBounds(10,320,90,30);
        
        signBtn.addActionListener((ActionEvent e) -> {
            if(txtID.getText().equals("") || new String(txtpass.getPassword()).equals("") || txtname.getText().equals("")){
                JOptionPane.showMessageDialog(null,"모든 정보를 입력하셔야 합니다.");
                return;
            }
            
            String[] arr = txtID.getText().split("");
            for(int i=0;i<txtID.getText().length();i++){
                if(arr[i].equals("_")){
                    JOptionPane.showMessageDialog(null,"_는 사용할 수 없습니다.");
                    return;
                }
            }
            if(UserData.compareId(txtID.getText())){
                JOptionPane.showMessageDialog(null,"중복된 아이디 입니다.");
                return;
            }
            try {
                String str = String.format("%s %s %s",txtID.getText(),new String(txtpass.getPassword()), txtname.getText());
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/texts/login/member.txt",true)));
                out.println(str);
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(SignupGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(false);
        });
        
        backBtn.addActionListener((ActionEvent e) -> 
            frame.setVisible(false)
        );
        
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    
}
