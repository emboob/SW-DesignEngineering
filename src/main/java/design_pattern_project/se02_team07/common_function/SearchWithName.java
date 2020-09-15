package design_pattern_project.se02_team07.common_function;

import java.util.ArrayList;
import java.util.List;

/**
 * 상태 패턴 사용의 두번째 단계
 * 이름으로 포켓몬을 검색하는 클래스
 *
 * @author  20163371 전찬혁
 * @version 1.0.0
 * @since   2019.05.28 클래스 최초 제작
 */
public class SearchWithName implements SearchWith {
    
    /**
     * 이름으로 검색을 한후 상태를 이름 검색 완료 상태로 만들어 타입 검색이 오도록 한다.
     * 
     * @param searchPokemon 검색 객체 이 변수에 상태를 바꿔가며 쓴다.
     * @param searchList 검색을 실행할 리스트
     * @param str 검색어
     * @return temp 검색한 결과가 담긴 포켓몬 리스트
     */
    @Override
    public List<Pokemon> search(final SearchPokemon searchPokemon,
            List<Pokemon> searchList, String str) {
        if("".equals(str)){
            searchPokemon.startSearch(new SearchWithType());
            return searchList;
        }
        else{
            List<Pokemon> temp = new ArrayList<>();
            for (Pokemon pokemon : searchList) {
                if (pokemon.getPokeName().contains(str)) {
                    temp.add(pokemon);
                }
            }
            searchPokemon.startSearch(new SearchWithType());
            return temp;
        }
    }
    
}
