package design_pattern_project.se02_team07.gui.maingame;

import design_pattern_project.se02_team07.common_function.Pokemon;

/**
 * 원하는 시점의 선택받지 못한 포켓몬의 정보를 저장하는 메멘토 클래스
 * 
 * @author  전찬혁
 * @version 1.0.0
 * @since   2019.06.01 메멘토 클래스의 이름 변경으로 인해 생성
 */
public class PokemonBucket {
    private final Pokemon notPickedPokemon;
    private final int clicked;
    
    /**
     * 메멘토 클래스의 생성자
     * 
     * @param notPickedPokemon 선택되지 않은 포켓몬
     * @param clicked 클릭된 위치
     */
    public PokemonBucket(Pokemon notPickedPokemon, int clicked) {
        this.notPickedPokemon = notPickedPokemon;
        this.clicked = clicked;
    }
    
    /**
     * 메멘토에 저장된 포켓몬을 다시 뽑아내는 메소드
     * 
     * @return 메멘토에 저장된 포켓몬
     */
    public Pokemon getSavedPokemon() {
        return this.notPickedPokemon;
    }
    
    /**
     * 메멘토에 저장된 위치 정보를 다시 뽑아내는 메소드
     * 
     * @return 클릭된 위치
     */
    public int getSavedClicked() {
        return this.clicked;
    }
    
}
