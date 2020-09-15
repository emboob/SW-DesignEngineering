package design_pattern_project.se02_team07.gui.maingame;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 커맨드 패턴을 적용 Invoker역할을하며, 캡처 기능을 실행시킬 버튼 클래스
 * 
 * @author  20153270 이승윤
 * @version 1.0.0
 * @since   2019.05.27 클래스 최초 생성
 *           2019.05.31 소나큐브 오류 수정
 *           2019.06.01 JButton extends 수정
 *           2019.06.03 버튼 클릭시 포커스 해제하였음
 */
public class CaptureButton{
    JButton button = new JButton();
    private Command theCommand;
    
    /**
     * 커맨드 인터페이스에 현재 명렁을 set하는 메소드
     * 
     * @param newCommand 받아오는 명령의 종류
     */
    public void setCommand(Command newCommand){
        this.theCommand = newCommand;
    }
    
    /**
     * 버튼이 눌러진 것을 받아들이는 메소드
     * 
     * @param frame 프레임을 받아와 캡쳐할 위치를 계산하기 위해 받아온다.
     */
    public void pressed(JFrame frame){
        theCommand.execute(frame);
    }
    
    /**
     * CaptureButton 클래스의 생성자.
     * 
     * @param frame 현재 JFrame의 위치를 지정하여 그 부분을 찍음.
     */
    public CaptureButton(JFrame frame) {
        
        button.setText("캡쳐하기");
        button.setFocusPainted(false);
        button.setBounds(200, 400, 100, 50);
        
        button.addActionListener((ActionEvent e) -> {
            Capture capture = new Capture();
            CaptureCommand co = new CaptureCommand(capture);
            setCommand(co);
            pressed(frame);
        });
    }
}
