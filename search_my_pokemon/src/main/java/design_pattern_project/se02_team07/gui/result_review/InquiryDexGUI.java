package design_pattern_project.se02_team07.gui.result_review;

import design_pattern_project.se02_team07.common_function.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

/**
 * 이 클래스는 '파트너 조회 기능의 메인 GUI클래스' 입니다.
 *
 * @author 20173116 신휘정
 * @since  2019.05.10 GUI 작성 및 완료 
 *          2019.05.17 이벤트 처리기 추가완료하였고 파트너 분석 클릭시 파이그래프 뜨도록 수정중 
 *          2019.05.19 setLayout때문에 파이그래프가 안떠서 절대 배치로 GUi위치 변경, 하지만 아직 파이그래프가 안뜸
 *          2019.05.21 파이그래프 안뜨는 오류 수정완료! 
 *          2019.05.31 소나큐브 오류 수정
 *          2019.06.01 기능 완성, JFrame extends 수정
 */
public class InquiryDexGUI {
    JPanel upPanel;
    JPanel downPanel;
    
    /**
     * 파트너 조회 기능 GUI 생성자.
     */
    public InquiryDexGUI() {
        JFrame frame = new JFrame();
        JButton mainMenu = new JButton("메인메뉴");
        JRadioButton everyPartner = new JRadioButton("역대 파트너 조회");
        JRadioButton specializedPartner = new JRadioButton("파트너 분석");
        JRadioButton noneRadio = new JRadioButton("");
        mainMenu.setFocusPainted(false);
        everyPartner.setFocusPainted(false);
        specializedPartner.setFocusPainted(false);
        List<Pokemon> userPokemonList = new ArrayList<>();
        final SearchPokemon search = new SearchPokemon();
        
        for(int i = 0 ; i < UserData.getPokemon().size();i++){
            search.startSearch(new SearchWithNum());
            userPokemonList.add(search.search(PokemonList.getPokeList(),UserData.getPokemon().get(i)).get(0));
        }
        
        frame.setLayout(null);
        frame.setPreferredSize(new Dimension(500, 800));
        ButtonGroup g = new ButtonGroup();
        
        noneRadio.setVisible(false);

        everyPartner.setSize(130, 50);
        everyPartner.setLocation(10, 10);

        specializedPartner.setSize(100, 50);
        specializedPartner.setLocation(150, 10);

        mainMenu.setSize(100, 50);
        mainMenu.setLocation(350, 10);
        
        upPanel = new JPanel();
        upPanel.setLayout(null);
        upPanel.setBounds(0,0,500,100);
        
        downPanel = new JPanel();
        downPanel.setLayout(null);
        
        JViewport vp = new JViewport();
        vp.setPreferredSize(new Dimension(500,160*((userPokemonList.size()/3)+2)));
        vp.setView(downPanel);
        
        JScrollPane downScroll = new JScrollPane(vp);
        downScroll.setBounds(0,100,500,700);
        downScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        g.add(everyPartner);
        g.add(specializedPartner);
        g.add(noneRadio);

        upPanel.add(everyPartner);
        upPanel.add(specializedPartner);
        upPanel.add(noneRadio);
        upPanel.add(mainMenu);
        
        frame.add(upPanel);
        frame.add(downScroll);

        everyPartner.addActionListener((ActionEvent e) -> {
            downPanel.removeAll();
            List<PokemonButton> buttonList = new ArrayList<>();
            for(int i = 0 ; i < userPokemonList.size() ; i++){
                buttonList.add(new PokemonButton(userPokemonList.get(i),5+(160*(i%3)),10+(160*(i/3)),150,150));
                downPanel.add(buttonList.get(i).getButton());
            }
            downPanel.repaint();
            downScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        });

        specializedPartner.addActionListener((ActionEvent e) -> {
            downPanel.removeAll();
            int[] typeNum = new int[18];
            for(int i = 0 ; i < 18 ; i++){
                typeNum[i] = 0;
            }
            
            String mainType;
            for(Pokemon pokemon : userPokemonList){
                mainType = pokemon.getMainType();
                switch(mainType){
                    case "Bug":
                        typeNum[0]++;
                        break;
                    case "Dark":
                        typeNum[1]++;
                        break;
                    case "Dragon":
                        typeNum[2]++;
                        break;
                    case "Electric":
                        typeNum[3]++;
                        break;
                    case "Fairy":
                        typeNum[4]++;
                        break;
                    case "Fighting":
                        typeNum[5]++;
                        break;
                    case "Fire":
                        typeNum[6]++;
                        break;
                    case "Flying":
                        typeNum[7]++;
                        break;
                    case "Ghost":
                        typeNum[8]++;
                        break;
                    case "Grass":
                        typeNum[9]++;
                        break;
                    case "Ground":
                        typeNum[10]++;
                        break;
                    case "Ice":
                        typeNum[11]++;
                        break;
                    case "Normal":
                        typeNum[12]++;
                        break;
                    case "Poison":
                        typeNum[13]++;
                        break;
                    case "Psychic":
                        typeNum[14]++;
                        break;
                    case "Rock":
                        typeNum[15]++;
                        break;
                    case "Steel":
                        typeNum[16]++;
                        break;
                    case "Water":
                        typeNum[17]++;
                        break;
                    default :
                        break;
                }
            }
            
            PieGraph com = new PieGraph(typeNum);
            List<ImageIcon> iconList = new ArrayList<>();
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Bug.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Dark.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Dragon.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Electric.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Fairy.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Fighting.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Fire.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Flying.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Ghost.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Grass.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Ground.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Ice.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Normal.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Poison.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Psychic.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Rock.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Steel.png"));
            iconList.add(new ImageIcon("src/main/resources/images/typeImg/Water.png"));
            
            List<JLabel> imageLabel = new ArrayList<>();
            List<JLabel> typeNumList = new ArrayList<>();
            for(ImageIcon icon : iconList){
                imageLabel.add(new JLabel(icon));
            }
            
            for(int i=0;i<imageLabel.size();i++){
                typeNumList.add(new JLabel(": "+typeNum[i]+"마리"));
                imageLabel.get(i).setBounds(100+(200*(i%2)), 320+(30*(i/2)), 50, 24);
                typeNumList.get(i).setBounds(150+(200*(i%2)), 320+(30*(i/2)), 50, 24);
                downPanel.add(imageLabel.get(i));
                downPanel.add(typeNumList.get(i));
            }
            
            com.setBounds(100, 0, 300, 300);
            
            downScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            downPanel.add(com);
            downPanel.repaint();
        });

        mainMenu.addActionListener((ActionEvent e) -> 
            frame.dispose()
        );
        
        frame.setTitle("포켓몬 역대 파트너 조회");
        frame.setSize(515, 800);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
}
