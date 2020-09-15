package design_pattern_project.se02_team07.gui.maingame;

import design_pattern_project.se02_team07.common_function.*;
import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * 커맨드 패턴을 적용하여 캡쳐기능 수행에서 ConcreteCommand 역할을 수행하는 클래스
 * 
 * @author  20153270 이승윤
 * @version 1.0.0
 * @since   2019.05.27 클래스 최초 생성
 */
public class CaptureCommand implements Command {
    Capture capture;
    
    /**
     * CaptureCommand 클래스의 생성자.
     * 
     * @param capture 캡처가 수행하기위해 커맨드의 명령을 전달
     */
    public CaptureCommand(Capture capture){
        this.capture = capture;
    }
    
    /**
     * Command 인터페이스에서 정의한 exectue를 구현하는 메소드
     * 
     * @param frame 캡처할 창의 크기를 전달
     */
    @Override
    public void execute(JFrame frame) {
        try {
            if(UserData.getName()!=null){
                capture.onScreenCapture(frame);
            }
            else
                capture.offScreenCaptrue();
        } catch (AWTException ex) {
            Logger.getLogger(CaptureCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
