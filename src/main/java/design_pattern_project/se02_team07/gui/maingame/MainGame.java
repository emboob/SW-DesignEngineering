package design_pattern_project.se02_team07.gui.maingame;

import design_pattern_project.se02_team07.common_function.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author  20163371 전찬혁
 * @version 1.0.0
 * @since   2019.05.12 클래스 최초 제작 2019.05.15 - 버튼 이벤트 하나의 함수로 통합
 *           2019.05.16 게임버튼 클래스의 변경으로 재조정 
 *           2019.05.19 변수명 직관적이게 변경 / PickedPokemon Stack에서 ArrayList로 변경/ 위키 연결 추가
 *           2019.05.26 메멘토 패턴 교수님의 피드백에 맞게 리스트로 변경       
 *           2019.05.31 소나큐브 오류 수정
 *           2019.06.01 GUI JFrame 상속에서 따로 변수로 변경
 *           2019.06.02 버튼 클릭시 포커스 해제하였음
 */
public class MainGame {
    /**
     * frame = 현재 JFrame
     * leftButton = 왼쪽 포켓몬 선택 버튼
     * rightButton = 오른쪽 포켓몬 선택 버튼
     * savedState = 메멘토들을 저장하는 리스트
     * notPickedPokemon = 선택되지 못한 포켓몬을 일시적으로 저장하는 객체
     * PickedPokemon = 결과 화면으로 넘어갈 선택된 포켓몬 리스트
     * backButton = 돌아가기 버튼
     * leftPokeInfo = 왼쪽 포켓몬 위키 사이트 연결 버튼
     * rightPokeInfo = 오른쪽 포켓몬 위키 사이트 연결 버튼
     * round = 현재 라운드
     * round_str = 현재 라운드와 남은 포켓몬 수를 띄우기 위한 문자열
     * pokemonList = 게임에 사용되는 포켓몬 리스트
     * logger = 로거
     * 
     * @author 전찬혁
     */
    JFrame frame;
    PokemonButton leftButton;
    PokemonButton rightButton;
    ArrayList<PokemonBucket> savedState  = new ArrayList<>();
    NotPickedPokemon notPickedPokemon = new NotPickedPokemon();
    ArrayList<Pokemon> pickedPokemon  = new ArrayList<>();
    JButton backButton;
    JButton leftPokeInfo;
    JButton rightPokeInfo;
    JButton backToMain;
    int round;
    JLabel roundStr;
    List <Pokemon> pokemonList;
    public static final Logger logger = Logger.getLogger(MainGame.class.getName());
    String roundStrMiddle = "라운드 / 남은 포켓몬 : " ;

    /**
     * 메인 게임의 생성자로 ManageGame에서 설정한 포켓몬 리스트를 받아와 게임을 실행함
     * 
     * @param pokemonlist 정렬된 포켓몬 목록
     */
    MainGame(List<Pokemon> pokemonlist) {
        this.pokemonList = new ArrayList<>();
        frame = new JFrame("나의 파트너를 찾아서! ");
        
        setPokemonList(pokemonlist);
        
        notPickedPokemon.set(null,0);
        notPickedPokemon.saveToPokemonBucket();

        round = 0;

        frame.setLayout(new BorderLayout());
        JPanel game = new JPanel(null);
        JPanel info = new JPanel();
        roundStr = new JLabel((round+1) + roundStrMiddle + (pokemonList.size() - 2 - round));

        leftButton = new PokemonButton(pokemonList.get(round), 150, 50, 200, 200);
        rightButton = new PokemonButton(pokemonList.get(round + 1), 450, 50, 200, 200);

        leftPokeInfo = new JButton("도감 보기");
        leftPokeInfo.setFocusPainted(false);
        leftPokeInfo.setBounds(200, 300, 100, 50);
        rightPokeInfo = new JButton("도감 보기");
        rightPokeInfo.setFocusPainted(false);
        rightPokeInfo.setBounds(500, 300, 100, 50);
        
        backButton = new JButton("돌아가기");
        backButton.setFocusPainted(false);
        backButton.setBounds(350, 400, 100, 50);
        
        backToMain = new JButton("메인화면");
        backToMain.setFocusPainted(false);
        backToMain.setBounds(680, 0, 100, 50);

        leftPokeInfo.addActionListener(new InfoButtonAction());
        rightPokeInfo.addActionListener(new InfoButtonAction());
        leftButton.getButton().addActionListener(new SelectButtonAction());
        rightButton.getButton().addActionListener(new SelectButtonAction());
        backButton.addActionListener(new BackButtonAction());
        backToMain.addActionListener(new BackButtonAction());
        
        game.add(leftButton.getButton());
        game.add(rightButton.getButton());
        game.add(leftPokeInfo);
        game.add(rightPokeInfo);
        game.add(backButton);
        game.add(backToMain);
        info.add(roundStr);
        frame.add(info, BorderLayout.NORTH);
        frame.add(game, BorderLayout.CENTER);

        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
    /**
     * ManageGame에서 받아온 포켓몬 리스트를 현재 게임에 세팅하는 메소드.
     * 
     * @param pokemonList ManageGame에서 받아온 포켓몬 리스트
     */
    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }
    
    /**
     * 버튼 이벤트 클래스fh 좌,우 버튼을 클릭시 화면이 넘어가는 구조로 되어있음.
     */
    class SelectButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == leftButton.getButton()) {
                pickedPokemon.add(leftButton.getPokemonInButton());
                if (round == pokemonList.size() - 2) {
                    new ResultGame(pickedPokemon);
                    frame.dispose();
                }
                else{
                    notPickedPokemon.set(rightButton.setButton(pokemonList.get(round + 2)),1);
                    savedState.add(notPickedPokemon.saveToPokemonBucket());
                    rightButton.getButton().repaint();
                    round++;
                    roundStr.setText((round+1) + roundStrMiddle + (pokemonList.size() - 2 - round));
                }
            } else if (e.getSource() == rightButton.getButton()) {
                pickedPokemon.add(rightButton.getPokemonInButton());
                if (round == pokemonList.size() - 2) {
                    new ResultGame(pickedPokemon);
                    frame.dispose();
                }
                else{
                    notPickedPokemon.set(leftButton.setButton(pokemonList.get(round + 2)),2);
                    savedState.add(notPickedPokemon.saveToPokemonBucket());
                    /**
                     * <pre>
                     *      button.repaint(); = 버튼에 새로 그림을 그려준다.
                     * </pre>
                     */
                    leftButton.getButton().repaint();
                    round++;
                    roundStr.setText((round+1) + roundStrMiddle + (pokemonList.size() - 2 - round));
                }
            }
        }
    }
    
    /**
     * 메멘토 기능을 이용하기 위한 다시 되돌리기 버튼 이벤트.
     */
    class BackButtonAction implements ActionListener{
   
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == backButton){
                if (round == 0) {
                    JOptionPane.showMessageDialog(null, "아직 선택된 포켓몬이 없습니다.");
                    roundStr.setText(round + roundStrMiddle + (pokemonList.size() - 2 - round));
                    
                    return;
                }
                notPickedPokemon.restorPokemonBucket(savedState.get(round-1));
                savedState.remove(round-1);
                if (notPickedPokemon.getClicked() == 1) {
                    pickedPokemon.remove(pickedPokemon.size() - 1);
                    rightButton.setButton(notPickedPokemon.getNotPickedPokemon());
                    rightButton.getButton().repaint();
                    round--;
                    roundStr.setText(round + roundStrMiddle + (pokemonList.size() - 2 - round));
                } else if (notPickedPokemon.getClicked() == 2) {
                    pickedPokemon.remove(pickedPokemon.size() - 1);
                    leftButton.setButton(notPickedPokemon.getNotPickedPokemon());
                    leftButton.getButton().repaint();
                    round--;
                    roundStr.setText(round + roundStrMiddle + (pokemonList.size() - 2 - round));
                }
            } else if(e.getSource() == backToMain){
                frame.dispose();
            }
        } 
    }
    
    /**
     * 포켓몬 위키와 연결되는 정보 버튼 이벤트.
     */
    class InfoButtonAction implements ActionListener{
   
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == leftPokeInfo){
                try {
                    Desktop.getDesktop().browse(new URI("https://pokemon.fandom.com/ko/wiki/"+leftButton.getPokemonInButton().getPokeName()+"_(포켓몬)"));
                } catch (URISyntaxException ex) {
                    logger.warning("문제 발생1");
                } catch (IOException ex) {
                    logger.warning("문제 발생2");
                }
            } else if(e.getSource() == rightPokeInfo){
                try {
                    Desktop.getDesktop().browse(new URI("https://pokemon.fandom.com/ko/wiki/"+rightButton.getPokemonInButton().getPokeName()+"_(포켓몬)"));
                } catch (URISyntaxException ex) {
                    logger.warning("문제 발생3");
                } catch (IOException ex) {
                    logger.warning("문제 발생4");
                }
            } 
        }
    }
    
}
