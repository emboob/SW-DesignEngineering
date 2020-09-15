package design_pattern_project.se02_team07.gui.login;

import design_pattern_project.se02_team07.common_function.*;
import design_pattern_project.se02_team07.gui.dex.*;
import design_pattern_project.se02_team07.gui.maingame.*;
import design_pattern_project.se02_team07.gui.result_review.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 사용자의 기능을 선택할 수 있는 클래스
 * 
 * @author  20153270 이승윤
 * @version 1.0.0
 * @since   2019.05.12 클래스 최초생성
 *           2019.05.15 회원정보를 받아오게끔 수정
 *           2019.05.21 UserData의 정보를 받아오게끔 수정
 *           2019.06.01 소나큐브 오류수정
 *           2019.06.01 JFrame extends 수정
 *           2019.06.02 버튼 클릭시 포커스 해제하였음
 */
public class UserSelectGUI{

    public UserSelectGUI() {
        JFrame frame = new JFrame("원하시는 기능을 선택하세요");
        PokemonList.setPokemonList();
        String name = UserData.getName();
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel label = new JLabel();
        JButton partner = new JButton("파트너 찾기");
        JButton pokemonDex = new JButton("포켓몬 도감");
        JButton lookup = new JButton("파트너 조회");
        
        partner.setFocusPainted(false);
        pokemonDex.setFocusPainted(false);
        lookup.setFocusPainted(false);
        
        if (name != null) {
            name += "님 반갑습니다.";
        } else {
            name = "Guest님 반갑습니다";
            lookup.setEnabled(false);
        }

        panel.add(label);
        panel.add(partner);
        panel.add(pokemonDex);
        panel.add(lookup);

        label.setText(name);
        label.setBounds(10, 10, 150, 30);
        partner.setBounds(100, 70, 200, 50);
        pokemonDex.setBounds(100, 150, 200, 50);
        lookup.setBounds(100, 230, 200, 50);
        
        /**
         *  파트너 찾기 이벤트(메인 게임) 발생.
         */
        partner.addActionListener((ActionEvent e) -> 
            new ManageGame()
        );
        /**
         * 포켓몬 도감 이벤트 발생.
         */
        pokemonDex.addActionListener((ActionEvent e) -> 
            new PokeDex()
        );
        
        /**
         * 파트너 조회 이벤트 발생.
         */
        lookup.addActionListener((ActionEvent e) -> {
            if(UserData.getPokemon().isEmpty()){
                JOptionPane.showMessageDialog(null,"아직 저장된 포켓몬이 없습니다.");
            }
            else
                new InquiryDexGUI();
        });
        
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
