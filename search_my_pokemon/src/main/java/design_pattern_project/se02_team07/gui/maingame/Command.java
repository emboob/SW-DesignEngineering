package design_pattern_project.se02_team07.gui.maingame;

import javax.swing.JFrame;

/**
 * 커맨드 패턴을 적용하여 Command 역할을 하며 캡처기능을 수행하는 인터페이스
 * 
 * @author  20153270 이승윤
 * @version 1.0.0
 * @since   2019.05.27 클래스 최초 생성
 */
public interface Command {
    
    public abstract void execute(JFrame frame);
}
