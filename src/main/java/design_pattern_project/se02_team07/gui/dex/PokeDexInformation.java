package design_pattern_project.se02_team07.gui.dex;

import design_pattern_project.se02_team07.common_function.*;
import java.awt.*;
import javax.swing.*;

/**
 * 포켓몬 도감에서 선택한 포켓몬의 상세 정보를 보여주는 클래스
 * 
 * @author  20153265 김동현
 * @version 1.0.0
 * @since   2019.05.05 JFrame 클래스 최초 제작
 *           2019.05.06 보조 타입이 없는 상황에서 사진 정렬을 위해 코드 수정
 *           2019.05.09 교수님께서 단일 객체가 아니면 StringBuilder를 사용하라고 하셔서 수정
 *           2019.05.26 PokeDex 변경으로 인하여 수정작업 시작
 *           2019.05.27 포켓몬의 상세정보를 포켓몬 빌더를 이용해 가져옴
 *           2019.06.01 포켓몬 빌더의 생성자에서 남는 값들을 전부 사용하여 최종버전 완성하였고, JFrame extends 수정
 *           2019.06.02 PokemonButton 클래스의 수정으로 인한 오류 해결
 *           2019.06.04 서브타입 속성 변경으로 인한 최종 수정
 */
public class PokeDexInformation {
    private JFrame thispage;
    private GridBagConstraints grid;
    
    /**
     * PokDexInformation의 생성자로 포켓몬 상세정보를 사용가능하게 만들어줌.
     * 
     * @param pokemon 버튼에 들어있는 포켓몬 객체가 매개변수가 됨.
     */
    PokeDexInformation(JButton pokemonButton){
        String pokeNum = pokemonButton.getText().substring(3, 6);
        final SearchPokemon search = new SearchPokemon();
        search.startSearch(new SearchWithNum());
        Pokemon pokemon = search.search(PokemonList.getPokeList(),pokeNum).get(0);
        thispage = new JFrame("포켓몬 정보");
        thispage.getContentPane().setLayout(new GridBagLayout());
        grid = new GridBagConstraints();
        
        ImageIcon needImage = new ImageIcon(pokemon.getPokeImg());
        Image necessaryResize = needImage.getImage();
        Image resizeImage = necessaryResize.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
        ImageIcon pokemonImage = new ImageIcon(resizeImage);
        ImageIcon typeImage1 = new ImageIcon(pokemon.getMainTypeImg());
        ImageIcon typeImage2 = new ImageIcon(pokemon.getSubTypeImg());
        ImageIcon closeButton = new ImageIcon("src/main/resources/images/guiImg/close.png");
        
        JButton pickedPokeNumber = new JButton();
        pickedPokeNumber.setText("No. " + pokemon.getPokeNum());
        pickedPokeNumber.setFont(new Font("고딕", Font.ITALIC, 15));
        pickedPokeNumber.setPreferredSize(new Dimension(90, 25));
        pickedPokeNumber.setVerticalTextPosition(SwingConstants.CENTER);
        pickedPokeNumber.setHorizontalTextPosition(SwingConstants.RIGHT);
        pickedPokeNumber.setBorderPainted(false);
        pickedPokeNumber.setFocusPainted(false);
        pickedPokeNumber.setContentAreaFilled(false);
        
        JButton pickedPokeName = new JButton();
        pickedPokeName.setText(pokemon.getPokeName());
        pickedPokeName.setFont(new Font("궁서", Font.ITALIC, 15));
        pickedPokeName.setPreferredSize(new Dimension(110, 25));
        pickedPokeName.setVerticalTextPosition(SwingConstants.CENTER);
        pickedPokeName.setHorizontalTextPosition(SwingConstants.LEFT);
        pickedPokeName.setBorderPainted(false);
        pickedPokeName.setFocusPainted(false);
        pickedPokeName.setContentAreaFilled(false);
        
        JButton pickedPokeImg = new JButton();
        pickedPokeImg.setIcon(pokemonImage);
        pickedPokeImg.setPreferredSize(new Dimension(250, 250));
        pickedPokeImg.setBorderPainted(false);
        pickedPokeImg.setFocusPainted(false);
        pickedPokeImg.setContentAreaFilled(false);
        
        JButton close = new JButton();
        close.setIcon(closeButton);
        /**
         * 사진의 크기가 72 x 28이므로 버튼의 크기를 조절하여 이미지 크기에 맞춰줌
         */
        close.setPreferredSize(new Dimension(72,28));
        close.setBorderPainted(false);
        close.setFocusPainted(false);
        close.setContentAreaFilled(false);
        
        JButton typeImageMain = new JButton();
        typeImageMain.setIcon(typeImage1);
        typeImageMain.setPreferredSize(new Dimension(50, 24));
        typeImageMain.setBorderPainted(false);
        typeImageMain.setFocusPainted(false);
        typeImageMain.setContentAreaFilled(false);
        
        JButton typeImageSub = new JButton();
        typeImageSub.setIcon(typeImage2);
        typeImageSub.setPreferredSize(new Dimension(50, 24));
        typeImageSub.setBorderPainted(false);
        typeImageSub.setFocusPainted(false);
        typeImageSub.setContentAreaFilled(false);
        
        JButton pokemonStat = new JButton();
        pokemonStat.setText("능력치 : " + pokemon.getBaseStats());
        pokemonStat.setFont(new Font("궁서", Font.ITALIC, 15));
        pokemonStat.setPreferredSize(new Dimension(125, 25));
        pokemonStat.setBorderPainted(false);
        pokemonStat.setFocusPainted(false);
        pokemonStat.setContentAreaFilled(false);
        
        JButton pokemonLevel = new JButton();
        if("가능".equals(pokemon.getEvolvable())) {
            String temp1 = pokemon.getFinalLevel();
            String temp2 = pokemon.getCurrentLevel();
            int temp3 = Integer.parseInt(temp1);
            int temp4 = Integer.parseInt(temp2);
            
            pokemonLevel.setText("최종 진화 " + (temp3 - temp4) + "단계 남음");
        }
        else if ("불가능".equals(pokemon.getEvolvable())){
            pokemonLevel.setText("최종 진화 상태");
        }
        else {
            pokemonLevel.setText("정보 오류");
        }
        
        pokemonLevel.setFont(new Font("궁서", Font.ITALIC, 15));
        pokemonLevel.setPreferredSize(new Dimension(180, 25));
        pokemonLevel.setBorderPainted(false);
        pokemonLevel.setFocusPainted(false);
        pokemonLevel.setContentAreaFilled(false);
        
        /***
         * <pre>
         *      grid.insets = new Insets(1,2,3,4);
         * </pre>
         * GridBagLayout방식에서 여백을 주는 방식으로 위 코드를 예로 들어보면
         * 윗 방향으로 1px, 왼쪽 방향으로 2px, 아래 방향으로 3px,
         * 오른쪽 방향으로 4px 띄운다는 의미이다.
         */
        
        grid.anchor = GridBagConstraints.CENTER;
        
        grid.gridx = 0;
        grid.gridy = 0;
        grid.insets = new Insets(10, 60, 5, -15);
        thispage.getContentPane().add(pickedPokeNumber, grid);
        grid.insets = new Insets(12, -15, 5, 60);
        
        grid.gridx = 1;
        thispage.getContentPane().add(pickedPokeName, grid);
        
        grid.gridx = 0;
        grid.gridy = 1;
        grid.gridwidth = 2;
        grid.insets = new Insets(0, 15, 0, 15);
        thispage.getContentPane().add(pickedPokeImg, grid);

        grid.gridy = 2;
        if(pokemon.getSubType() == null) {
            grid.insets = new Insets(10, 10, 5, 10);
            thispage.getContentPane().add(typeImageMain, grid);            
        }
        else {
            grid.gridwidth = 1;
            grid.insets = new Insets(10, 60, 5, -47);
            thispage.getContentPane().add(typeImageMain, grid);
            
            grid.insets = new Insets(10, -22, 5, 60);
            grid.gridx = 1;
            thispage.getContentPane().add(typeImageSub, grid);
        }
        
        grid.gridx = 0;
        grid.gridwidth = 2;
        grid.gridy = 3;
        grid.insets = new Insets(5, 0, 5, 0);
        thispage.getContentPane().add(pokemonStat, grid);
            
        grid.gridy = 4;
        grid.insets = new Insets(5, 0, 5, 0);
        thispage.getContentPane().add(pokemonLevel, grid);
        
        grid.gridy = 5;
        grid.insets = new Insets(5,0,10,0);
        thispage.getContentPane().add(close, grid);
        
        thispage.pack();
        thispage.setVisible(true);
        thispage.setResizable(false);
        
        close.addActionListener(e -> 
           thispage.dispose()
        );
    }

}
