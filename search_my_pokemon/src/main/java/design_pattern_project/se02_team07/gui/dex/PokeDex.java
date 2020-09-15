package design_pattern_project.se02_team07.gui.dex;

import design_pattern_project.se02_team07.common_function.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 포켓몬 도감 목록 GUI 클래스
 * 
 * @author  20153265 김동현
 * @version 1.0.0
 * @since   2019.05.04 JFrame 클래스 최초 제작
 *           2019.05.06 버튼으로 포켓몬 객체를 넘겨주어 실행시키도록 하였음.
 *           2019.05.09 교수님께서 단일 객체가 아니면 StringBuilder를 사용하라고 하셔서 수정
 *           2019.05.26 포켓몬 도감 디자인 완전히 바꾸었으며, 기존에 찬혁이가 만들어 둔 포켓몬 버튼과 포켓몬 리스트 사용함.
 *           2019.05.27 버튼 리스너 연결 및 패널 조정
 *           2019.05.28 검색 기능 추가 및 리셋하면 라디오 버튼 선택 해제
 *           2019.06.01 JFrame extends 수정
 *           2019.06.02 PokemonButton 클래스 수정으로 인한 오류 해결
 */
public final class PokeDex{
    private List<PokemonButton> pokemonButtonList;
    private List<Pokemon> pokemonList;
    private JPanel resultPanel;
    private ButtonGroup typeGroup;
    private JRadioButton noneRadio;
    private JViewport viewport;
    JTextField pokemonNum;
    JTextField pokemonName;
    
    /**
     * PokeDex GUI 생성자.
     */
    public PokeDex() {
        JFrame frame = new JFrame();
        pokemonButtonList = new ArrayList<>();
        this.pokemonList = new ArrayList<>();
        this.pokemonList = PokemonList.getPokeList();
        resultPanel = new JPanel();
        
        ImageIcon pokeDexTitle = new ImageIcon("src/main/resources/images/guiImg/dex.png");
        ImageIcon normalType = new ImageIcon("src/main/resources/images/typeImg/Normal.png");
        ImageIcon fireType = new ImageIcon("src/main/resources/images/typeImg/Fire.png");
        ImageIcon waterType = new ImageIcon("src/main/resources/images/typeImg/Water.png");
        ImageIcon electricType = new ImageIcon("src/main/resources/images/typeImg/Electric.png");
        ImageIcon grassType = new ImageIcon("src/main/resources/images/typeImg/Grass.png");
        ImageIcon iceType = new ImageIcon("src/main/resources/images/typeImg/Ice.png");
        ImageIcon fightingType = new ImageIcon("src/main/resources/images/typeImg/Fighting.png");
        ImageIcon poisonType = new ImageIcon("src/main/resources/images/typeImg/Poison.png");
        ImageIcon groundType = new ImageIcon("src/main/resources/images/typeImg/Ground.png");
        ImageIcon flyingType = new ImageIcon("src/main/resources/images/typeImg/Flying.png");
        ImageIcon psychicType = new ImageIcon("src/main/resources/images/typeImg/Psychic.png");
        ImageIcon bugType = new ImageIcon("src/main/resources/images/typeImg/Bug.png");
        ImageIcon rockType = new ImageIcon("src/main/resources/images/typeImg/Rock.png");
        ImageIcon ghostType = new ImageIcon("src/main/resources/images/typeImg/Ghost.png");
        ImageIcon dragonType = new ImageIcon("src/main/resources/images/typeImg/Dragon.png");
        ImageIcon darkType = new ImageIcon("src/main/resources/images/typeImg/Dark.png");
        ImageIcon steelType = new ImageIcon("src/main/resources/images/typeImg/Steel.png");
        ImageIcon fairyType = new ImageIcon("src/main/resources/images/typeImg/Fairy.png");
        ImageIcon searchImg = new ImageIcon("src/main/resources/images/guiImg/search.png");
        ImageIcon resetImg = new ImageIcon("src/main/resources/images/guiImg/searchreset.png");
        ImageIcon addImg = new ImageIcon("src/main/resources/images/guiImg/addpokemon.png");
        
        JLabel pokemonTitle = new JLabel(pokeDexTitle);
        JLabel pokemonNumLabel = new JLabel("도감 번호 :");
        JLabel pokemonNameLabel = new JLabel("이름 :");
        JLabel pokemonTypeLabel = new JLabel("타입");   
        
        pokemonNum = new JTextField();
        pokemonName = new JTextField();

        JButton searchCondition = new JButton(searchImg);
        JButton resultReset = new JButton(resetImg);
        JButton addPokemon = new JButton(addImg);
        
        typeGroup = new ButtonGroup();
        
        JRadioButtonMenuItem normalTypeRadio = new JRadioButtonMenuItem(normalType);
        JRadioButtonMenuItem fireTypeRadio = new JRadioButtonMenuItem(fireType);
        JRadioButtonMenuItem waterTypeRadio = new JRadioButtonMenuItem(waterType);
        JRadioButtonMenuItem electricTypeRadio = new JRadioButtonMenuItem(electricType);
        JRadioButtonMenuItem grassTypeRadio = new JRadioButtonMenuItem(grassType);
        JRadioButtonMenuItem iceTypeRadio = new JRadioButtonMenuItem(iceType);
        JRadioButtonMenuItem fightingTypeRadio = new JRadioButtonMenuItem(fightingType);
        JRadioButtonMenuItem poisonTypeRadio = new JRadioButtonMenuItem(poisonType);
        JRadioButtonMenuItem groundTypeRadio = new JRadioButtonMenuItem(groundType);
        JRadioButtonMenuItem flyingTypeRadio = new JRadioButtonMenuItem(flyingType);
        JRadioButtonMenuItem psychicTypeRadio = new JRadioButtonMenuItem(psychicType);
        JRadioButtonMenuItem bugTypeRadio = new JRadioButtonMenuItem(bugType);
        JRadioButtonMenuItem rockTypeRadio = new JRadioButtonMenuItem(rockType);
        JRadioButtonMenuItem ghostTypeRadio = new JRadioButtonMenuItem(ghostType);
        JRadioButtonMenuItem dragonTypeRadio = new JRadioButtonMenuItem(dragonType);
        JRadioButtonMenuItem darkTypeRadio = new JRadioButtonMenuItem(darkType);
        JRadioButtonMenuItem steelTypeRadio = new JRadioButtonMenuItem(steelType);
        JRadioButtonMenuItem fairyTypeRadio = new JRadioButtonMenuItem(fairyType);
        noneRadio = new JRadioButton();
        noneRadio.setVisible(false);
        
        /**
         * 라디오 버튼에 텍스트 입혀주는 것으로 찾아보니 getActionCommand라는 
         * 메소드가 있길래, ActionCommand를 set해서 씁니다.
         */
        normalTypeRadio.setActionCommand("Normal");
        fireTypeRadio.setActionCommand("Fire");
        waterTypeRadio.setActionCommand("Water");
        electricTypeRadio.setActionCommand("Electric");
        grassTypeRadio.setActionCommand("Grass");
        iceTypeRadio.setActionCommand("Ice");
        fightingTypeRadio.setActionCommand("Fighting");
        poisonTypeRadio.setActionCommand("Poison");
        groundTypeRadio.setActionCommand("Ground");
        flyingTypeRadio.setActionCommand("Flying");
        psychicTypeRadio.setActionCommand("Psychic");
        bugTypeRadio.setActionCommand("Bug");
        rockTypeRadio.setActionCommand("Rock");
        ghostTypeRadio.setActionCommand("Ghost");
        dragonTypeRadio.setActionCommand("Dragon");
        darkTypeRadio.setActionCommand("Dark");
        steelTypeRadio.setActionCommand("Steel");
        fairyTypeRadio.setActionCommand("Fairy");
        noneRadio.setActionCommand("");
        
        typeGroup.add(normalTypeRadio);
        typeGroup.add(fireTypeRadio);
        typeGroup.add(waterTypeRadio);
        typeGroup.add(electricTypeRadio);
        typeGroup.add(grassTypeRadio);
        typeGroup.add(iceTypeRadio);
        typeGroup.add(fightingTypeRadio);
        typeGroup.add(poisonTypeRadio);
        typeGroup.add(groundTypeRadio);
        typeGroup.add(flyingTypeRadio);
        typeGroup.add(psychicTypeRadio);
        typeGroup.add(bugTypeRadio);
        typeGroup.add(rockTypeRadio);
        typeGroup.add(ghostTypeRadio);
        typeGroup.add(dragonTypeRadio);
        typeGroup.add(darkTypeRadio);
        typeGroup.add(steelTypeRadio);
        typeGroup.add(fairyTypeRadio);
        typeGroup.add(noneRadio);
        
        JPanel searchConditionPanel = new JPanel();
        
        frame.setTitle("포켓몬 도감");
        
        frame.setLayout(null);
        searchConditionPanel.setLayout(null);
        resultPanel.setLayout(null);
        
        pokemonTitle.setBounds(15, 0, 170, 45);
        pokemonNumLabel.setFont(new Font("고딕", Font.BOLD, 20));
        pokemonNumLabel.setBounds(30, 60, 120, 20);
        pokemonNum.setBounds(140, 60, 27, 20);
        pokemonNameLabel.setFont(new Font("고딕", Font.BOLD, 20));
        pokemonNameLabel.setBounds(30, 95, 80, 20);
        pokemonName.setBounds(100, 95, 67, 20);
        pokemonTypeLabel.setFont(new Font("고딕", Font.BOLD, 20));
        pokemonTypeLabel.setBounds(30, 130, 60, 20);
        normalTypeRadio.setBounds(25, 163, 70, 20);
        fireTypeRadio.setBounds(95, 163, 70, 20);
        waterTypeRadio.setBounds(25, 188, 70, 20);
        electricTypeRadio.setBounds(95, 188, 70, 20);
        grassTypeRadio.setBounds(25, 213, 70, 20);
        iceTypeRadio.setBounds(95, 213, 70, 20);
        fightingTypeRadio.setBounds(25, 238, 70, 20);
        poisonTypeRadio.setBounds(95, 238, 70, 20);
        groundTypeRadio.setBounds(25, 263, 70, 20);
        flyingTypeRadio.setBounds(95, 263, 70, 20);
        psychicTypeRadio.setBounds(25, 288, 70, 20);
        bugTypeRadio.setBounds(95, 288, 70, 20);
        rockTypeRadio.setBounds(25, 313, 70, 20);
        ghostTypeRadio.setBounds(95, 313, 70, 20);
        dragonTypeRadio.setBounds(25, 338, 70, 20);
        darkTypeRadio.setBounds(95, 338, 70, 20);
        steelTypeRadio.setBounds(25, 363, 70, 20);
        fairyTypeRadio.setBounds(95, 363, 70, 20);
        searchCondition.setBounds(55, 395, 80, 36);
        searchCondition.setBorderPainted(false);
        searchCondition.setFocusPainted(false);
        searchCondition.setContentAreaFilled(false);
        resultReset.setBounds(39, 450, 122, 36);
        resultReset.setBorderPainted(false);
        resultReset.setFocusPainted(false);
        resultReset.setContentAreaFilled(false);
        addPokemon.setBounds(47, 505, 106, 36);
        addPokemon.setBorderPainted(false);
        addPokemon.setFocusPainted(false);
        addPokemon.setContentAreaFilled(false);
        
        /**
         * 처음 시작할때 포켓몬 리스트를 전부 받아와서 새로 버튼을 그려준다.
         */
        viewport = new JViewport();
        resetResultPanel();
        
        JScrollPane scrollPane = new JScrollPane(viewport);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        searchConditionPanel.setBounds(0, 10, 200, 590);
        scrollPane.setBounds(200, 10, 620, 542);
        
        searchConditionPanel.add(pokemonTitle);
        searchConditionPanel.add(pokemonNumLabel);
        searchConditionPanel.add(pokemonNum);
        searchConditionPanel.add(pokemonNameLabel);
        searchConditionPanel.add(pokemonName);
        searchConditionPanel.add(pokemonTypeLabel);
        searchConditionPanel.add(normalTypeRadio);
        searchConditionPanel.add(fireTypeRadio);
        searchConditionPanel.add(waterTypeRadio);
        searchConditionPanel.add(electricTypeRadio);
        searchConditionPanel.add(grassTypeRadio);
        searchConditionPanel.add(iceTypeRadio);
        searchConditionPanel.add(fightingTypeRadio);
        searchConditionPanel.add(poisonTypeRadio);
        searchConditionPanel.add(groundTypeRadio);
        searchConditionPanel.add(flyingTypeRadio);
        searchConditionPanel.add(psychicTypeRadio);
        searchConditionPanel.add(bugTypeRadio);
        searchConditionPanel.add(rockTypeRadio);
        searchConditionPanel.add(ghostTypeRadio);
        searchConditionPanel.add(dragonTypeRadio);
        searchConditionPanel.add(darkTypeRadio);
        searchConditionPanel.add(steelTypeRadio);
        searchConditionPanel.add(fairyTypeRadio);
        searchConditionPanel.add(searchCondition);
        searchConditionPanel.add(resultReset);
        searchConditionPanel.add(addPokemon);
        
        frame.add(searchConditionPanel);
        frame.add(scrollPane);
        
        frame.pack();
        frame.setSize(845, 600);
        frame.setVisible(true);
        frame.setResizable(false);
        searchCondition.addActionListener(e->
            search()
        );
        
        resultReset.addActionListener(e->
            resetResultPanel()
        );
        
        addPokemon.addActionListener(e-> 
            addPokemon()
        );
    }
    
    /**
     * 검색결과를 리셋하는 메소드로 최초에는 모든 포켓몬의 리스트를 받아오도록 설정.
     */
    public void resetResultPanel() {
        resultPanel.removeAll();
        
        pokemonButtonList.clear();
        
        for(int i  = 0; i < this.pokemonList.size(); i++){
            pokemonButtonList.add(new PokemonButton(this.pokemonList.get(i), 200*(i%3)+10, 200*(i/3)+10, 180, 180));
            resultPanel.add(pokemonButtonList.get(i).getButton());
            pokemonButtonList.get(i).getButton().addActionListener(new PrintInformation());
        }
        pokemonNum.setText("");
        pokemonName.setText("");
        noneRadio.setSelected(true);
        resultPanel.repaint();
        
        viewport.setPreferredSize(new Dimension(815, 10+ (200*(pokemonButtonList.size()/3+1))));
        viewport.setView(resultPanel);
    }
    
    /**
     * 포켓몬을 검색하는 메소드로 조건에 따라 다른 포켓몬을 ArrayList에 저장하여 결과를 출력함.
     */
    public void search() {
        /**
         * <pre>
         *  resultPanel.removeAll();
         * </pre>
         * removeAll은 패널 전체를 비워주는 메소드
         */
        resultPanel.removeAll();
        pokemonButtonList.clear();

        final SearchPokemon search = new SearchPokemon();
        List<Pokemon> searchResult;

        searchResult = search.search(pokemonList,pokemonNum.getText());
        searchResult = search.search(searchResult,pokemonName.getText());
        searchResult = search.search(searchResult,typeGroup.getSelection().getActionCommand());

        for(int i  = 0; i < searchResult.size(); i++){
            pokemonButtonList.add(new PokemonButton(searchResult.get(i), 200*(i%3)+10, 200*(i/3)+10, 180, 180));
            resultPanel.add(pokemonButtonList.get(i).getButton());
            pokemonButtonList.get(i).getButton().addActionListener(new PrintInformation());
        }

        resultPanel.revalidate();
        resultPanel.repaint();
    }
    
    /**
     * 포켓몬 추가하는 버튼을 입격할때 실행되는 메소드로 로그인이 되어있지 않다면 사용불가,
     * 로그인이 되어 있다면 포켓몬 추가 GUI를 불러온다.
     */
    public void addPokemon(){
        if(UserData.getPokemon() == null){
            JOptionPane.showMessageDialog(null,"Guest는 사용할 수 없습니다.");
        } else{
            new PokemonAdder();
        }   
    }
    
    /**
     * 포켓몬 버튼을 클릭하였을때 포켓몬 상세 정보를 표시하기 위하여 PokeDexInformation으로 연결되는
     * 클래스로, 정보를 그대로 가져와 넘긴다.
     */
    class PrintInformation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            new PokeDexInformation((JButton)arg0.getSource());
        }
    }

}
