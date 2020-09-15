package design_pattern_project.se02_team07.gui.maingame;

import design_pattern_project.se02_team07.common_function.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 메인 게임의 결과 출력 화면
 * 
 * @author 20163371 전찬혁
 * @version 1.0.0
 * @since   2019.05.16 클래스 최초 제작
 *           2019.05.19 결과 리스트 하단부 배치 / 리스트 스크롤 추가 / 리스트 중복 제거
 *           2019.05.20 리스트 중복 제거 방식 변경
 *           2019.05.22 리스트 따로 창 띄워서 출력
 *           2019.05.27 캡쳐 기능, 게임 결과 db저장 기능, 메인화면으로 복귀 기능 추가
 *           2019.05.31 소나큐브 오류 수정
 *           2019.06.01 JFrame extends 수정
 *           2019.06.02 게스트 이용시 일어나는 오류 수정
 *           2019.06.03 버튼 클릭시 포커스 해제하였고, 결과 레이블 폰트 변경
 */
public class ResultGame {
    private List<PokemonButton> pickedButtonList;
    private List<Pokemon> pickedPokemon;
    private PokemonButton resultPokemon;
    private JPanel titlePanel;
    private JPanel resultPanel;
    private JLabel nameTitleLabel = new JLabel("결과");
    private JButton loadList;
    private JButton exitGame;
    private JButton reGame;
    private CaptureButton captureButton;
    private JFrame frame = new JFrame("나의 파트너");
    
    /**
     * 게임 결과 화면 생성자.
     * 
     * @param selectedPokemonList 내가 골랐던 포켓몬 리스트들을 나중에 사용함.
     */
    ResultGame(List<Pokemon> selectedPokemonList) {
        pickedPokemon = new ArrayList<>();
        for (int i = 0; i < selectedPokemonList.size(); i++) {
            if (!pickedPokemon.contains(selectedPokemonList.get(i))) {
                pickedPokemon.add(selectedPokemonList.get(i));
            }
        }
        this.pickedButtonList = new ArrayList<>();
        this.resultPokemon = new PokemonButton(pickedPokemon.get(pickedPokemon.size() - 1), 275, 50, 250, 250);

        for (int i = 0; i < pickedPokemon.size(); i++) {
            this.pickedButtonList.add(new PokemonButton(this.pickedPokemon.get(i), 200*(i%4)+10, 200*(i/4)+10, 180, 180));
        }

        frame.setLayout(new BorderLayout());
        
        nameTitleLabel.setFont(new Font("궁서", Font.BOLD, 20));
        
        titlePanel = new JPanel();
        titlePanel.add(nameTitleLabel);

        resultPanel = new JPanel();
        resultPanel.setLayout(null);
        resultPanel.add(resultPokemon.getButton());

        captureButton = new CaptureButton(frame);
        resultPanel.add(captureButton.button);
        
        exitGame = new JButton("게임완료");
        reGame = new JButton("다시하기");
        exitGame.setFocusPainted(false);
        reGame.setFocusPainted(false);
        exitGame.setBounds(350, 400, 100, 50);
        reGame.setBounds(500, 400, 100, 50);
        exitGame.addActionListener(new ButtonAction());
        reGame.addActionListener(new ButtonAction());
        
        resultPanel.add(exitGame);
        resultPanel.add(reGame);

        loadList = new JButton("선택한 포켓몬 보기 →");
        loadList.setFocusPainted(false);
        loadList.setBounds(590, 490, 200, 50);
        loadList.addActionListener(new ButtonAction());
        resultPanel.add(loadList);
        
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(resultPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setResizable(false);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(UserData.getName()!=null){
                    List<String> temp = UserData.getPokemon();
                    temp.add(resultPokemon.getPokemonInButton().getPokeNum());
                    UserData.setPokemon(temp);
                    UserData.updateUserData();
                }
            }
        });
    }
    
    /**
     * ResultGame의 내부 클래스로 결과 저장 버튼 이벤트를 담당함.
     */
    class ButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loadList) {
                new PickedPokemonFrame();
            }else if(e.getSource() == exitGame){
                if(UserData.getName()==null){
                    JOptionPane.showMessageDialog(null, "로그인 하지 않으면 결과가 저장되지 않습니다.");
                    frame.dispose();
                }else{
                    List<String> temp = UserData.getPokemon();
                    temp.add(resultPokemon.getPokemonInButton().getPokeNum());
                    UserData.setPokemon(temp);
                    UserData.updateUserData();
                    frame.dispose();
                }
            } else if(e.getSource() == reGame){
                new ManageGame();
                frame.dispose();
            }
        }
    }

    /**
     * ResultGame의 내부 클래스로 선택했던 포켓몬 버튼 이벤트를 담당함.
     */
    class PickedPokemonFrame {
        private JFrame subFrame = new JFrame("다음에 만나요!");
        public PickedPokemonFrame(){
            subFrame.setLayout(new BorderLayout());
            
            JLabel listFrameTitleLabel = new JLabel("내가 선택한 포켓몬들");
            JPanel listFrameTitlePanel = new JPanel();
            listFrameTitlePanel.add(listFrameTitleLabel);
            
            JPanel pickedPokemonList = new JPanel();
            pickedPokemonList.setLayout(null);
            for (int i = 0; i < pickedPokemon.size(); i++) {
                pickedPokemonList.add(pickedButtonList.get(i).getButton());
            }

            JViewport vp = new JViewport();
            vp.setPreferredSize(new Dimension(835,10+ (200*(pickedPokemon.size()/4+1))));
            vp.setView(pickedPokemonList);

            JScrollPane scroll = new JScrollPane(vp);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            subFrame.add(listFrameTitlePanel,BorderLayout.NORTH);
            subFrame.add(scroll,BorderLayout.CENTER);
            
            subFrame.pack();
            subFrame.setSize(835, 600);
            subFrame.setVisible(true);
            subFrame.setResizable(false);
        }
    }

}
