package design_pattern_project.se02_team07.common_function;

import javax.swing.*;
import java.awt.*;

/**
 * 프로그램 전역에서 쓸 수 있는 포켓몬 사진 이름 번호가 들어있는 버튼 클래스
 * 
 * @author 20163371 전찬혁
 * @version 1.0.0
 * @since  2019.05.12 클래스 최초 제작
 *          2019.05.16 왼쪽 버튼과 오른쪽 버튼 클래스로 나뉘어져 있던 것을 통합
 *          2019.05.21 사진 크기가 버튼 크기에 맞춰지게 변경
 *          2019.05.27 포켓몬 버튼 클릭시 해당 포켓몬 객체가 넘어가 PokeDexInformation이 나오도록 추가 및 폰트 변경
 *          2019.05.31 소나큐브 오류 수정
 *          2019.06.01 JButton extends 수정
 *          2019.06.03 보안성 소나큐브 오류 수정
 */

public final class PokemonButton{
    /**
     * PokemonInButton = 버튼에 들어있는 포켓몬의 정보
     * width = 버튼 가로 길이
     * height = 버튼 세로 길이
     * 
     * @author 전찬혁
     */
    private Pokemon pokemonInButton;
    private JButton button;
    private int buttonWidth;
    private int buttonHeight;
    
    /**
     * 포켓몬 버튼의 생성자
     * 버튼 안에 들어갈 포켓몬, 위치, 크기를 받아 만든다.
     * 
     * @param pokemonInButton 버튼에 들어갈 포켓몬
     * @param x 버튼의 x 좌표
     * @param y 버튼의 y 좌표
     * @param width width 버튼의 가로 길이
     * @param height height 버튼의 세로 길이
     * 
     * <pre>
     *  setVerticalTextPosition(SwingConstants.BOTTOM) = 문자열을 버튼 내에서 하단 배치
     *  setHorizontalTextPosition(SwingConstants.CENTER) = 문자열을 버튼 내 수평 중앙 배치
     * </pre>
     */
    public PokemonButton(Pokemon pokemonInButton, int x, int y, int width, int height){
        this.button = new JButton();
        this.pokemonInButton = pokemonInButton;
        this.buttonWidth = width;
        this.buttonHeight = height;
        setButton(pokemonInButton);
        button.setBounds(x, y, width, height);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);  
    }
    
    /**
     * 버튼의 포켓몬 정보를 설정하는 메소드
     * 
     * @param pokemon 새로 버튼에 넣을 포켓몬
     * @return 포켓몬 버튼에 입혀진 이미지 및 정보 객체
     */
    public Pokemon setButton(Pokemon pokemon){
        Pokemon temp = this.pokemonInButton;
        this.pokemonInButton = pokemon;
        ImageIcon tempIcon = new ImageIcon(this.pokemonInButton.getPokeImg());
        Image tempImage = tempIcon.getImage();
        Image temp2Image = tempImage.getScaledInstance(buttonWidth-30, buttonHeight-30, java.awt.Image.SCALE_SMOOTH);
        ImageIcon pokemonImg = new ImageIcon(temp2Image);
        button.setIcon(pokemonImg);
        button.setText("No." + this.pokemonInButton.getPokeNum() + " " + this.pokemonInButton.getPokeName());
        button.setFont(new Font("궁서", Font.ITALIC, 13));
        return temp;
    }

    public Pokemon getPokemonInButton() {
        return pokemonInButton;
    }
    
    public JButton getButton() {
        return button;
    }
    
}
