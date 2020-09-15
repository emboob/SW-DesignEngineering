package design_pattern_project.se02_team07.gui.dex;

import design_pattern_project.se02_team07.common_function.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 포켓몬 추가할 때 사용할 GUI클래스
 *
 * @author  20173116 신휘정
 * @version 1.0.0
 * @since   2019.05.28 클래스 최초 제작
 *           2019.05.30 회의 결과에 따라 수정        
 *           2019.05.31 라디오버튼 명령 읽어오기 시도중
 *           2019.06.01 명령 읽어오기 성공하여 제작 완료
 *           2019.06.03 라디오 버튼 클릭시 포커스 해제하였음
 *           2019.06.04 진화 불가능 선택시 기본 값 설정
 */
public class PokemonAdder{

    private static final String IMGPATH = "src/main/resources/images/pokeImg/";
    private ButtonGroup mainType = new ButtonGroup();
    private ButtonGroup subType =  new ButtonGroup();
    
    private String evolable;
    private ButtonGroup evolableTF;

    private JPanel jPanel;
    private JButton adder;

    private JTextField pokeNum;
    private JTextField pokeName;
    private JTextField baseStats;
    private JTextField finalLevel;
    private JTextField currentLevel;
    private JFrame frame = new JFrame("포켓몬을 추가합니다");

    /**
     * PokemonAdder 클래스의 생성자.
     */
    PokemonAdder() {
        init();
    }
    
    /**
     * 포켓몬 추가하는 메소드.
     */
    private void init() {
        jPanel = new JPanel();
        adder = new JButton("추가하기");
        JButton resetSubtype = new JButton("서브타입 초기화");
        
        adder.setFocusPainted(false);
        resetSubtype.setFocusPainted(false);
        
        jPanel.setLayout(null);
        setJPanel();

        /**
         * <pre> adderClicked(JButton adder);
         * </pre> adder의 이벤트처리기
         */
        adderClicked();
        
        frame.add(jPanel);
        jPanel.setBounds(20, 20, 805, 480);
        frame.add(adder);
        adder.setBounds(250, 520, 89, 27);
        frame.add(resetSubtype);
        resetSubtype.setBounds(480, 520, 160, 27);
        
        resetSubtype.addActionListener((ActionEvent e) -> 
            subType.clearSelection()
        );

        frame.setBounds(0, 0, 845, 600);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
    }

    /**
     * 라디오버튼이 입혀져있는 패널을 setting하는 메소드.
     */
    private void setJPanel() {
        JRadioButton evolableTrue;
        JRadioButton evolableFalse;
        JRadioButton noneEvolable;
        JLabel labelFilePath;
        JLabel labelPokeNum;
        JLabel labelPokeName;
        JLabel labelEvolable;
        JLabel labelPokeMainType;
        JLabel labelPokeSubType;
        JLabel labelBaseStats;
        JLabel labelFinalLevel;
        JLabel labelCurrentLevel;
        String errorMsg = "숫자로 입력하세요.";
        
        /**
         * 첫째 줄 : 파일 경로.
         */
        labelFilePath = new JLabel();
        labelFilePath.setText("파일 경로 : ");
        jPanel.add(labelFilePath);
        labelFilePath.setBounds(150, 10, 108, 24);
        
        labelFilePath = new JLabel();
        labelFilePath.setText(IMGPATH);
        labelFilePath.setPreferredSize(new Dimension(400, 30));
        jPanel.add(labelFilePath);
        labelFilePath.setBounds(300, 10, 400, 30);

        /**
         * 두번째 줄 : 포켓몬 번호.
         */
        labelPokeNum = new JLabel();
        labelPokeNum.setText("도감 번호 : ");
        jPanel.add(labelPokeNum);
        labelPokeNum.setBounds(150, 60, 108, 24);

        pokeNum = new JTextField();
        pokeNum.setText("세자리 숫자로 입력하세요.");
        pokeNum.setPreferredSize(new Dimension(400, 30));
        jPanel.add(pokeNum);
        pokeNum.setBounds(300, 60, 400, 30);
        
        /**
         * 세번째 줄 : 포켓몬 이름.
         */
        labelPokeName = new JLabel();
        labelPokeName.setText("포켓몬 이름 : ");
        jPanel.add(labelPokeName);
        labelPokeName.setBounds(140, 110, 128, 24);

        pokeName = new JTextField();
        pokeName.setText("이름을 입력하세요.");
        pokeName.setPreferredSize(new Dimension(400, 30));
        jPanel.add(pokeName);
        pokeName.setBounds(300, 110, 400, 30);

        /**
         * 네번째 줄 : 포켓몬 메인 타입.
         */
        labelPokeMainType = new JLabel();
        labelPokeMainType.setText("메인 타입 : ");
        jPanel.add(labelPokeMainType);
        labelPokeMainType.setBounds(150, 160, 128, 24);

        /**
         * <pre>
         * makeImgbutton(JPanel jPanel, ButtonGroup g, int y)
         * </pre> jPanel에 버튼그룹g를 만들고 y위치에 라디오버튼 모양으로 속성버튼을 만들어 준다. 끝나면 버튼 그룹을 리턴한다.
         */
        mainType = makeImgbutton(mainType, 163);
        
        /**
         * 다섯번째 줄 : 포켓몬 서브 타입. 
         */
        JLabel instruction = new JLabel();
        instruction.setText("<html>서브타입이 없다면<br>선택하지 마세요<br></html>");
        jPanel.add(instruction);
        instruction.setBounds(25,230,128,48);
        
        labelPokeSubType = new JLabel();
        labelPokeSubType.setText("서브 타입 : ");
        jPanel.add(labelPokeSubType);
        labelPokeSubType.setBounds(150, 230, 128, 24);

        /**
         * jPanel에 버튼그룹g를 만들고 y위치에 라디오버튼 모양으로 속성버튼을
         * 만들어 주며 끝나면 버트그룹을 리턴한다.
         * 
         * <pre>
         * makeImgbutton(JPanel jPanel, ButtonGroup g, int y)
         * </pre> .
         */
        subType = makeImgbutton(subType, 233);
        
        /**
         * 여섯번째 줄 : 포켓몬의 능력치.
         */
        labelBaseStats = new JLabel();
        labelBaseStats.setText("기본 스텟 : ");
        jPanel.add(labelBaseStats);
        labelBaseStats.setBounds(150, 290, 128, 24);

        baseStats = new JTextField();
        baseStats.setText(errorMsg);
        baseStats.setPreferredSize(new Dimension(400, 30));
        jPanel.add(baseStats);
        baseStats.setBounds(300, 290, 400, 30);

        /**
         * 일곱번째 줄 : 포켓몬의 진화 가능 여부.
         */
        labelEvolable = new JLabel();
        labelEvolable.setText("진화 : ");
        jPanel.add(labelEvolable);
        labelEvolable.setBounds(130, 340, 150, 24);
        
        evolableTrue = new JRadioButton("가능");
        evolableFalse = new JRadioButton("불가능");  
        evolableTrue.setFocusPainted(false);
        evolableFalse.setFocusPainted(false);
        evolableTF = new ButtonGroup();
        evolableTF.add(evolableTrue);
        evolableTF.add(evolableFalse);
        jPanel.add(evolableTrue);
        jPanel.add(evolableFalse);
        evolableTrue.setBounds(300, 340, 100, 30);
        evolableFalse.setBounds(500, 340, 100, 30);
        
        noneEvolable = new JRadioButton();
        noneEvolable.setActionCommand("");
        evolableTF.add(noneEvolable);
        noneEvolable.setSelected(true);
        
        /**
         * 라디오 버튼 클릭시의 이벤트 처리.
         */
        evolableTrue.addActionListener((ActionEvent e) -> {
            evolable = "가능";
            finalLevel.setEnabled(true);
            currentLevel.setEnabled(true);
        });
        
        evolableFalse.addActionListener((ActionEvent e) -> {
            evolable = "불가능";
            finalLevel.setEnabled(false);
            currentLevel.setEnabled(false);
            finalLevel.setText("1");
            currentLevel.setText("1");
        });
        
        /**
         * 여덟번째 줄 : 포켓몬의 최종 진화 단계.
         */
        labelFinalLevel = new JLabel();
        labelFinalLevel.setText("최종 진화 단계 : ");
        jPanel.add(labelFinalLevel);
        labelFinalLevel.setBounds(130, 390, 150, 24);

        finalLevel = new JTextField();
        finalLevel.setText(errorMsg);
        finalLevel.setPreferredSize(new Dimension(400, 30));
        jPanel.add(finalLevel);
        finalLevel.setBounds(300, 390, 400, 30);

        /**
         * 아홉번째 줄 : 포켓몬의 현재 진화 단계.
         */
        labelCurrentLevel = new JLabel();
        labelCurrentLevel.setText("현재 진화 단계 : ");
        jPanel.add(labelCurrentLevel);
        labelCurrentLevel.setBounds(130, 440, 150, 24);

        currentLevel = new JTextField();
        currentLevel.setText(errorMsg);
        currentLevel.setPreferredSize(new Dimension(400, 30));
        jPanel.add(currentLevel);
        currentLevel.setBounds(300, 440, 400, 30);
    }
    
    /**
     * 버튼 그룹을 가지고 이미지를 붙여서 반환하여 화면에 뿌리기 위한 메소드
     * 
     * @param buttonGroup 메인타입 그룹 or 서브타입 그룹중 설정할 그룹
     * @param y 버튼의 y위치
     * @return 이렇게 가공된 ButtonGroup을 리턴
     */
    private ButtonGroup makeImgbutton(ButtonGroup buttonGroup, int y) {
        JRadioButton noneRadioInAdder;

        ImageIcon normalTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Normal.png");
        ImageIcon fireTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Fire.png");
        ImageIcon waterTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Water.png");
        ImageIcon electricTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Electric.png");
        ImageIcon grassTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Grass.png");
        ImageIcon iceTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Ice.png");
        ImageIcon fightingTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Fighting.png");
        ImageIcon poisonTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Poison.png");
        ImageIcon groundTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Ground.png");
        ImageIcon flyingTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Flying.png");
        ImageIcon psychicTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Psychic.png");
        ImageIcon bugTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Bug.png");
        ImageIcon rockTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Rock.png");
        ImageIcon ghostTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Ghost.png");
        ImageIcon dragonTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Dragon.png");
        ImageIcon darkTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Dark.png");
        ImageIcon steelTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Steel.png");
        ImageIcon fairyTypeInAdder = new ImageIcon("src/main/resources/images/typeImg/Fairy.png");

        JRadioButtonMenuItem normalTypeRadioInAdder = new JRadioButtonMenuItem(normalTypeInAdder);
        JRadioButtonMenuItem fireTypeRadioInAdder = new JRadioButtonMenuItem(fireTypeInAdder);
        JRadioButtonMenuItem waterTypeRadioInAdder = new JRadioButtonMenuItem(waterTypeInAdder);
        JRadioButtonMenuItem electricTypeRadioInAdder = new JRadioButtonMenuItem(electricTypeInAdder);
        JRadioButtonMenuItem grassTypeRadioInAdder = new JRadioButtonMenuItem(grassTypeInAdder);
        JRadioButtonMenuItem iceTypeRadioInAdder = new JRadioButtonMenuItem(iceTypeInAdder);
        JRadioButtonMenuItem fightingTypeRadioInAdder = new JRadioButtonMenuItem(fightingTypeInAdder);
        JRadioButtonMenuItem poisonTypeRadioInAdder = new JRadioButtonMenuItem(poisonTypeInAdder);
        JRadioButtonMenuItem groundTypeRadioInAdder = new JRadioButtonMenuItem(groundTypeInAdder);
        JRadioButtonMenuItem flyingTypeRadioInAdder = new JRadioButtonMenuItem(flyingTypeInAdder);
        JRadioButtonMenuItem psychicTypeRadioInAdder = new JRadioButtonMenuItem(psychicTypeInAdder);
        JRadioButtonMenuItem bugTypeRadioInAdder = new JRadioButtonMenuItem(bugTypeInAdder);
        JRadioButtonMenuItem rockTypeRadioInAdder = new JRadioButtonMenuItem(rockTypeInAdder);
        JRadioButtonMenuItem ghostTypeRadioInAdder = new JRadioButtonMenuItem(ghostTypeInAdder);
        JRadioButtonMenuItem dragonTypeRadioInAdder = new JRadioButtonMenuItem(dragonTypeInAdder);
        JRadioButtonMenuItem darkTypeRadioInAdder = new JRadioButtonMenuItem(darkTypeInAdder);
        JRadioButtonMenuItem steelTypeRadioInAdder = new JRadioButtonMenuItem(steelTypeInAdder);
        JRadioButtonMenuItem fairyTypeRadioInAdder = new JRadioButtonMenuItem(fairyTypeInAdder);
        noneRadioInAdder = new JRadioButton();
        noneRadioInAdder.setVisible(false);
        noneRadioInAdder.setSelected(true);

        normalTypeRadioInAdder.setActionCommand("Normal");
        fireTypeRadioInAdder.setActionCommand("Fire");
        waterTypeRadioInAdder.setActionCommand("Water");
        electricTypeRadioInAdder.setActionCommand("Electric");
        grassTypeRadioInAdder.setActionCommand("Grass");
        iceTypeRadioInAdder.setActionCommand("Ice");
        fightingTypeRadioInAdder.setActionCommand("Fighting");
        poisonTypeRadioInAdder.setActionCommand("Poison");
        groundTypeRadioInAdder.setActionCommand("Ground");
        flyingTypeRadioInAdder.setActionCommand("Flying");
        psychicTypeRadioInAdder.setActionCommand("Psychic");
        bugTypeRadioInAdder.setActionCommand("Bug");
        rockTypeRadioInAdder.setActionCommand("Rock");
        ghostTypeRadioInAdder.setActionCommand("Ghost");
        dragonTypeRadioInAdder.setActionCommand("Dragon");
        darkTypeRadioInAdder.setActionCommand("Dark");
        steelTypeRadioInAdder.setActionCommand("Steel");
        fairyTypeRadioInAdder.setActionCommand("Fairy");
        noneRadioInAdder.setActionCommand("");
        
        buttonGroup.add(normalTypeRadioInAdder);
        buttonGroup.add(fireTypeRadioInAdder);
        buttonGroup.add(waterTypeRadioInAdder);
        buttonGroup.add(electricTypeRadioInAdder);
        buttonGroup.add(grassTypeRadioInAdder);
        buttonGroup.add(iceTypeRadioInAdder);
        buttonGroup.add(fightingTypeRadioInAdder);
        buttonGroup.add(poisonTypeRadioInAdder);
        buttonGroup.add(groundTypeRadioInAdder);
        buttonGroup.add(flyingTypeRadioInAdder);
        buttonGroup.add(psychicTypeRadioInAdder);
        buttonGroup.add(bugTypeRadioInAdder);
        buttonGroup.add(rockTypeRadioInAdder);
        buttonGroup.add(ghostTypeRadioInAdder);
        buttonGroup.add(dragonTypeRadioInAdder);
        buttonGroup.add(darkTypeRadioInAdder);
        buttonGroup.add(steelTypeRadioInAdder);
        buttonGroup.add(fairyTypeRadioInAdder);
        buttonGroup.add(noneRadioInAdder);
        
        normalTypeRadioInAdder.setBounds(150, y, 70, 20);
        fireTypeRadioInAdder.setBounds(220, y, 70, 20);
        waterTypeRadioInAdder.setBounds(290, y, 70, 20);
        electricTypeRadioInAdder.setBounds(360, y, 70, 20);
        grassTypeRadioInAdder.setBounds(430, y, 70, 20);
        iceTypeRadioInAdder.setBounds(500, y, 70, 20);
        fightingTypeRadioInAdder.setBounds(570, y, 70, 20);
        poisonTypeRadioInAdder.setBounds(640, y, 70, 20);
        groundTypeRadioInAdder.setBounds(710, y, 70, 20);

        int gap = 20;

        flyingTypeRadioInAdder.setBounds(150, y + gap, 70, 20);
        psychicTypeRadioInAdder.setBounds(220, y + gap, 70, 20);
        bugTypeRadioInAdder.setBounds(290, y + gap, 70, 20);
        rockTypeRadioInAdder.setBounds(360, y + gap, 70, 20);
        ghostTypeRadioInAdder.setBounds(430, y + gap, 70, 20);
        dragonTypeRadioInAdder.setBounds(500, y + gap, 70, 20);
        darkTypeRadioInAdder.setBounds(570, y + gap, 70, 20);
        steelTypeRadioInAdder.setBounds(640, y + gap, 70, 20);
        fairyTypeRadioInAdder.setBounds(710, y + gap, 70, 20);

        jPanel.add(fireTypeRadioInAdder);
        jPanel.add(waterTypeRadioInAdder);
        jPanel.add(electricTypeRadioInAdder);
        jPanel.add(grassTypeRadioInAdder);
        jPanel.add(iceTypeRadioInAdder);
        jPanel.add(fightingTypeRadioInAdder);
        jPanel.add(poisonTypeRadioInAdder);
        jPanel.add(groundTypeRadioInAdder);
        jPanel.add(flyingTypeRadioInAdder);
        jPanel.add(psychicTypeRadioInAdder);
        jPanel.add(bugTypeRadioInAdder);
        jPanel.add(rockTypeRadioInAdder);
        jPanel.add(ghostTypeRadioInAdder);
        jPanel.add(dragonTypeRadioInAdder);
        jPanel.add(darkTypeRadioInAdder);
        jPanel.add(steelTypeRadioInAdder);
        jPanel.add(fairyTypeRadioInAdder);

        return buttonGroup;
    }
    
    /**
     * 추가하기 버튼을 클릭하였을때 실행할 메소드로 진화 예외처리 옵션이 포함된 메소드.
     */
    private void adderClicked() {
        adder.addActionListener((ActionEvent e) -> {
            try{                
                String output;
                boolean evol = true;
                boolean mType = true;
                Integer.parseInt(pokeNum.getText());
                
                output = pokeNum.getText() + "/"; 
                output = output + pokeName.getText() + "/";
 
                if(("".equals(mainType.getSelection().getActionCommand()))) {
                    mType = false;
                JOptionPane.showMessageDialog(null,"메인타입을 입력하세요.");
                } else{
                    mType = true;
                }
                output = output + mainType.getSelection().getActionCommand() + "/";
                output = output + subType.getSelection().getActionCommand() + "/";
                output = output + Integer.parseInt(baseStats.getText()) + "/";
                
                if(("".equals(evolableTF.getSelection().getActionCommand()))) {
                    evol = false;
                    JOptionPane.showMessageDialog(null,"진화 가능 여부를 입력하세요.");
                } else {
                    evol = true;
                }
                output = output + evolable + "/";
                
                output = output + Integer.parseInt(finalLevel.getText())+ "/"; 
                output = output + Integer.parseInt(currentLevel.getText()); 
                output = output + "\n";
                
                if(evol&&mType){                
                    PokemonList.addPokemon(output);
                    JOptionPane.showMessageDialog(null,"추가 되었습니다.");
                    frame.setVisible(false);
                }
            } catch(NumberFormatException exception){
                JOptionPane.showMessageDialog(null,"도감번호, 기본스텟, 최종진화단계, 현재진화단계는 숫자로 입력하세요.");
                pokeNum.setText("");
                baseStats.setText("");
                finalLevel.setText("");
                currentLevel.setText("");
            }         
        });
    }
    
}
