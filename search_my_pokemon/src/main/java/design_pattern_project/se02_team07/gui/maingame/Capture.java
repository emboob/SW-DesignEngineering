package design_pattern_project.se02_team07.gui.maingame;

import design_pattern_project.se02_team07.common_function.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * 커맨드 패턴을 적용하여 Receiver역할을 하며, 캡쳐기능을 수행하는 클래스
 * 
 * @author  20153270 이승윤
 * @version 1.0.0
 * @since   2019.05.27 클래스 최초 생성 경로 수정
 *           2019.05.28 IOXception 발생시 logger사용
 *           2019.05.31 소나큐브 오류 수정
 */
public class Capture {
    public static final Logger logger = Logger.getLogger(Capture.class.getName());

    /**
     * 화면을 캡처하는 메소드로 사용자인 경우에 실행되어 화면을 캡처하여 저장해줌.
     * 
     * @param frame 프레임을 받아와 캡쳐할 위치를 계산하기 위해 받아온다.
     * @throws AWTException  AWTException예외 처리
     */
    public void onScreenCapture(JFrame frame) throws AWTException {
        JOptionPane.showMessageDialog(null, "project/src/main/resources/images/capture 폴더에 저장됩니다.");
        try{
            Rectangle rectangle = new Rectangle();
            rectangle.setSize(255, 255);
            rectangle.setLocation(frame.getLocation().x+275,frame.getLocation().y+100);
            
            /**
             * 로봇 클래스의 createScreenCapture을 이용하여 전체 화면을 캡처함.
             */
            BufferedImage screencapture = new Robot().createScreenCapture(rectangle);

            /**
             * 캡처한 사진은 .jpg로 저장됨.
             */
            File file = new File("src/main/resources/images/capture/" +
                    UserData.getName() + "님의 " + (UserData.getPokemon().size() + 1)
                    + "회차 게임 결과.jpg");
            ImageIO.write(screencapture, "jpg", file);
        } catch (IOException e) {
            logger.warning("문제 발생");
        }
    }
    
    /**
     * 화면을 캡처하는 메소드로 비사용자인 경우에 실행되어 불가능하다고 알려줌.
     */
    public void offScreenCaptrue(){
        JOptionPane.showMessageDialog(null, "로그인 후 사용할 수 있습니다.");
    }
    
}
