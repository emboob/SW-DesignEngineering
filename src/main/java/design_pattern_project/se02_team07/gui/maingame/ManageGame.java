package design_pattern_project.se02_team07.gui.maingame;

import design_pattern_project.se02_team07.common_function.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.List;

/**
 * 메인 게임에서 사용할 포켓몬을 설정하는 클래스
 *
 * @author  20163371 전찬혁
 * @version 1.0.0
 * @since   2019.05.25 클래스 최초 생성
 *           2019.05.31 소나큐브 오류 수정
 *           2019.06.01 JFrame extends 수정
 *           2019.06.02 버튼클릭시 포커스 해제하였고, 게임 설정 폰트 변경
 */
public class ManageGame{
    
    /**
     * ManageGame 생성자.
     */
    public ManageGame(){
        JFrame frame = new JFrame("게임 설정");
        frame.setLayout(new BorderLayout());
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("게임 설정");
        title.setFont(new Font("궁서", Font.BOLD, 20));
        titlePanel.add(title);
        
        JPanel settingPanel = new JPanel();
        settingPanel.setLayout(null);
        
        JLabel usePokemonNumLabel = new JLabel("사용할 포켓몬 수");
        JTextField usePokemonNum = new JTextField();
        usePokemonNumLabel.setBounds(80, 50, 100, 20);
        usePokemonNum.setBounds(200, 50, 100, 20);
        
        JLabel usePokemonMinNumLabel = new JLabel("Min 도감 번호");
        JTextField usePokemonMinNum = new JTextField();
        usePokemonMinNumLabel.setBounds(80, 100, 100, 20);
        usePokemonMinNum.setBounds(200, 100, 100, 20);
        
        JLabel usePokemonMaxNumLabel = new JLabel("Max 도감 번호");
        JTextField usePokemonMaxNum = new JTextField();
        usePokemonMaxNumLabel.setBounds(80, 150, 100, 20);
        usePokemonMaxNum.setBounds(200, 150, 100, 20);
        
        JButton runGame = new JButton("게임 시작");
        runGame.setFocusPainted(false);
        runGame.setBounds(150,200,100,50);
        
        runGame.addActionListener((ActionEvent e) -> {
            try {
                int usingPokeNum = Integer.parseInt(usePokemonNum.getText());
                int usingMaxNum = Integer.parseInt(usePokemonMaxNum.getText());
                int usingMinNum = Integer.parseInt(usePokemonMinNum.getText());
                /**
                 * Collections.shuffle() = 리스트 섞기.
                 * 
                 * <pre>
                 *      Collections.shuffle(pokemonList) = 포켓몬 리스트를 섞음.
                 * </pre>
                 */
                
                List<Pokemon> tempList;
                tempList = PokemonList.makeListWithNum(PokemonList.getPokeList(), usingMinNum, usingMaxNum);
                if(tempList.size()<2){
                    JOptionPane.showMessageDialog(null, "게임에 필요한 포켓몬의 수가 부족합니다.");
                    usePokemonNum.setText("");
                    usePokemonMinNum.setText("");
                    usePokemonMaxNum.setText("");
                }
                else{
                    if(usingPokeNum > tempList.size()){
                        JOptionPane.showMessageDialog(null, "최대 "+tempList.size()+"마리의 포켓몬까지 입력 가능합니다.");
                        usePokemonNum.setText("");
                        usePokemonMinNum.setText("");
                        usePokemonMaxNum.setText("");
                    }
                    else{
                        List<Pokemon> usingList;
                        Collections.shuffle(tempList);
                        usingList = tempList.subList(0, usingPokeNum);
                        if(usingList.size()<2){
                            JOptionPane.showMessageDialog(null, "게임에 필요한 포켓몬의 수가 부족합니다.");
                            usePokemonNum.setText("");
                            usePokemonMinNum.setText("");
                            usePokemonMaxNum.setText("");
                        }
                        else{
                            new MainGame(usingList);
                            frame.dispose();
                        }
                    }
                }
                
            } catch(NumberFormatException exception){
                JOptionPane.showMessageDialog(null, "잘못된 값이 입력되어 있습니다.");
                usePokemonNum.setText("");
                usePokemonMinNum.setText("");
                usePokemonMaxNum.setText("");
            }
        });
        
        settingPanel.add(usePokemonNumLabel);
        settingPanel.add(usePokemonNum);
        settingPanel.add(usePokemonMinNumLabel);
        settingPanel.add(usePokemonMinNum);
        settingPanel.add(usePokemonMaxNumLabel);
        settingPanel.add(usePokemonMaxNum);
        settingPanel.add(runGame);
        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(settingPanel,BorderLayout.CENTER);
        
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    
}
