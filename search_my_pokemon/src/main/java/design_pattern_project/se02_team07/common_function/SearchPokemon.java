package design_pattern_project.se02_team07.common_function;

import java.util.List;

/**
 * 상태 패턴을 이용하려고 만들었던 검색 클래스
 * 검색 상태를 가지고 있다.
 *
 * @author  20163371 전찬혁
 * @version 1.0.0
 * @since   2019.05.28 클래스 최초 제작
 *           2019.05.31 소나큐브 오류 수정
 */
public final class SearchPokemon {

    private SearchWith myState;

    /**
     * 생성자로 시작은 항상 도감번호 검색으로 시작한다.
     */
    public SearchPokemon() {
        startSearch(new SearchWithNum());
    }

    /**
     * 찾는 행동을 시작하여 매개변수를 바꿔주는 메소드.
     * 
     * @param newState 새로운 검색 상태
     */
    public void startSearch(final SearchWith newState) {
        myState = newState;
    }

    /**
     * 전체 리스트에서 포켓몬을 조건에 따라 찾는 메소드.
     * 
     * @param searchList 검색을 실행할 리스트
     * @param str 번호, 이름, 타입을 받을 변수
     * @return 현재 상태의 search 함수를 실행해 그 결과를 리턴한다.
     */
    public List<Pokemon> search(final List<Pokemon> searchList, final String str) {
        return myState.search(this, searchList, str);
    }

}
