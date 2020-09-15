package design_pattern_project.se02_team07.gui.maingame;

import design_pattern_project.se02_team07.common_function.*;

/**
 * @author  20163371 전찬혁
 * @version 1.0.0
 * @since   2019.05.12 클래스 최초 제작
 *           2019.05.19 변수명 직관적이게 변경
 *           2019.05.26 교수님 피드백에 맞춰 변경
 *           2019.05.31 소나큐브 오류 수정
 *           2019.06.01 메멘토 클래스 이름 변경으로 인한 메소드 이름 변경
 */
public class NotPickedPokemon {
    /**
     * notPickedPokemon = 비선택 포켓몬
     * clicked = 클릭 위치를 저장하는 변수
     * 
     * @author 전찬혁
     */
    private Pokemon pokemon;
    private int clicked;
    
    /**
     * 포켓몬과 클릭 위치 저장 메서드
     * 
     * @param pokemon 저장할 포켓몬
     * @param clicked 유저가 클릭한 포켓몬 위치 (1: 왼쪽, 2: 오른쪽)
     */
    public void set(Pokemon pokemon, int clicked) {
        this.pokemon = pokemon;
        this.clicked = clicked;
    }

    /**
     * 상태를 저장하고 있던 메멘토 객체를 리턴함.
     * 
     * @return Memento 메멘토 객체 
     */
    public PokemonBucket saveToPokemonBucket() {
        return new PokemonBucket(this.pokemon, this.clicked);
    }

    /**
     * 메멘토 복구 메서드
     * 
     * @param pokemonBucket 메멘토에 저장된 포켓몬 객체의 상태를 복구
     */
    public void restorPokemonBucket(PokemonBucket pokemonBucket) {
        this.pokemon = pokemonBucket.getSavedPokemon();
        this.clicked = pokemonBucket.getSavedClicked();
    }

    public Pokemon getNotPickedPokemon() {
        return pokemon;
    }

    public int getClicked() {
        return clicked;
    }
}
